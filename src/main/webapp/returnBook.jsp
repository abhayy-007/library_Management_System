<%@page import="java.util.List" %>
<%@page import="org.bson.Document" %>
<html>
<head>
    <title>Return Book</title>
    <link rel="stylesheet" href="project-7/Renting-Books/Rent-Books/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>
<body>
    <div class="container">
        <h1>Return a Book</h1>
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: green; font-weight: bold;"><%= request.getAttribute("message") %></p>
        <% } %>
        <form action="ReturnBookServlet" method="post">
            <label for="rentID">Rent ID:</label>
            <input type="text" id="rentID" name="rentID" required />
            <input type="submit" value="Return Book" />
        </form>

        <h2>Active Rentals</h2>
        <table border="1" style="width:100%;margin-top:1rem">
            <thead>
                <tr>
                    <th>Rent ID</th>
                    <th>Book ID</th>
                    <th>Borrower</th>
                    <th>Due Date</th>
                </tr>
            </thead>
            <tbody>
                <% List<Document> rentals = (List<Document>) request.getAttribute("rentals");
                   if (rentals != null) {
                       for (Document r : rentals) {
                           if (!r.getBoolean("Returned", false)) { %>
                <tr>
                    <td><%= r.getObjectId("_id").toString() %></td>
                    <td><%= r.getString("Book_ID") %></td>
                    <td><%= r.getString("Borrower_Name") %></td>
                    <td><%= r.getString("Due_Date") %></td>
                </tr>
                <%         }
                       }
                   } %>
            </tbody>
        </table>

        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
    </div>
</body>
</html>
