package com.library.controller;

import com.library.dao.StaffsDAO;
import com.library.model.StaffsModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AdminManageStaffs")
public class AdminManageStaffsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StaffsDAO staffDAO;

    public void init() {
        staffDAO = new StaffsDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equalsIgnoreCase(action)) {
                addStaff(request, response);
            } else if ("delete".equalsIgnoreCase(action)) {
                deleteStaff(request, response);
            } else {
                response.getWriter().write("Invalid action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while processing your request.");
        }
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String staffID = request.getParameter("staffID");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");

        if (staffID == null || name == null || password == null || contact == null) {
            response.getWriter().write("All fields are required!");
            return;
        }

        StaffsModel staff = new StaffsModel(staffID, name, password, contact);

        try {
            staffDAO.insertStaff(staff);
            response.getWriter().write("Staff added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Failed to add staff.");
        }
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String staffID = request.getParameter("staffID");

        if (staffID == null) {
            response.getWriter().write("Staff ID is required!");
            return;
        }

        try {
            boolean isDeleted = staffDAO.deleteStaff(staffID);

            if (isDeleted) {
                response.getWriter().write("Staff deleted successfully!");
            } else {
                response.getWriter().write("Failed to delete staff. Staff ID may not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while deleting staff.");
        }
    }
}
