package com.gm.demofilm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gm.demofilm.entities.Film;
import com.gm.demofilm.feignclients.BookFeignClient;
import com.gm.demofilm.repositories.FilmRepository;
import com.gm.demofilm.services.exceptions.DataIntegrityException;
import com.gm.demofilm.services.exceptions.ObjectNotFoundException;

@Service
public class FilmService {
	
	@Autowired
	private BookFeignClient bookFeignClient;
	
	@Autowired
	private FilmRepository repository;
	
	// read - get
	public Film findById(Long id) {
		Optional<Film> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Film.class.getName()));
	}
	
	public List<Film> findAll(){
		return repository.findAll();
	}
	
	public Film findByTitle(String title) {
		Film obj = repository.findByTitle(title);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Object not found! Title: " + title + ", Type: " + Film.class.getName());
		}
		return obj;
	}
	
	// Feign
		public Film getBook(Long bookId) {
			com.gm.demofilm.entities.Book book = bookFeignClient.findById(bookId).getBody();
			return new Film(book.getId(), book.getTitle(), book.getAuthor(), book.getCountry(), book.getReleaseDate(), book.getPublisher(), book.getType());
		}

	// create - post
	public Film insert(Film obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	// update - put
	public Film update(Film obj) {
		findById(obj.getId());
		return repository.save(obj);
	}

	// delete
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a film");
		}
	}
	
	public Page<Film> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}