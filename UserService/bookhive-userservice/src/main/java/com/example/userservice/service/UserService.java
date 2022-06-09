package com.example.userservice.service;

import com.example.userservice.exceptions.UserWithIDAlreadyPresentException;
import com.example.userservice.exceptions.UserWithIDNotPresentException;
import com.example.userservice.model.User;

public interface UserService {

    User getUserById(int id) throws UserWithIDNotPresentException;
    User addNewUser(User user) throws UserWithIDAlreadyPresentException;
    User updateUser(User user) throws UserWithIDNotPresentException;
    void deleteUser(int id) throws UserWithIDNotPresentException;

    boolean verifyUser(String username, String password);
    String generateToken(String username);

}
