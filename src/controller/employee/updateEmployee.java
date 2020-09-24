package controller.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import service.EmployeeService;

@WebServlet("/updateEmployee")
public class updateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updateEmployee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String idUpEm = request.getParameter("idUpEm");
		String nameUpEm = request.getParameter("nameUpEm");
		String emailUpEm = request.getParameter("emailUpEm");
		String passUpEm = request.getParameter("passUpEm");
		String birthDateUpEm = request.getParameter("birthDateUpEm");
		String addressUpEm = request.getParameter("addressUpEm");
		String phoneNumberUpEm = request.getParameter("phoneNumberUpEm");
		String positionUpEm = request.getParameter("positionUpEm");

		Employee em = new Employee(idUpEm, nameUpEm, emailUpEm, passUpEm, birthDateUpEm, addressUpEm, phoneNumberUpEm, positionUpEm.equals("1")?"Tổ trưởng":"Nhân viên", "1");
		EmployeeService.updateEmployee(em);
	}

}
