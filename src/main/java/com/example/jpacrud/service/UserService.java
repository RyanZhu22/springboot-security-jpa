package com.example.jpacrud.service;

import com.example.jpacrud.entity.User;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);
    Optional<User> authenticateUser(String username, String password);
}
