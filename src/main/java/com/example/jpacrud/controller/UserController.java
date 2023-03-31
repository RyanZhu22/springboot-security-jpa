package com.example.jpacrud.controller;


import com.example.jpacrud.entity.User;
import com.example.jpacrud.enums.ResponseStatus;
import com.example.jpacrud.repository.UserRepository;
import com.example.jpacrud.service.UserService;
import com.example.jpacrud.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<Result<User>> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return createResponseEntity(ResponseStatus.CREATED, HttpStatus.CREATED, registeredUser);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Result<User>> loginUser(@RequestBody User loginUser) {
        Optional<User> authenticatedUser = userService.authenticateUser(loginUser.getUsername(), loginUser.getPassword());
        return authenticatedUser.map(user -> createResponseEntity(ResponseStatus.SUCCESS, HttpStatus.OK, user))
                .orElseGet(() -> createResponseEntity(ResponseStatus.NOT_FOUND, HttpStatus.NOT_FOUND, null));
    }

    private <T> ResponseEntity<Result<T>> createResponseEntity(ResponseStatus status, HttpStatus httpStatus, T data) {
        Result<T> result = new Result<>(status, data);
        return new ResponseEntity<>(result, httpStatus);
    }
}
