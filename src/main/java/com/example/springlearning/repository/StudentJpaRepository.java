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

    Page<Student> findByCoursesId(
            int courseId,
            Pageable pageable
    );

    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Student> findByEmailContainingIgnoreCase(
            String email,
            Pageable pageable
    );

    @Query("""
            SELECT DISTINCT s
            FROM Student s
            LEFT JOIN FETCH s.courses
            """)
    List<Student> findAllWithCourses();
    
    @Query("""
            SELECT s
            FROM Student s
            WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%')))
            """)
    Page<Student> searchStudents(
            String name,
            String email,
            Pageable pageable
    );

    @EntityGraph(attributePaths = "courses")
    Page<Student> findAll(Pageable pageable);
}