package com.ryan.estudos_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String dizerOla(){
        return "Olá, spring Boot!";
    }
}
