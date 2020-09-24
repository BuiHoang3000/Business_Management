package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CutomerService;

public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/customer.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/customer.jsp").forward(request, response);
	}
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			int totalPage = CutomerService.countCustomer(con);
			System.out.println(totalPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("good bye");
	}
}
