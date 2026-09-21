package com.example.springlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping("/students/{id}")
    public Student studentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
    
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    
    @PutMapping("/students/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        student.setId(id);

        return studentService.updateStudent(student);
    }
    
    @DeleteMapping("/students/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}