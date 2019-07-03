package com.lanfairy.elly.androidsummary.aop.dynamic_proxy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;

import java.lang.reflect.Proxy;

/***
 * 通过动态代理来实现切面编程 AOP
 */
public class AOPActivity extends AppCompatActivity implements ILogin{
    //接口的应用
    private ILogin proxyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        /**
         * 第一个参数：类加载器
         * 第二个参数：代理对象的目标类
         * 第三个参数：回调处理类
         */
        proxyLogin = (ILogin) Proxy.newProxyInstance(
                this.getClassLoader(),
                new Class[]{ILogin.class},
                new LoginHandler(this));
    }

    public void me(View view) {
        proxyLogin.login();
    }

    public void play(View view) {
        proxyLogin.login();
    }

    public void look(View view) {
        proxyLogin.login();
    }

    @Override
    public void login() {
        Intent intent = new Intent(
                AOPActivity.this, MenberAcitivity.class);
        startActivity(intent);
    }
}
