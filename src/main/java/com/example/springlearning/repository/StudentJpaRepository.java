package com.example.springlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlearning.model.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

    List<Student> findByCourseId(int courseId);
}