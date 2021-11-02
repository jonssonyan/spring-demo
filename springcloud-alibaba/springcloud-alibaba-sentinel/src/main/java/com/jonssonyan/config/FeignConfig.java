package com.jonssonyan.config;

import com.jonssonyan.service.impl.EchoServiceFallback;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}
