package com.jonssonyan.service;

import com.jonssonyan.config.FeignConfig;
import com.jonssonyan.service.impl.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-provider", fallback = EchoServiceFallback.class, configuration = FeignConfig.class)
public interface EchoService {
    @GetMapping(value = "/echo/{str}")
    String echo(@PathVariable("str") String str);
}
