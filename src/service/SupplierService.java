package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Supplier;

public class SupplierService {
	public static List<Supplier> querySupplier(Connection con) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_SUPPLIERS]";
        try {
			con = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement pstm = con.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Supplier> list = new ArrayList<Supplier>();
        while (rs.next()) {
        	Supplier su = new Supplier();
            su.setSu_ID(rs.getString("SU_ID"));
            su.setSu_Name(rs.getString("SU_Name"));
            su.setSu_PhoneNumber(rs.getString("SU_PhoneNumber"));
            su.setSu_Address(rs.getString("SU_Address"));
            su.setSu_Status(rs.getString("SU_Status"));
            list.add(su);
        }
        return list;
    }
 
    public static Supplier findSupplier(Connection conn, String code) throws SQLException {
        String sql = "EXEC [dbo].[FIND_SUPPLIER_BY_ID] @su_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Supplier su = new Supplier();
            su.setSu_ID(rs.getString("SU_ID"));
            su.setSu_Name(rs.getString("SU_Name"));
            su.setSu_PhoneNumber(rs.getString("SU_PhoneNumber"));
            su.setSu_Address(rs.getString("SU_Address"));
            su.setSu_Status(rs.getString("SU_Status"));
            return su;
        }
        return null;
    }
 
    public static void updateSupplier(Connection conn, Supplier su) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_SUPPLIERS] @su_ID = ?, @su_Name = ?, @su_PhoneNumber = ?, @su_Address = ?, @su_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, su.getSu_ID());
        pstm.setString(2, su.getSu_Name());
        pstm.setString(3, su.getSu_PhoneNumber());
        pstm.setString(4, su.getSu_Address());
        pstm.setString(5, su.getSu_Status());
        
        pstm.executeUpdate();
    }
 
    public static void insertSupplier(Connection conn, Supplier su) throws SQLException {
        String sql = "EXEC [dbo].[ADD_SUPPLIER] @su_Name = ?, @su_PhoneNumber ?, @su_Address ?, @su_Status ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, su.getSu_Name());
        pstm.setString(2, su.getSu_PhoneNumber());
        pstm.setString(3, su.getSu_Address());
        pstm.setString(4, su.getSu_Status());
 
        pstm.executeUpdate();
    }
 
    public static void deleteSupplier(Connection conn, String id) throws SQLException {
        String sql = "EXEC [dbo].[DELETE_SUPPLIER_BY_ID] @su_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
}
