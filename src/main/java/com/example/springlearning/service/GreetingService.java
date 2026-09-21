package com.example.springlearning.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${app.name:Default Application}")
    private String appName;

    public String getGreeting() {
        return "Hello from GreetingService!";
    }

    public String getPersonalizedGreeting(String name) {
        return "Hello, " + name + "!";
    }

    public String getApplicationGreeting() {
        return "Welcome to " + appName + "!";
    }
}