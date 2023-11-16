package com.jonssonyan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("table2")
public class Table2 implements Serializable {
    private static final long serialVersionUID = 5070680807005442973L;
    @TableId
    private Long id;
    @TableField
    private String name;
}
