<!DOCTYPE html>
<html>
<head>
    <title>Success</title>
    <link rel="stylesheet" href="project-7/Renting-Books/Sucess-page/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
</head>
<body>
    <div class="container">
        <h1>Book Rented Successfully!</h1>
        <% 
            String bookID = (String) request.getAttribute("bookID");
            String userName = (String) request.getAttribute("userName");
            String dueDate = (String) request.getAttribute("dueDate");
            if (bookID != null && userName != null) {
        %>
        <p>Book: <strong><%= bookID %></strong></p>
        <p>Rented To: <strong><%= userName %></strong></p>
        <p>Due Date: <strong><%= dueDate %></strong></p>
        <% } %>
        <a href="RentBookServlet" class="back-btn">Rent Another Book</a>
        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
    </div>
    <script src="project-7/Renting-Books/Sucess-page/script.js"></script>
</body>
</html>
