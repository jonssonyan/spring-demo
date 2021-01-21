package com.springboot.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageVO extends BaseVO {
    private String msgId;
    private String content;
    private Long createTime;
}
