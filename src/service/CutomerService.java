package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


public class CutomerService {
	public static List<Customer> queryCustomer(Connection con) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_CUSTOMERS]";
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
        List<Customer> listCus = new ArrayList<Customer>();
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
            listCus.add(cus);
        }
        return listCus;
    }
 
    public static Customer findCustomer(Connection conn, String code) throws SQLException {
        String sql = "EXEC [dbo].[FIND_CUSTOMER_BY_ID] @ct_ID = ?";
 
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
        return null;
    }
 
    public static void updateCustomer(Connection conn, Customer cus) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_CUSTOMER] @ct_ID = ?, @ct_Name = ?, @ct_Email = ?, @ct_Password = ?, @ct_Address = ?, @ct_PhoneNumber = ?, @ct_Rank = ?, @ct_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, cus.getPs_ID());
        pstm.setString(2, cus.getPs_Name());
        pstm.setString(3, cus.getPs_Email());
        pstm.setString(4, cus.getPs_Password());
        pstm.setString(5, cus.getPs_Address());
        pstm.setString(6, cus.getPs_PhoneNumber());
        pstm.setString(7, cus.getCT_Rank());
        pstm.setString(8, cus.getPs_Status());
        
        pstm.executeUpdate();
    }
 
    public static void insertCustomer(Connection conn, Customer cus) throws SQLException {
        String sql = "EXEC [dbo].[ADD_CUSTOMER] @ct_Name =?, @ct_Email =?, @ct_Password =?, @ct_Address =?, @ct_PhoneNumber =?, @ct_Rank =?, @ct_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, cus.getPs_Name());
        pstm.setString(2, cus.getPs_Email());
        pstm.setString(3, cus.getPs_Password());
        pstm.setString(4, cus.getPs_Address());
        pstm.setString(5, cus.getPs_PhoneNumber());
        pstm.setString(6, cus.getCT_Rank());
        pstm.setString(7, cus.getPs_Status());
 
        pstm.executeUpdate();
    }
 
    public static void deleteCustomer(Connection conn, String id) throws SQLException {
        String sql = "EXEC [dbo].[DELETE_CUSTOMER_BY_ID] @ct_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
}
