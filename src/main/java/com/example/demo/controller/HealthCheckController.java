package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String home(){
        return "home-aws";
    }

    @GetMapping("/healthz")
    public String healthz(){
        return "healthz-aws";
    }

}
