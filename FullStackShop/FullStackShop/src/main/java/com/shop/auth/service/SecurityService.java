package com.shop.auth.service;

/**
 * Provides current logged-in user and auto login user after registration
 * @author ashh412
 *
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}