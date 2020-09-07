package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
