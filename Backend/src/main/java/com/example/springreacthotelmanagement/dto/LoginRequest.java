package com.example.springreacthotelmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    private String password;

}
