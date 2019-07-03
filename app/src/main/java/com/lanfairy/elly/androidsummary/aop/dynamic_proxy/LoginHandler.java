package com.lanfairy.elly.androidsummary.aop.dynamic_proxy;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoginHandler implements InvocationHandler {
    private Context mContext;
    private Object mTarget;

    public LoginHandler(Context context) {
        this.mContext = context;
        this.mTarget = context;
    }


    /**
     * 这个invoke就是拦截Object对象的所有方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, mContext)){
            //执行拦截对象的方法
           result = method.invoke(mTarget, args);
        }else {
            Toast.makeText(mContext, "请先登陆", Toast.LENGTH_LONG).show();
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
        }
        return result;
    }
}
