package controller.category;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categories;
import service.CategoryService;

@WebServlet("/updateCategory")
public class updateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updateCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idUpdateCa = request.getParameter("idUpdateCa");
		String nameUpdateCa = request.getParameter("nameUpdateCa");
		
		Categories ca = new Categories(idUpdateCa, nameUpdateCa, "1");
		
		try {
			CategoryService.updateCategories(ca);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
