package com.lanfairy.elly.androidsummary.aop.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lanfairy.elly.androidsummary.aop.dynamic_proxy.SharePreferenceUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoginAOPAspect {
    private static final String TAG = LoginAOPAspect.class.getSimpleName();

    //定义切面的规则
    //1、就在原来的应用中那些注解的地方放到当前切面进行处理
    //execution（注解名   注解用的地方）
    @Pointcut("execution(@com.lanfairy.elly.androidsummary.aop.annotation.LoginAOP * *(..))")
    public void methodLoginAOPAspect() {
    }

    //2、对进入切面的内容如何处理
    //@Before 在切入点之前运行
//    @After("methodAnnottatedWithBehaviorTrace()")
    //@Around 在切入点前后都运行  这种情况  无法断点调试
//    @Before("methodLoginAOPAspect()")
    @Around("methodLoginAOPAspect()")
    public Object weaveJoinPoint1(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Log.i(TAG, String.format("Login功能：%s类的%s方法执行了", className, methodName));
        Object[] args = joinPoint.getArgs();
        Context context = (Context) args[0];
        //当切入点是在Acitity 或 Fragment 或 view 中时 可通过下面获取 减少切入函数参数
        /*
        if (joinPoint.getThis() instanceof Activity) {
            context = (Activity) joinPoint.getThis();
        } else if (joinPoint.getThis() instanceof Fragment) {
            context = ((Fragment) joinPoint.getThis()).getActivity();
        } else if (joinPoint.getThis() instanceof View) {
            context = ((View) joinPoint.getThis()).getContext();
        }
        */
        Object result = null;
        if (SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, context)) {
            result = joinPoint.proceed();
        } else {
            context.startActivity(new Intent(context, LoginAspectActivity.class));
        }


        Log.i(TAG, "args: " + args);
        return result;
    }


    /**
     * aop 统计时 需要切入点函数内的某个数值时  重新写个函数 将需要的数值以函数参数的形式传递给aspect
     * 通过jointPoint.getArgs获取
     */
}
