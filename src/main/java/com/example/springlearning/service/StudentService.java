package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.model.Student;
import com.example.springlearning.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent() {
        return studentRepository.getStudent();
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
}