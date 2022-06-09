package com.example.productservice.repository;


import com.example.productservice.model.Book;
import com.example.productservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository

public interface UserBookRepository extends MongoRepository<User, Integer> {

}
