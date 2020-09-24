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

import model.Categories;
import service.CategoryService;

@WebServlet("/loadUpdateCa")
public class loadUpdateCa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loadUpdateCa() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idUpdateCa = request.getParameter("idUpdateCa");
		
		Categories ca = new Categories();
		try {
			ca = CategoryService.findCategories(idUpdateCa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		String objCa = "";
		try {
			objCa = gson.toJson(ca);
		} catch (Exception e) {
			System.out.println(e);
		}
		out.write(objCa);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
