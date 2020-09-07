package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Orders;

public class OrderSevice {
	public static List<Orders> queryOrders(Connection con) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_ORDERS]";
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
        List<Orders> list = new ArrayList<Orders>();
        while (rs.next()) {
            Orders order = new Orders();
            order.setOd_ID(rs.getString("OD_ID"));
            order.setOd_CT_ID(rs.getString("OD_CT_ID"));
            order.setOd_EM_ID(rs.getString("OD_EM_ID"));
            order.setOd_OrderDate(rs.getString("OD_OrderDate"));
            order.setOd_Status(rs.getString("OD_Status"));
            list.add(order);
        }
        return list;
    }
 
    public static Orders findOrder(Connection conn, String code) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_ORDERS_BY_ID] @od_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            Orders order = new Orders();
            order.setOd_ID(rs.getString("OD_ID"));
            order.setOd_CT_ID(rs.getString("OD_CT_ID"));
            order.setOd_EM_ID(rs.getString("OD_EM_ID"));
            order.setOd_OrderDate(rs.getString("OD_OrderDate"));
            order.setOd_Status(rs.getString("OD_Status"));
            return order;
        }
        return null;
    }
 
    public static void updateOrder(Connection conn, Orders order) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_ORDER] @od_ID = ?, @od_CT_ID = ?, @od_EM_ID = ?, @od_OrderDate = ?, @od_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, Integer.parseInt(order.getOd_ID()));
        pstm.setInt(2, Integer.parseInt(order.getOd_CT_ID()));
        pstm.setInt(3, Integer.parseInt(order.getOd_EM_ID()));
        pstm.setString(4, order.getOd_OrderDate());
        pstm.setString(5, order.getOd_Status());
        
        pstm.executeUpdate();
    }
 
    public static void insertOrder(Connection conn, Orders order) throws SQLException {
        String sql = "EXEC [dbo].[ADD_ORDERS] @od_CT_ID = ?, @od_EM_ID = ?, @od_OrderDate = ?, @od_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, Integer.parseInt(order.getOd_CT_ID()));
        pstm.setInt(2, Integer.parseInt(order.getOd_EM_ID()));
        pstm.setString(3, order.getOd_OrderDate());
        pstm.setString(4, order.getOd_Status());
        
        pstm.executeUpdate();
    }
 
    public static void deleteOrder(Connection conn, String id) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_STATUS_ORDER] @od_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
}
