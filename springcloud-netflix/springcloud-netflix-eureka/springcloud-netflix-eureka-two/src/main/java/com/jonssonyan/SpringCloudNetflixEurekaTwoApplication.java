package com.jonssonyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudNetflixEurekaTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixEurekaTwoApplication.class, args);
    }

}
