document
  .getElementById("updateBookForm")
  .addEventListener("submit", function (event) {
    const bookID = document.getElementById("bookID").value.trim();
    const copies = document.getElementById("copies").value.trim();

    // Basic validation to check if the Book ID field is empty
    if (!bookID) {
      event.preventDefault(); // Prevent form submission
      alert("Please provide a valid Book ID.");
      return;
    }

    // Check if the "copies" field contains a positive number
    if (copies <= 0) {
      event.preventDefault(); // Prevent form submission
      alert("Please enter a valid number of copies.");
      return;
    }
  });
