package com.shop.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.app.model.UserDTO;
import com.shop.app.repository.UserDTORepository;
import com.shop.rest.exception.Messages;
import com.shop.rest.exception.user.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
class UserController implements IRestController<UserDTO> {

	private final UserDTORepository repository;
	@Autowired
	private Messages message;
	private String errMsgUserNotFound = "error.user.notfound";

	UserController(UserDTORepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<List<UserDTO>> listAll() {
		List<UserDTO> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		log.info("List of users");
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<UserDTO> getOne(Long id) {
		Optional<UserDTO> record = repository.findById(id);
		record.orElseThrow(() -> new UserNotFoundException(message.get(errMsgUserNotFound) + id));
		return new ResponseEntity<UserDTO>(record.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> deleteRecord(Long id) {
		Optional<UserDTO> record = repository.findById(id);
		record.orElseThrow(() -> new UserNotFoundException(message.get(errMsgUserNotFound) + id));
		repository.deleteById(id);

		return new ResponseEntity<UserDTO>(record.get(), HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<UserDTO> createRecord(UserDTO record) {
		String name = record.getName();
		log.info("Creating User : {}", record);
		Optional<UserDTO> user = repository.findByName(name);
		if (user.isPresent()) {
			log.error("Unable to create User. A User with name {} already exist", record.getName());
			return new ResponseEntity<UserDTO>(record, HttpStatus.CONFLICT);
		}
		repository.save(record);
		log.info("Created user " + record.getName());
		return new ResponseEntity<UserDTO>(record, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDTO> updateRecord(Long id, UserDTO record) {
		Optional<UserDTO> currentUser = repository.findById(id);

		currentUser.orElseThrow(() -> new UserNotFoundException(message.get(errMsgUserNotFound) + id));

		currentUser.get().setName(record.getName());
		currentUser.get().setAddress(record.getAddress());
		currentUser.get().setEmail(record.getEmail());

		repository.saveAndFlush(currentUser.get());
		return new ResponseEntity<UserDTO>(currentUser.get(), HttpStatus.OK);

	}

}