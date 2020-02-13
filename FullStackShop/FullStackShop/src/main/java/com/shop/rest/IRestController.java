package com.shop.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IRestController<T> {
	@GetMapping("/")
	public ResponseEntity<List<T>> listAll();
	
	@GetMapping("/{id}")
	ResponseEntity<T> getOne(@PathVariable Long id);
		
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> createRecord(@RequestBody final T record);
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> updateRecord(	@PathVariable("id") final Long id, @RequestBody T record);

	 @DeleteMapping("/{id}")
	 public ResponseEntity<T> deleteRecord(@PathVariable("id") final Long id);
}
