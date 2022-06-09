package com.example.userservice.service;

import com.example.userservice.exceptions.UserWithIDAlreadyPresentException;
import com.example.userservice.exceptions.UserWithIDNotPresentException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int id) throws UserWithIDNotPresentException {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new UserWithIDNotPresentException();
    }

    @Override
    public User addNewUser(User user) throws UserWithIDAlreadyPresentException {
        Optional<User> optional = userRepository.findById(user.getId());
        Optional<User> optionalUserName = userRepository.findByUsername(user.getUsername());
        if (optional.isEmpty() && optionalUserName.isEmpty()) {
            userRepository.save(user);
            return user;
        }
        throw new UserWithIDAlreadyPresentException();
    }

    @Override
    public User updateUser(User user) throws UserWithIDNotPresentException {
        Optional<User> optional = userRepository.findById(user.getId());
        if (optional.isPresent()) {
            userRepository.save(user);
            return optional.get();
        }
        throw new UserWithIDNotPresentException();
    }

    @Override
    public void deleteUser(int id) throws UserWithIDNotPresentException {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.deleteById(id);
        }
        throw new UserWithIDNotPresentException();
    }

    public boolean verifyUser(String username, String password) {
        // TODO Auto-generated method stub
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }

    public String generateToken(String username) {
        
        Optional<User> optional = userRepository.findByUsername(username);
        String jwtToken;
        jwtToken = Jwts.builder()
                .setSubject(Integer.toString(optional.get().getId()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "stackroute")
                .compact();

        return jwtToken;
    }


}
