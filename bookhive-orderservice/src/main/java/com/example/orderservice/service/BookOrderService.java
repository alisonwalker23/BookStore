package com.example.orderservice.service;

import java.util.List;

import com.example.orderservice.exceptions.BookOrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.BookOrder;
import com.example.orderservice.model.Cart;

public interface BookOrderService {

    List<BookOrder> getAllBookOrders();
    BookOrder getBookOrderById(int id) throws BookOrderWithTheIDNotPresentException;
    Cart addBookToCart(BookOrder book, int cartid) throws CartWithTheIDNotPresentException;
    void deleteBookFromCart(int id) throws BookOrderWithTheIDNotPresentException;
    List<BookOrder> getBooksFromCart(int id) throws CartWithTheIDNotPresentException;
}
