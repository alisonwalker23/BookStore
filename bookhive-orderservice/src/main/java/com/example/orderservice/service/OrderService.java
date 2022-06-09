package com.example.orderservice.service;

import com.example.orderservice.exceptions.OrderWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.OrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.UserWithTheIDNotPresentException;
import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    List<Order> getAllOrdersForUser(int userId) throws UserWithTheIDNotPresentException;
    Order getOrderById(int id) throws OrderWithTheIDNotPresentException;
    Order createOrder(Order order) throws OrderWithTheIDAlreadyPresentException;
    void deleteOrder(int id) throws OrderWithTheIDNotPresentException;
    Order updateOrder(Order order) throws OrderWithTheIDNotPresentException;
    Order updateOrderStatus(int id,String status) throws OrderWithTheIDNotPresentException;


}
