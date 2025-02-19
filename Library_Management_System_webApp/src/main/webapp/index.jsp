<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Homepage</title>

    <link rel="stylesheet" href="project-7/homepage/hp.css" />

    <link
      href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
      rel="stylesheet"
    />

    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />
  </head>
  <body>
    <header class="container header">
      <nav class="nav">
        <div class="logo">
          <h2>Managekit.</h2>
        </div>

        <div class="nav_menu" id="nav_menu">
          <button class="close_btn" id="close_btn">
            <i class="ri-close-fill"></i>
          </button>

          <ul class="nav_menu_list">
            <li class="nav_menu_item">
              <a
                href="http://localhost:8081/Library_Management_System_webApp/login.jsp"
                class="nav_menu_link"
                >Create account</a
              >
            </li>
            <li class="nav_menu_item">
              <a href="#" class="nav_menu_link">about</a>
            </li>
            <li class="nav_menu_item">
              <a href="#" class="nav_menu_link">service</a>
            </li>
            <li class="nav_menu_item">
              <a href="#" class="nav_menu_link">contact</a>
            </li>
          </ul>
        </div>

        <button class="toggle_btn" id="toggle_btn">
          <i class="ri-menu-line"></i>
        </button>
      </nav>
    </header>

    <section class="wrapper">
      <div class="container">
        <div class="grid-cols-2">
          <div class="grid-item-1">
            <h1 class="main-heading">
              Welcome to <span>Managekit.</span> <br />
              Find books.
            </h1>
            <p class="info-text">
              Search your beloved author's books and rent the books.
            </p>

            <div class="btn_wrapper">
              <button class="btn view_more_btn">
                <a href="#">Explore<i class="ri-arrow-right-line"></i></a>
              </button>

              <button class="btn documentation_btn">documentation</button>
            </div>
          </div>
          <div class="grid-item-2">
            <div class="team_img_wrapper">
              <img
                src="https://img.freepik.com/free-vector/student-with-laptop-sitting-huge-books-library-male-character-studying-computer-flat-vector-illustration-online-education-knowledge-concept-banner-website-design-landing-web-page_74855-22550.jpg?t=st=1730043350~exp=1730046950~hmac=125af485ec41853a5c11e88c053e2c5e48f7ed4b1afd75cd9c899ac80f3f0d57&w=1060"
                alt="team-img"
              />
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="wrapper">
      <div class="container" data-aos="fade-up" data-aos-duration="1000">
        <div class="grid-cols-3">
          <div class="grid-col-item">
            <div class="icon">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
                />
              </svg>
            </div>
            <div class="featured_info">
              <span class="title">Built for Readers </span>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempore
                ratione facilis animi voluptas exercitationem molestiae.
              </p>
            </div>
          </div>
          <div class="grid-col-item">
            <div class="icon">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M17 14v6m-3-3h6M6 10h2a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v2a2 2 0 002 2zm10 0h2a2 2 0 002-2V6a2 2 0 00-2-2h-2a2 2 0 00-2 2v2a2 2 0 002 2zM6 20h2a2 2 0 002-2v-2a2 2 0 00-2-2H6a2 2 0 00-2 2v2a2 2 0 002 2z"
                />
              </svg>
            </div>
            <div class="featured_info">
              <span class="title">Designed to be manage</span>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Ut
                ipsum esse corrupti. Quo, labore debitis!
              </p>
            </div>
          </div>

          <div class="grid-col-item">
            <div class="icon">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <p>
                  stroke-linecap="round" stroke-linejoin="round"
                  stroke-width="2" d="M10 20l4-16m4 4l4 4-4 4M6 16l-4-4 4-4"
                </p>
              </svg>
            </div>
            <div class="featured_info">
              <span class="title">Documentation for everything</span>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Non
                nostrum voluptate totam ipsa corrupti vero!
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- ==== ANIMATE ON SCROLL JS CDN -->
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <!-- ==== GSAP CDN ==== -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.8.0/gsap.min.js"></script>
    <!-- ==== SCRIPT.JS ==== -->
    <script src="project-7/homepage/hp.js" defer></script>
  </body>
</html>
