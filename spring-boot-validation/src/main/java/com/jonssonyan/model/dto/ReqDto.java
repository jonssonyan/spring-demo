package com.jonssonyan.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReqDto {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 20, message = "用户名长度必须在6到20之间")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20之间")
    private String password;
    @Email(message = "请输入有效的邮箱地址")
    private String email;
    @Max(value = 200, message = "年龄必须小于200")
    @Min(value = 0, message = "年龄必须大于0")
    private Integer age;
    @NotEmpty(message = "教育经历不能为空")
    private List<String> education;
    @Past(message = "出生日期必须是过去的日期")
    private LocalDate birthDate;
}
