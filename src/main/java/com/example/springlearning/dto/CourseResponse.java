package com.example.springlearning.dto;

public class CourseResponse {

    private int id;
    private String name;

    public CourseResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}