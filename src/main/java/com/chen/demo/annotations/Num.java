package com.chen.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Num {

    int lessInt() default Integer.MAX_VALUE;

    int moreInt() default Integer.MIN_VALUE;

    double lessDou() default Double.MAX_VALUE;

    double moreDou() default Double.MIN_VALUE;

    String message() default "";
}
