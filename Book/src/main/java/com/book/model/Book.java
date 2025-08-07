package com.book.model;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private double price;
	private String isbn;
	private String publisher;
	private String publicationDate;

	public Book(int id, String title, String author, double price, String isbn, String publisher,
			String publicationDate) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
	}
	public Book() {
		super();
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	

}
