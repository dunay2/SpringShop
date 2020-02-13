package com.shop.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entities.Employee;
import com.shop.repositories.EmployeeRepository;
import com.shop.rest.exception.employee.EmployeeExistsException;
import com.shop.rest.exception.employee.EmployeeNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@Slf4j
class EmployeeController implements IRestController<Employee> {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<List<Employee>> listAll() {
		List<Employee> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		log.info("List of employee");
		return new ResponseEntity<List<Employee>>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> getOne(Long id) {
		Optional<Employee> record = repository.findById(id);
		record.orElseThrow(() -> new EmployeeNotFoundException(id));
		log.info("found employee " + id);
		return new ResponseEntity<Employee>(record.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> deleteRecord(Long id) {
		Optional<Employee> record = repository.findById(id);
		record.orElseThrow(() -> new EmployeeNotFoundException(id));
		repository.deleteById(id);
		log.info("Deleted employee " + id);
		return new ResponseEntity<Employee>(record.get(), HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Employee> createRecord(Employee record) {
		String name = record.getName();

		Optional<Employee> employee = repository.findByName(name);
		if (!employee.isPresent()) {
			repository.save(record);
			log.info("Created employee " + record.getName());
			return new ResponseEntity<Employee>(record, HttpStatus.CREATED);
		}

		return new ResponseEntity<Employee>(record, HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<Employee> updateRecord(Long id, Employee record) {
		Optional<Employee> currentEmployee = repository.findById(id);

		currentEmployee.orElseThrow(() -> new EmployeeNotFoundException(id));

		currentEmployee.get().setName(record.getName());
		currentEmployee.get().setRole(record.getRole());

		repository.saveAndFlush(currentEmployee.get());
		log.info("Updated employee " + id);
		return new ResponseEntity<Employee>(currentEmployee.get(), HttpStatus.OK);

	}

}