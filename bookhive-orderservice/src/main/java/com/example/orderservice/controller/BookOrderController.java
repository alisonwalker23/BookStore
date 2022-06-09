package com.example.orderservice.controller;

import com.example.orderservice.exceptions.BookOrderWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.BookOrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.BookOrder;
import com.example.orderservice.model.Cart;
import com.example.orderservice.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;

    @GetMapping("/bookorders")
    public ResponseEntity<List<BookOrder>> getAllBookOrdersHandler() {
        ResponseEntity<List<BookOrder>> responseEntity;
        List<BookOrder> bookOrders = bookOrderService.getAllBookOrders();
        responseEntity = new ResponseEntity<>(bookOrders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/bookorders/{bookId}")
    public ResponseEntity<?> getBookOrdersByIDHandler(@PathVariable("bookId") int id) {
        ResponseEntity<?> responseEntity;
        try {
            BookOrder bookOrder = bookOrderService.getBookOrderById(id);
            responseEntity = new ResponseEntity<>(bookOrder, HttpStatus.OK);
        } catch (BookOrderWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<>("Book Order with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }
    //get books from cart
    @GetMapping("/bookorders/cart/{cartId}")
    public ResponseEntity<?> getBooksFromCartHandler(@PathVariable("cartId") int id) {
        ResponseEntity<?> responseEntity;
        try {
            List<BookOrder> bookOrders = bookOrderService.getBooksFromCart(id);
            responseEntity = new ResponseEntity<>(bookOrders, HttpStatus.OK);
        } catch (CartWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<>("Cart with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }

    //add book to cart
    @PostMapping("/bookorders/cart/{cartid}")
    public ResponseEntity<?> addBookToCartHandler(@RequestParam("cartid") int id, @RequestBody BookOrder bookOrder) {
        ResponseEntity<?> responseEntity;
        try {
            Cart updatedCart = bookOrderService.addBookToCart(bookOrder,id);
            responseEntity = new ResponseEntity<Cart>(updatedCart, HttpStatus.CREATED);
        } catch (CartWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to add book to the cart: Cart not found", HttpStatus.CONFLICT);
        } return responseEntity;
    }

    @DeleteMapping("/bookOrders/{bookId}")
    public ResponseEntity<String> deleteBookOrderHandler(@PathVariable("bookId") int id) {
        ResponseEntity<String> responseEntity;
        try {
            bookOrderService.deleteBookFromCart(id);
            responseEntity = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch(BookOrderWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<>("Book Order with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }


    
}
