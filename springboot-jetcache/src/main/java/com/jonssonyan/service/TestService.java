package com.jonssonyan.service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    @Cached(name = "test-hello-", cacheType = CacheType.REMOTE, expire = 30, timeUnit = TimeUnit.MINUTES)
    @CacheRefresh(refresh = 30, timeUnit = TimeUnit.MINUTES)
    public String hello() {
        return "Hello JetCache";
    }
}
