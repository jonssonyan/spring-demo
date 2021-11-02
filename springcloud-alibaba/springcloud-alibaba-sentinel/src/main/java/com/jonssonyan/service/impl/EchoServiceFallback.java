package com.jonssonyan.service.impl;

import com.jonssonyan.service.EchoService;

public class EchoServiceFallback implements EchoService {
    public String echo(String str) {
        return "echo fallback";
    }
}
