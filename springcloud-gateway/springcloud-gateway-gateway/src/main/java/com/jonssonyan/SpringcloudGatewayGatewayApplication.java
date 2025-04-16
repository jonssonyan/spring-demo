package com.jonssonyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringcloudGatewayGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudGatewayGatewayApplication.class, args);
    }

    @GetMapping("who")
    public String ping() {
        return "this is gateway";
    }
}
