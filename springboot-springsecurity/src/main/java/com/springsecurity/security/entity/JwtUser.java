package com.springsecurity.security.entity;

import com.springsecurity.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * JWT用户对象
 */
@Data
public class JwtUser implements UserDetails {

    private static final long serialVersionUID = 9054880854653960891L;
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        authorities = user.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}