package com.shop.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.app.model.EmployeeDTO;
import com.shop.app.repository.EmployeeRepository;
import com.shop.rest.exception.employee.EmployeeNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@Slf4j
class EmployeeController implements IRestController<EmployeeDTO> {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<List<EmployeeDTO>> listAll() {
		List<EmployeeDTO> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<EmployeeDTO>>(HttpStatus.NO_CONTENT);
		}
		log.info("List of employee");
		return new ResponseEntity<List<EmployeeDTO>>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmployeeDTO> getOne(Long id) {
		Optional<EmployeeDTO> record = repository.findById(id);
		record.orElseThrow(() -> new EmployeeNotFoundException(id));
		log.info("found employee " + id);
		return new ResponseEntity<EmployeeDTO>(record.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmployeeDTO> deleteRecord(Long id) {
		Optional<EmployeeDTO> record = repository.findById(id);
		record.orElseThrow(() -> new EmployeeNotFoundException(id));
		repository.deleteById(id);
		log.info("Deleted employee " + id);
		return new ResponseEntity<EmployeeDTO>(record.get(), HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<EmployeeDTO> createRecord(EmployeeDTO record) {
		String name = record.getName();

		Optional<EmployeeDTO> employee = repository.findByName(name);
		if (!employee.isPresent()) {
			repository.save(record);
			log.info("Created employee " + record.getName());
			return new ResponseEntity<EmployeeDTO>(record, HttpStatus.CREATED);
		}

		return new ResponseEntity<EmployeeDTO>(record, HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<EmployeeDTO> updateRecord(Long id, EmployeeDTO record) {
		Optional<EmployeeDTO> currentEmployee = repository.findById(id);

		currentEmployee.orElseThrow(() -> new EmployeeNotFoundException(id));

		currentEmployee.get().setName(record.getName());
		currentEmployee.get().setRole(record.getRole());

		repository.saveAndFlush(currentEmployee.get());
		log.info("Updated employee " + id);
		return new ResponseEntity<EmployeeDTO>(currentEmployee.get(), HttpStatus.OK);

	}

}