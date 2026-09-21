package com.example.springlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlearning.model.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

}