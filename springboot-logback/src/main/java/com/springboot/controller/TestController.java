package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test")
    public void test() {
        for (int i = 0; i < 2000; i++) {
            log.error("hello");
            log.info("hello");
        }
    }
}
