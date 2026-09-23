package com.example.springlearning.exception;

@SuppressWarnings("serial")
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(int id) {
        super("Course with ID " + id + " not found");
    }
}