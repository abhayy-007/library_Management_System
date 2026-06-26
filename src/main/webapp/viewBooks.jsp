<%@page import="java.util.List" %>
<%@page import="com.library.model.BooksModel" %>
<html>

<head>
    <title>View All Books</title>
    <link rel="stylesheet" href="project-7/View-All/view-AllBooks/style.css">
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>

<body>
    <div class="container">
        <h1>All Books</h1>

        <!-- Search input field to filter the books -->
        <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for books...">

        <!-- Table displaying book details -->
        <table id="booksTable" border="1">
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Copies</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Get the list of books from the request attribute
                    List<BooksModel> books = (List<BooksModel>) request.getAttribute("books");
                    if (books != null && !books.isEmpty()) {
                        for (BooksModel book : books) {
                %>
                <tr>
                    <td><%= book.getBook_ID() %></td>
                    <td><%= book.getCategory() %></td>
                    <td><%= book.getName() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getCopies() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="5">No books found</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Link to go back to the staff dashboard -->
        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
    </div>

    <!-- JavaScript for search functionality -->
    <script src="project-7/View-All/view-AllBooks/script.js"></script>
</body>

</html>
