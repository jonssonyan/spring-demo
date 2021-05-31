package com.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2961686750510451767L;
    private Long id;
    private String name;
    private Integer age;
}
