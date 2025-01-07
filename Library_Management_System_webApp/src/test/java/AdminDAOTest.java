import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.AdminDAO;
import com.library.model.AdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class AdminDAOTest {

    private AdminDAO adminDAO;

    @BeforeEach
    void setUp() {
        adminDAO = new AdminDAO();

        // Insert sample data into the Admin table for testing
        try (Connection connection = adminDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Admin (User_ID, Name) VALUES (?, ?)")) {

            preparedStatement.setString(1, "xyz@123");
            preparedStatement.setString(2, "XYZ");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the Admin table after each test
        try (Connection connection = adminDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Admin WHERE User_ID = ?")) {

            preparedStatement.setString(1, "A001");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
