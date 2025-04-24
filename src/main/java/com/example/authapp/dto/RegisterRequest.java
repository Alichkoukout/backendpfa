package com.example.authapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be 6-20 characters")
    private String password;

    // Optional additional fields
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
    private String name;
}