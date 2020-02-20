package com.shop.auth.service;

import com.shop.auth.model.User;
import com.shop.auth.repository.UserRepository;
import com.shop.auth.repository.UserRolesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

	
    @Autowired
    private UserRepository userAuthRepository;
    @Autowired
    private UserRolesRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userAuthRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userAuthRepository.findByUsername(username);
    }

}






