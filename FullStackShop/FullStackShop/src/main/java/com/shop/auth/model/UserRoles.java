package com.shop.auth.model;

/** 
 * User authentication roles
 * Define JPA and Hibernate Entity
 * 
 */
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "ROLE")
public class UserRoles {
	@Id
	@GeneratedValue // (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	private String enabled;
}
