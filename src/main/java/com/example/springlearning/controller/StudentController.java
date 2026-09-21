package com.example.springlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.model.Student;
import com.example.springlearning.service.StudentService;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-data")
    public Student studentData() {
        return studentService.getStudent();
    }

    @GetMapping("/students")
    public List<Student> students() {
        return studentService.getStudents();
    }
}