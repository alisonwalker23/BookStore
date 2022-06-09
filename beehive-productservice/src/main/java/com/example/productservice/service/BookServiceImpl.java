package com.example.productservice.service;

import com.example.productservice.exceptions.BookWithTheIDAlreadyPresentException;
import com.example.productservice.exceptions.BookWithTheIDNotPresentException;
import com.example.productservice.model.Book;
import com.example.productservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) throws BookWithTheIDNotPresentException {

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BookWithTheIDNotPresentException();
    }

    @Override
    public Book addNewBook(Book book) throws BookWithTheIDAlreadyPresentException {
        Optional<Book> optional = bookRepository.findById(book.getId());

        if (optional.isEmpty()) {
            bookRepository.save(book);
            return book;
        }
        throw new BookWithTheIDAlreadyPresentException();
    }

    @Override
    public void deleteBook(int id) throws BookWithTheIDNotPresentException {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            bookRepository.deleteById(id);
        }
        throw new BookWithTheIDNotPresentException();
    }

    @Override
    public Book updateBook(Book book) throws BookWithTheIDNotPresentException {
        Optional<Book> optional = bookRepository.findById(book.getId());
        if (optional.isPresent()) {
            bookRepository.save(book);
            return optional.get();
        }
        throw new BookWithTheIDNotPresentException();
    }

    public ResponseEntity<?> getBooksByCategory(String category) {
        Optional<List<Book>> optional = bookRepository.getBooksByCategory(category);
        if (optional.isPresent()) {
            if (optional.get().isEmpty()) {
                return ResponseEntity.ok("No books found with the category: " + category);
            }
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
};
