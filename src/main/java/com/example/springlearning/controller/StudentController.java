package com.example.springlearning.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<StudentResponse> students(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return studentService.getStudents(pageable)
                .map(this::toStudentResponse);
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
    
    @GetMapping("/students/search")
    public Page<StudentResponse> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,

            @PageableDefault(size = 10, sort = "id")
            Pageable pageable) {

        return studentService
                .searchStudents(name, email, pageable)
                .map(this::toStudentResponse);
    }
    
    @GetMapping("/students/search/course")
    public Page<StudentResponse> searchStudentsByCourse(
            @RequestParam @Positive(message = "Course ID must be positive")
            int courseId,

            @PageableDefault(size = 10, sort = "id")
            Pageable pageable) {

        return courseService
                .getStudentsByCourseId(courseId, pageable)
                .map(this::toStudentResponse);
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
    
    @PostMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentResponse> addCourseToStudent(
            @PathVariable @Positive(message = "Student ID must be positive")
            int studentId,

            @PathVariable @Positive(message = "Course ID must be positive")
            int courseId) {

        Course course = courseService.getCourseById(courseId);

        Student updatedStudent =
                studentService.addCourseToStudent(
                        studentId,
                        courseId,
                        course
                );

        return ResponseEntity.ok(
                toStudentResponse(updatedStudent)
        );
    }
    
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentResponse> removeCourseFromStudent(
            @PathVariable @Positive(message = "Student ID must be positive")
            int studentId,

            @PathVariable @Positive(message = "Course ID must be positive")
            int courseId) {

        Student updatedStudent =
                studentService.removeCourseFromStudent(
                        studentId,
                        courseId
                );

        return ResponseEntity.ok(
                toStudentResponse(updatedStudent)
        );
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