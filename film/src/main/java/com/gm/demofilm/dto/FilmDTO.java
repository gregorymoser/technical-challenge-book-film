package com.gm.demofilm.dto;

import java.io.Serializable;

import com.gm.demofilm.entities.Film;

public class FilmDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String author; 
	private String country;
	private String releaseDate;
	private String cinematography;
	private String type;
	
	public FilmDTO() {
		
	}
	
	public FilmDTO(Film obj) {
		title = obj.getTitle();
		author = obj.getAuthor();
		country = obj.getCountry();
		releaseDate = obj.getReleaseDate();
		cinematography = obj.getCinematography();
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

	public String getCinematography() {
		return cinematography;
	}

	public void setCinematography(String cinematography) {
		this.cinematography = cinematography;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
