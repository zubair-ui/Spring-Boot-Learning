package com.example.springlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlearning.model.Course;

public interface CourseJpaRepository extends JpaRepository<Course, Integer> {
}