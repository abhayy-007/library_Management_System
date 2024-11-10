package com.library.model;

public class Book {

    private int id;

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int quantity;

    public Book() {}

    public Book(String title, String author, String isbn, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.quantity = quantity;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book(int id, String title, String author, String isbn, String genre, int quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", genre=" + genre
				+ ", quantity=" + quantity + "]";
	}

   
}