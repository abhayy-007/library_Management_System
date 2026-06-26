import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.AdminDAO;
import com.library.model.AdminModel;
import com.library.util.ConfigLoader;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import org.bson.Document;

class AdminDAOTest {

    private AdminDAO adminDAO;
    private MongoClient mongoClient;
    private String dbName;

    @BeforeEach
    void setUp() {
        adminDAO = new AdminDAO();
        dbName = ConfigLoader.get("mongodb.database", "lmsdb");
        mongoClient = MongoClients.create(ConfigLoader.get("mongodb.uri", "mongodb://localhost:27017"));
        mongoClient.getDatabase(dbName).getCollection("Admin")
            .insertOne(new Document("User_ID", "xyz@123").append("Name", "XYZ"));
    }

    @AfterEach
    void tearDown() {
        mongoClient.getDatabase(dbName).getCollection("Admin")
            .deleteOne(Filters.eq("User_ID", "xyz@123"));
        mongoClient.close();
    }

    @Test
    void testAuthenticateValidAdmin() {
        AdminModel admin = new AdminModel();
        admin.setUser_ID("xyz@123");
        admin.setName("XYZ");

        boolean isAuthenticated = adminDAO.authenticate(admin);
        assertTrue(isAuthenticated, "The admin should be authenticated with valid credentials.");
    }

    @Test
    void testAuthenticateInvalidAdmin() {
        AdminModel admin = new AdminModel();
        admin.setUser_ID("A002");
        admin.setName("Nonexistent User");

        boolean isAuthenticated = adminDAO.authenticate(admin);
        assertFalse(isAuthenticated, "The admin should not be authenticated with invalid credentials.");
    }

    @Test
    void testAuthenticateNullValues() {
        AdminModel admin = new AdminModel();
        admin.setUser_ID(null);
        admin.setName(null);

        boolean isAuthenticated = adminDAO.authenticate(admin);
        assertFalse(isAuthenticated, "The admin should not be authenticated with null values.");
    }

    @Test
    void testAuthenticateEmptyValues() {
        AdminModel admin = new AdminModel();
        admin.setUser_ID("");
        admin.setName("");

        boolean isAuthenticated = adminDAO.authenticate(admin);
        assertFalse(isAuthenticated, "The admin should not be authenticated with empty values.");
    }
}
