package com.example.springlearning.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springlearning.model.Student;

@Repository
public class StudentRepository {
	
	public Student getStudent() {
	    return new Student(1, "Zubair", "bhuttozubair6@gmail.com");
	}
	
	public List<Student> getStudents() {
	    return List.of(
	        new Student(1, "Zubair", "bhuttozubair6@gmail.com"),
	        new Student(2, "Ali", "ali5@gmail.com"),
	        new Student(3, "Ahmed", "ahmed4@gmail.com")
	    );
	}

}