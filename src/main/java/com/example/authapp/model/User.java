package com.example.authapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String email;
    
    private String password;

    private String name;

    @Transient // This field won't be persisted to the database
    private String confirmPassword;
}