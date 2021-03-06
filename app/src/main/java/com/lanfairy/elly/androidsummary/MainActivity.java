package com.lanfairy.elly.androidsummary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lanfairy.elly.androidsummary.aop.annotation.BehaviorTrace;
import com.lanfairy.elly.androidsummary.aop.annotation.LoginAOP;
import com.lanfairy.elly.androidsummary.aop.aspect.AOPAspectActivity;
import com.lanfairy.elly.androidsummary.aop.dynamic_proxy.AOPActivity;
import com.lanfairy.elly.androidsummary.asyncTask.AsyncTaskAty;
import com.lanfairy.elly.androidsummary.chooseAnimation.ChooseAnimationAty;
import com.lanfairy.elly.androidsummary.dataStore.DataStoreAty;
import com.lanfairy.elly.androidsummary.contact.ContactActivity;
import com.lanfairy.elly.androidsummary.service.MyServiceDemoAty;
import com.lanfairy.elly.androidsummary.ui.UIActivity;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final Class<?>[] ACTIVITY = {
            UIActivity.class,
            ContactActivity.class,
            DataStoreAty.class,
            AsyncTaskAty.class,
            ChooseAnimationAty.class,
            MyServiceDemoAty.class,
            AOPActivity.class,
            AOPAspectActivity.class
    };
    private static final ArrayList TITLES = new ArrayList<String>(Arrays.asList(
            "UI相关demo",
            "短信 联系人",
            "Android 数据存储",
            "async task",
            "choose animation",
            "Service 服务",
            "动态代理实现面向切面编程AOP",
            "AspectJ实现面向切面编程AOP"
    ));
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mainListView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TITLES));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, ACTIVITY[position]));
            }
        });


//        fileTest();
        testRx();
    }
    @BehaviorTrace("testRx")
    private void testRx() {
        Observable.fromArray(
                1,2,3,4,5
        ).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: "+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onNext: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete ");
            }
        });
    }

    static final String LOGTAG = "文件操作";
    private void fileTest() {
        File file = new File("/storage/emulated/0/Test/test.txt");
//        File file = null;
//        try {
//            file = new File(new URI("file:///Users/elly/Desktop/AndroidStudy/filetest/test.plist"));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        if (file.exists()){
            Log.v(LOGTAG, "file exists");
        }else {
            Log.v(LOGTAG, "file not exists");
            try {
                Log.v(LOGTAG, file.getCanonicalPath());
                if (file.createNewFile()) {

                    Log.v(LOGTAG, "file create success");

               }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
