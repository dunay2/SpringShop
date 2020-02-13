package com.shop.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.shop.entities.User;
import com.shop.repositories.UserRepository;
import com.shop.rest.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	private final UserRepository repository;

	UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// method to get user by id
	@GetMapping("/{id}")
	ResponseEntity<User> getOne(@PathVariable Long id) {
		Optional<User> user = repository.findById(id);
		user.orElseThrow(() -> new UserNotFoundException(id));

		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	// method to create an user
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody final User user) {
		// if (repository.findByName(user.getName()) != null) {
		// return new ResponseEntity<User>(
		// new CustomErrorType(
		// "Unable to create new user. A User with name " + user.getName() + " already
		// exist."),
		// HttpStatus.CONFLICT);
		// }
		repository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	// method to update an existing user
//	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> updateUser(
//	@PathVariable("id") final Long id, @RequestBody User user) {
//	User currentUser = repository.findById(id);
//	if (currentUser == null) {
//	return new ResponseEntity<User>(
//	new CustomErrorType("Unable to upate. User with id "
//	+ id + " not found."), HttpStatus.NOT_FOUND);
//	}

//	currentUser.setName(user.getName());
//	currentUser.setAddress(user.getAddress());
//	currentUser.setEmail(user.getEmail());
//	repository.saveAndFlush(currentUser);
//	return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//	}
	// delete an existing user
	// @DeleteMapping("/{id}")
	// public ResponseEntity<User> deleteUser(@PathVariable("id") final Long id) {
	// User user = repository.findById(id);
	// if (user == null) {
	// return new ResponseEntity<User>(
	// new CustomErrorType("Unable to delete. User with id "
	// + id + " not found."), HttpStatus.NOT_FOUND);
//	}
	// repository.deleteById(id);
	// return new ResponseEntity<User>(
	// new CustomErrorType("Deleted User with id "
	// + id + "."), HttpStatus.NO_CONTENT);
//}
}