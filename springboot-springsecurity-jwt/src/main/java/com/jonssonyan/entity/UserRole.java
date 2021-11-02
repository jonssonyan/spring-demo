package com.jonssonyan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -8433321155371757553L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("role_id")
    private Long roleId;
    @TableField(exist = false)
    private Role role;
}
