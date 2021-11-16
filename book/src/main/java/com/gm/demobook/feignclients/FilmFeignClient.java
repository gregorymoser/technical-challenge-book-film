package com.gm.demobook.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gm.demobook.entities.Film;

@Component
@FeignClient(name="demo-film", url="localos:8001", path = "/films")
public interface FilmFeignClient {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Film> findById(@PathVariable Long id);
}
