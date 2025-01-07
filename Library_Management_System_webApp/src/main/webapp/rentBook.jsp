<!DOCTYPE html>
<html>
  <head>
    <title>Rent a Book</title>
    <link
      rel="stylesheet"
      href="project-7/Renting-Books/Rent-Books/style.css"
    />
    <!-- Link to CSS file -->
  </head>

  <body>
    <div class="container">
      <h1>Rent a Book</h1>
      <form action="RentBookServlet" method="post">
        <label for="bookID">Book ID:</label>
        <input type="text" id="bookID" name="bookID" required />

        <label for="borrowerName">Borrower Name:</label>
        <input type="text" id="borrowerName" name="borrowerName" required />

        <label for="borrowerContact">Borrower Contact:</label>
        <input
          type="text"
          id="borrowerContact"
          name="borrowerContact"
          required
        />

        <input type="submit" value="Rent Book" />
      </form>
      <a href="staffDashboard.jsp">Back to Staff Dashboard</a>
    </div>
    <script src="project-7/Renting-Books/Rent-Books/script.js"></script>
    <!-- Link to JavaScript file -->
  </body>
</html>
