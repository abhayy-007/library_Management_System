package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.library.model.AdminModel;
import com.library.model.StaffsModel;
import com.library.dao.AdminDAO;
import com.library.dao.StaffsDAO;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); // Set the content type
        String userType = request.getParameter("userType"); // Determine user type (admin/staff)
        String id = request.getParameter("id"); // Get ID (Admin_ID or Staff_ID)
        String name = request.getParameter("name"); // Get Name

        try {
            if ("admin".equalsIgnoreCase(userType)) {
                if (authenticateAdmin(id, name)) {
                    response.getWriter().write("Admin login successful!");
                } else {
                    response.getWriter().write("Invalid Admin_ID or Name.");
                }
            } else if ("staff".equalsIgnoreCase(userType)) {
                if (authenticateStaff(id, name)) {
                    response.getWriter().write("Staff login successful!");
                } else {
                    response.getWriter().write("Invalid Staff_ID or Name.");
                }
            } else {
                response.getWriter().write("Invalid user type.");
            }
        } catch (Exception e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean authenticateAdmin(String userId, String name) {
        AdminModel admin = new AdminModel();
        admin.setUser_ID(userId); // Assuming AdminModel has setUserId() method
        admin.setName(name);     // Assuming AdminModel has setName() method
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.authenticate(admin); // Call the DAO's authenticate method
    }

    private boolean authenticateStaff(String staffId, String name) {
        StaffsModel staff = new StaffsModel();
        staff.setStaff_ID(staffId); // Assuming StaffModel has setStaffId() method
        staff.setName(name);       // Assuming StaffModel has setName() method
        StaffsDAO staffDAO = new StaffsDAO();
        return staffDAO.authenticate(staff); // Call the DAO's authenticate method
    }
}
