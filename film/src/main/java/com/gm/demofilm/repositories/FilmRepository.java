package com.gm.demofilm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gm.demofilm.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	
	@Transactional(readOnly=true)
	Film findByTitle(String title);
}
