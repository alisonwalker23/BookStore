package com.example.orderservice.controller;


import com.example.orderservice.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.Cart;
import com.example.orderservice.service.CartService;

import org.apache.http.client.protocol.ResponseContentEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<?> getCartByIdHandler(@PathVariable("cartId") int id) {
        ResponseEntity<?> responseEntity;
        try {
            Cart cart = cartService.getCartById(id);
            responseEntity = new ResponseEntity<Cart>(cart, HttpStatus.OK);
        } catch (CartWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Cart with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }
    //create cart
    @PostMapping("/cart")
    public ResponseEntity<?> createCartHandler(@RequestBody Cart cart) {
        ResponseEntity<?> responseEntity;
        try {
            Cart newCart = cartService.createCart(cart);
            responseEntity = new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
        } catch (CartWithTheIDAlreadyPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to create a new cart: Duplicate Resource", HttpStatus.CONFLICT);
        } return responseEntity;
    }

    @PutMapping("/cart/{cartId}")
    public ResponseEntity<?> updateCartHandler(@PathVariable("cartId") int id, @RequestBody Cart cart) {
        ResponseEntity<?> responseEntity;
        try {
            cart.setId(id);
            cartService.updateCart(cart);
            responseEntity = new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
        } catch (CartWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Cart with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> deleteCartHandler(@PathVariable("cartId") int id) {
        ResponseEntity<String> responseEntity;
        try {
            cartService.deleteCart(id);
            responseEntity = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch(CartWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Cart with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }
    //Create or get user cart
    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<?> findFirstCartByUserIdHandler(@PathVariable("userId") int id){
    	ResponseEntity<?> responseEntity;
    	
    	try {
    		Cart cart = cartService.findFirstCartByUserId(id);
    		responseEntity = new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
    	}
    	catch (CartWithTheIDNotPresentException e) {
    	    responseEntity = new ResponseEntity<String>("Cart with that ID was not found", HttpStatus.NOT_FOUND);
    	} return responseEntity;
    }

}
