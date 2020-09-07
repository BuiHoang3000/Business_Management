package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categories;


public class CategoryService {
	public static List<Categories> queryCategories(Connection con) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_CATEGORIES]";
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
        List<Categories> list = new ArrayList<Categories>();
        while (rs.next()) {
        	Categories ca = new Categories();
            ca.setCa_ID(rs.getString("CA_ID"));
            ca.setCa_Name(rs.getString("CA_Name"));
            ca.setCa_Status(rs.getString("CA_Status"));
            list.add(ca);
        }
        return list;
    }
 
    public static Categories findCategories(Connection conn, String code) throws SQLException {
        String sql = "EXEC [dbo].[FIND_CATEGORIES_BY_ID] @ca_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Categories ca = new Categories();
            ca.setCa_ID(rs.getString("CA_ID"));
            ca.setCa_Name(rs.getString("CA_Name"));
            ca.setCa_Status(rs.getString("CA_Status"));
            return ca;
        }
        return null;
    }
 
    public static void updateCategories(Connection conn, Categories ca) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_CATEGORIES] @ca_ID = ?, @ca_Name = ?, @ca_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ca.getCa_ID());
        pstm.setString(2, ca.getCa_Name());
        pstm.setString(3, ca.getCa_Status());
        
        pstm.executeUpdate();
    }
 
    public static void insertCategories(Connection conn, Categories ca) throws SQLException {
        String sql = "EXEC [dbo].[ADD_CATEGORY] @ca_Name = ?, @ca_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ca.getCa_Name());
        pstm.setString(2, ca.getCa_Status());
 
        pstm.executeUpdate();
    }
 
    public static void deleteCategories(Connection conn, String id) throws SQLException {
        String sql = "EXEC [dbo].[DELETE_CATEGORY_BY_ID] @ca_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
}
