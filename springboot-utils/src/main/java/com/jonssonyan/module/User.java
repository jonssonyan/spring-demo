package com.jonssonyan.module;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private String name;
    private Integer age;
    private BigDecimal salary;
    private Integer birthdate;
    private Boolean old;
}
