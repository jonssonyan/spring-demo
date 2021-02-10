package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("test")
public class Test implements Serializable {
    private static final long serialVersionUID = -7477174144735352366L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
}
