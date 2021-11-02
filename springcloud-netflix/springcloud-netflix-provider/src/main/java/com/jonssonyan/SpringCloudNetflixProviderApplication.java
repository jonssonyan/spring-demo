package com.jonssonyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 暴露服务用
public class SpringCloudNetflixProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixProviderApplication.class, args);
    }

}
