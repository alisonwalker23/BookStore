package com.example.orderservice.repository;

import com.example.orderservice.model.BookOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends MongoRepository<BookOrder, Integer> {
}
