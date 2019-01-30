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

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final Class<?>[] ACTIVITY = {
            ContactActivity.class,
            DataStoreAty.class,
            AsyncTaskAty.class,
            ChooseAnimationAty.class
    };
    private static final ArrayList TITLES = new ArrayList<String>(Arrays.asList(
            "短信 联系人",
            "Android 数据存储",
            "async task",
            "choose animation"
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
