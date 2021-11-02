package com.jonssonyan.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private Long id;
    @NotEmpty(message = "用户名不能为空")
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "[a-zA-Z0-9\u4e00-\u9fa5]{2,20}", message = "用户民必须为2-20个字母加数字")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
    @Size(min = 3, max = 16)
    private String password;
    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 100, message = "年龄必须在0-100之间")
    private Integer age;
    @NotEmpty(message = "邮箱能为空")
    @NotNull(message = "邮箱能为空")
    private String email;

    public User() {
    }

    public User(Long id, String username, String password, Integer age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
