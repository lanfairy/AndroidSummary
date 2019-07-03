package com.lanfairy.elly.androidsummary.aop.aspect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.aop.dynamic_proxy.SharePreferenceUtil;

public class LoginAspectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aspect);
    }

    public void login(View view) {
        SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN, true, this);
        finish();
    }
}
