package com.springsecurity.exception;

import com.springsecurity.entity.vo.ResultVO;
import com.springsecurity.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO<Object> defaultExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("---BaseException Handler---Host {} invokes url {} ERROR: ", req.getRemoteHost(), req.getRequestURL(), e);
        return ResultVOUtils.fail("系统错误，请联系网站管理员！");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultVO<Object> RuntimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        log.error("---BaseException Handler---Host {} invokes url {} ERROR: ", req.getRemoteHost(), req.getRequestURL(), e);
        return ResultVOUtils.fail(e.getMessage());
    }
}
