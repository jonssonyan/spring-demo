package com.jonssonyan.controller;

import com.jonssonyan.annotation.RequestLimit;
import com.jonssonyan.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    @RequestLimit(count = 2)
    public Result test() {
        return Result.success();
    }
}
