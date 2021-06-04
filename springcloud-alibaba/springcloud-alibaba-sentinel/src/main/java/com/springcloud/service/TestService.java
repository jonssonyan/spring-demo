package com.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    /**
     * @param name
     * @return
     * @SentinelResource 注解用来标识资源是否被限流、降级
     */
    @SentinelResource(value = "sayHello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
