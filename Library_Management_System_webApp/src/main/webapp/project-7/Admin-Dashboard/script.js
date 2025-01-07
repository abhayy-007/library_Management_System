// Optional: Display an alert when a link is clicked (for example)
document.querySelectorAll("a").forEach((link) => {
  link.addEventListener("click", function (e) {
    alert(`You clicked on the "${e.target.textContent}" link!`);
  });
});
