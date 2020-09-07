package service;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;

public class LoginService {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	 
    private static final String ATT_NAME_EMPLOYEE = "ATTRIBUTE_FOR_STORE_EMPLOYEE_IN_COOKIE";
 
    // Lưu trữ Connection vào attribute của request.
    // Thông tin lưu trữ này chỉ tồn tại trong thời gian yêu cầu (request)
    // cho tới khi dữ liệu được trả về trình duyệt người dùng.
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Lấy đối tượng Connection đã được lưu trữ trong attribute của request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Lưu trữ thông tin người dùng đã login vào Session.
    public static void storeLoginedEm(HttpSession session, Employee loginedEm) {
        // Trên JSP có thể truy cập thông qua ${loginedEm}
        session.setAttribute("loginedEm", loginedEm);
    }
 
    // Lấy thông tin người dùng lưu trữ trong Session.
    public static Employee getLoginedEm(HttpSession session) {
        Employee loginedEm = (Employee) session.getAttribute("loginedEm");
        return loginedEm;
    }
 
    // Lưu thông tin người dùng vào Cookie.
    public static void storeUserCookie(HttpServletResponse response, Employee em) {
        System.out.println("Store user cookie");
        Cookie cookieEm = new Cookie(ATT_NAME_EMPLOYEE, em.getPs_Name());
        // 1 ngày (Đã đổi ra giây)
        cookieEm.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieEm);
    }
 
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_EMPLOYEE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Xóa Cookie của người dùng
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_EMPLOYEE, null);
        // 0 giây. (Cookie này sẽ hết hiệu lực ngay lập tức)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
