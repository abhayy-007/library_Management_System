package com.library.dao;

import com.library.model.StaffsModel;
import com.library.util.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class StaffsDAO {

    private final MongoCollection<Document> collection;

    public StaffsDAO() {
        collection = MongoDBManager.getInstance().getDatabase().getCollection("Staff");
        collection.createIndex(new Document("Staff_ID", 1), new IndexOptions().unique(true));
    }

    public boolean authenticate(StaffsModel staff) {
        Document doc = collection.find(Filters.and(
            Filters.eq("Staff_ID", staff.getStaff_ID()),
            Filters.eq("Password", staff.getPassword())
        )).first();
        return doc != null;
    }

    public boolean insertStaff(StaffsModel staff) {
        Document doc = new Document()
            .append("Staff_ID", staff.getStaff_ID())
            .append("Name", staff.getName())
            .append("Password", staff.getPassword())
            .append("Contact", staff.getContact());
        try {
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStaff(String staffID) {
        var result = collection.deleteOne(Filters.eq("Staff_ID", staffID));
        return result.getDeletedCount() > 0;
    }

    public List<StaffsModel> selectAllUsers() {
        List<StaffsModel> staffs = new ArrayList<>();
        for (Document doc : collection.find()) {
            staffs.add(new StaffsModel(
                doc.getString("Staff_ID"),
                doc.getString("Name"),
                doc.getString("Password"),
                doc.getString("Contact")
            ));
        }
        return staffs;
    }
}
