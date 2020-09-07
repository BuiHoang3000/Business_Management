package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderDetail;

public class OrderDetailService {
	public static List<OrderDetail> queryOrderDetail(Connection con) throws SQLException {
        String sql = "EXEC [dbo].[SHOW_ORDER_DETAIL]";
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
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        while (rs.next()) {
            OrderDetail de = new OrderDetail();
            de.setDe_OD_ID(rs.getString("DE_OD_ID"));
            de.setDe_PR_ID(rs.getString("DE_PR_ID"));
            de.setDe_Quantify(rs.getString("DE_Quantify"));
            de.setDe_Price(rs.getString("DE_Price"));
            de.setDe_Status(rs.getString("DE_Status"));
            list.add(de);
        }
        return list;
    }
 
    public static OrderDetail findOrderDetail(Connection conn, String code) throws SQLException {
        String sql = "EXEC [dbo].[FIND_ORDER_DETAIL_BY_ID] @de_OD_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            OrderDetail de = new OrderDetail();
            de.setDe_OD_ID(rs.getString("DE_OD_ID"));
            de.setDe_PR_ID(rs.getString("DE_PR_ID"));
            de.setDe_Quantify(rs.getString("DE_Quantify"));
            de.setDe_Price(rs.getString("DE_Price"));
            de.setDe_Status(rs.getString("DE_Status"));
            return de;
        }
        return null;
    }
 
    public static void updateOrderDetail(Connection conn, OrderDetail de) throws SQLException {
        String sql = "EXEC [dbo].[UPDATE_ORDER_DETAIL] @de_ID = ?, @de_Quantify = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, Integer.parseInt(de.getDe_OD_ID()));
        pstm.setInt(2, Integer.parseInt(de.getDe_Quantify()));
        
        pstm.executeUpdate();
    }
 
    public static void insertOrderDetail(Connection conn, OrderDetail de) throws SQLException {
        String sql = "EXEC [dbo].[ADD_ORDERS_DETAIL] @de_OD_ID = ?, @de_PR_ID =?, @de_Quantify = ?, @de_Status = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, Integer.parseInt(de.getDe_OD_ID()));
        pstm.setInt(2, Integer.parseInt(de.getDe_PR_ID()));
        pstm.setInt(3, Integer.parseInt(de.getDe_Quantify()));
        pstm.setString(4, de.getDe_Status());
        
        pstm.executeUpdate();
    }
 
    public static void deleteOrder(Connection conn, String id) throws SQLException {
        String sql = "EXEC [dbo].[DELETE_ORDER_DETAIL] @de_OD_ID = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
}
