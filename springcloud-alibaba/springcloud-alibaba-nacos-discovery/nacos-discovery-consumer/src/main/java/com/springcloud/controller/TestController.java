package com.springcloud.controller;

import com.springcloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestService testService;

    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
    }

    @GetMapping(value = "/print/{str}")
    public String print(@PathVariable String str) {
        return testService.print(str);
    }
}