package com.shop.auth.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.auth.model.User;
import com.shop.auth.repository.UserRepository;

/**
 * 
 * Implements UserDetailsService for login/authentication with Spring Security's
 * 
 * @author ashh412
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userAuthRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userAuthRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with user-name: " + username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		String roles = user.getRoles().stream().map(e -> e.toString()).reduce("", String::concat);
		authorities = AuthorityUtils.createAuthorityList(roles);
		return authorities;

	}
}
