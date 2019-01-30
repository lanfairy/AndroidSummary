package com.lanfairy.elly.androidsummary.Contact.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

public class SMSUtil {
    public static final String URISTR = "content://sms/";
    public static final String ADDRESS = "address";
    public static final String BODY = "body";
    public static final String DATE = "date";

    public static void backup(Context context) {
        try {
            //[1]获取XmlSerializer的实例
            XmlSerializer serializer = Xml.newSerializer();
            //[2]设置序列化器参数
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup.xml");
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos, "utf-8");
            //[3]写xml文档开头
            serializer.startDocument("utf-8", true);
            //[4]写xml的根节点
            serializer.startTag(null, "smss");
            //[5]构造uri
            Uri uri = Uri.parse(URISTR);
            //[6]由于短信的数据库已经通过内容提供者暴露出来 所以我们直接通过内容解析者查询
            Cursor cursor = context.getContentResolver().query(uri, new String[]{ADDRESS, DATE, BODY}, null, null, null);
            while (cursor.moveToNext()){
                String address = cursor.getString(0);
                String date = cursor.getString(1);
                String body = cursor.getString(2);
                //[7]写sms节点
                serializer.startTag(null, "sms");

                //[8]写address节点
                serializer.startTag(null, ADDRESS);
                serializer.text(address);
                serializer.endTag(null, ADDRESS);

                //[9]写date节点
                serializer.startTag(null, DATE);
                serializer.text(date);
                serializer.endTag(null, DATE);

                //[10]写body节点
                serializer.startTag(null, BODY);
                serializer.text(body);
                serializer.endTag(null, BODY);
                serializer.endTag(null, "sms");
            }
            serializer.endTag(null, "smss");
            serializer.endDocument();
            fos.close();
            Toast.makeText(context, "短信备份成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
