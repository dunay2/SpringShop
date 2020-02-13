package com.shop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entities.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//User findByName(String name);
}