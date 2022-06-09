package com.example.orderservice.service;

import com.example.orderservice.exceptions.OrderWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.OrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.UserWithTheIDNotPresentException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersForUser(int userId) throws UserWithTheIDNotPresentException {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> userOrder = new ArrayList<>();
        for(Order order: allOrders) {
            if (order.getUserId() == userId) {
                userOrder.add(order);
            }
        } return userOrder;
    }

    @Override
    public Order getOrderById(int id) throws OrderWithTheIDNotPresentException {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } throw new OrderWithTheIDNotPresentException();
    }

    @Override
    public Order createOrder(Order order) throws OrderWithTheIDAlreadyPresentException {
        Optional<Order> optional = orderRepository.findById(order.getOrderId());
        if(optional.isEmpty()) {
            System.out.println("message");
            orderRepository.save(order);
            return order;
        } throw new OrderWithTheIDAlreadyPresentException();
    }

    @Override
    public void deleteOrder(int id) throws OrderWithTheIDNotPresentException {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()) {
            orderRepository.delete(optional.get());
        } throw new OrderWithTheIDNotPresentException();
    }

    @Override
    public Order updateOrder(Order order) throws OrderWithTheIDNotPresentException {
        Optional<Order> optional = orderRepository.findById(order.getOrderId());
        if(optional.isPresent()) {
            orderRepository.save(order);
            return optional.get();
        } throw new OrderWithTheIDNotPresentException();
    }

    @Override
    public Order updateOrderStatus(int id, String status) throws OrderWithTheIDNotPresentException {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()) {
            Order order = optional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return order;
        } throw new OrderWithTheIDNotPresentException();
    }


}
