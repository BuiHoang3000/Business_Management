package controller.customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CutomerService;

@WebServlet("/updateCustomer")
public class updateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updateCustomer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idCus = request.getParameter("idCus");
		String nameCus = request.getParameter("nameCus");
		String emailCus = request.getParameter("emailCus");
		String passCus = request.getParameter("passCus");
		String addressCus = request.getParameter("addressCus");
		String phoneNumberCus = request.getParameter("phoneCus");
		String rankCus = request.getParameter("rankCus");

		Customer cus = new Customer(idCus, nameCus, emailCus, passCus, "", addressCus, phoneNumberCus, rankCus, "1");
		try {
			CutomerService.updateCustomer(cus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
