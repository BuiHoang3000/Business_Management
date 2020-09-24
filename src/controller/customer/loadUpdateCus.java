package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Customer;
import service.CutomerService;

@WebServlet("/loadUpdateCus")
public class loadUpdateCus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loadUpdateCus() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Customer cus = null;
		String idUpdate = request.getParameter("idCusUpdate");
		try {
			cus = CutomerService.findCustomer(idUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String objReturn = "";
		try {
			objReturn = gson.toJson(cus);
		} catch(Exception e) {
			System.out.println(e);
		}
		out.write(objReturn);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
