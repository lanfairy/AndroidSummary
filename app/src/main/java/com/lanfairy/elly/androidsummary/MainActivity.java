package com.lanfairy.elly.androidsummary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lanfairy.elly.androidsummary.AsyncTask.AsyncTaskAty;
import com.lanfairy.elly.androidsummary.ChooseAnimation.ChooseAnimationAty;
import com.lanfairy.elly.androidsummary.DataStore.DataStoreAty;
import com.lanfairy.elly.androidsummary.Contact.ContactActivity;
import com.lanfairy.elly.androidsummary.Service.MyServiceDemoAty;
import com.lanfairy.elly.androidsummary.UI.UIActivity;

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
            MyServiceDemoAty.class
    };
    private static final ArrayList TITLES = new ArrayList<String>(Arrays.asList(
            "UI相关demo",
            "短信 联系人",
            "Android 数据存储",
            "async task",
            "choose animation",
            "Service 服务"
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
