package com.springcloud.service.impl;

import com.springcloud.service.EchoService;

public class EchoServiceFallback implements EchoService {
    public String echo(String str) {
        return "echo fallback";
    }
}
