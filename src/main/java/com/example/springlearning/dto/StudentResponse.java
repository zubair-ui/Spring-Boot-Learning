package com.example.springlearning.dto;

public class StudentResponse {

    private int id;
    private String name;
    private String email;
    private CourseResponse course;

    public StudentResponse(
            int id,
            String name,
            String email,
            CourseResponse course) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
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

    public CourseResponse getCourse() {
        return course;
    }
}