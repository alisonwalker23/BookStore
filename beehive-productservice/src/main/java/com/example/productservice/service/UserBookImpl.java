package com.example.productservice.service;
import com.example.productservice.model.Book;
import com.example.productservice.model.User;
import com.example.productservice.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserBookImpl implements UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;



    @Override
    public User getUserModel(int id) {
        return userBookRepository.findById(id).get();
    }

    @Override
    public User userAddToBookList(int userId,Book book) {
        Optional<User> user = userBookRepository.findById(userId);
        if(user.isPresent()) {
            User user1 = user.get();
           user1.getBooks().add(book);
            return userBookRepository.save(user1);
        }
        return null;

    }


    @Override
    public User addUser(User user) {
        return userBookRepository.save(user);
    }
}
