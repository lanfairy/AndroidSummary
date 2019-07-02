package com.lanfairy.elly.androidsummary;

import android.app.Application;

import com.lanfairy.elly.androidsummary.aop.annotation.BehaviorTrace;

import org.xutils.x;
public class SummaryApplication extends Application {
    @BehaviorTrace("SummaryApplication")
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
    }
}
