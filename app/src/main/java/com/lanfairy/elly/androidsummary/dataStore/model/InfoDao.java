package com.lanfairy.elly.androidsummary.dataStore.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lanfairy.elly.androidsummary.dataStore.util.SqliteOpenHelperUtil;

public class InfoDao {
    private static final String TAG = "InfoDao";
    public static class InfoBean {
        private String name;
        private String phone;

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public InfoBean(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
    }


    private SqliteOpenHelperUtil sqliteOpenHelper;

    public InfoDao(Context context) {
        //创建一个帮助类对象
        sqliteOpenHelper = new SqliteOpenHelperUtil(context);
    }

    //增
    public boolean add(InfoBean bean) {
        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
        //是用map封装的对象，用来存放值
        ContentValues values = new ContentValues();
        values.put("name", bean.name);
        values.put("phone", bean.phone);
        //table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
        long result = db.insert("info", null, values);//底层是在拼装sql语句
        //关闭数据库对象
        db.close();
        //-1代表添加失败
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public int del(String name) {
        SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
        //table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
        int result = db.delete("info", "name = ?", new String[]{name});
        db.execSQL("delete from info where name=?", new String[]{name});
        db.close();
        System.out.println("删除的结果 : "+result);

        return result;
    }

    public int update(InfoBean bean) {
        SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("phone", bean.phone);
        //table:表名, values：更新的值, whereClause:更新的条件, whereArgs：更新条件的占位符的值,返回值：成功修改多少行
        int result = db.update("info", value, "name=?", new String[]{bean.name});
        db.close();
        return result;
    }

    public void query(String name) {
        SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
        //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
        //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor = db.query("info", new String[]{"_id", "name", "phone"}, "name=?", new String[]{name}, null, null, "_id desc");
        if (cursor != null && cursor.getCount() > 0) {
            //循环遍历结果集，获取每一行的内容
            while (cursor.moveToNext()) {//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);
//                System.out.println("_id:" + id + "name:" + name_str + ";phone:" + phone);
                Log.i(TAG, "query: "+"_id:" + id + "name:" + name_str + ";phone:" + phone);
            }
            //关闭结果集
            cursor.close();
        }
        db.close();
    }


}


/**
 * adb shell
 * adb devices
 *  adb -s 设备的序列号 shell
 *  cd /data/data/com.lanfairy.elly.androidsummary/databases
 * */