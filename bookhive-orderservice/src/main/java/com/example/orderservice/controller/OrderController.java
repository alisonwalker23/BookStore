package com.example.orderservice.controller;


import com.example.orderservice.exceptions.OrderWithTheIDAlreadyPresentException;
import com.example.orderservice.exceptions.OrderWithTheIDNotPresentException;
import com.example.orderservice.exceptions.UserWithTheIDNotPresentException;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrdersHandler(){
        ResponseEntity<List<Order>> responseEntity;
        List<Order> orders = orderService.getAllOrders();
        responseEntity = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("users/{id}/orders")
    public ResponseEntity<?> getAllOrdersForUserHandler(@PathVariable("id") int id) {
        ResponseEntity<?> responseEntity;
        List<Order> orders = orderService.getAllOrders();
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId() == id) {
                userOrders.add(order);
            }
        }
        responseEntity = new ResponseEntity<List<Order>>(userOrders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrdersById(@PathVariable("orderId")int id) {
        ResponseEntity<?> responseEntity;
        try {
            Order order = orderService.getOrderById(id);
            responseEntity = new ResponseEntity<Order>(order, HttpStatus.OK);
        } catch (OrderWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<>("Order with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrderHandler(@RequestBody Order order) {
        ResponseEntity<?> responseEntity;
        System.out.println(order);
        try {
            Order newOrder = orderService.createOrder(order);
            responseEntity = new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (OrderWithTheIDAlreadyPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to create a new order: Duplicate Resource", HttpStatus.CONFLICT);
        } return responseEntity;
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<?> updateOrderHandler(@PathVariable("orderId") int id, @RequestBody Order order) {
        ResponseEntity<?> responseEntity;
        try {
            order.setOrderId(id);
            orderService.updateOrder(order);
            responseEntity = new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
        } catch(OrderWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Order with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrderHandler(@PathVariable("orderId") int id) {
        ResponseEntity<String> responseEntity;
        try {
            orderService.deleteOrder(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(OrderWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<>("Order with that ID was not found", HttpStatus.NOT_FOUND);
        } return responseEntity;
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<?> updateOrderStatusHandler(@PathVariable("orderId") int id, @RequestBody String status) throws OrderWithTheIDNotPresentException {
        ResponseEntity<?> responseEntity;
        orderService.updateOrderStatus(id, status);
        responseEntity = new ResponseEntity<>("Order status updated", HttpStatus.ACCEPTED);
        return responseEntity;

    }

}
