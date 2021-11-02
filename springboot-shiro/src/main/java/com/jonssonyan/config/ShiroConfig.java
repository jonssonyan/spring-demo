package com.jonssonyan.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultSecurityManager") DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);
        // 添加shiro内置过滤器
        /*
            anon：无参，开放权限，可以理解为匿名用户或游客
            authc：无参，需要认证
            logout：无参，注销，执行后会直接跳转到shiroFilterFactoryBean.setLoginUrl(); 设置的 url
            authcBasic：无参，表示 httpBasic 认证
            user：无参，表示必须存在用户，当登入操作时不做检查
            ssl：无参，表示安全的URL请求，协议为 https
            perms[user]：参数可写多个，表示需要某个或某些权限才能通过，多个参数时写 perms["user, admin"]，当有多个参数时必须每个参数都通过才算通过
            roles[admin]：参数可写多个，表示是某个或某些角色才能通过，多个参数时写 roles["admin，user"]，当有多个参数时必须每个参数都通过才算通过
            rest[user]：根据请求的方法，相当于 perms[user:method]，其中 method 为 post，get，delete 等
            port[8081]：当请求的URL端口不是8081时，跳转到schemal://serverName:8081?queryString 其中 schmal 是协议 http 或 https 等等，serverName 是你访问的 Host，8081 是 Port 端口，queryString 是你访问的 URL 里的 ? 后面的参数
         */
        Map<String, String> map = new LinkedHashMap<>();
        // 设置访问页面需要的权限
        map.put("/user/add", "perms[user:add]");
        map.put("/user/update", "perms[user:update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 设置转到登陆界面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 设置没有权限跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
