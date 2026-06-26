package com.library.dao;

import com.library.model.AdminModel;
import com.library.util.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

public class AdminDAO {

    private final MongoCollection<Document> collection;

    public AdminDAO() {
        collection = MongoDBManager.getInstance().getDatabase().getCollection("Admin");
        collection.createIndex(new Document("User_ID", 1), new IndexOptions().unique(true));
    }

    public boolean insertAdmin(AdminModel admin) {
        Document doc = new Document()
            .append("User_ID", admin.getUser_ID())
            .append("Name", admin.getName())
            .append("Password", admin.getPassword())
            .append("Contact", admin.getContact());
        try {
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticate(AdminModel admin) {
        Document doc = collection.find(Filters.and(
            Filters.eq("User_ID", admin.getUser_ID()),
            Filters.eq("Password", admin.getPassword())
        )).first();
        return doc != null;
    }
}
