package com.example.productservice.controller;


import com.example.productservice.exceptions.BookWithTheIDAlreadyPresentException;
import com.example.productservice.exceptions.BookWithTheIDNotPresentException;
import com.example.productservice.exceptions.UserWithTheIDAlreadyPresentException;
import com.example.productservice.model.Book;
import com.example.productservice.model.User;
import com.example.productservice.service.BookService;

import com.example.productservice.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserBookController {
    @Autowired
    private UserBookService userBookService;
    @Autowired
    private BookService bookService;



    @PostMapping("/user")
    public ResponseEntity<?> addUserHandler(@RequestBody User user) {
        System.out.println(user);
        ResponseEntity<?> responseEntity;
        System.out.println(userBookService);
        User newUser = userBookService.addUser(user);
        responseEntity = new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        System.out.println(newUser);

        return responseEntity;
    }

    @GetMapping("/user/{userId}/books/")
    public ResponseEntity<?> getAllOrdersForUserHandler(@PathVariable("userId") int id) {
        ResponseEntity<?> responseEntity;
        User user = userBookService.getUserModel(id);
        return new ResponseEntity<>(user.getBooks(), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/books")
    public ResponseEntity<?>addToUserBookListHandler(@PathVariable("userId") int id, @RequestBody Book book) throws BookWithTheIDAlreadyPresentException {
        ResponseEntity<?> responseEntity;
     return new ResponseEntity<>(userBookService.userAddToBookList(id, book), HttpStatus.OK);


    }



}
