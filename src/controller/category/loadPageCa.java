package controller.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.CategoryService;

@WebServlet("/loadPageCa")
public class loadPageCa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loadPageCa() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int totalCa = 0;
		try {
			totalCa = CategoryService.countCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int page = 0;
		int caInPage = 10;
		if(totalCa % caInPage == 0)
			page = totalCa / caInPage;
		else
			page = totalCa / caInPage + 1;
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String objPageCa = "";
		try {
			objPageCa = gson.toJson(page);
		} catch (Exception e) {
			System.out.println(e);
		}
		out.write(objPageCa);
		out.flush();
	}

}
