package com.example.springlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.model.Student;
import com.example.springlearning.service.GreetingService;

@RestController
public class HelloController {
	
    private final GreetingService greetingService;
    
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return greetingService.getPersonalizedGreeting(name);
    }

    @GetMapping("/greet")
    public String greet() {
        return greetingService.getGreeting();
    }
    
    @GetMapping("/welcome")
    public String welcome() {
        return greetingService.getApplicationGreeting();
    }
    
    @GetMapping("/student")
    public String student() {
        return greetingService.getStudentGreeting();
    }
    
    @GetMapping("/student-data")
    public Student studentData() {
        return greetingService.getStudent();
    }
          
    @GetMapping("/students")
    public List<Student> students() {
        return greetingService.getStudents();
    }
}