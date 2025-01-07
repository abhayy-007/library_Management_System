document
  .getElementById("addBookForm")
  .addEventListener("submit", function (event) {
    const bookID = document.getElementById("bookID").value.trim();
    const category = document.getElementById("category").value.trim();
    const name = document.getElementById("name").value.trim();
    const author = document.getElementById("author").value.trim();
    const copies = document.getElementById("copies").value.trim();

    // Basic validation to check if any of the fields are empty
    if (!bookID || !category || !name || !author || !copies) {
      event.preventDefault(); // Prevent form submission
      alert("Please fill out all fields before submitting.");
    }

    // Check if the "copies" field is a valid positive number
    if (copies <= 0) {
      event.preventDefault(); // Prevent form submission
      alert("Please enter a valid number of copies.");
    }
  });
