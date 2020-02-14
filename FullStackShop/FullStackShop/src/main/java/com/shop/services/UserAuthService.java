package com.shop.services;

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
import com.shop.entities.UserSecurity;
import com.shop.repositories.UserSecurityRepository;

@Service
public class UserAuthService implements UserDetailsService {
	@Autowired
	private UserSecurityRepository userSecurityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSecurity user = userSecurityRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Opps! user not found with user-name: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(UserSecurity user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
		return authorities;
	}
}