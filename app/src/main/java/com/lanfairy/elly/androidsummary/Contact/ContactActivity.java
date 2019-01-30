package com.lanfairy.elly.androidsummary.Contact;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lanfairy.elly.androidsummary.Contact.TheContact.Contact;
import com.lanfairy.elly.androidsummary.Contact.util.QueryContactsUtils;
import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.Contact.util.SMSUtil;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ContactActivity extends AppCompatActivity {
    private String mySmsPkg;
    private String defaultSmsPkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            defaultSmsPkg = Telephony.Sms.getDefaultSmsPackage(this);
            mySmsPkg = this.getPackageName();
        }
        if (!defaultSmsPkg.equals(mySmsPkg)) {
            // 如果这个App不是默认的Sms App，则修改成默认的SMS APP
            // 因为从Android 4.4开始，只有默认的SMS APP才能对SMS数据库进行处理
            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, mySmsPkg);
            startActivity(intent);
        }

    }

    //点击按钮查询短信内容 然后把短信内容进行备份
    public void smsBackup(View view) {
        SMSUtil.backup(this);
    }

    //点击按钮 往短信数据库里面插入一条记录
    public void smsInsertMsg(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(mySmsPkg.equals(Telephony.Sms.getDefaultSmsPackage(ContactActivity.this))) {
                //对短信数据库进行处理
                String phoneNum = "110";
                String bodyStr = "请您马上过来一趟 否则后果自负";
                ContentValues values = new ContentValues();
                values.put(Telephony.Sms.ADDRESS, phoneNum);
                values.put(Telephony.Sms.DATE, System.currentTimeMillis());
                long dateSent = System.currentTimeMillis();// - 5000
                values.put(Telephony.Sms.DATE_SENT, dateSent);
                values.put(Telephony.Sms.READ, false);
                values.put(Telephony.Sms.SEEN, false);
                values.put(Telephony.Sms.STATUS, Telephony.Sms.STATUS_COMPLETE);
                values.put(Telephony.Sms.BODY, bodyStr);
                values.put(Telephony.Sms.TYPE, Telephony.Sms.MESSAGE_TYPE_INBOX);
    //            ContentResolver resolver = getContentResolver();
    //            Uri uri=resolver.insert(Telephony.Sms.CONTENT_URI,values);
                Uri uri = Uri.parse(SMSUtil.URISTR);
                uri = getContentResolver().insert(uri, values);
                if (uri != null) {
                    long uriId = ContentUris.parseId(uri);
                    System.out.println("uriId " + uriId);
                }

                Toast.makeText(ContactActivity.this, "Insert a short Message.",
                        Toast.LENGTH_LONG).show();



            }
        }
    }

    public void querySms(View view) {
        //打印出收件箱里的
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null, null, null);
            String msg = "";
            while (cursor.moveToNext()) {
                int dateColumn = cursor.getColumnIndex("date");
                int phoneColumn = cursor.getColumnIndex("address");
                int smsColumn = cursor.getColumnIndex("body");
//            System.out.println("count " + cursor.getCount() + " position " + cursor.getPosition());
                //把从短信中获取的时间戳换成一定格式的时间
                SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date(Long.parseLong(cursor.getString(dateColumn)));
                String time = sfd.format(date);
                msg = time + " " + cursor.getString(phoneColumn) + ":" + cursor.getString(smsColumn);
                System.out.println(msg);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //对短信数据库处理结束后，恢复原来的默认SMS APP
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsPkg);
        startActivity(intent);
        System.out.println("Recover default SMS App");
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public void queryContact(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

//       List<Contact> contactList = QueryContactsUtils.queryContacts(ContactActivity.this);
//        int i = 1;
//        for (Contact contact: contactList) {
//            Log.e("contact", contact.toString() +i++);
//        }

//        List<Contact> contactList =  QueryContactsUtils.getAllContact(ContactActivity.this);
//        int i = 1;
//        for (Contact contact: contactList) {
//            Log.e("contact", contact.toString() +i++);
//        }

        try {
            QueryContactsUtils.getContactInfoJson(ContactActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
