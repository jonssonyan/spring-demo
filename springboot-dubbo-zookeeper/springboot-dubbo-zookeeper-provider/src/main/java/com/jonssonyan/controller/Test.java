package com.jonssonyan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    /**
     * 测试接口
     * @return
     */
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
