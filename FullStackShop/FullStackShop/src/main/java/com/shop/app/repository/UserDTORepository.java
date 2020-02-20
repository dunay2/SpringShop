package com.shop.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.app.model.UserDTO;

//@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Long> {
	Optional<UserDTO> findByName(String name);
}

