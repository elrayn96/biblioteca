package com.armando.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}

//The container 'Maven Dependencies' references non existing library 'C:\Users\AAM\.m2\repository\org\thymeleaf\thymeleaf-spring6\3.1.3.RELEASE\thymeleaf-spring6-3.1.3.RELEASE.jar'
