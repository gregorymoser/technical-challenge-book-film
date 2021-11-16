package com.gm.demobook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gm.demobook.entities.Book;
import com.gm.demobook.entities.Film;
import com.gm.demobook.feignclients.FilmFeignClient;
import com.gm.demobook.repositories.BookRepository;
import com.gm.demobook.services.exceptions.DataIntegrityException;
import com.gm.demobook.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private FilmFeignClient filmFeignClient;
	
	@Autowired
	private BookRepository repository;
	
	// read - get
	public Book findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Book.class.getName()));
	}
	
	public List<Book> findAll(){
		return repository.findAll();
	}
	
	public Book findByTitle(String title) {
		Book obj = repository.findByTitle(title);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Object not found! Title: " + title + ", Type: " + Book.class.getName());
		}
		return obj;
	}
	
	// Feign
	public Book getFilm(Long filmId) {
		Film film = filmFeignClient.findById(filmId).getBody();
		return new Book(film.getId(), film.getTitle(), film.getAuthor(), film.getCountry(), film.getReleaseDate(), film.getCinematography(), film.getType());
	}

	// create - post
	public Book insert(Book obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	// update - put
	public Book update(Book obj) {
		findById(obj.getId());
		return repository.save(obj);
	}

	// delete
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a book");
		}
	}
	
	public Page<Book> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}