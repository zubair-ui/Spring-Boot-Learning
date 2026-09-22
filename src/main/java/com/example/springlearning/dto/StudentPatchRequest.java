package com.example.springlearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class StudentPatchRequest {

    @Size(min = 2, max = 50,
          message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Email must be valid")
    @Size(max = 100,
          message = "Email must not exceed 100 characters")
    private String email;

    public StudentPatchRequest() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}