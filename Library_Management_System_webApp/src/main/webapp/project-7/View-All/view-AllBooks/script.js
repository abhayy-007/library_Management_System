// Function to filter the table based on the search input
function filterTable() {
  let input = document.getElementById("searchInput"); // Get the input field
  let filter = input.value.toUpperCase(); // Convert input to uppercase for case-insensitive comparison
  let table = document.getElementById("booksTable"); // Get the table
  let tr = table.getElementsByTagName("tr"); // Get all rows in the table

  // Loop through all table rows, and hide those who don't match the search query
  for (let i = 1; i < tr.length; i++) {
    let td = tr[i].getElementsByTagName("td");
    let match = false;

    // Check if any cell in the row matches the filter
    for (let j = 0; j < td.length; j++) {
      let txtValue = td[j].textContent || td[j].innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        match = true;
        break;
      }
    }

    // Hide the row if no match is found
    if (match) {
      tr[i].style.display = "";
    } else {
      tr[i].style.display = "none";
    }
  }
}
