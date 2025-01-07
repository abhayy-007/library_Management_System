<%@page import="java.util.List" %>
<%@page import="com.library.model.StaffsModel" %>
<html>

<head>
    <title>View All Staffs</title>
    <link rel="stylesheet" href="project-7/View-All/view-AllStaffs/style.css"> <!-- Link to CSS file -->
</head>

<body>
    <div class="container" action="ViewAllStaffsServlet" method="post">
        <h1>All Staffs</h1>

        <!-- Search input field to filter the staffs -->
        <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for staff...">

        <!-- Table displaying staff details -->
        <table id="staffsTable" border="1">
            <thead>
                <tr>
                    <th>Staff ID</th>
                    <th>Name</th>
                    <th>Contact</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Get the list of staffs passed by the servlet
                    List<StaffsModel> staffs = (List<StaffsModel>) request.getAttribute("staffs");

                    if (staffs != null && !staffs.isEmpty()) {
                        for (StaffsModel staff : staffs) {
                %>
                <tr>
                    <td><%= staff.getStaff_ID() %></td>
                    <td><%= staff.getName() %></td>
                    <td><%= staff.getContact() %></td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3">No staff records found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Link to go back to the admin dashboard -->
        <a href="adminDashboard.jsp">Back to Admin Dashboard</a>
    </div>

    <!-- JavaScript for search functionality -->
    <script src="project-7/View-All/view-AllStaffs/script.js"></script>
</body>

</html>
