package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Customer;
import service.CutomerService;

@WebServlet("/loadData")
public class loadData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loadData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		// Set request and response UTF-8
		//request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// Get page
		String page = request.getParameter("page");

		int p = 0;
		if(page != null)
			p = Integer.parseInt(page);
		else
			p = 1;
		
		// Pagination
		int fromCT = 0;
		int toCT = 0;
		int ctInPage = 10;
		fromCT = (p - 1) * ctInPage + 1;
		toCT = p * ctInPage;
		
		// Get data
		List<Customer> listCus = new ArrayList<>();
		try {
			listCus = CutomerService.getCustomerLM(con, fromCT, toCT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Return json
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String objectToReturn = gson.toJson(listCus);
		out.write(objectToReturn);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
