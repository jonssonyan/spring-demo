package com.jonssonyan.controller;

import com.jonssonyan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return testService.hello();
    }
}
