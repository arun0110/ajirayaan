package com.ajira.ajirayaan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/environment")
public class EnvironmentController {

    @GetMapping("ping")
    public String ping(){
        return "its Up!";
    }

}
