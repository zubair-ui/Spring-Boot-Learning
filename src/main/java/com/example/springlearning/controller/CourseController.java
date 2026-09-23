package com.example.springlearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Positive;

import com.example.springlearning.dto.CourseRequest;
import com.example.springlearning.dto.CourseResponse;
import com.example.springlearning.dto.CourseStudentResponse;
import com.example.springlearning.model.Course;
import com.example.springlearning.service.CourseService;

@RestController
@Validated
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<CourseResponse> getCourses() {

        List<Course> courses = courseService.getCourses();

        return courses.stream()
                .map(this::toCourseResponse)
                .toList();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        Course course = courseService.getCourseById(id);

        return ResponseEntity.ok(toCourseResponse(course));
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<CourseStudentResponse>> getStudentsByCourseId(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        List<CourseStudentResponse> students =
                courseService.getStudentsByCourseId(id)
                        .stream()
                        .map(student -> new CourseStudentResponse(
                                student.getId(),
                                student.getName(),
                                student.getEmail()
                        ))
                        .toList();

        return ResponseEntity.ok(students);
    }

    @PostMapping("/courses")
    public CourseResponse createCourse(
            @RequestBody CourseRequest request) {

        Course course = new Course(
                0,
                request.getName()
        );

        Course savedCourse = courseService.createCourse(course);

        return toCourseResponse(savedCourse);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable @Positive(message = "ID must be positive") int id,
            @RequestBody CourseRequest request) {

        Course course = new Course(
                id,
                request.getName()
        );

        Course updatedCourse = courseService.updateCourse(course);

        return ResponseEntity.ok(toCourseResponse(updatedCourse));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> patchCourse(
            @PathVariable @Positive(message = "ID must be positive") int id,
            @RequestBody CourseRequest request) {

        Course updatedCourse = courseService.patchCourse(
                id,
                request.getName()
        );

        return ResponseEntity.ok(toCourseResponse(updatedCourse));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    private CourseResponse toCourseResponse(Course course) {

        List<CourseStudentResponse> students =
                course.getStudents()
                        .stream()
                        .map(student -> new CourseStudentResponse(
                                student.getId(),
                                student.getName(),
                                student.getEmail()
                        ))
                        .toList();

        return new CourseResponse(
                course.getId(),
                course.getName(),
                students
        );
    }
}