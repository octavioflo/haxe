package com.haxe.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project")
public class Projects {
    
    @GetMapping
    public String getProject() {
        return "{\"hello\": \"world\"}";
    }
}
