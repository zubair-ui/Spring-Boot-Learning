package com.example.springlearning.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GreetingService {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(GreetingService.class);

    @Value("${app.name:Default Application}")
    private String appName;

    public String getGreeting() {

        logger.info("Getting greeting from GreetingService");
        logger.debug("Preparing greeting response");

        return "Hello from GreetingService!";
    }

    public String getPersonalizedGreeting(String name) {
        return "Hello, " + name + "!";
    }

    public String getApplicationGreeting() {
        return "Welcome to " + appName + "!";
    }
}