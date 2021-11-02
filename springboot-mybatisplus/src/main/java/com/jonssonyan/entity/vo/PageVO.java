package com.jonssonyan.entity.vo;

import lombok.Data;

@Data
public class PageVO {
    private Long pageSize = 10L;
    private Long pageNum = 1L;
}
