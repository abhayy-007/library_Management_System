<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Book Copies</title>
    <link rel="stylesheet" href="project-7/UpdateBook-Copies/style.css" />
  </head>

  <body>
    <div class="container">
      <h1>Update Book Copies</h1>
      <form action="BooksServlet" method="post" id="updateBookForm">
        <input type="hidden" name="action" value="update" />

        <label for="bookID">Book ID:</label>
        <input type="text" name="bookID" id="bookID" required />

        <label for="copies">New Copies:</label>
        <input type="number" name="copies" id="copies" required min="1" />

        <button type="submit">Update Copies</button>
      </form>
    </div>

    <script src="project-7/UpdateBook-Copies/script.js"></script>
  </body>
</html>
