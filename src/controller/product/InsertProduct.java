package controller.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.LoginService;
import service.ProductService;

public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/product");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("nameAdd") != null && request.getParameter("priceAdd") != null && request.getParameter("quantifyAdd") != null) {
			String category = request.getParameter("categoryAdd");
			String name = request.getParameter("nameAdd");
			float price = Float.parseFloat(request.getParameter("priceAdd"));
			int quantify = Integer.parseInt(request.getParameter("quantifyAdd"));
			String supplier = request.getParameter("supplierAdd");
			
			Product pr = new Product(category,name,price,quantify,supplier,"1");
			Connection con = LoginService.getStoredConnection(request);
			try {
				ProductService.insertProduct(con, pr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath()+"/product");
	}

}
