import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.BooksDAO;
import com.library.model.BooksModel;

class BooksDAOTest {

    private BooksDAO booksDAO;

    @BeforeEach
    void setUp() {
        booksDAO = new BooksDAO();

        // Insert some sample data into the database before each test
        booksDAO.insertBook(new BooksModel("B001", "Fiction", "The Great Gatsby", "F. Scott Fitzgerald", 5));
        booksDAO.insertBook(new BooksModel("B002", "Science", "A Brief History of Time", "Stephen Hawking", 3));
    }

    @AfterEach
    void tearDown() {
        // Clean up the database after each test
        booksDAO.deleteBook("B001");
        booksDAO.deleteBook("B002");
    }

    @Test
    void testInsertBook() {
        BooksModel book = new BooksModel("B003", "Drama", "Hamlet", "William Shakespeare", 4);
        booksDAO.insertBook(book);

        BooksModel retrievedBook = booksDAO.selectBookByID("B003");
        assertNotNull(retrievedBook);
        assertEquals("Hamlet", retrievedBook.getName());

        // Cleanup
        booksDAO.deleteBook("B003");
    }

    @Test
    void testSelectBookByID() {
        BooksModel book = booksDAO.selectBookByID("B001");
        assertNotNull(book);
        assertEquals("Fiction", book.getCategory());
        assertEquals("The Great Gatsby", book.getName());
    }

    @Test
    void testSelectAllBooks() {
        List<BooksModel> books = booksDAO.selectAllBooks();
        assertNotNull(books);
        assertTrue(books.size() >= 2); // At least 2 books should be present
    }

    @Test
    void testDeleteBook() {
        boolean isDeleted = booksDAO.deleteBook("B002");
        assertTrue(isDeleted);

        BooksModel book = booksDAO.selectBookByID("B002");
        assertNull(book);
    }

    @Test
    void testUpdateCopies() {
        boolean isUpdated = booksDAO.updateCopies("B001", 10);
        assertTrue(isUpdated);

        BooksModel book = booksDAO.selectBookByID("B001");
        assertNotNull(book);
        assertEquals(10, book.getCopies());
    }

    @Test
    void testSelectAllUsers() {
        List<BooksModel> books = booksDAO.selectAllUsers();
        assertNotNull(books);
        assertTrue(books.size() >= 2); // At least 2 books should be present
    }
}
