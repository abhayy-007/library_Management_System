package com.library.dao;

import com.library.model.UserModel;
import com.library.util.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final MongoCollection<Document> collection;

    public UserDAO() {
        collection = MongoDBManager.getInstance().getDatabase().getCollection("Users");
        collection.createIndex(new Document("User_ID", 1), new IndexOptions().unique(true));
    }

    public boolean insertUser(UserModel user) {
        try {
            Document doc = new Document()
                .append("User_ID", user.getUserID())
                .append("Name", user.getName())
                .append("Password", user.getPassword())
                .append("Contact", user.getContact());
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticate(String userID, String password) {
        Document doc = collection.find(Filters.and(
            Filters.eq("User_ID", userID),
            Filters.eq("Password", password)
        )).first();
        return doc != null;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        for (Document doc : collection.find()) {
            users.add(new UserModel(
                doc.getString("User_ID"),
                doc.getString("Name"),
                doc.getString("Password"),
                doc.getString("Contact")
            ));
        }
        return users;
    }

    public UserModel getUserById(String userID) {
        Document doc = collection.find(Filters.eq("User_ID", userID)).first();
        if (doc != null) {
            return new UserModel(
                doc.getString("User_ID"),
                doc.getString("Name"),
                doc.getString("Password"),
                doc.getString("Contact")
            );
        }
        return null;
    }
}
