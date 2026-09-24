package com.example.springlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
public class SpringLearningBootApplication {
	
	@Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizePageable() {
        return resolver -> resolver.setMaxPageSize(100);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringLearningBootApplication.class, args);
        
    }

}