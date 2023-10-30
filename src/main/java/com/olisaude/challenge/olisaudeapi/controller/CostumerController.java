package com.olisaude.challenge.olisaudeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/costumer")
public class CostumerController {
    @GetMapping
    public String olaMundo(){
        return "Hello World Spring!";
    }
}

