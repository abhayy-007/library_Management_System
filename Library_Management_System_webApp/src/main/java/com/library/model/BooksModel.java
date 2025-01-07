package com.library.model;

public class BooksModel {
	
	private String Book_ID;
    private String Category;
    private String Name;
    private String Author;
    private int Copies;
    
	public BooksModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BooksModel(String book_ID, String category, String name, String author, int copies) {
		super();
		Book_ID = book_ID;
		Category = category;
		Name = name;
		Author = author;
		Copies = copies;
	}

	public String getBook_ID() {
		return Book_ID;
	}

	public void setBook_ID(String book_ID) {
		Book_ID = book_ID;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getCopies() {
		return Copies;
	}

	public void setCopies(int copies) {
		Copies = copies;
	}

	@Override
	public String toString() {
		return "BooksModel [Book_ID=" + Book_ID + ", Category=" + Category + ", Name=" + Name + ", Author=" + Author
				+ ", Copies=" + Copies + "]";
	}
	
}
