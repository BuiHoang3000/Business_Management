package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import service.EmployeeService;
import service.LoginService;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("email");
        String password = request.getParameter("pass");
        
        Employee em = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = LoginService.getStoredConnection(request);
            // Tìm user trong DB.
			em = EmployeeService.findEm(conn, userName, password);
 
			if (em == null) {
			    hasError = true;
			    errorString = "User Name or password invalid";
			}
        }

        if (hasError) {
            em = new Employee();
            em.setPs_Email(userName);
            em.setPs_Password(password);

            // Lưu các thông tin vào request attribute trước khi forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("em", em);
 
            // Forward (Chuyển tiếp) tới trang /WEB-INF/views/login.jsp
            request.getServletContext().getRequestDispatcher("/view/login.jsp?err=1").forward(request, response);
        }

        else {
            HttpSession session = request.getSession();
            LoginService.storeLoginedEm(session, em);
            
            response.sendRedirect(request.getContextPath() + "/product");
        }
	}

}
