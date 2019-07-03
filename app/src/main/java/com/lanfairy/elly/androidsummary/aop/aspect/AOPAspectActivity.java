package com.lanfairy.elly.androidsummary.aop.aspect;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.aop.annotation.LoginAOP;
import com.lanfairy.elly.androidsummary.aop.annotation.SecurityCheckAnnotation;

/***
 * 通过动态代理来实现切面编程 AOP
 */
public class AOPAspectActivity extends AppCompatActivity {
    private static final String TAG = "AOPAspectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_aspect);

    }

    @LoginAOP
    public void login(Context context) {
        Intent intent = new Intent(
                AOPAspectActivity.this, MenberAspectAcitivity.class);
        startActivity(intent);
    }


    public void me(View view) {
        login(this);
    }

    public void play(View view) {
        login(this);
    }

    public void look(View view) {
        login(this);
    }


    //为checkPhoneState使用SecurityCheckAnnotation注解，并指明调用该函数的人需要声明的权限
    //"android.permission.READ_PHONE_STATE"
    @SecurityCheckAnnotation(declaredPermission= Manifest.permission.READ_PHONE_STATE)
    public void checkPhoneState(){

        Log.v(TAG,"Read Phone State succeed");
        return;
    }
    @SecurityCheckAnnotation(declaredPermission= Manifest.permission.READ_PHONE_STATE)
    public void permission(View view) {
        Log.v(TAG,"Read Phone State succeed");
        return;
    }
}
