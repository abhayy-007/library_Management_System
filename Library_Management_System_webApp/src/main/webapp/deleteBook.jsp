<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Delete Book</title>
    <link rel="stylesheet" href="project-7/Delete-Book/style.css" />
  </head>

  <body>
    <div class="container">
      <h1>Delete Book</h1>
      <form action="BooksServlet" method="post" id="deleteBookForm">
        <input type="hidden" name="action" value="delete" />

        <label for="bookID">Book ID:</label>
        <input type="text" name="bookID" id="bookID" required />

        <button type="submit">Delete Book</button>
      </form>
    </div>

    <script src="project-7/Delete-Book/script.js"></script>
  </body>
</html>
