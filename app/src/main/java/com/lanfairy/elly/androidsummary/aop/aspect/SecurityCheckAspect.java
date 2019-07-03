package com.lanfairy.elly.androidsummary.aop.aspect;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.lanfairy.elly.androidsummary.aop.annotation.SecurityCheckAnnotation;
import com.lanfairy.elly.androidsummary.utils.SecurityCheckManager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecurityCheckAspect {
    private static final String TAG = "SecurityCheckAspect";

    /**
     * 来看这个Pointcut，首先，它在选择Jpoint的时候，把@SecurityCheckAnnotation使用上了，这表明所有那些public的，
     * 并且携带有这个注解的API都是目标JPoint
     * 接着，由于我们希望在函数中获取注解的信息，所有这里的poincut函数有一个参数，参数类型是
     * SecurityCheckAnnotation，参数名为ann
     * 这个参数我们需要在后面的advice里用上，所以pointcut还使用了@annotation(ann)这种方法来告诉
     * AspectJ，这个ann是一个注解
     */
    @Pointcut("execution(@com.lanfairy.elly.androidsummary.aop.annotation.SecurityCheckAnnotation * *(..)) && @annotation(ann)")
    public void checkPermssion(SecurityCheckAnnotation ann) {
    }

    /**
     * 接下来是advice，advice的真正功能由check函数来实现，这个check函数第二个参数就是我们想要
     * 的注解。在实际运行过程中，AspectJ会把这个信息从JPoint中提出出来并传递给check函数。
     * 若使用@Around时 neededPermission 可通过joinPoint 来获取
     * 而使用@Before  joinPoint 为 null
     */
    @Before("checkPermssion(securityCheckAnnotation)")
    public void check(JoinPoint joinPoint, SecurityCheckAnnotation securityCheckAnnotation) {
        //从注解信息中获取声明的权限。
        String neededPermission = securityCheckAnnotation.declaredPermission();
        Log.e(TAG, joinPoint.getThis().toString());
        Log.e(TAG, joinPoint.toShortString());
        Activity activity = null;
        if (joinPoint.getThis() instanceof Activity){
            activity = (Activity) joinPoint.getThis();
        }
        else if (joinPoint.getThis() instanceof Fragment){
            activity = ((Fragment)joinPoint.getThis()).getActivity();
        }
//        else if (joinPoint.getThis() instanceof View){
//            activity = ((View) joinPoint.getThis()).getContext()
//        }
        Log.e(TAG, "\tneeded permission is " + neededPermission);

        SecurityCheckManager manager = SecurityCheckManager.getInstance();
        manager.checkPermission((Activity) joinPoint.getThis(),neededPermission);
//        if(manager.checkPermission((Activity) joinPoint.getThis(),neededPermission) == false){
//            Log.e(TAG, "\tNeed to declare permission:" + neededPermission);
////            throw new SecurityException("Need to declare permission:" + neededPermission);
//        }

        return;
    }

}
