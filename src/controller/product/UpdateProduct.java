package controller.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;

import model.Product;
import service.LoginService;
import service.ProductService;


public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProduct() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/product.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		int quantify = Integer.parseInt(request.getParameter("quantify"));
		String supplier = request.getParameter("supplier");
		
		Product pr = new Product(id,category,name,price,quantify,supplier,"1");
		Connection con = LoginService.getStoredConnection(request);
		try {
			ProductService.updateProduct(con, pr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/product");
	}
}
