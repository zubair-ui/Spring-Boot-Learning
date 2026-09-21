package com.example.springlearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.model.Student;
import com.example.springlearning.service.GreetingService;
import com.example.springlearning.service.MessageService;

@RestController
public class HelloController {
	
    private final GreetingService greetingService;
    private final MessageService messageService;
    
    public HelloController(
            GreetingService greetingService,
            MessageService messageService) {

        this.greetingService = greetingService;
        this.messageService = messageService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return greetingService.getPersonalizedGreeting(name);
    }

    @GetMapping("/greet")
    public String greet() {
        return greetingService.getGreeting();
    }

    @GetMapping("/message")
    public String message() {
        return messageService.getMessage();
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