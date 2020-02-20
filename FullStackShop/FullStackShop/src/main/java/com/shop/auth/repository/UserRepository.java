package com.shop.auth.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.auth.model.User; 

/**
 * Spring Data JPA Repository for user authentication
 * Provides some CRUD operations
 * @return
 * 
 */


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
public User findByUsername(String username);
}