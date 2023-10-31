package com.example.BTL.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;



@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String author;
	private String category;
	private Date date;
	private int numPage;
	private int quantity;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	 @OneToMany(mappedBy = "book" ,cascade = CascadeType.ALL)
	    private List<Rating> rating;
	
	

	public Book(int id, String title, String author, String category, Date date, int numPage, int quantity,
			String image, List<Rating> rating) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.date = date;
		this.numPage = numPage;
		this.quantity = quantity;
		this.image = image;
		this.rating = rating;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	
	
	
}
