package com.jonssonyan;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 在启动类加注解,排查SecurityAutoConfiguration配置
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivitiApplication.class, args);
    }

}
