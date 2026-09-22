package com.example.springlearning.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;

import com.example.springlearning.dto.StudentPatchRequest;
import com.example.springlearning.dto.StudentRequest;
import com.example.springlearning.dto.StudentResponse;
import com.example.springlearning.model.Student;
import com.example.springlearning.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;


@RestController
@Validated
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
    public ResponseEntity<StudentResponse> studentById(@PathVariable 
    		@Positive(message = "ID must be positive") int id) {

        Student student = studentService.getStudentById(id);

        StudentResponse response = new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
        );

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                0,
                request.getName(),
                request.getEmail()
        );

        Student savedStudent = studentService.createStudent(student);

        StudentResponse response = new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail()
        );

        URI location = URI.create("/students/" + savedStudent.getId());

        return ResponseEntity
                .created(location)
                .body(response);
    }
    
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable @Positive(message = "ID must be positive") int id,
            @Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                id,
                request.getName(),
                request.getEmail()
        );

        Student updatedStudent = studentService.updateStudent(student);

        StudentResponse response = new StudentResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail()
        );

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponse> patchStudent(
            @PathVariable @Positive(message = "ID must be positive") int id,
            @RequestBody StudentPatchRequest request) {

        Student student = studentService.getStudentById(id);

        if (request.getName() != null) {
            student.setName(request.getName());
        }

        if (request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }

        Student updatedStudent = studentService.updateStudent(student);

        StudentResponse response = new StudentResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail()
        );

        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}