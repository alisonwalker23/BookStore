package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@Document
public class User {
    @Id
    private int userId;


    private List<Book> books;

	public User(int userId, List<Book> books) {
		this.userId = userId;
		this.books = books;
	}

	public User(){}

	@Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", books=" + books +
                '}';
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
    
    
}



