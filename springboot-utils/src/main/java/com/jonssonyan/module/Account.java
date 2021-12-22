package com.jonssonyan.module;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Account {
    private Long id;
    private String username;
    private String password; // 密码
    private Integer sex;  // 性别
    private LocalDate birthday; // 生日
    private LocalDateTime createTime; // 创建时间
    private String config; // 其他扩展信息，以JSON格式存储
}
