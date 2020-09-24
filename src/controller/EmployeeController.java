package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Employee;
import service.EmployeeService;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/employee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String getPage = request.getParameter("page");
		String search = request.getParameter("searchEm");
		int page;
		if(getPage == null)
			page = 1;
		else
			page = Integer.parseInt(getPage);
		int emInPage = 10;
		int from = (page - 1) * emInPage + 1;
		int to = page * emInPage;

		List<Employee> listEm = EmployeeService.show_EM_LM(search, from, to);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String objEm = "";
		try {
			objEm = gson.toJson(listEm);
		} catch (Exception e){
			System.out.println(e);
		}
		out.write(objEm);
		out.flush();
	}

}
