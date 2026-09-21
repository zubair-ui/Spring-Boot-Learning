package com.example.springlearning.repository;

import org.springframework.stereotype.Repository;

import com.example.springlearning.model.Student;

@Repository
public class StudentRepository {
	
	public Student getStudent() {
	    return new Student(1, "Zubair", "bhuttozubair6@gmail.com");
	}

}