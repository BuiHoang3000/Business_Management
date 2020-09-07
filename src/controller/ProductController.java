package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CategoryService;
import service.LoginService;
import service.ProductService;
import service.SupplierService;
import model.Categories;
import model.Product;
import model.Supplier;

public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProductController() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = LoginService.getStoredConnection(request);
		
		String p = request.getParameter("page");
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		String size = request.getParameter("size");
		if(size == null) {
			size = "10";
		}
		String num = request.getParameter("prNumberPerPage");

		int prNumberPerPage;
		if(num == null) {
			prNumberPerPage = Integer.parseInt(size);
		} else {
			prNumberPerPage = Integer.parseInt(num);
		}
		
		String errorString = null;
		List<Product> listPr = new ArrayList<>();
		List<Categories> listCa = new ArrayList<>();
		List<Supplier> listSu = new ArrayList<>();
		int numOfPages = 1;
		int totalPage = 0;
		try {
			listCa = CategoryService.queryCategories(conn);
			listSu = SupplierService.querySupplier(conn);
			totalPage = ProductService.countProduct(conn);
			if(numOfPages % prNumberPerPage != 0) {
				numOfPages = (totalPage / prNumberPerPage) + 1;
			} else {
				numOfPages = totalPage /prNumberPerPage;
			}
			int fromPr = 0, toPr = 0;
			fromPr = (page - 1) * prNumberPerPage + 1;
			toPr = page * prNumberPerPage;			
			listPr = ProductService.queryProduct(conn, fromPr, toPr);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("productList", listPr);
		request.setAttribute("categoryList", listCa);
		request.setAttribute("supplierList", listSu);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", prNumberPerPage);
		request.setAttribute("numOfPages", numOfPages);
		// /view/product.jsp
		request.getServletContext().getRequestDispatcher("/view/product.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Connection conn = LoginService.getStoredConnection(request);
		String findPr = request.getParameter("searchPr");
		if(findPr == null)
			findPr = "";
		String p = request.getParameter("page");
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		String size = request.getParameter("size");
		if(size == null) {
			size = "10";
		}
		String num = request.getParameter("prNumberPerPage");

		int prNumberPerPage;
		if(num == null) {
			prNumberPerPage = Integer.parseInt(size);
		} else {
			prNumberPerPage = Integer.parseInt(num);
		}
		
		String errorString = null;
		List<Product> listPr = new ArrayList<>();
		List<Categories> listCa = new ArrayList<>();
		List<Supplier> listSu = new ArrayList<>();
		int numOfPages = 1;
		int totalPage = 0;
		try {
			listCa = CategoryService.queryCategories(conn);
			listSu = SupplierService.querySupplier(conn);
			int fromPr = 0, toPr = 0;
			fromPr = (page - 1) * prNumberPerPage + 1;
			toPr = page * prNumberPerPage;
			totalPage = ProductService.countProductSearch(conn, findPr);
			if(numOfPages % prNumberPerPage != 0) {
				numOfPages = (totalPage / prNumberPerPage) + 1;
			} else {
				numOfPages = totalPage /prNumberPerPage;
			}	
			listPr = ProductService.findProduct(conn, findPr, fromPr, toPr);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("productList", listPr);
		request.setAttribute("categoryList", listCa);
		request.setAttribute("supplierList", listSu);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", prNumberPerPage);
		request.setAttribute("numOfPages", numOfPages);
		// /view/product.jsp
		request.getServletContext().getRequestDispatcher("/view/product.jsp").forward(request, response);
	}
}
