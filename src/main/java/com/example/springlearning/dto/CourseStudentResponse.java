package com.example.springlearning.dto;

public class CourseStudentResponse {

    private int id;
    private String name;
    private String email;

    public CourseStudentResponse(
            int id,
            String name,
            String email) {

        this.id = id;
        this.name = name;
        this.email = email;
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
}