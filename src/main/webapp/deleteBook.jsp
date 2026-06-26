<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Delete Book</title>
    <link rel="stylesheet" href="project-7/Delete-Book/style.css" />
    <style>.back-btn{display:block;width:100%;padding:15px;background:#546e7a;color:#fff;text-decoration:none;border:none;border-radius:8px;margin:6px 0;font-size:16px;cursor:pointer;text-align:center;box-sizing:border-box;transition:background .3s}.back-btn:hover{background:#455a64}</style>
  </head>

  <body>
    <div class="container">
      <h1>Delete Book</h1>
      <form action="BooksServlet" method="post" id="deleteBookForm">
        <input type="hidden" name="action" value="delete" />

        <label for="bookID">Book ID:</label>
        <input type="text" name="bookID" id="bookID" required />

        <button type="submit">Delete Book</button>
        <a href="staffDashboard.jsp" class="back-btn">Back to Staff Dashboard</a>
      </form>
    </div>

    <script src="project-7/Delete-Book/script.js"></script>
  </body>
</html>
