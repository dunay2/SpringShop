package com.shop.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entities.User;
import com.shop.repositories.UserRepository;
import com.shop.rest.exception.user.UserExistsException;
import com.shop.rest.exception.user.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
class UserController implements IRestController<User> {

	private final UserRepository repository;

	UserController(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<List<User>> listAll() {
		List<User> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		log.info("listado de usuarios");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<User> getOne(Long id) {
		Optional<User> record = repository.findById(id);
		record.orElseThrow(() -> new UserNotFoundException(id));
		return new ResponseEntity<User>(record.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> deleteRecord(Long id) {
		Optional<User> record = repository.findById(id);
		record.orElseThrow(() -> new UserNotFoundException(id));
		repository.deleteById(id);
		
		return new ResponseEntity<User>(record.get(), HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<User> createRecord(User record) {
		String userName = record.getName();
		Optional<User> user = repository.findByName(userName);
		user.orElseThrow(() -> new UserExistsException(userName));

		repository.save(record);

		return new ResponseEntity<User>(record, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<User> updateRecord(Long id, User record) {
		Optional<User> currentUser = repository.findById(id);

		currentUser.orElseThrow(() -> new UserNotFoundException(id));

		currentUser.get().setName(record.getName());
		currentUser.get().setAddress(record.getAddress());
		currentUser.get().setEmail(record.getEmail());
		
		repository.saveAndFlush(currentUser.get());
		return new ResponseEntity<User>(currentUser.get(), HttpStatus.OK);

	}

}