package com.lanfairy.elly.androidsummary.DataStore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.lanfairy.elly.androidsummary.DataStore.SharedPreferenceFragment;

import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class UserInfoUtil {
    public static final String USERINF_FILENAME = "userinfo";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //保存用户名密码
    public static boolean saveUserInfo(Context context, String username, String password) {
        //1.通过Context对象创建一个SharedPreference对象
        //name:sharedpreference文件的名称    mode:文件的操作模式
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences share = context.getSharedPreferences(USERINF_FILENAME, Context.MODE_PRIVATE);
        //2.通过sharedPreferences对象获取一个Editor对象
        Editor editor = share.edit();
        //3.往Editor中添加数据
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        //4.提交Editor对象

        return editor.commit();
    }

    //获取用户名密码
    public static Map<String, String> getUserInfo(Context context) {

        //1.通过Context对象创建一个SharedPreference对象
//        SharedPreferences shared = context.getSharedPreferences(USERINF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        //2.通过sharedPreference获取存放的数据
        //key:存放数据时的key   defValue: 默认值,根据业务需求来写
        Map map = new HashMap<String, String>();
        map.put(USERNAME, shared.getString(USERNAME, ""));
        map.put(PASSWORD, shared.getString(PASSWORD, ""));

        return map;
    }

    //删除用户信息
    public static void deleteUserInfo(Context context) {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = shared.edit();
//        edit.clear();
        if (shared.contains(USERNAME))
            edit.remove(USERNAME);
        if (shared.contains(PASSWORD))
            edit.remove(PASSWORD);
        edit.commit();
    }
}
