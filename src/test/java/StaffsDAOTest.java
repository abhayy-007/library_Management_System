import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import com.library.dao.StaffsDAO;
import com.library.model.StaffsModel;
import com.library.util.ConfigLoader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;

class StaffsDAOTest {

    private StaffsDAO staffsDAO;
    private MongoClient mongoClient;
    private String dbName;


    @BeforeEach
    void setUp() {
        staffsDAO = new StaffsDAO();
        dbName = ConfigLoader.get("mongodb.database", "lmsdb");
        mongoClient = MongoClients.create(ConfigLoader.get("mongodb.uri", "mongodb://localhost:27017"));
        mongoClient.getDatabase(dbName).getCollection("Staff")
                .insertOne(new Document()
                        .append("Staff_ID", "S001")
                        .append("Name", "John Doe")
                        .append("Password", "password123")
                        .append("Contact", "1234567890"));
    }

    @AfterEach
    void tearDown() {
        var collection = mongoClient.getDatabase(dbName).getCollection("Staff");
        collection.deleteOne(Filters.eq("Staff_ID", "S001"));
        collection.deleteOne(Filters.eq("Staff_ID", "S002"));
        mongoClient.close();
    }

    @Test
    void testAuthenticateSuccessful() {
        StaffsModel staff = new StaffsModel("S001", "John Doe", "password123", "1234567890");
        boolean isAuthenticated = staffsDAO.authenticate(staff);

        assertTrue(isAuthenticated, "Authentication should succeed with valid credentials.");
    }

    @Test
    void testAuthenticateFailure() {
        StaffsModel staff = new StaffsModel("S001", "John Doe", "wrongpassword", "1234567890");
        boolean isAuthenticated = staffsDAO.authenticate(staff);

        assertFalse(isAuthenticated, "Authentication should fail with invalid credentials.");
    }

    @Test
    void testInsertStaffSuccessful() {
        StaffsModel newStaff = new StaffsModel("S002", "Jane Smith", "securepass", "0987654321");
        boolean isInserted = staffsDAO.insertStaff(newStaff);

        assertTrue(isInserted, "Inserting a new staff record should succeed.");
    }

    @Test
    void testInsertStaffFailureDuplicateID() {
        StaffsModel duplicateStaff = new StaffsModel("S001", "Duplicate Name", "anotherpass", "1112223333");
        boolean isInserted = staffsDAO.insertStaff(duplicateStaff);

        assertFalse(isInserted, "Inserting a staff record with a duplicate ID should fail.");
    }

    @Test
    void testDeleteStaffSuccessful() {
        StaffsModel newStaff = new StaffsModel("S002", "Jane Smith", "securepass", "0987654321");
        staffsDAO.insertStaff(newStaff);

        boolean isDeleted = staffsDAO.deleteStaff("S002");

        assertTrue(isDeleted, "Deleting a staff record with a valid ID should succeed.");
    }

    @Test
    void testDeleteStaffFailureInvalidID() {
        boolean isDeleted = staffsDAO.deleteStaff("INVALID_ID");

        assertFalse(isDeleted, "Deleting a staff record with an invalid ID should fail.");
    }

    @Test
    void testSelectAllUsers() {
        List<StaffsModel> staffs = staffsDAO.selectAllUsers();

        assertNotNull(staffs, "The returned list of staffs should not be null.");
        assertTrue(staffs.size() > 0, "The returned list of staffs should contain at least one staff.");
    }
}
