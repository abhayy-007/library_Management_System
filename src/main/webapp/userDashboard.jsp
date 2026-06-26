<%@page import="java.util.List" %>
<%@page import="org.bson.Document" %>
<%@page import="com.library.model.BooksModel" %>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="project-7/View-All/view-AllBooks/style.css" />
    <style>
        .back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}
        .ext-btn{display:inline-block;padding:8px 16px;background:#4caf50;color:#fff;text-decoration:none;border:none;border-radius:4px;cursor:pointer;font-size:14px}
        .ext-btn:disabled{background:#ccc;cursor:not-allowed}
        h2{margin-top:2rem}
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, <%= request.getAttribute("userId") %>!</h1>

        <h2>Browse Books</h2>
        <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for books...">
        <table id="booksTable" border="1">
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Available Copies</th>
                </tr>
            </thead>
            <tbody>
                <% List<BooksModel> books = (List<BooksModel>) request.getAttribute("books");
                   if (books != null && !books.isEmpty()) {
                       for (BooksModel book : books) { %>
                <tr>
                    <td><%= book.getBook_ID() %></td>
                    <td><%= book.getCategory() %></td>
                    <td><%= book.getName() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getCopies() %></td>
                </tr>
                <%     }
                   } else { %>
                <tr><td colspan="5">No books found</td></tr>
                <% } %>
            </tbody>
        </table>

        <h2>My Rentals</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Book Name</th>
                    <th>Rent Date</th>
                    <th>Due Date</th>
                    <th>Status</th>
                    <th>Extension</th>
                </tr>
            </thead>
            <tbody>
                <% List<Document> rentals = (List<Document>) request.getAttribute("rentals");
                   if (rentals != null && !rentals.isEmpty()) {
                       for (Document rent : rentals) {
                           boolean returned = rent.getBoolean("Returned", false);
                           String extStatus = rent.getString("Extension_Status");
                           String rentId = rent.getObjectId("_id").toString();
                           boolean canExtend = !returned && (extStatus == null || "rejected".equals(extStatus));
                %>
                <tr>
                    <td><%= rent.getString("Book_Name") %></td>
                    <td><%= rent.getString("Rent_Date") %></td>
                    <td><%= rent.getString("Due_Date") %></td>
                    <td><%= returned ? "Returned" : "Active" %></td>
                    <td>
                        <% if (returned) { %>
                            --
                        <% } else if ("pending".equals(extStatus)) { %>
                            Pending
                        <% } else if ("approved".equals(extStatus)) { %>
                            Approved
                        <% } else { %>
                            <form action="ExtensionRequestServlet" method="post" style="margin:0">
                                <input type="hidden" name="rentID" value="<%= rentId %>" />
                                <button type="submit" class="ext-btn">Request Extension (+7 days)</button>
                            </form>
                        <% } %>
                    </td>
                </tr>
                <%     }
                   } else { %>
                <tr><td colspan="5">No rentals found</td></tr>
                <% } %>
            </tbody>
        </table>

        <a href="logout" class="back-btn">Logout</a>
    </div>
    <script>
        function filterTable() {
            var input = document.getElementById("searchInput");
            var filter = input.value.toUpperCase();
            var table = document.getElementById("booksTable");
            var tr = table.getElementsByTagName("tr");
            for (var i = 1; i < tr.length; i++) {
                var td = tr[i].getElementsByTagName("td");
                var found = false;
                for (var j = 0; j < td.length; j++) {
                    if (td[j] && td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                        found = true; break;
                    }
                }
                tr[i].style.display = found ? "" : "none";
            }
        }
    </script>
</body>
</html>
