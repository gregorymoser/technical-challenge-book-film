package com.gm.demofilm.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gm.demofilm.entities.Book;

@Component
@FeignClient(name="demo-book", url="localos:8001", path = "/books")
public interface BookFeignClient {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Book> findById(@PathVariable Long id);
}
