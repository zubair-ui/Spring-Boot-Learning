package com.example.springlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
	    return "Hello, " + name + "!";
	}
	
	@GetMapping("/greet")
	public String greet() {
		return "Welcome to my Spring Boot API!";
	}

}
