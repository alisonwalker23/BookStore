package com.example.orderservice.service;

import com.example.orderservice.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.CartWithTheIDNotPresentException;
import com.example.orderservice.model.BookOrder;
import com.example.orderservice.model.Cart;
import com.example.orderservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartById(int id) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } throw new CartWithTheIDNotPresentException();
    }

    @Override
    public Cart createCart(Cart cart) throws CartWithTheIDAlreadyPresentException {
        Optional<Cart> optional = cartRepository.findById(cart.getId());
        if(optional.isEmpty()) {
            cartRepository.save(cart);
            return cart;
        } throw new CartWithTheIDAlreadyPresentException();
    }

    @Override
    public void deleteCart(int id) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findById(id);
        if(optional.isPresent()) {
            cartRepository.delete(optional.get());
        } throw new CartWithTheIDNotPresentException();
    }

    @Override
    public Cart updateCart(Cart cart) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findById(cart.getId());
        if(optional.isPresent()) {
            cartRepository.save(cart);
            return optional.get();
        }
        throw new CartWithTheIDNotPresentException();
    }
    
    public Cart findFirstCartByUserId(int userid) throws CartWithTheIDNotPresentException {
        Optional<Cart> optional = cartRepository.findFirstCartByUserId(userid);
        if(optional.isPresent()) {
            return optional.get();
        } 
        Cart newCart = new Cart();
        newCart.setUserId(userid);
        newCart.setBookOrders(new ArrayList<BookOrder>());
        newCart.setId(userid);
        cartRepository.save(newCart);
        return newCart;
    }



}
