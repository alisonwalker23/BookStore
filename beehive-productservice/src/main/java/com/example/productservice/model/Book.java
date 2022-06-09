package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document

public class Book {

    @Id
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
	private int quantity;
	private String authorName;


	public Book(){}

	public Book(int id, String title, String description, double price, String category, String image, int quantity, String authorName) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
		this.quantity = quantity;
		this.authorName = authorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {return quantity;}

	public void setQuantity(int quantity) {this.quantity = quantity;}

	public String getAuthorName() {return authorName;}

	public void setAuthorName(String authorName) {this.authorName = authorName;}
}
