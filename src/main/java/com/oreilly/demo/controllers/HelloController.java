package com.oreilly.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// receive HTTP requests and return response
@Controller
public class HelloController {

    @GetMapping("/hello") // http:://localhost:8080/hello?name=Dolly --> spring maps to this function
    public String sayHello(
            // map param query string to name
            @RequestParam(value = "name", required = false, defaultValue="World") String name, Model model) {
        model.addAttribute("user", name); // look for hello.html in src/main/resources/templates
        return "hello";
        // spring takes all attributes from model; adds them to http servlet request and forward it to destination
    }
}
