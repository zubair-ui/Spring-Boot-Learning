package com.example.springlearning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springlearning.model.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

    List<Student> findByCoursesId(int courseId);

    @Query("""
            SELECT DISTINCT s
            FROM Student s
            LEFT JOIN FETCH s.courses
            """)
    List<Student> findAllWithCourses();

    @EntityGraph(attributePaths = "courses")
    Page<Student> findAll(Pageable pageable);
    
    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}