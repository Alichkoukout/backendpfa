package com.example.authapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.authapp.exception.EmailAlreadyExistsException;
import com.example.authapp.exception.PasswordMismatchException;
import com.example.authapp.model.User;
import com.example.authapp.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
        }
        
        if (user.getPassword() == null || user.getConfirmPassword() == null) {
            throw new PasswordMismatchException("Password and confirm password are required");
        }
        
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new PasswordMismatchException("Password and confirm password do not match");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public Optional<User> login(User loginUser) {
        Optional<User> user = userRepository.findByEmail(loginUser.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {
            throw new PasswordMismatchException("Invalid credentials!");
        }
        return user;
    }




}