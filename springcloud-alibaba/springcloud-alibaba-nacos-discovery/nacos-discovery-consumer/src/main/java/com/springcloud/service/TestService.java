package com.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("service-provider")
public interface TestService {
    @GetMapping("/echo/{str}")
    String print(@PathVariable(value = "str") String str);
}
