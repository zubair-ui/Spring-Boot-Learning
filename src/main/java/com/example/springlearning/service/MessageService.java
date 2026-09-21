package com.example.springlearning.service;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

    public String getMessage() {
        return "Hello from MessageService!";
    }
}