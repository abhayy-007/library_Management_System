import static org.junit.jupiter.api.Assertions.*;

import com.library.dao.RentDAO;
import com.library.model.RentModel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class RentDAOTest {

    private RentDAO rentDAO;

    @BeforeEach
    void setUp() {
        rentDAO = new RentDAO();

        // Insert sample data into the Books table
        try (Connection connection = rentDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Books (Book_ID, Category, Name, Author, Copies) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, "B001");
            preparedStatement.setString(2, "Fiction");
            preparedStatement.setString(3, "The Great Gatsby");
            preparedStatement.setString(4, "F. Scott Fitzgerald");
            preparedStatement.setInt(5, 5); // Copies
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the Books and Rent tables after each test
        try (Connection connection = rentDAO.getConnection()) {
            PreparedStatement deleteBooksStmt = connection.prepareStatement("DELETE FROM Books WHERE Book_ID = ?");
            deleteBooksStmt.setString(1, "B001");
            deleteBooksStmt.executeUpdate();

            PreparedStatement deleteRentStmt = connection.prepareStatement("DELETE FROM Rent WHERE Book_ID = ?");
            deleteRentStmt.setString(1, "B001");
            deleteRentStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRentBookSuccessful() {
        RentModel rent = new RentModel();
        rent.setBookID("B001");
        rent.setBorrowerName("John Doe");
        rent.setBorrowerContact("1234567890");

        boolean result = rentDAO.rentBook(rent);

        assertTrue(result, "The book should be successfully rented.");
    }

    @Test
    void testRentBookWhenNoCopiesLeft() {
        // Set Copies to 0 for the book
        try (Connection connection = rentDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Books SET Copies = 0 WHERE Book_ID = ?")) {

            preparedStatement.setString(1, "B001");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RentModel rent = new RentModel();
        rent.setBookID("B001");
        rent.setBorrowerName("John Doe");
        rent.setBorrowerContact("1234567890");

        boolean result = rentDAO.rentBook(rent);

        assertFalse(result, "The book should not be rented when there are no copies left.");
    }

    @Test
    void testRentBookWithInvalidBookID() {
        RentModel rent = new RentModel();
        rent.setBookID("INVALID_ID");
        rent.setBorrowerName("John Doe");
        rent.setBorrowerContact("1234567890");

        boolean result = rentDAO.rentBook(rent);

        assertFalse(result, "The book should not be rented with an invalid Book_ID.");
    }

    @Test
    void testRentBookWithNullValues() {
        RentModel rent = new RentModel();
        rent.setBookID(null);
        rent.setBorrowerName(null);
        rent.setBorrowerContact(null);

        boolean result = rentDAO.rentBook(rent);

        assertFalse(result, "The book should not be rented when null values are provided.");
    }
}
