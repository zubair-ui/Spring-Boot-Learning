package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.exception.CourseNotFoundException;
import com.example.springlearning.model.Course;
import com.example.springlearning.repository.CourseJpaRepository;

@Service
public class CourseService {

    private final CourseJpaRepository courseRepository;

    public CourseService(CourseJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public Course getCourseById(int id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }
}