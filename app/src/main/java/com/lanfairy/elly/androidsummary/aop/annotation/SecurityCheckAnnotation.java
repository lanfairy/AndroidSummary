package com.lanfairy.elly.androidsummary.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheckAnnotation {
    public String declaredPermission();  //declarePermssion是一个函数，其实代表了注解里的参数
}
