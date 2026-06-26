import static org.junit.jupiter.api.Assertions.*;

import com.library.dao.RentDAO;
import com.library.util.ConfigLoader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

class RentDAOTest {

    private RentDAO rentDAO;
    private MongoClient mongoClient;
    private String dbName;

    @BeforeEach
    void setUp() {
        rentDAO = new RentDAO();
        dbName = ConfigLoader.get("mongodb.database", "lmsdb");
        mongoClient = MongoClients.create(ConfigLoader.get("mongodb.uri", "mongodb://localhost:27017"));
        mongoClient.getDatabase(dbName).getCollection("Books")
            .insertOne(new Document()
                .append("Book_ID", "B001")
                .append("Category", "Fiction")
                .append("Name", "The Great Gatsby")
                .append("Author", "F. Scott Fitzgerald")
                .append("Copies", 5));
    }

    @AfterEach
    void tearDown() {
        var booksCollection = mongoClient.getDatabase(dbName).getCollection("Books");
        var rentCollection = mongoClient.getDatabase(dbName).getCollection("Rent");
        booksCollection.deleteOne(Filters.eq("Book_ID", "B001"));
        rentCollection.deleteMany(Filters.eq("Book_ID", "B001"));
        mongoClient.close();
    }

    @Test
    void testRentBookSuccessful() {
        boolean result = rentDAO.rentBook("B001", "U001", "John Doe", "1234567890");
        assertTrue(result, "The book should be successfully rented.");
    }

    @Test
    void testRentBookWhenNoCopiesLeft() {
        mongoClient.getDatabase(dbName).getCollection("Books")
            .updateOne(Filters.eq("Book_ID", "B001"), Updates.set("Copies", 0));

        boolean result = rentDAO.rentBook("B001", "U001", "John Doe", "1234567890");
        assertFalse(result, "The book should not be rented when there are no copies left.");
    }

    @Test
    void testRentBookWithInvalidBookID() {
        boolean result = rentDAO.rentBook("INVALID_ID", "U001", "John Doe", "1234567890");
        assertFalse(result, "The book should not be rented with an invalid Book_ID.");
    }

    @Test
    void testReturnBook() {
        rentDAO.rentBook("B001", "U001", "John Doe", "1234567890");

        Document rentDoc = mongoClient.getDatabase(dbName).getCollection("Rent")
            .find(Filters.eq("Book_ID", "B001")).first();
        String rentID = rentDoc.getObjectId("_id").toString();

        boolean result = rentDAO.returnBook(rentID);
        assertTrue(result, "The book should be returned successfully.");

        Document updated = mongoClient.getDatabase(dbName).getCollection("Rent")
            .find(Filters.eq("_id", rentDoc.getObjectId("_id"))).first();
        assertTrue(updated.getBoolean("Returned"));
        assertNotNull(updated.getString("Return_Date"));
    }

    @Test
    void testRequestAndApproveExtension() {
        rentDAO.rentBook("B001", "U001", "John Doe", "1234567890");

        Document rentDoc = mongoClient.getDatabase(dbName).getCollection("Rent")
            .find(Filters.eq("Book_ID", "B001")).first();
        String rentID = rentDoc.getObjectId("_id").toString();

        assertTrue(rentDAO.requestExtension(rentID));

        Document afterRequest = mongoClient.getDatabase(dbName).getCollection("Rent")
            .find(Filters.eq("_id", rentDoc.getObjectId("_id"))).first();
        assertEquals("pending", afterRequest.getString("Extension_Status"));

        assertTrue(rentDAO.handleExtension(rentID, "approve"));

        Document afterApprove = mongoClient.getDatabase(dbName).getCollection("Rent")
            .find(Filters.eq("_id", rentDoc.getObjectId("_id"))).first();
        assertEquals("approved", afterApprove.getString("Extension_Status"));
    }
}
