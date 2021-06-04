package com.springcloud.config;

import com.springcloud.service.impl.EchoServiceFallback;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}
