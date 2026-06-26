import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.BooksDAO;
import com.library.model.BooksModel;
import com.library.util.ConfigLoader;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import org.bson.Document;

class BooksDAOTest {

    private BooksDAO booksDAO;
    private MongoClient mongoClient;
    private String dbName;

    @BeforeEach
    void setUp() {
        booksDAO = new BooksDAO();
        dbName = ConfigLoader.get("mongodb.database", "lmsdb");
        mongoClient = MongoClients.create(ConfigLoader.get("mongodb.uri", "mongodb://localhost:27017"));
        var collection = mongoClient.getDatabase(dbName).getCollection("Books");

        collection.insertOne(new Document()
            .append("Book_ID", "B001")
            .append("Category", "Fiction")
            .append("Name", "The Great Gatsby")
            .append("Author", "F. Scott Fitzgerald")
            .append("Copies", 5));
        collection.insertOne(new Document()
            .append("Book_ID", "B002")
            .append("Category", "Science")
            .append("Name", "A Brief History of Time")
            .append("Author", "Stephen Hawking")
            .append("Copies", 3));
    }

    @AfterEach
    void tearDown() {
        var collection = mongoClient.getDatabase(dbName).getCollection("Books");
        collection.deleteOne(Filters.eq("Book_ID", "B001"));
        collection.deleteOne(Filters.eq("Book_ID", "B002"));
        mongoClient.close();
    }

    @Test
    void testInsertBook() {
        BooksModel book = new BooksModel("B003", "Drama", "Hamlet", "William Shakespeare", 4);
        booksDAO.insertBook(book);

        BooksModel retrievedBook = booksDAO.selectBookByID("B003");
        assertNotNull(retrievedBook);
        assertEquals("Hamlet", retrievedBook.getName());

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
        assertTrue(books.size() >= 2);
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
        assertTrue(books.size() >= 2);
    }
}
