# springboot-springsecurity-jwt

## 登录接口
```
POST http://localhost:9017/security/auth/login
{
    "username": "user",
    "password": "user",
    "rememberMe": true
}
```
登录成功之后，会将token放在Headers里的Authorization字段