package com.springboot.exception;

import com.springboot.enums.ErrorCode;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = -2252396809936062938L;
    private final ErrorCode errorCode;
    private final transient HashMap<String, Object> data = new HashMap<>();

    public BaseException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    BaseException(ErrorCode errorCode, Map<String, Object> data, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
