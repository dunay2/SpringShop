package com.shop.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entities.Employee;
import com.shop.repositories.EmployeeRepository;
import com.shop.rest.exception.employee.EmployeeNotFoundException;

@RestController
@RequestMapping("/api/employees")
class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public ResponseEntity<List<Employee>> listAll() {
		List<Employee> employees = repository.findAll();
		if (employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	
	// Single item
	
	@GetMapping("/{id}")
	Employee getOne(@PathVariable Long id) {
		
		return repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException( id));
		
		
		//User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		//return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
		
	}


	@PutMapping("/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}

	@DeleteMapping("/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
