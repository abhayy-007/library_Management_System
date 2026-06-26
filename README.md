# Library Management System

College project — web app for managing a library. Admins manage staff, staff manage books, and anyone can rent 'em.

## Features

- Login as Admin, Staff, or User (JWT-based)
- Register new admins and users
- Add / delete / view staff (admin only)
- Add / delete / update / view books (staff only)
- Rent books to borrowers
- Return books
- Request / approve book return extensions
- Logout

## Built With

- Java 25, Jakarta Servlet, JSP
- MongoDB Atlas (cloud)
- Maven, Tomcat embedded via Cargo plugin

## Before You Start — MongoDB Setup

This project uses MongoDB Atlas. If you're on a college/university WiFi (or any network that blocks DNS SRV lookups), the default `mongodb+srv://` connection string **will not work**. You need a direct connection string instead.

### How to get the right connection string

1. Log in to [MongoDB Atlas](https://cloud.mongodb.com)
2. Go to your cluster → **Connect** → **Drivers**
3. Under **Connection String**, pick the one that **does NOT have `+srv`** in it. It'll look something like this:

   ```
   mongodb://cluster0-shard-00-00.xxxxx.mongodb.net:27017,
              cluster0-shard-00-01.xxxxx.mongodb.net:27017,
              cluster0-shard-00-02.xxxxx.mongodb.net:27017
   ```

4. Add your credentials and query params at the end:

   ```
   mongodb://username:password@host1,host2,host3/?ssl=true&replicaSet=atlas-xxx-shard-0&authSource=admin&retryWrites=true
   ```

5. You also need a JWT secret — any base64-encoded string of at least 256 bits. You can generate one with:

   ```bash
   openssl rand -base64 32
   ```

### Set up your config files

Copy the example files (they have no real secrets) and fill in your own values:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
cp src/test/resources/application.properties.example src/test/resources/application.properties
```

Open both and replace the placeholders with your actual MongoDB URI and JWT secret.

## How to Run

```bash
# Make sure port 8090 is free
fuser -k 8090/tcp

# Build and start
mvn clean package cargo:run -DskipTests
```

Open: `http://localhost:8090/library_management_web_app/`

## Pages

| Page | What it does |
|------|-------------|
| `/` | Landing page, links to login/register |
| `register.jsp` | Register as admin or staff |
| `registerUser.jsp` | Register as a regular user |
| `login.jsp` | Login page |
| `adminLogin.jsp` | Separate login for admins |
| `userLogin.jsp` | Login for regular users |
| `adminDashboard.jsp` | Admin menu: add/view/delete staff, logout |
| `staffDashboard.jsp` | Staff menu: add/delete/update/view books, rent, logout |
| `userDashboard.jsp` | User dashboard |
| `addStaff.jsp` | Add a new staff member |
| `deleteStaff.jsp` | Delete staff by ID |
| `viewStaffs.jsp` | Table of all staff members |
| `addBook.jsp` | Add a new book |
| `deleteBook.jsp` | Delete book by ID |
| `updateBook.jsp` | Change number of copies |
| `viewBooks.jsp` | Table of all books |
| `rentBook.jsp` | Rent a book (enter borrower details) |
| `returnBook.jsp` | Return a rented book |
| `manageExtensions.jsp` | Manage book return extension requests |

## Project Structure

```
src/main/java/com/library/
├── controller/     → Servlet classes (Login, Register, Books, Rent, etc.)
├── dao/            → Database access (AdminDAO, StaffsDAO, BooksDAO, RentDAO, UserDAO)
├── model/          → POJOs (AdminModel, BooksModel, StaffsModel, UserModel)
└── util/           → Helpers (MongoDB connection, JWT, config loader, filter, lifecycle)
src/main/webapp/
├── *.jsp           → All the web pages
├── assets/         → Images
└── project-7/      → CSS and JS files
src/test/
├── java/           → JUnit tests
└── resources/      → Test config (application.properties)
```

## Database (MongoDB Atlas)

| Collection | Fields |
|-----------|--------|
| **Admin** | user ID, name, password, contact |
| **Staff** | staff ID, name, password, contact |
| **Books** | book ID, category, name, author, copies |
| **Rent** | rent ID, book ID, borrower name, borrower contact, rent date, due date, status |
| **Users** | user accounts for borrowers |

Connection settings live in `src/main/resources/application.properties`.

## Common Issues

| Problem | Fix |
|---------|-----|
| Port 8090 already in use | `fuser -k 8090/tcp` and try again |
| MongoDB connection fails | Double check your URI in `application.properties` — especially if you're on university WiFi, make sure you're using the non-`+srv` format |
| Wrong password on login | Caps lock? Or just re-register |
| `application.properties` not found | Did you copy the `.example` file? See setup above |
