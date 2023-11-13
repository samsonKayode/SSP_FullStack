package com.ssp.backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class DemoController {

    @GetMapping
    public String welcome() {
        return "welcome";
    }
}
