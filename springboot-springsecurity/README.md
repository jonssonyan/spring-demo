# springsecurity+jwt权限系统设计

## 介绍
springsecurity+jwt权限系统demo

## 开发工具
1. jdk1.8
2. intelij idea 2019.3.5
3. mysql 5.7.29

## 框架\技术
1. springsecurity
2. jwt
3. springboot
4. mybatis-plus

## 登录接口
```
POST http://localhost:8888/security/auth/login
{
    "username": "user",
    "password": "user",
    "rememberMe": true
}
```
登录成功之后，会将token放在Headers里的Authorization字段