package com.example.productservice.service;

import com.example.productservice.model.Book;
import com.example.productservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserBookService  {


    User getUserModel(int id);
    User userAddToBookList(int userId, Book book);

    User addUser(User user);


}




