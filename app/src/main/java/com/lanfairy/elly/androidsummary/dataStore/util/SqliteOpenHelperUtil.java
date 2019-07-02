package com.lanfairy.elly.androidsummary.dataStore.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteOpenHelperUtil extends SQLiteOpenHelper {
    //context :上下文   ， name：数据库文件的名称 如果为null则在内存中创造一个数据库,磁盘中不会创建     factory：用来创建cursor(结果集)对象，默认为null
    //version:数据库的版本号，从1开始，如果发生改变，onUpgrade方法将会调用,4.0之后只能升不能将
    public SqliteOpenHelperUtil(Context context) {
        super(context, "info.db", null, 1);
    }

    //oncreate方法是数据库第一次创建的时候会被调用;  特别适合做表结构的初始化,需要执行sql语句；SQLiteDatabase db可以用来执行sql语句
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20))");
        db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20),phone varchar(11))");
        Log.v("数据库", "onCreate");
    }

    //onUpgrade数据库版本号发生改变时才会执行； 特别适合做表结构的修改
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table info add phone varchar(11)");
        Log.v("数据库", "onCreate");
    }
}
