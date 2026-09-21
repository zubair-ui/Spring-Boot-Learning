package com.example.springlearning.exception;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(int id) {
        super("Student with ID " + id + " not found");
    }
}