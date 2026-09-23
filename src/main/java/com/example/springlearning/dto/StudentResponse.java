package com.example.springlearning.dto;

import java.util.List;

public class StudentResponse {

    private int id;
    private String name;
    private String email;
    private List<CourseResponse> courses;

    public StudentResponse(
            int id,
            String name,
            String email,
            List<CourseResponse> courses) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }
}