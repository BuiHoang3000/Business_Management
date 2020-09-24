package controller.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.EmployeeService;

@WebServlet("/paginationEm")
public class paginationEm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public paginationEm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int emInPage = 10;
		int totalPage;
		int totalEm = EmployeeService.countEm();
		if(totalEm % emInPage == 0)
			totalPage = totalEm / emInPage;
		else
			totalPage = totalEm / emInPage + 1;
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		String objPage = "";
		try {
			objPage = gson.toJson(totalPage);
		} catch(Exception e) {
			System.out.println(e);
		}
		out.write(objPage);
		out.flush();
	}

}
