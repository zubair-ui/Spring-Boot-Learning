package com.example.springlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springlearning.dto.CourseStudentCountResponse;
import com.example.springlearning.model.Course;

public interface CourseJpaRepository
        extends JpaRepository<Course, Integer> {

    @Query("""
            SELECT new com.example.springlearning.dto.CourseStudentCountResponse(
                c.id,
                c.name,
                COUNT(s)
            )
            FROM Course c
            LEFT JOIN c.students s
            GROUP BY c.id, c.name
            ORDER BY COUNT(s) DESC
            """)
    List<CourseStudentCountResponse> getStudentCountPerCourse();
}