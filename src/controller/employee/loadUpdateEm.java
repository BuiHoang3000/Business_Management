package controller.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Employee;
import service.EmployeeService;

@WebServlet("/loadUpdateEm")
public class loadUpdateEm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loadUpdateEm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idEmUpdate = request.getParameter("idEmUpdate");
		Employee em = EmployeeService.getEmById(idEmUpdate);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		String objEm = gson.toJson(em);
		out.write(objEm);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
