package com.jonssonyan.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置权限。这里只要登陆的用户都会执行下面这段代码，实际开发中，这里的权限应该从数据库中获取
        info.addStringPermission("user:add");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        // username，password从数据库中获取，这里为了方便在代码中写死
        String username = "admin";
        String password = "123";
        // 前端传来的用户名密码从token中获取
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 用户名认证
        if (!token.getUsername().equals(username)) {
            return null; // 用户名不正确，抛出异常UnknownAccountException
        }
        // 密码认证，shiro做，加密了
        return new SimpleAuthenticationInfo("", password, "");
    }
}
