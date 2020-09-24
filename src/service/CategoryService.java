package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categories;


public class CategoryService {
	public static List<Categories> queryCategories(String search, int from, int to) throws SQLException {
        String sql = "EXEC SHOW_CATEGORIES @search = ?, @from = ?, @to = ?";
        
        Connection con = null;
        List<Categories> list = new ArrayList<Categories>();
        try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, search);
			pstm.setString(2, from+"");
			pstm.setString(3, to+"");
			 
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) {
	        	Categories ca = new Categories();
	            ca.setCa_ID(rs.getString("CA_ID"));
	            ca.setCa_Name(rs.getString("CA_Name"));
	            ca.setCa_Status(rs.getString("CA_Status"));
	            list.add(ca);
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
	
	public static int countCategory() throws SQLException {
		String sql = "EXEC COUNT_CATEGORY";
		
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
 
    public static Categories findCategories(String code) throws SQLException {
        String sql = "EXEC FIND_CATEGORIES_BY_ID @ca_ID = ?";
        
        Connection conn = null;
        Categories ca = new Categories();
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            ca.setCa_ID(rs.getString("CA_ID"));
	            ca.setCa_Name(rs.getString("CA_Name"));
	            ca.setCa_Status(rs.getString("CA_Status"));
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
        return ca;
    }
 
    public static void updateCategories(Categories ca) throws SQLException {
        String sql = "EXEC UPDATE_CATEGORIES @ca_ID = ?, @ca_Name = ?";
 
        Connection conn = null;
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setString(1, ca.getCa_ID());
	        pstm.setString(2, ca.getCa_Name());
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void insertCategories(Categories ca) throws SQLException {
        String sql = "EXEC ADD_CATEGORY @ca_Name = ?, @ca_Status = ?";
 
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setString(1, ca.getCa_Name());
	        pstm.setString(2, ca.getCa_Status());
	 
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void deleteCategories(String id) throws SQLException {
        String sql = "EXEC UPDATE_CA_MUL_STATUS @StringID = ?";
        
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
