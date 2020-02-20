package com.shop.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.app.model.EmployeeDTO;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
	Optional<EmployeeDTO> findByName(String name);
}
