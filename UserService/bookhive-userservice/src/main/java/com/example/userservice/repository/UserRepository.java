package com.example.userservice.repository;

import com.example.userservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<Object> findByUsernameAndPassword(String username, String password);
    
    Optional<User> findByUsername(String username);
    
//    public Optional<User> findByUsernameAndPassword(String username, String password);
}
