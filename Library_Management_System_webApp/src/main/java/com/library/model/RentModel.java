package com.library.model;

public class RentModel {
    private int rentID;
    private String bookID;
    private String borrowerName;
    private String borrowerContact;

    public RentModel() {
    }

    public RentModel(String bookID, String borrowerName, String borrowerContact) {
        this.bookID = bookID;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(String borrowerContact) {
        this.borrowerContact = borrowerContact;
    }
}
