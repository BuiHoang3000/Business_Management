package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Categories;
import service.CategoryService;

@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CategoryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/categories.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("searchCA");
		String page = request.getParameter("page");
		int p = 0;
		if(page == null)
			p = 1;
		else
			p = Integer.parseInt(page);
		int fromCa = 0, toCa = 0;
		int caInPage = 10;
		fromCa = (p - 1) * caInPage + 1;
		toCa = p * caInPage;
		
		List<Categories> listCa = new ArrayList<Categories>();
		
		try {
			listCa = CategoryService.queryCategories(search, fromCa, toCa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;");
		
		Gson gson = new Gson();
		String objCa = "";
		try {
			objCa = gson.toJson(listCa);
		} catch(Exception e) {
			System.out.println(e);
		}
		out.write(objCa);
		out.flush();
	}

}
