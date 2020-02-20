package com.shop.auth.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.auth.model.UserRoles; 

/**
 * Spring Data JPA Repository for user-roles authentication
 * Provides some CRUD operations
 * @return
 * 
 */
@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

}


