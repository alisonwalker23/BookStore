package com.example.productservice.controller;

import com.example.productservice.exceptions.BookWithTheIDAlreadyPresentException;
import com.example.productservice.exceptions.BookWithTheIDNotPresentException;
import com.example.productservice.model.Book;
import com.example.productservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getAllBooksHandler() {

        ResponseEntity<List<Book>> responseEntity;
        List<Book> books = bookService.getAllBooks();
        responseEntity = new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/books")
    public ResponseEntity<?> addBookHander(@RequestBody Book book) {

        ResponseEntity<?> responseEntity;
        try {
            Book newBook = bookService.addNewBook(book);
            responseEntity = new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
        } catch (BookWithTheIDAlreadyPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to store the book: Duplicate Resource", HttpStatus.CONFLICT);
        }

        return responseEntity;

    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookByIdHandler(@PathVariable("bookId") int id) {

        ResponseEntity<?> responseEntity;

        try {
            Book book = bookService.getBookById(id);
            responseEntity = new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (BookWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Book with the ID not found", HttpStatus.NOT_FOUND);
        }

        return responseEntity;

    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBookHandler(@PathVariable("bookId") int id, @RequestBody Book updatedBook) {
        ResponseEntity<?> responseEntity;
        try {
            updatedBook.setId(id);
            Book user = bookService.updateBook(updatedBook);
            responseEntity = new ResponseEntity<Book>(user, HttpStatus.OK);
            return responseEntity;
        } catch (BookWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Book with ID not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBookHandler(@PathVariable("bookId") int id) {
        ResponseEntity<?> responseEntity;
        try {
            Book book = bookService.getBookById(id);
            bookService.deleteBook(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted Book", HttpStatus.OK);
        } catch (BookWithTheIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("Book with ID not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("/books/category/{category_term}")
    public ResponseEntity<?> categoryHandler(@PathVariable("category_term") String category) {
        return bookService.getBooksByCategory(category);
    }



    }
