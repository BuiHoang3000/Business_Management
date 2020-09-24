package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductDetail;

public class ProductService {
	public static List<Product> queryProduct(Connection con, int fromPr, int toPr) throws SQLException {
        //DBHELPER
		String sql = "SHOW_PRO_LM @from = ?, @to = ?";
        try {
			con = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {}
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, fromPr+"");
        pstm.setString(2, toPr+"");
 
        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setPr_ID(rs.getString("PR_ID"));
            product.setPr_CA_ID(rs.getString("CA_Name"));
            product.setPr_Name(rs.getString("PR_Name"));
            product.setPr_Price(Float.parseFloat(rs.getString("PR_Price")));
            product.setPr_Quantify(Integer.parseInt(rs.getString("PR_Quantify")));
            product.setPr_SU_ID(rs.getString("SU_Name"));
            product.setPr_Status(rs.getString("PR_Status"));
            list.add(product);
            
        }
        con.close();
        return list;
    }
	
	public static List<Product> queryAllProduct(Connection con) throws SQLException {
        String sql = "EXEC SHOW_ALL_PRODUCT";
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
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setPr_ID(rs.getString("PR_ID"));
            product.setPr_CA_ID(rs.getString("CA_Name"));
            product.setPr_Name(rs.getString("PR_Name"));
            product.setPr_Price(Float.parseFloat(rs.getString("PR_Price")));
            product.setPr_Quantify(Integer.parseInt(rs.getString("PR_Quantify")));
            product.setPr_SU_ID(rs.getString("SU_Name"));
            product.setPr_Status(rs.getString("PR_Status"));
            list.add(product);
            
        }
        return list;
    }
 
    public static List<Product> findProduct(Connection conn, String code, int fromPr, int toPr) throws SQLException {

    	String sql;
    	PreparedStatement pstm = null;
    	try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sql = "EXEC FIND_PRODUCT_ALL @StringFind = ?, @from = ?, @to = ?";
		pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
        pstm.setString(2, (fromPr+""));
        pstm.setString(3, (toPr+""));

        ResultSet rs = pstm.executeQuery();
        List<Product> listPr = new ArrayList<>();
        while (rs.next()) {
        	Product product = new Product();
            product.setPr_ID(rs.getString("PR_ID"));
            product.setPr_CA_ID(rs.getString("CA_Name"));
            product.setPr_Name(rs.getString("PR_Name"));
            product.setPr_Price(Float.parseFloat(rs.getString("PR_Price")));
            product.setPr_Quantify(Integer.parseInt(rs.getString("PR_Quantify")));
            product.setPr_SU_ID(rs.getString("SU_Name"));
            product.setPr_Status(rs.getString("PR_Status"));
            listPr.add(product);
        }
        return listPr;
    }
    
    public static List<Product> findProductUpdate(Connection conn, String code) throws SQLException {

    	String sql;
    	PreparedStatement pstm = null;
    	try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Integer.parseInt(code);
        sql = "EXEC FIND_PRODUCT_ALL_BY_ID @IntFind = ?";
        pstm = conn.prepareStatement(sql);
	    pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();
        List<Product> listPr = new ArrayList<>();
        while (rs.next()) {
        	Product product = new Product();
            product.setPr_ID(rs.getString("PR_ID"));
            product.setPr_CA_ID(rs.getString("CA_Name"));
            product.setPr_Name(rs.getString("PR_Name"));
            product.setPr_Price(Float.parseFloat(rs.getString("PR_Price")));
            product.setPr_Quantify(Integer.parseInt(rs.getString("PR_Quantify")));
            product.setPr_SU_ID(rs.getString("SU_Name"));
            product.setPr_Status(rs.getString("PR_Status"));
            listPr.add(product);
        }
        return listPr;
    }
    
    public static List<ProductDetail> findProductDetail(Connection conn, String code) throws SQLException {

    	String sql;
    	PreparedStatement pstm = null;
    	try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Integer.parseInt(code);
        sql = "EXEC FIND_PRODUCT_DETAIL_ALL_BY_ID @IntFind = ?";
        pstm = conn.prepareStatement(sql);
	    pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();
        List<ProductDetail> listPr = new ArrayList<>();
        while (rs.next()) {
        	ProductDetail product = new ProductDetail();
            product.setPr_ID(rs.getString("PR_ID"));
            product.setPr_CA_ID(rs.getString("CA_Name"));
            product.setPr_Name(rs.getString("PR_Name"));
            product.setPr_Price(Float.parseFloat(rs.getString("PR_Price")));
            product.setPr_Quantify(Integer.parseInt(rs.getString("PR_Quantify")));
            product.setPr_SU_ID(rs.getString("SU_Name"));
            product.setPr_PD_Image(rs.getString("PD_Image"));
            product.setPr_Status(rs.getString("PR_Status"));
            listPr.add(product);
        }
        return listPr;
    }
 
    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "{CALL UPDATE_PRODUCT(?,?,?,?,?,?,?)}";
    	try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getPr_ID());
        pstm.setString(2, product.getPr_CA_ID());
        pstm.setString(3, product.getPr_Name());
        pstm.setFloat(4, product.getPr_Price());
        pstm.setInt(5, product.getPr_Quantify());
        pstm.setString(6, product.getPr_SU_ID());
        pstm.setString(7, product.getPr_Status());
        
        pstm.executeUpdate();
    }
 
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "{CALL ADD_PRODUCT(?,?,?,?,?,?)}";
        try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getPr_CA_ID());
        pstm.setString(2, product.getPr_Name());
        pstm.setFloat(3, product.getPr_Price());
        pstm.setInt(4, product.getPr_Quantify());
        pstm.setString(5, product.getPr_SU_ID());
        pstm.setString(6, product.getPr_Status());
        pstm.executeUpdate();
    }
 
    public static void deleteProduct(Connection conn, String id) throws SQLException {
        String sql = "{CALL UPDATE_MUL_STATUS(?)}";
        try {
			conn = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
    
    public static int countProduct(Connection con) throws SQLException {
        String sql = "EXEC COUNT_PRODUCT";
        try {
			con = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int count = 0;
        CallableStatement st = con.prepareCall(sql);
        ResultSet rs = st.executeQuery();
        if(rs.next())
        	count = rs.getInt("SL");
        return count;
    }
    
    public static int countProductSearch(Connection con, String code) throws SQLException {
        String sql = "EXEC COUNT_PRODUCT_SEARCH @StringFind = N'"+code+"'";
        try {
			con = ConnectionSQL.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int count = 0;
        CallableStatement st = con.prepareCall(sql);
        ResultSet rs = st.executeQuery();
        if(rs.next())
        	count = rs.getInt("SL");
        return count;
    }
}
