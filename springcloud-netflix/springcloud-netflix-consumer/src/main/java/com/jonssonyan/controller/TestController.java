package com.jonssonyan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 注入了RestTemplate，getInfo中使用RestTemplate对象均衡的去获取服务并消费。
     * 可以看到我们使用服务名称（Server-Provider）去获取服务的，而不是使用传统的IP加端口的形式。
     * 这就体现了使用Eureka去获取服务的好处，我们只要保证这个服务名称不变即可，IP和端口不再是我们关心的点
     *
     * @return
     */
    @GetMapping("/info")
    public String getInfo() {
        return this.restTemplate.getForEntity("http://Server-Provider/hello", String.class).getBody();
    }
}
