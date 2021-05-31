package com.springboot.advice;

import com.springboot.entity.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(HttpServletRequest req, Exception e) {
        return Result.fail("系统错误，请联系网站管理员！");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result runtimeException(HttpServletRequest req, RuntimeException e) {
        return Result.fail(e.getMessage());
    }
}
