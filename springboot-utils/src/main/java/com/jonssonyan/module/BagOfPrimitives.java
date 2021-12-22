package com.jonssonyan.module;

import lombok.Data;

@Data
public class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
}
