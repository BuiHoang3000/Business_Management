package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Orders;

public class OrderSevice {
	public static List<Orders> queryOrders(String search, int from, int to) throws SQLException {
        String sql = "EXEC SHOW_ORDERS @search = ?, @from = ?, @to = ?";
        
        Connection con = null;
        List<Orders> list = new ArrayList<Orders>();
        try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			 
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) {
	            Orders order = new Orders();
	            order.setOd_ID(rs.getString("OD_ID"));
	            order.setOd_CT_ID(rs.getString("OD_CT_ID"));
	            order.setOd_EM_ID(rs.getString("OD_EM_ID"));
	            order.setOd_OrderDate(rs.getString("OD_OrderDate"));
	            order.setOd_Status(rs.getString("OD_Status"));
	            list.add(order);
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
	
	public static int countOrder() throws SQLException {
		String sql = "EXEC COUNT_ORDER";
		
		int count = 0;
		Connection con = null;
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
 
    public static Orders findOrder(String code) throws SQLException {
        String sql = "EXEC SHOW_ORDERS_BY_ID @od_ID = ?";
 
        Connection conn = null;
        Orders order = new Orders();
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            order.setOd_ID(rs.getString("OD_ID"));
	            order.setOd_CT_ID(rs.getString("OD_CT_ID"));
	            order.setOd_EM_ID(rs.getString("OD_EM_ID"));
	            order.setOd_OrderDate(rs.getString("OD_OrderDate"));
	            order.setOd_Status(rs.getString("OD_Status"));
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
        return order;
    }
 
    public static void updateOrder(Orders order) throws SQLException {
        String sql = "EXEC UPDATE_ORDER @od_ID = ?, @od_CT_ID = ?, @od_EM_ID = ?, @od_OrderDate = ?, @od_Status = ?";
 
        Connection conn = null;
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setInt(1, Integer.parseInt(order.getOd_ID()));
	        pstm.setInt(2, Integer.parseInt(order.getOd_CT_ID()));
	        pstm.setInt(3, Integer.parseInt(order.getOd_EM_ID()));
	        pstm.setString(4, order.getOd_OrderDate());
	        pstm.setString(5, order.getOd_Status());
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void insertOrder(Orders order) throws SQLException {
        String sql = "EXEC ADD_ORDERS @od_CT_ID = ?, @od_EM_ID = ?, @od_OrderDate = ?, @od_Status = ?";
 
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setInt(1, Integer.parseInt(order.getOd_CT_ID()));
	        pstm.setInt(2, Integer.parseInt(order.getOd_EM_ID()));
	        pstm.setString(3, order.getOd_OrderDate());
	        pstm.setString(4, order.getOd_Status());
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
    }
 
    public static void deleteOrder(String id) throws SQLException {
        String sql = "EXEC UPDATE_STATUS_ORDER @od_ID = ?";
        
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
