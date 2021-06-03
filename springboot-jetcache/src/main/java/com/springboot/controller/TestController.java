package com.springboot.controller;

import com.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String hello() {
        return testService.hello();
    }
}
