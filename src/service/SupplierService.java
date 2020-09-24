package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Supplier;

public class SupplierService {
	public static List<Supplier> querySupplier(String srearch, int from, int to) throws SQLException {
        String sql = "EXEC SHOW_SUPPLIERS @search = ?, @from = ?, @to= ?";
        
        List<Supplier> list = new ArrayList<Supplier>();
        Connection con = null;
        try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, srearch);
			pstm.setString(2, from+"");
			pstm.setString(3, to+"");
			 
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) {
	        	Supplier su = new Supplier();
	            su.setSu_ID(rs.getString("SU_ID"));
	            su.setSu_Name(rs.getString("SU_Name"));
	            su.setSu_PhoneNumber(rs.getString("SU_PhoneNumber"));
	            su.setSu_Address(rs.getString("SU_Address"));
	            su.setSu_Status(rs.getString("SU_Status"));
	            list.add(su);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
        return list;
    }
	
	public static int countSupplier() throws SQLException {
		String sql = "EXEC COUNT_SUPPLIER";
		
		Connection con = null;
		int count = 0;
		try {
			con = ConnectionSQL.getSQLServerConnection();
			CallableStatement st = con.prepareCall(sql);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt("SL");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return count;
	}
 
    public static Supplier findSupplier(String code) throws SQLException {
        String sql = "EXEC FIND_SUPPLIER_BY_ID @su_ID = ?";
 
        Connection conn = null;
        Supplier su = new Supplier();
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            su.setSu_ID(rs.getString("SU_ID"));
	            su.setSu_Name(rs.getString("SU_Name"));
	            su.setSu_PhoneNumber(rs.getString("SU_PhoneNumber"));
	            su.setSu_Address(rs.getString("SU_Address"));
	            su.setSu_Status(rs.getString("SU_Status"));
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
        return su;
    }
 
    public static void updateSupplier(Supplier su) throws SQLException {
        String sql = "EXEC UPDATE_SUPPLIERS @su_ID = ?, @su_Name = ?, @su_PhoneNumber = ?, @su_Address = ?, @su_Status = ?";
 
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setString(1, su.getSu_ID());
	        pstm.setString(2, su.getSu_Name());
	        pstm.setString(3, su.getSu_PhoneNumber());
	        pstm.setString(4, su.getSu_Address());
	        pstm.setString(5, su.getSu_Status());
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void insertSupplier(Supplier su) throws SQLException {
        String sql = "EXEC ADD_SUPPLIER @su_Name = ?, @su_PhoneNumber ?, @su_Address ?, @su_Status ?";
 
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setString(1, su.getSu_Name());
	        pstm.setString(2, su.getSu_PhoneNumber());
	        pstm.setString(3, su.getSu_Address());
	        pstm.setString(4, su.getSu_Status());
	 
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void deleteSupplier(String id) throws SQLException {
        String sql = "EXEC DELETE_SUPPLIER_BY_ID @su_ID = ?";
        
        Connection con = null;
        
        try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			 
	        pstm.setString(1, id);
	 
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
    }
}
