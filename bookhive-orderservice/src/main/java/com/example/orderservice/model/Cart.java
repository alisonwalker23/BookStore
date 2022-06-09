package com.example.orderservice.model;

import java.util.List;

public class Cart {

    private int id;
    private List<BookOrder> bookOrders;
    private int userId;


    
    public Cart(int id, List<BookOrder> bookOrders, int userId) {
		super();
		this.id = id;
		this.bookOrders = bookOrders;
		this.userId = userId;
	}

	public Cart() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public List<BookOrder> getBookOrders() {
		return bookOrders;
	}

	public void setBookOrders(List<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", bookOrders=" + bookOrders + ", userId=" + userId + "]";
	}


}
