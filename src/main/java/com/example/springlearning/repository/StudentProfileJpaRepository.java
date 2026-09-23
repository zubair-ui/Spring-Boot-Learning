package com.example.springlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlearning.model.StudentProfile;

public interface StudentProfileJpaRepository
        extends JpaRepository<StudentProfile, Integer> {
}