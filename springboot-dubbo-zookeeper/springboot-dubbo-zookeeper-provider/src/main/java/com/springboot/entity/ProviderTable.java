package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("provider_table")
public class ProviderTable implements Serializable {
    private static final long serialVersionUID = -4866881424774460874L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String content;
}