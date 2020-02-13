package com.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByName(String name);
}
