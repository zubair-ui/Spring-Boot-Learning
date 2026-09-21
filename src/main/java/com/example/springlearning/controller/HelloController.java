package com.example.springlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.service.GreetingService;

@RestController
public class HelloController {
	
	private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
	    return "Hello, " + name + "!";
	}
	
	@GetMapping("/greet")
	public String greet() {
		return greetingService.getGreeting();
	}

}
