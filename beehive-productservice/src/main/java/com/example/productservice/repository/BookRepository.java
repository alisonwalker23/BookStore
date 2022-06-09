package com.example.productservice.repository;


import com.example.productservice.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository


public interface BookRepository extends MongoRepository<Book, Integer> {
    Optional<List<Book>> getBooksByCategory(String category);
}
