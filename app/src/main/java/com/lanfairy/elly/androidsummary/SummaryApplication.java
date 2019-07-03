package com.lanfairy.elly.androidsummary;

import android.app.Application;
import android.content.Context;

import org.xutils.x;
public class SummaryApplication extends Application {
    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
    }
}
