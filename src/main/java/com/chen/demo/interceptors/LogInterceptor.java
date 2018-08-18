package com.chen.demo.interceptors;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class LogInterceptor implements HandlerInterceptor{

    private static final Logger log = Logger.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object h) throws Exception{
        log.info("url        : " + request.getRequestURL());
        log.info("uri        : " + request.getRequestURI());
        log.info("httpMethod : " + request.getMethod());
        log.info("params     : " + new Gson().toJson(request.getParameterMap()));
        log.info("ip         : " + request.getRemoteAddr());
        long startTime = System.currentTimeMillis();
        log.info("time       : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startTime)));
        request.setAttribute("logStartTime", startTime);
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headers = new ArrayList<>();
        while(headerNames.hasMoreElements()){
            String head = headerNames.nextElement();
            headers.add(head + ":" + request.getHeader(head));
        }
        log.info("headers    : " + StringUtils.join(headers, ","));
        if(h instanceof HandlerMethod){
            HandlerMethod handler = (HandlerMethod) h;
            log.info("controller : " + handler.getBean().getClass().getName());
            log.info("method     : " + handler.getMethod().getName());
        }
        log.info("mediaType  : " + request.getHeader("content-type"));
        InputStream inputStream = request.getInputStream();
        if(inputStream != null){
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            log.info("jsonParam  : " + sb.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView mv) throws Exception{
        long startTime = (Long) request.getAttribute("logStartTime");
        log.info("exeTime    : " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception e) throws Exception{

    }
}
