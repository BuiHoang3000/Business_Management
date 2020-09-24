package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


public class CutomerService {
	public static int countCustomer(Connection con) throws SQLException {
		String sql = "{CALL COUNT_CUSTOMER}";
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
			ConnectionSQL.closeConnect(con);
		}
		return count;
	}
	
	public static List<Customer> getCustomerLM(Connection con, int from, int to) throws SQLException{
		String sql = "EXEC SHOW_CUS_LM @from = ?, @to = ?";
		List<Customer> ct = new ArrayList<Customer>();
		try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, from+"");
			pstm.setString(2, to+"");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Customer cus = new Customer();
				cus.setPs_ID(rs.getString("CT_ID"));
				cus.setPs_Name(rs.getString("CT_Name"));
				cus.setPs_Email(rs.getString("CT_Email"));
				cus.setPs_Address(rs.getString("CT_Address"));
				cus.setPs_PhoneNumber(rs.getString("CT_PhoneNumber"));
				cus.setCT_Rank(rs.getString("CT_Rank"));
				cus.setPs_Status(rs.getString("CT_Status"));
				ct.add(cus);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionSQL.closeConnect(con);
		}
		return ct;
	}
 
    public static Customer findCustomer(String code) throws SQLException {
        String sql = "EXEC [dbo].[FIND_CUSTOMER_BY_ID] @ct_ID = ?";
        Connection conn = null;
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	        	Customer cus = new Customer();
	            cus.setPs_ID(rs.getString("CT_ID"));
	            cus.setPs_Name(rs.getString("CT_Name"));
	            cus.setPs_Email(rs.getString("CT_Email"));
	            cus.setPs_Password(rs.getString("CT_Password"));
	            cus.setPs_Address(rs.getString("CT_Address"));
	            cus.setPs_PhoneNumber(rs.getString("CT_PhoneNumber"));
	            cus.setCT_Rank(rs.getString("CT_Rank"));
	            cus.setPs_Status(rs.getString("CT_Status"));
	            return cus;
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
        
        return null;
    }
 
    public static void updateCustomer(Customer cus) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_CUSTOMER] @ct_ID = ?, @ct_Name = ?, @ct_Email = ?, @ct_Password = ?, @ct_Address = ?, @ct_PhoneNumber = ?, @ct_Rank = ?, @ct_Status = ?";
        Connection conn = null;
		try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setString(1, cus.getPs_ID());
	        pstm.setString(2, cus.getPs_Name());
	        pstm.setString(3, cus.getPs_Email());
	        pstm.setString(4, cus.getPs_Password());
	        pstm.setString(5, cus.getPs_Address());
	        pstm.setString(6, cus.getPs_PhoneNumber());
	        pstm.setString(7, cus.getCT_Rank().equals("1") ? "Đồng" : (cus.getCT_Rank().equals("2") ? "Bạc" : "Vàng"));
	        pstm.setString(8, cus.getPs_Status());
	        System.out.println(cus.getCT_Rank());
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
        
    }
 
    public static void insertCustomer(Connection conn, Customer cus) throws SQLException {
        String sql = "EXEC [dbo].[ADD_CUSTOMER] @ct_Name =?, @ct_Email =?, @ct_Password =?, @ct_Address =?, @ct_PhoneNumber =?, @ct_Rank =?, @ct_Status = ?";
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setString(1, cus.getPs_Name());
	        pstm.setString(2, cus.getPs_Email());
	        pstm.setString(3, cus.getPs_Password());
	        pstm.setString(4, cus.getPs_Address());
	        pstm.setString(5, cus.getPs_PhoneNumber());
	        pstm.setString(6, cus.getCT_Rank() == "1" ? "Đồng" : (cus.getCT_Rank() == "2" ? "Bạc" : "Vàng"));
	        pstm.setString(7, cus.getPs_Status());
	 
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void deleteCustomer(String id) throws SQLException {
        String sql = "EXEC CUSTOMER_MUL_STATUS @StringID = ?";
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, id);
	 
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
}
