package com.book.model;

public class Order {
	private int id;//primary key
	private int userId;//foreign key
	private int bookId;//foreign key
	private int quantity;
	private double total;
	private String orderDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Order(int id, int userId, int bookId, int quantity, double total, String orderDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.total = total;
		this.orderDate = orderDate;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
