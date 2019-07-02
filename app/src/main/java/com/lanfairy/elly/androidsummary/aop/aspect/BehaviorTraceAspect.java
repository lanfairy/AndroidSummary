package com.lanfairy.elly.androidsummary.aop.aspect;

import android.os.SystemClock;
import android.util.Log;

import com.lanfairy.elly.androidsummary.aop.annotation.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Random;

@Aspect
public class BehaviorTraceAspect {
    private static final String TAG = BehaviorTraceAspect.class.getSimpleName();
    //定义切面的规则
    //1、就在原来的应用中那些注解的地方放到当前切面进行处理
    //execution（注解名   注解用的地方）
    @Pointcut("execution(@com.lanfairy.elly.androidsummary.aop.annotation.BehaviorTrace * *(..))")
    public void methodAnnotatedBehaviorTrace(){
    }

    //2、对进入切面的内容如何处理
    //@Before 在切入点之前运行
//    @After("methodAnnottatedWithBehaviorTrace()")
    //@Around 在切入点前后都运行
    @Around("methodAnnotatedBehaviorTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        //注解value
        String value = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value();
        long begin = System.currentTimeMillis();

        //执行被注解的方法
        Object result = joinPoint.proceed();

//        SystemClock.sleep(new Random().nextInt(3000));
        long duration = System.currentTimeMillis() - begin;
        Log.i(TAG, String.format("%s功能：%s类的%s方法执行了，用时%d ms",
            value, className, methodName, duration));

        return result;
    }


}
