package com.springsecurity.exception;


import com.springsecurity.enums.ErrorCode;

import java.util.Map;

public class UserNameNotFoundException extends BaseException {
    private static final long serialVersionUID = -5198335251843980155L;

    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
