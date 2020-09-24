package controller.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import service.EmployeeService;

@WebServlet("/addEmployee")
public class addEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public addEmployee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nameAddEm = request.getParameter("nameAddEm");
		String emailAddEm = request.getParameter("emailAddEm");
		String passAddEm = request.getParameter("passAddEm");
		String birthDateAddEm = request.getParameter("birthDateAddEm");
		String addressAddEm = request.getParameter("addressAddEm");
		String phoneNumberAddEm = request.getParameter("phoneNumberAddEm");
		String positionAddEm = request.getParameter("positionAddEm");
		
		Employee em = new Employee("", nameAddEm, emailAddEm, passAddEm, birthDateAddEm, addressAddEm, phoneNumberAddEm, positionAddEm, "1");
		EmployeeService.addEmployee(em);
	}

}
