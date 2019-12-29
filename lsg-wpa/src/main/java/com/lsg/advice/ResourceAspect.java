package com.lsg.advice;

import com.alibaba.fastjson.JSON;
import com.lsg.common.HttpRequestUtil;
import com.lsg.common.RequestDealContext;
import com.lsg.common.StringUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Administrator
 */
@Component
@Aspect
@Order(1)
public class ResourceAspect {
    public static Logger log = LoggerFactory.getLogger(ResourceAspect.class);

    @Around("(within(com.lsg.controller.*))")
    protected Object aroudAdivce(ProceedingJoinPoint jp) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return jp.proceed();
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();
        RequestDealContext.clear();
        HttpRequestUtil.resolveContext(request);
        String argsString = "";
        if (log.isInfoEnabled() || log.isDebugEnabled()) {
            if (Arrays.asList(jp.getArgs()).stream().filter(arg -> arg instanceof File || arg instanceof InputStream || arg instanceof InputStreamSource || arg instanceof byte[]).count() == 0) {
                argsString = JSON.toJSONString(jp.getArgs());
            }
        }
        log.info("{}请求开始.......: {} , args={} ", Thread.currentThread().getName(), RequestDealContext.getLogString(), argsString);
        Object rt = null;
        rt = jp.proceed();
        long cost = System.currentTimeMillis() - RequestDealContext.getTimeStart();
        if (log.isInfoEnabled()) {
            String resStatus = "200 OK";
            if (rt instanceof ResponseEntity) {
                ResponseEntity resEnt = (ResponseEntity) rt;
                resStatus = resEnt.getStatusCode().value() + " " + resEnt.getStatusCode().getReasonPhrase();
            }
            log.info("{}请求结束  [cost={}] : {} , args={} , res={}", Thread.currentThread().getName(), StringUtils.leftPad(cost + "", 5), RequestDealContext.getLogString(), argsString, resStatus);
        }
        RequestDealContext.clear();
        return rt;
    }
}
