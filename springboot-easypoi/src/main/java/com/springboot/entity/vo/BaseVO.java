package com.springboot.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseVO {
    private Long id;
    private Long pageSize = 20L;
    private Long pageNum = 1L;
    private Date startTime;
    private Date endTime;
}
