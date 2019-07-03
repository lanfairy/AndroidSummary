package com.lanfairy.elly.androidsummary.aop.aspect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lanfairy.elly.androidsummary.R;

public class MenberAspectAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menber_aspect);
    }
}
