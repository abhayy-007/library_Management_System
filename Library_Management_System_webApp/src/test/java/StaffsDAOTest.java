import static org.junit.jupiter.api.Assertions.*;

import com.library.dao.StaffsDAO;
import com.library.model.StaffsModel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

class StaffsDAOTest {

    private StaffsDAO staffsDAO;

    @BeforeEach
    void setUp() {
        staffsDAO = new StaffsDAO();

        // Insert sample staff data into the database for testing
        try (Connection connection = staffsDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Staff (Staff_ID, Name, Password, Contact) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, "S001");
            preparedStatement.setString(2, "John Doe");
            preparedStatement.setString(3, "password123");
            preparedStatement.setString(4, "1234567890");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the Staff table after each test
        try (Connection connection = staffsDAO.getConnection()) {
            PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM Staff WHERE Staff_ID = ?");
            deleteStmt.setString(1, "S001");
            deleteStmt.executeUpdate();

            deleteStmt.setString(1, "S002");
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
