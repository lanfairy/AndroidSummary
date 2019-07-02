package com.lanfairy.elly.androidsummary.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lanfairy.elly.androidsummary.R;

public class MyServiceDemoAty extends AppCompatActivity {
    private MyService.DownloadBinder mDownloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service_demo_aty);
        initView();

    }

    private void initView() {
        findViewById(R.id.start_service).setOnClickListener(v -> {
            Intent startIntent = new Intent(MyServiceDemoAty.this, MyService.class);
            startService(startIntent);//启动服务
        });
        findViewById(R.id.stop_service).setOnClickListener(v -> {
            Intent stopIntent = new Intent(MyServiceDemoAty.this, MyService.class);
            stopService(stopIntent);
        });
        findViewById(R.id.bind_service).setOnClickListener(v -> {
            Intent bindIntent = new Intent(MyServiceDemoAty.this, MyService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        });
        findViewById(R.id.unbind_service).setOnClickListener(v -> {
            unbindService(connection);
        });
    }

}
