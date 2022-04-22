package com.jonssonyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
