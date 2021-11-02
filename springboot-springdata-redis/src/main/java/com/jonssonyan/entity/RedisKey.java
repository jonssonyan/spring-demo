package com.jonssonyan.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisKey {

    public static final String SEPARATOR = ".";

    /**
     * Redis key 的前缀
     */
    private String prefix;

    /**
     * Redis key 的内容
     */
    private String suffix;

    public String of() {
        return String.format("%s.%s", prefix, suffix);
    }
}
