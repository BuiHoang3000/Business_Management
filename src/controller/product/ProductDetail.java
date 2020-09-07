package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.LoginService;
import service.ProductService;

public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Connection conn = LoginService.getStoredConnection(request);
		
		String idPr = request.getParameter("id");
		List<model.ProductDetail> listPrDetail = new ArrayList<>();
		try {
			listPrDetail = ProductService.findProductDetail(conn,idPr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        //Import gson-2.2.2.jar
        Gson gson = new Gson();
        String objectToReturn = gson.toJson(listPrDetail); //Convert List -> Json
        out.write(objectToReturn); //Đưa Json trả về Ajax
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/product");
	}

}
