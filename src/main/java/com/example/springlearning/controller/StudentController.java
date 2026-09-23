package com.example.springlearning.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import com.example.springlearning.dto.StudentPatchRequest;
import com.example.springlearning.dto.StudentRequest;
import com.example.springlearning.dto.StudentResponse;
import com.example.springlearning.model.Course;
import com.example.springlearning.model.Student;
import com.example.springlearning.service.CourseService;
import com.example.springlearning.service.StudentService;

@RestController
@Validated
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(
            StudentService studentService,
            CourseService courseService) {

        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/students")
    public List<StudentResponse> students() {

        List<Student> students = studentService.getStudents();

        return students.stream()
                .map(this::toStudentResponse)
                .toList();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> studentById(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(toStudentResponse(student));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                0,
                request.getName(),
                request.getEmail()
        );

        if (request.getCourseIds() != null) {

        	List<Course> courses = new ArrayList<>(
        	        request.getCourseIds()
        	                .stream()
        	                .map(courseService::getCourseById)
        	                .toList()
        	);

            student.setCourses(courses);
        }

        Student savedStudent = studentService.createStudent(student);

        URI location = URI.create("/students/" + savedStudent.getId());

        return ResponseEntity
                .created(location)
                .body(toStudentResponse(savedStudent));
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

        if (request.getCourseIds() != null) {

        	List<Course> courses = new ArrayList<>(
        	        request.getCourseIds()
        	                .stream()
        	                .map(courseService::getCourseById)
        	                .toList()
        	);

            student.setCourses(courses);
        }

        Student updatedStudent = studentService.updateStudent(student);

        return ResponseEntity.ok(toStudentResponse(updatedStudent));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponse> patchStudent(
            @PathVariable @Positive(message = "ID must be positive") int id,
            @Valid @RequestBody StudentPatchRequest request) {

        Student student = studentService.getStudentById(id);

        if (request.getName() != null) {
            student.setName(request.getName());
        }

        if (request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }

        if (request.getCourseIds() != null) {

        	List<Course> courses = new ArrayList<>(
        	        request.getCourseIds()
        	                .stream()
        	                .map(courseService::getCourseById)
        	                .toList()
        	);

            student.setCourses(courses);
        }

        Student updatedStudent = studentService.updateStudent(student);

        return ResponseEntity.ok(toStudentResponse(updatedStudent));
    }

    private StudentResponse toStudentResponse(Student student) {

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCourses()
                        .stream()
                        .map(course -> new com.example.springlearning.dto.CourseResponse(
                                course.getId(),
                                course.getName(),
                                List.of()
                        ))
                        .toList()
        );
    }
}