package com.library.model;

public class UserModel {
    private String userID;
    private String name;
    private String password;
    private String contact;

    public UserModel() {}
    public UserModel(String userID, String name, String password, String contact) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.contact = contact;
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
