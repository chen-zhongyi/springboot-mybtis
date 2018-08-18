package com.chen.demo.exceptions;

import com.chen.demo.vos.ResponseData;
import com.chen.demo.vos.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseData businessException(HttpServletRequest request, BusinessException e) throws Exception{
        //logger.error("---BusinessException Handler---Host " + request.getRemoteHost() + " invokes url " + request.getRequestURL() + " ERROR: ", e);
        return ResponseData.failed(e.status());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData defaultException(HttpServletRequest request, Exception e) throws Exception{
        //logger.error("---UnknownException Handler---Host " + request.getRemoteHost() + " invokes url " + request.getRequestURL() + " ERROR: ", e);
        return ResponseData.failed(ResponseStatus.FAIL);
    }
}
