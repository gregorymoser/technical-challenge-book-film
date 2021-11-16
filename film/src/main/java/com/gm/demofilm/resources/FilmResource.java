package com.gm.demofilm.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gm.demofilm.dto.FilmDTO;
import com.gm.demofilm.entities.Film;
import com.gm.demofilm.repositories.FilmRepository;
import com.gm.demofilm.services.FilmService;

@RestController
@RequestMapping(value = "/films")
public class FilmResource {

	@Autowired
	private FilmService service;

	@Autowired
	private FilmRepository repository;
	
	// read all
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<FilmDTO>> findAll() {
			List<Film> list = repository.findAll();
			List<FilmDTO> listDto = list.stream().map(obj -> new FilmDTO(obj)).collect(Collectors.toList()); 
			return ResponseEntity.ok().body(listDto);
		}

	// get by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Film> findById(@PathVariable Long id) {
		Film obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// get by title
		@RequestMapping(value="/title", method=RequestMethod.GET)
		public ResponseEntity<Film> find(@RequestParam(value="value") String title) {
			Film obj = service.findByTitle(title); 
			return ResponseEntity.ok().body(obj);
		}

	// create
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Film obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Film obj, @PathVariable Long id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<FilmDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="title") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Film> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<FilmDTO> listDto = list.map(obj -> new FilmDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}