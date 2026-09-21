package com.example.springlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.springlearning.dto.StudentRequest;
import com.example.springlearning.dto.StudentResponse;
import com.example.springlearning.model.Student;
import com.example.springlearning.service.StudentService;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentResponse> students() {

        List<Student> students = studentService.getStudents();

        return students.stream()
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getEmail()
                ))
                .toList();
    }
    
    @GetMapping("/students/{id}")
    public StudentResponse studentById(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }
    
    @PostMapping("/students")
    public StudentResponse createStudent(@RequestBody StudentRequest request) {

        Student student = new Student(
                0,
                request.getName(),
                request.getEmail()
        );

        Student savedStudent = studentService.createStudent(student);

        return new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail()
        );
    }
    
    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(
            @PathVariable int id,
            @RequestBody StudentRequest request) {

        Student student = new Student(
                id,
                request.getName(),
                request.getEmail()
        );

        Student updatedStudent = studentService.updateStudent(student);

        return new StudentResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail()
        );
    }
    
    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}