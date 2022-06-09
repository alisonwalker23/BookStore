package com.example.userservice.controller;

import com.example.userservice.exceptions.UserWithIDAlreadyPresentException;
import com.example.userservice.exceptions.UserWithIDNotPresentException;
import com.example.userservice.model.LoginUser;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @GetMapping(value ="/gateway/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        ResponseEntity<?> responseEntity;
        try {
            User user = userService.getUserById(id);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (UserWithIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("User with ID not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping(value = "/gateway/users/register")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        ResponseEntity<?> responseEntity;
        try {
            User newUser = userService.addNewUser(user);
            responseEntity = new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        } catch (UserWithIDAlreadyPresentException e) {
            responseEntity = new ResponseEntity<String>("Failed to create user: Duplicate Resource", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/gateway/users/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginUser loginUser ){

        ResponseEntity<?> responseEntity;

        Map<String, String> tokenMap = new HashMap<>();

        boolean isUserValid = userService.verifyUser(loginUser.getUsername(),loginUser.getPassword());

        if(isUserValid) {
//
            String token = userService.generateToken(loginUser.getUsername());
            tokenMap.put("token", token);
            responseEntity = new ResponseEntity<Map<String, String>>(tokenMap,HttpStatus.OK);
        } else {
            tokenMap.clear();
            tokenMap.put("token", null);
            tokenMap.put("message", "Invalid User Credentials");
            responseEntity = new ResponseEntity<Map<String,String>>(tokenMap,HttpStatus.FORBIDDEN);
        }

        return responseEntity;
//                return forbidden response;

    }

    @PutMapping(value = "/gateway/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User updatedUser) {
        ResponseEntity<?> responseEntity;
        try {
            updatedUser.setId(id);
            User user = userService.updateUser(updatedUser);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (UserWithIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("User with ID not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/gateway/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        ResponseEntity<?> responseEntity;
        try {
            User user = userService.getUserById(id);
            userService.deleteUser(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (UserWithIDNotPresentException e) {
            responseEntity = new ResponseEntity<String>("User with ID not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("/gateway/users/isAuthenticated")
    public ResponseEntity<Map<String,Object>> verifyToken(@RequestHeader("Authorization") String authHeader){
        System.out.println("Request received");

        ResponseEntity<Map<String, Object>> responseEntity;
        HashMap<String, Object> map = new HashMap<>();
        map.clear();
        System.out.println(authHeader);
        String token = authHeader.split(" ")[1];
        try {
           Claims claim = Jwts.parser()
                    .setSigningKey("stackroute")
                    .parseClaimsJws(token)
                    .getBody();
            map.put("isAuthenticated", true);
            map.put("userid", claim.getSubject());
            responseEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);

        }catch(Exception e) {
            map.put("isAuthenticated", false);
            responseEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.FORBIDDEN);
        }

        return responseEntity;

    }

}
