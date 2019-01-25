package com.lanfairy.elly.androidsummary.DataStore;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;

public class DataStoreAty extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);
    }

    public void btnOnClick(View view) {
        switch (view.getId()){
            case R.id.sharedPreferenceBtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.dataStoreAty,  SharedPreferenceFragment.newInstance())
                        .commit();
                break;
            case R.id.sqliteBtn:
                break;
        }
    }




}
