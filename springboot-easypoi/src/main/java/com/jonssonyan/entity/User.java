package com.jonssonyan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1348665906085238496L;
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String qq;
    private Date createTime;
    private Date updateTime;
}
