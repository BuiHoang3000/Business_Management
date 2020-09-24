package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeService {

	public static Employee findEm(Connection con, String email, String password) {
		String sql = "EXEC [dbo].[LOGIN_ACOUNT] @em_Email = ? , @em_Password = ?";
		try {
			try {
				con = ConnectionSQL.getSQLServerConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Employee em = new Employee();
				em.setPs_Email(email);
				em.setPs_Password(password);
				em.setPs_ID(rs.getString("EM_ID"));
				em.setPs_Name(rs.getString("EM_Name"));
				em.setPs_BirthDate(rs.getString("EM_BirthDate"));
				em.setPs_Address(rs.getString("EM_Address"));
				em.setPs_PhoneNumber(rs.getString("EM_PhoneNumber"));
				em.setEm_Position(rs.getString("EM_Position"));
				em.setPs_Status(rs.getString("EM_Status"));
				return em;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Employee findEm(Connection con, String email) {
		String sql = "EXEC [dbo].[FIND_EMPLOYEE_BY_EMAIL] @em_Email = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Employee em = new Employee();
				em.setPs_Email(email);
				em.setPs_ID(rs.getString("EM_ID"));
				em.setPs_Name(rs.getString("EM_Name"));
				em.setPs_Password(rs.getString("EM_Password"));
				em.setPs_BirthDate(rs.getString("EM_BirthDate"));
				em.setPs_Address(rs.getString("EM_Address"));
				em.setPs_PhoneNumber(rs.getString("EM_PhoneNumber"));
				em.setEm_Position(rs.getString("EM_Position"));
				em.setPs_Status(rs.getString("EM_Status"));
				return em;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Employee> show_EM_LM(String search, int from, int to){
		String sql = "EXEC SHOW_EM_LM @search=?, @from = ?, @to = ?";
		Connection con = null;
		List<Employee> listEm = new ArrayList<Employee>();
		try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, search + "");
			pstm.setString(2, from + "");
			pstm.setString(3, to + "");
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Employee em = new Employee();
				em.setPs_ID(rs.getString("EM_ID"));
				em.setPs_Name(rs.getString("EM_Name"));
				em.setPs_Email(rs.getString("EM_Email"));
				em.setPs_BirthDate(rs.getString("EM_BirthDate"));
				em.setPs_Address(rs.getString("EM_Address"));
				em.setPs_PhoneNumber(rs.getString("EM_PhoneNumber"));
				em.setEm_Position(rs.getString("EM_Position"));
				listEm.add(em);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listEm;
	}
	
	public static int countEm() {
		String sql = "EXEC COUNT_EMPLOYEE";
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public static void addEmployee(Employee em) {
		String sql = "EXEC ADD_EMPLOYEES @em_Name = ?, @em_Email = ?, @em_Password = ?, @em_BirthDate = ?, @em_Address = ?, @em_PhoneNumber = ?, @em_Position = ?, @em_Status = ?";
		Connection con = null;
		try {
			con = ConnectionSQL.getSQLServerConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, em.getPs_Name());
			ps.setString(2, em.getPs_Email());
			ps.setString(3, em.getPs_Password());
			ps.setString(4, em.getPs_BirthDate());
			ps.setString(5, em.getPs_Address());
			ps.setString(6, em.getPs_PhoneNumber());
			ps.setString(7, em.getEm_Position() == "1"? "Tổ trưởng" : "Nhân viên");
			ps.setString(8, em.getPs_Status());
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Employee getEmById(String id) {
		String sql = "EXEC FIND_EM_BY_ID @em_ID = ?";
		Connection con = null;
		Employee em = new Employee();
		try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				em.setPs_ID(rs.getString("EM_ID"));
				em.setPs_Name(rs.getString("EM_Name"));
				em.setPs_Email(rs.getString("EM_Email"));
				em.setPs_Password(rs.getString("EM_Password"));
				em.setPs_BirthDate(rs.getString("EM_BirthDate"));
				em.setPs_Address(rs.getString("EM_Address"));
				em.setPs_PhoneNumber(rs.getString("EM_PhoneNumber"));
				em.setEm_Position(rs.getString("EM_Position"));
				em.setPs_Status(rs.getString("EM_Status"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return em;
	}
	
	public static void updateEmployee(Employee em) {
		String sql = "EXEC UPDATE_EMPLOYESS @em_ID = ?,  @em_Name = ?, @em_Email = ?, @em_Password = ?, @em_BirthDate = ?, @em_Address = ?, @em_PhoneNumber = ?, @em_Position = ?, @em_Status = ?";
		Connection con = null;
		try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, em.getPs_ID());
			ps.setString(2, em.getPs_Name());
			ps.setString(3, em.getPs_Email());
			ps.setString(4, em.getPs_Password());
			ps.setString(5, em.getPs_BirthDate());
			ps.setString(6, em.getPs_Address());
			ps.setString(7, em.getPs_PhoneNumber());
			ps.setString(8, em.getEm_Position());
			ps.setString(9, em.getPs_Status());
			System.out.println(em.getEm_Position());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteEmployee(String ids) {
		String sql = "EXEC UPDATE_EM_MUL_STATUS @StringID = ?";
		Connection con = null;
		try {
			con = ConnectionSQL.getSQLServerConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ids);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
