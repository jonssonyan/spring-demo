package com.jonssonyan.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.util.Date;

@Data
@ExcelTarget("userExportVo")
public class UserExportVo {
    @Excel(name = "编号")
    private Long id;
    @Excel(name = "用户名", isImportField = "true")
    private String username;
    @Excel(name = "邮箱", isImportField = "true")
    private String email;
    @Excel(name = "电话号码", isImportField = "true")
    private String phone;
    @Excel(name = "qq号码", isImportField = "true")
    private String qq;
    @Excel(name = "创建时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss", width = 40)
    private Date createTime;
    @Excel(name = "更新时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss", width = 40)
    private Date updateTime;
}