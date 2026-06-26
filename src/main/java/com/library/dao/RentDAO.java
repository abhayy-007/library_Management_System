package com.library.dao;

import com.library.util.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {

    private static final int RENTAL_DURATION_DAYS = 14;
    private static final int EXTENSION_DAYS = 7;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    private final MongoCollection<Document> booksCollection;
    private final MongoCollection<Document> rentCollection;

    public RentDAO() {
        MongoDatabase db = MongoDBManager.getInstance().getDatabase();
        booksCollection = db.getCollection("Books");
        rentCollection  = db.getCollection("Rent");
    }

    public boolean rentBook(String bookID, String userID, String userName, String userContact) {
        Bson filter = Filters.and(
            Filters.eq("Book_ID", bookID),
            Filters.gt("Copies", 0)
        );
        var updateResult = booksCollection.updateOne(filter, Updates.inc("Copies", -1));

        if (updateResult.getModifiedCount() > 0) {
            String rentDate = LocalDate.now().format(FMT);
            String dueDate = LocalDate.now().plusDays(RENTAL_DURATION_DAYS).format(FMT);

            Document rentDoc = new Document()
                .append("Book_ID", bookID)
                .append("Borrower_Name", userName)
                .append("Borrower_Contact", userContact)
                .append("User_ID", userID)
                .append("Rent_Date", rentDate)
                .append("Due_Date", dueDate)
                .append("Returned", false)
                .append("Return_Date", null)
                .append("Extension_Requested", false)
                .append("Extension_Status", null)
                .append("Extension_Request_Date", null);
            rentCollection.insertOne(rentDoc);
            return true;
        }
        return false;
    }

    public boolean returnBook(String rentID) {
        Document doc = rentCollection.find(Filters.eq("_id", new ObjectId(rentID))).first();
        if (doc == null) return false;

        String bookID = doc.getString("Book_ID");
        booksCollection.updateOne(Filters.eq("Book_ID", bookID), Updates.inc("Copies", 1));

        rentCollection.updateOne(
            Filters.eq("_id", new ObjectId(rentID)),
            Updates.combine(
                Updates.set("Returned", true),
                Updates.set("Return_Date", LocalDate.now().format(FMT))
            )
        );
        return true;
    }

    public List<Document> getRentalsByUser(String userID) {
        List<Document> results = new ArrayList<>();
        for (Document doc : rentCollection.find(Filters.eq("User_ID", userID)).sort(new Document("Rent_Date", -1))) {
            results.add(doc);
        }
        return results;
    }

    public List<Document> getAllRentals() {
        List<Document> results = new ArrayList<>();
        for (Document doc : rentCollection.find().sort(new Document("Rent_Date", -1))) {
            results.add(doc);
        }
        return results;
    }

    public List<Document> getPendingExtensions() {
        List<Document> results = new ArrayList<>();
        for (Document doc : rentCollection.find(Filters.eq("Extension_Status", "pending"))) {
            results.add(doc);
        }
        return results;
    }

    public boolean requestExtension(String rentID) {
        var result = rentCollection.updateOne(
            Filters.eq("_id", new ObjectId(rentID)),
            Updates.combine(
                Updates.set("Extension_Requested", true),
                Updates.set("Extension_Status", "pending"),
                Updates.set("Extension_Request_Date", LocalDate.now().format(FMT))
            )
        );
        return result.getModifiedCount() > 0;
    }

    public boolean handleExtension(String rentID, String action) {
        Document doc = rentCollection.find(Filters.eq("_id", new ObjectId(rentID))).first();
        if (doc == null) return false;

        if ("approve".equals(action)) {
            String currentDue = doc.getString("Due_Date");
            String newDue = LocalDate.parse(currentDue, FMT).plusDays(EXTENSION_DAYS).format(FMT);
            rentCollection.updateOne(
                Filters.eq("_id", new ObjectId(rentID)),
                Updates.combine(
                    Updates.set("Extension_Status", "approved"),
                    Updates.set("Due_Date", newDue)
                )
            );
        } else {
            rentCollection.updateOne(
                Filters.eq("_id", new ObjectId(rentID)),
                Updates.set("Extension_Status", "rejected")
            );
        }
        return true;
    }

    public String getBookName(String bookID) {
        Document doc = booksCollection.find(Filters.eq("Book_ID", bookID)).first();
        return doc != null ? doc.getString("Name") : "Unknown";
    }
}
