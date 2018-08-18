package com.chen.demo.aops;

import com.chen.demo.exceptions.BusinessException;
import com.chen.demo.vos.OneData;
import com.chen.demo.vos.ResponseData;
import com.chen.demo.vos.ResponseStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Aspect
@Component
@Order(1)
public class LogManager {

    private static final Logger log = Logger.getLogger(LogManager.class);
    
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    @Pointcut(value = "execution(com.chen.demo.vos.ResponseData com.chen.*.controllers.*.*(..))")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object data = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            MethodSignature method = (MethodSignature) jp.getSignature();
            Enumeration<String> headerNames = request.getHeaderNames();
            List<String> headers = new ArrayList<>();
            while(headerNames.hasMoreElements()){
                String head = headerNames.nextElement();
                headers.add(head + ":" + request.getHeader(head));
            }
            String vo = null;
            Object[] params = jp.getArgs();
            for(int i = 0;i < params.length;++i){
                Object param = params[i];
                if(! (param instanceof OneData))    continue;
                vo = gson.toJson(param);
            }
            log.info("time       : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startTime)));
            log.info("url        : " + request.getRequestURL());
            log.info("uri        : " + request.getRequestURI());
            log.info("httpMethod : " + request.getMethod());
            log.info("mediaType  : " + request.getHeader("content-type"));
            log.info("headers    : " + StringUtils.join(headers, ","));
            log.info("ip         : " + request.getRemoteAddr());
            log.info("controller : " + method.getMethod().getDeclaringClass().getName());
            log.info("method     : " + method.getName());
            log.info("params     : " + gson.toJson(request.getParameterMap()));
            log.info("voParam    : " + vo);
            data = jp.proceed();
            log.info("result     : " + gson.toJson(data));
        }catch (Throwable e){
            if(e instanceof BusinessException){
                log.info("result     : " + gson.toJson(ResponseData.failed(((BusinessException) e).status())));
            }else {
                log.info("result     : " + gson.toJson(ResponseData.failed(ResponseStatus.FAIL)));
            }
            log.info("Exception  : ", e);
            throw e;
        }finally {
            log.info("exeTime    : " + (System.currentTimeMillis() - startTime));
        }
        return data;
    }
}
