package controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CutomerService;

@WebServlet("/addCustomer")
public class addCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addCustomer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nameCus = request.getParameter("nameCus");
		String emailCus = request.getParameter("emailCus");
		String passCus = request.getParameter("passCus");
		String addressCus = request.getParameter("addressCus");
		String phoneNumberCus = request.getParameter("phoneNumberCus");
		String rankCus = request.getParameter("rankCus");
		
		Customer cus = new Customer("", nameCus, emailCus, passCus, "", addressCus, phoneNumberCus, rankCus, "1");
		
		Connection con = null;
		try {
			CutomerService.insertCustomer(con, cus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
