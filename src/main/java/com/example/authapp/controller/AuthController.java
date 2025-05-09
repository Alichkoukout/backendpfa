package com.example.authapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.authapp.model.User;
import com.example.authapp.service.AuthService;

import java.util.Optional;

//api
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value ="3000")
public class AuthController {
    @Autowired
    private AuthService authService;



    //endpoint
    @PostMapping("/signup")
    public User signup(@Valid @RequestBody User user) {
        return authService.signup(user);
    }

    @PostMapping("/login")
    public Optional<User> login(@Valid @RequestBody User user) {
        return authService.login(user);
    }
}