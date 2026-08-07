package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "📚 Welcome to My Library Management System";
    }

    @GetMapping("/about")
    public String about() {
        return """
                =====================================
                Library Management System API
                Developed using Spring Boot
                Database: PostgreSQL
                Documentation: Swagger UI
                =====================================
                """;
    }

    @GetMapping("/status")
    public String status() {
        return "Application is Running Successfully ✅";
    }

}