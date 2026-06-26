package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.library.model.AdminModel;
import com.library.model.StaffsModel;
import com.library.dao.AdminDAO;
import com.library.dao.StaffsDAO;
import com.library.util.JwtUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userType = request.getParameter("userType");
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            if ("admin".equalsIgnoreCase(userType)) {
                if (authenticateAdmin(id, password)) {
                    String token = JwtUtil.generateToken(id, "admin");
                    Cookie jwtCookie = new Cookie("jwt", token);
                    jwtCookie.setHttpOnly(true);
                    jwtCookie.setPath("/");
                    response.addCookie(jwtCookie);
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("adminLogin.jsp?error=Invalid+Admin+ID+or+Password");
                }
            } else if ("staff".equalsIgnoreCase(userType)) {
                if (authenticateStaff(id, password)) {
                    String token = JwtUtil.generateToken(id, "staff");
                    Cookie jwtCookie = new Cookie("jwt", token);
                    jwtCookie.setHttpOnly(true);
                    jwtCookie.setPath("/");
                    response.addCookie(jwtCookie);
                    response.sendRedirect("staffDashboard.jsp");
                } else {
                    response.sendRedirect("adminLogin.jsp?error=Invalid+Staff+ID+or+Password");
                }
            } else {
                response.sendRedirect("adminLogin.jsp?error=Invalid+user+type");
            }
        } catch (Exception e) {
            response.sendRedirect("adminLogin.jsp?error=An+error+occurred");
            e.printStackTrace();
        }
    }

    private boolean authenticateAdmin(String userId, String password) {
        AdminModel admin = new AdminModel();
        admin.setUser_ID(userId);
        admin.setPassword(password);
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.authenticate(admin);
    }

    private boolean authenticateStaff(String staffId, String password) {
        StaffsModel staff = new StaffsModel();
        staff.setStaff_ID(staffId);
        staff.setPassword(password);
        StaffsDAO staffDAO = new StaffsDAO();
        return staffDAO.authenticate(staff);
    }
}
