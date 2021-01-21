package com.springsecurity.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginFailedException extends AuthenticationException {
    private static final long serialVersionUID = -837435727140811050L;

    public LoginFailedException(String detail) {
        super(detail);
    }
}
