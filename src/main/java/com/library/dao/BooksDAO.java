package com.library.dao;

import com.library.model.BooksModel;
import com.library.util.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    private final MongoCollection<Document> collection;

    public BooksDAO() {
        collection = MongoDBManager.getInstance().getDatabase().getCollection("Books");
    }

    public void insertBook(BooksModel book) {
        Document doc = new Document()
            .append("Book_ID", book.getBook_ID())
            .append("Category", book.getCategory())
            .append("Name", book.getName())
            .append("Author", book.getAuthor())
            .append("Copies", book.getCopies());
        collection.insertOne(doc);
    }

    public BooksModel selectBookByID(String bookID) {
        Document doc = collection.find(Filters.eq("Book_ID", bookID)).first();
        if (doc != null) {
            return new BooksModel(
                doc.getString("Book_ID"),
                doc.getString("Category"),
                doc.getString("Name"),
                doc.getString("Author"),
                doc.getInteger("Copies")
            );
        }
        return null;
    }

    public List<BooksModel> selectAllBooks() {
        List<BooksModel> books = new ArrayList<>();
        for (Document doc : collection.find()) {
            books.add(new BooksModel(
                doc.getString("Book_ID"),
                doc.getString("Category"),
                doc.getString("Name"),
                doc.getString("Author"),
                doc.getInteger("Copies")
            ));
        }
        return books;
    }

    public boolean deleteBook(String bookID) {
        var result = collection.deleteOne(Filters.eq("Book_ID", bookID));
        return result.getDeletedCount() > 0;
    }

    public boolean updateCopies(String bookID, int newCopies) {
        var result = collection.updateOne(
            Filters.eq("Book_ID", bookID),
            Updates.set("Copies", newCopies)
        );
        return result.getModifiedCount() > 0;
    }

    /** Alias kept for compatibility. */
    public List<BooksModel> selectAllUsers() {
        return selectAllBooks();
    }
}
