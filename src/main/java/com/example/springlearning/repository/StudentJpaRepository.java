package com.example.springlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springlearning.model.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT s FROM Student s WHERE s.name = :name")
	List<Student> findStudentsByName(@Param("name") String name);

}