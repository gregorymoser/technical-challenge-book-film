package com.gm.demobook.dto;

import java.io.Serializable;

import com.gm.demobook.entities.Book;

public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String country;
	private String releaseDate;
	private String publisher;
	private String type;
	
	public BookDTO() {
		
	}
	
	public BookDTO(Book obj) {
		title = obj.getTitle();
		author = obj.getAuthor();
		country = obj.getCountry();
		releaseDate = obj.getReleaseDate();
		publisher = obj.getPublisher();
		type = obj.getType();
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
