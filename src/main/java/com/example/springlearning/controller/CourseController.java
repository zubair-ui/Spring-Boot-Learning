package com.example.springlearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.dto.CourseRequest;
import com.example.springlearning.dto.CourseResponse;
import com.example.springlearning.model.Course;
import com.example.springlearning.service.CourseService;

import jakarta.validation.constraints.Positive;

@RestController
@Validated
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable @Positive(message = "ID must be positive") int id) {

        Course course = courseService.getCourseById(id);

        return ResponseEntity.ok(
                new CourseResponse(
                        course.getId(),
                        course.getName()
                )
        );
    }

    @GetMapping("/courses")
    public List<CourseResponse> getCourses() {

        List<Course> courses = courseService.getCourses();

        return courses.stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getName()
                ))
                .toList();
    }

    @PostMapping("/courses")
    public CourseResponse createCourse(
            @RequestBody CourseRequest request) {

        Course course = new Course(
                0,
                request.getName()
        );

        Course savedCourse = courseService.createCourse(course);

        return new CourseResponse(
                savedCourse.getId(),
                savedCourse.getName()
        );
    }
}