document
  .getElementById("deleteBookForm")
  .addEventListener("submit", function (event) {
    const bookID = document.getElementById("bookID").value.trim();

    // Basic validation to check if the Book ID field is empty
    if (!bookID) {
      event.preventDefault(); // Prevent form submission
      alert("Please provide a valid Book ID.");
    }
  });
