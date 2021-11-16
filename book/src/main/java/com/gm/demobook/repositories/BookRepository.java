package com.gm.demobook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gm.demobook.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	@Transactional(readOnly=true)
	Book findByTitle(String title);
	
}