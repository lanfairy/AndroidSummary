package com.lanfairy.elly.androidsummary.SMS;

import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.SMS.util.SMSUtil;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class SMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
    }

    //点击按钮查询短信内容 然后把短信内容进行备份
    public void smsBackup(View view) {
        SMSUtil.backup(this);
    }
}
