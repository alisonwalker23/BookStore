package com.example.orderservice.service;
import com.example.orderservice.exceptions.BookOrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.BookOrder;
import com.example.orderservice.model.Cart;
import com.example.orderservice.repository.BookOrderRepository;
import com.example.orderservice.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOrderServiceImpl implements BookOrderService {

    @Autowired
    private BookOrderRepository bookOrderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<BookOrder> getAllBookOrders() {
        return bookOrderRepository.findAll();
    }

    @Override
    public BookOrder getBookOrderById(int id) throws BookOrderWithTheIDNotPresentException {
        Optional<BookOrder> optional = bookOrderRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } throw new BookOrderWithTheIDNotPresentException();
    }

    @Override
    public Cart addBookToCart(BookOrder book, int cartid) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findById(cartid);
        if(optional.isEmpty()) {
        Cart cart = new Cart();

        }
       Cart cart = optional.get();
       List<BookOrder> bookOrderList = cart.getBookOrders();
       bookOrderList.add(book);
       cart.setBookOrders(bookOrderList);
       return cart;
    }

    public List<BookOrder> getBooksFromCart(int cartid) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findById(cartid);
        if(optional.isEmpty()) {
            throw new CartWithTheIDNotPresentException();
        }
        Cart cart = optional.get();
        List<BookOrder> bookOrders = cart.getBookOrders();
        return bookOrders;
    }

    @Override
    public void deleteBookFromCart(int id) throws BookOrderWithTheIDNotPresentException {
        Optional<BookOrder> optional = bookOrderRepository.findById(id);
        if(optional.isPresent()) {
            bookOrderRepository.delete(optional.get());
        } throw new BookOrderWithTheIDNotPresentException();
    }


}
