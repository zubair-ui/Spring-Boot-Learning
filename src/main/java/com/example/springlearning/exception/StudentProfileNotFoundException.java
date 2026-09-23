package com.example.springlearning.exception;

@SuppressWarnings("serial")
public class StudentProfileNotFoundException extends RuntimeException {

    public StudentProfileNotFoundException(int id) {
        super("Student profile with ID " + id + " not found");
    }
}