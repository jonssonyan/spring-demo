package com.jonssonyan.entity.vo;

import lombok.Data;

@Data
public class BaseVO {
    private Long id;
    private Integer pageNum = 0;
    private Integer pageSize = 2;
    private Long startTime;
    private Long endTime;
}