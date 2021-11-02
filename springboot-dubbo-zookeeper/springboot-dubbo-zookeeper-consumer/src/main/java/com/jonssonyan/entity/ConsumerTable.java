package com.jonssonyan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("consumer_table")
public class ConsumerTable implements Serializable {
    private static final long serialVersionUID = -7278120508807579299L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String content;
}
