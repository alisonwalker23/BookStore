package com.example.productservice.service;

import com.example.productservice.exceptions.BookWithTheIDAlreadyPresentException;
import com.example.productservice.exceptions.BookWithTheIDNotPresentException;
import com.example.productservice.model.Book;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id) throws BookWithTheIDNotPresentException;
    Book addNewBook(Book book) throws BookWithTheIDAlreadyPresentException;
    void deleteBook(int id) throws BookWithTheIDNotPresentException;
    Book updateBook(Book book) throws BookWithTheIDNotPresentException;
    ResponseEntity<?> getBooksByCategory(String category);


}
