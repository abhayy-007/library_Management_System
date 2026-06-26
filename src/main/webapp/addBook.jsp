<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add New Book</title>
    <link rel="stylesheet" href="project-7/Add-Book/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
  </head>

  <body>
    <div class="container">
      <h1>Add New Book</h1>
      <form action="BooksServlet" method="post" id="addBookForm">
        <input type="hidden" name="action" value="insert" />

        <label for="bookID">Book ID:</label>
        <input type="text" name="bookID" id="bookID" required />

        <label for="category">Category:</label>
        <input type="text" name="category" id="category" required />

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required />

        <label for="author">Author:</label>
        <input type="text" name="author" id="author" required />

        <label for="copies">Copies:</label>
        <input type="number" name="copies" id="copies" required />

        <button type="submit">Add Book</button>
        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
      </form>
    </div>

    <script src="project-7/Add-Book/script.js"></script>
  </body>
</html>
