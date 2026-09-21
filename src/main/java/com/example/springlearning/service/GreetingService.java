package com.example.springlearning.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.example.springlearning.repository.StudentRepository;

@Service
public class GreetingService {

    private final StudentRepository studentRepository;
    
    @Value("${app.name:Default Application}")
    private String appName;

    public GreetingService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String getGreeting() {
        return "Hello from GreetingService!";
    }

    public String getPersonalizedGreeting(String name) {
        return "Hello, " + name + "!";
    }

    public String getStudentGreeting() {
        String studentName = studentRepository.getStudentName();
        return "Hello, " + studentName + "!";
    }
    
    public String getApplicationGreeting() {
        return "Welcome to " + appName + "!";
    }
}