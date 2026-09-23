package com.example.springlearning.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class StudentPatchRequest {

    @Size(min = 2, max = 50,
          message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Email must be valid")
    @Size(max = 100,
          message = "Email must not exceed 100 characters")
    private String email;

    private List<@Positive(message = "Course ID must be positive") Integer> courseIds;

    public StudentPatchRequest() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }
}