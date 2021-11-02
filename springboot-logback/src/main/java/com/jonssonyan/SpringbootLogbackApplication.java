package com.jonssonyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootLogbackApplication {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test")
    public void test() {
        for (int i = 0; i < 2000; i++) {
            log.error("hello");
            log.info("hello");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLogbackApplication.class, args);
    }

}
