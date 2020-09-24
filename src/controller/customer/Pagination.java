package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.CutomerService;

@WebServlet("/Pagination")
public class Pagination extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Pagination() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		int totalCT = 0;
		int page = 0;
		try {
			 totalCT = CutomerService.countCustomer(con);
			int ctInPage = 10;
			if(totalCT % ctInPage == 0)
				page = totalCT / ctInPage;
			else
				page = totalCT / ctInPage + 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		String objectToReturn = gson.toJson(page);
		out.write(objectToReturn);
		out.flush();
	}

}
