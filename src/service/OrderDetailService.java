package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderDetail;

public class OrderDetailService {
	public static List<OrderDetail> queryOrderDetail(String search, int from, int to) throws SQLException {
        String sql = "EXEC SHOW_ORDER_DETAIL @search = ?, @from = ?, @to = ?";
        
        Connection con = null;
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			 
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) {
	            OrderDetail de = new OrderDetail();
	            de.setDe_OD_ID(rs.getString("DE_OD_ID"));
	            de.setDe_PR_ID(rs.getString("DE_PR_ID"));
	            de.setDe_Quantify(rs.getString("DE_Quantify"));
	            de.setDe_Price(rs.getString("DE_Price"));
	            de.setDe_Status(rs.getString("DE_Status"));
	            list.add(de);
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
	
	public static int countOrderDetail() throws SQLException {
		String sql = "EXEC COUNT_ORDER_DETAIL";
		
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
 
    public static OrderDetail findOrderDetail(String code) throws SQLException {
        String sql = "EXEC FIND_ORDER_DETAIL_BY_ID @de_OD_ID = ?";
 
        OrderDetail de = new OrderDetail();
        
        Connection conn = null;
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            de.setDe_OD_ID(rs.getString("DE_OD_ID"));
	            de.setDe_PR_ID(rs.getString("DE_PR_ID"));
	            de.setDe_Quantify(rs.getString("DE_Quantify"));
	            de.setDe_Price(rs.getString("DE_Price"));
	            de.setDe_Status(rs.getString("DE_Status"));
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
        return de;
    }
 
    public static void updateOrderDetail(OrderDetail de) throws SQLException {
        String sql = "EXEC UPDATE_ORDER_DETAIL @de_ID = ?, @de_Quantify = ?";
 
        Connection conn = null;
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setInt(1, Integer.parseInt(de.getDe_OD_ID()));
	        pstm.setInt(2, Integer.parseInt(de.getDe_Quantify()));
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void insertOrderDetail(OrderDetail de) throws SQLException {
        String sql = "EXEC ADD_ORDERS_DETAIL @de_OD_ID = ?, @de_PR_ID =?, @de_Quantify = ?, @de_Status = ?";
        
        Connection conn = null;
        
        try {
			conn = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			 
	        pstm.setInt(1, Integer.parseInt(de.getDe_OD_ID()));
	        pstm.setInt(2, Integer.parseInt(de.getDe_PR_ID()));
	        pstm.setInt(3, Integer.parseInt(de.getDe_Quantify()));
	        pstm.setString(4, de.getDe_Status());
	        
	        pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
    }
 
    public static void deleteOrder(String id) throws SQLException {
        String sql = "EXEC DELETE_ORDER_DETAIL @de_OD_ID = ?";
        
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
