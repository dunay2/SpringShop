package com.shop.auth.service;

import com.shop.auth.model.User;

/**
 * Provide interface service methods for registering an account
 * 
 * @author ashh412
 *
 */
public interface UserService {

	void save(User user);

	User findByUsername(String username);

}
