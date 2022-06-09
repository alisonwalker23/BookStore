package com.example.orderservice.service;

import com.example.orderservice.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.Cart;

public interface CartService {

    Cart getCartById(int id) throws CartWithTheIDNotPresentException;
    Cart createCart(Cart cart) throws CartWithTheIDAlreadyPresentException;
    void deleteCart(int id) throws CartWithTheIDNotPresentException;
    Cart updateCart(Cart cart) throws CartWithTheIDNotPresentException;
    Cart findFirstCartByUserId(int id) throws CartWithTheIDNotPresentException;
}
