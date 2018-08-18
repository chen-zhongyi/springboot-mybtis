package com.chen.demo.aops;

import com.chen.demo.annotations.*;
import com.chen.demo.exceptions.BusinessException;
import com.chen.demo.vos.OneData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
@Order(2)
public class ValidateManager {

    private static final Logger log = Logger.getLogger(LogManager.class);

    @Pointcut(value = "execution(com.chen.demo.vos.ResponseData com.chen.*.controllers.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void validate(JoinPoint jp) throws IllegalAccessException, BusinessException{
        MethodSignature method = (MethodSignature) jp.getSignature();
        method.getParameterTypes();
        Object[] params = jp.getArgs();
        method.getMethod().getParameterAnnotations();
        if(isNeedValidate(method.getMethod().getParameterAnnotations()) == false) return ;
        List<String> errors = new ArrayList<>();
        for(int i = 0;i < params.length;++i){
            Object param = params[i];
            if(! (param instanceof OneData))    continue;
            Class<?> clazz = param.getClass();
            for(Field field : clazz.getDeclaredFields()){
                for(Annotation annotation : field.getAnnotations()){
                    if(annotation instanceof Size)  size(field, param, (Size) annotation, errors);
                    else if(annotation instanceof Num)  num(field, param, (Num) annotation, errors);
                    else if(annotation instanceof NotNull)  notNull(field, param, (NotNull) annotation, errors, method.getName());
                    else if(annotation instanceof Regex)    regex(field, param, (Regex) annotation, errors);
                }
            }
        }
        if(errors.size() > 0){
           throw new BusinessException(StringUtils.join(errors, ", "));
        }
    }

    private static void size(Field field, Object o, Size size, List<String> errors) throws IllegalAccessException{
        field.setAccessible(true);
        if(field.get(o) == null)   return ;
        int x = field.get(o).toString().length();
        if(x < size.min() || x > size.max())    errors.add(size.message());
    }

    private static void num(Field field, Object o, Num num, List<String> errors) throws IllegalAccessException{
        field.setAccessible(true);
        if(field.get(o) == null)    return ;
        if(num.lessInt() != Integer.MIN_VALUE || num.moreInt() != Integer.MIN_VALUE){
            int x = field.getInt(o);
            if(x < num.moreInt() || x > num.lessInt())  errors.add(num.message());
        }
        if(num.lessDou() != Double.MIN_VALUE || num.moreDou() != Double.MIN_VALUE){
            double x = field.getDouble(o);
            if(x < num.moreDou() || x > num.lessDou())  errors.add(num.message());
        }
    }

    private static void notNull(Field field, Object o, NotNull notNull, List<String> errors, String methodName) throws IllegalAccessException{
        field.setAccessible(true);
        if(field.get(o) == null && notNull.method().contains(methodName))  errors.add(notNull.message());
    }

    private static void regex(Field field, Object o, Regex regex, List<String> errors) throws IllegalAccessException{
        field.setAccessible(true);
        if(field.get(o) == null)    return ;
        String str = (String) field.get(o);
        if(! str.matches(regex.value()))    errors.add(regex.message());
    }

    private static boolean isNeedValidate(Annotation[][] annotations){
        boolean isNeed = true;
        for(Annotation[] a : annotations){
            for(Annotation annotation : a){
                if(annotation instanceof NotValidate)   {
                    isNeed = false;
                    break;
                }
            }
            if(isNeed == false) break;
        }
        return isNeed;
    }
}
