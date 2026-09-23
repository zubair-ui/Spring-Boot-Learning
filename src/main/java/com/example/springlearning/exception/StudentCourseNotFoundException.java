package com.example.springlearning.exception;

public class StudentCourseNotFoundException extends RuntimeException {

    public StudentCourseNotFoundException(int studentId, int courseId) {
        super(
            "Course with ID " + courseId
            + " is not assigned to student with ID " + studentId
        );
    }
}