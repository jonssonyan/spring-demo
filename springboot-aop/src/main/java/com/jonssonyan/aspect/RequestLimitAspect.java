package com.jonssonyan.aspect;

import com.alibaba.fastjson.JSON;
import com.jonssonyan.annotation.RequestLimit;
import com.jonssonyan.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class RequestLimitAspect {

    private static final String UTF = "UTF-8";
    private static final String CONTENTTYPE = "application/json; charset=utf-8";

    private final Map<String, Integer> map = new ConcurrentHashMap<>();

    @Before("within(com.jonssonyan.controller.*) && @annotation(com.jonssonyan.annotation.RequestLimit)")
    public void requestLimit(final JoinPoint joinPoint) {
        RequestLimit limit = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RequestLimit.class);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        ServletOutputStream outputStream = null;

        try {
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if (map.get(key) == null || map.get(key) == 0) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
            int count = map.get(key);
            if (count > 0) {
                Timer timer = new Timer();
                //创建一个计时器任务
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        map.remove(key);
                    }
                };
                timer.schedule(task, limit.time());
            }
            if (count > limit.count()) {
                log.error("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                assert response != null;
                response.setCharacterEncoding(UTF);
                response.setContentType(CONTENTTYPE);
                outputStream = response.getOutputStream();
                outputStream.write(JSON.toJSONString(Result.fail("请求过于频繁！")).getBytes());
                outputStream.flush();
            }
        } catch (Exception e) {
            log.error("发生异常: ", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
