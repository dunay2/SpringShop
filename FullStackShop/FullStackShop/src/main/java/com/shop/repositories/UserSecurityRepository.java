package com.shop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.entities.UserSecurity;


@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
public UserSecurity findByUsername(String username);
}