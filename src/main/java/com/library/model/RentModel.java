package com.library.model;

public class RentModel {
    private int rentID;
    private String bookID;
    private String borrowerName;
    private String borrowerContact;
    private String userID;
    private String rentDate;
    private String dueDate;
    private boolean returned;
    private String returnDate;
    private boolean extensionRequested;
    private String extensionStatus;
    private String extensionRequestDate;

    public RentModel() {}

    public RentModel(String bookID, String borrowerName, String borrowerContact, String userID, String rentDate, String dueDate) {
        this.bookID = bookID;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
        this.userID = userID;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public int getRentID() { return rentID; }
    public void setRentID(int rentID) { this.rentID = rentID; }
    public String getBookID() { return bookID; }
    public void setBookID(String bookID) { this.bookID = bookID; }
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    public String getBorrowerContact() { return borrowerContact; }
    public void setBorrowerContact(String borrowerContact) { this.borrowerContact = borrowerContact; }
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getRentDate() { return rentDate; }
    public void setRentDate(String rentDate) { this.rentDate = rentDate; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public boolean isExtensionRequested() { return extensionRequested; }
    public void setExtensionRequested(boolean extensionRequested) { this.extensionRequested = extensionRequested; }
    public String getExtensionStatus() { return extensionStatus; }
    public void setExtensionStatus(String extensionStatus) { this.extensionStatus = extensionStatus; }
    public String getExtensionRequestDate() { return extensionRequestDate; }
    public void setExtensionRequestDate(String extensionRequestDate) { this.extensionRequestDate = extensionRequestDate; }
}
