package com.library.model;

import java.util.Date;

public class Transaction {

    private int id;
    private Book book;
    private User user;
    private Date borrowDate;
    private Date returnDate;
    private boolean isReturned;

    public Transaction() {}

    @Override
	public String toString() {
		return "Transaction [id=" + id + ", book=" + book + ", user=" + user + ", borrowDate=" + borrowDate
				+ ", returnDate=" + returnDate + ", isReturned=" + isReturned + "]";
	}

	public Transaction(Book book, User user, Date borrowDate) {
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.isReturned = false;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public Transaction(int id, Book book, User user, Date borrowDate, Date returnDate, boolean isReturned) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.isReturned = isReturned;
	}

    
}
