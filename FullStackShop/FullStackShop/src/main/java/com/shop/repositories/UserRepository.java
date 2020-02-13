package com.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entities.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
}