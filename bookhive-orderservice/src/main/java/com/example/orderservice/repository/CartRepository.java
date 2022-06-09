package com.example.orderservice.repository;

import com.example.orderservice.model.Cart;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer> {
	
	Optional<Cart>findFirstCartByUserId(int id);
	
}
