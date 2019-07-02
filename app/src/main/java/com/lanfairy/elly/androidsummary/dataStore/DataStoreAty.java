package com.lanfairy.elly.androidsummary.dataStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lanfairy.elly.androidsummary.dataStore.util.SqliteOpenHelperUtil;
import com.lanfairy.elly.androidsummary.R;

public class DataStoreAty extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);

        SqliteOpenHelperUtil sqliteUtil = new SqliteOpenHelperUtil(DataStoreAty.this);
//        SQLiteDatabase database = sqliteUtil.getReadableDatabase();
    }

    public void btnOnClick(View view) {
        switch (view.getId()){
            case R.id.sharedPreferenceBtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.dataStoreAty,  SharedPreferenceFragment.getInstance())
                        .commit();
                break;
            case R.id.sqliteBtn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.dataStoreAty, SqliteDBFragment.getInstance())
                        .commit();
            case R.id.litepal_sqlite_Btn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.dataStoreAty, LitePalSqliteDBFragment.getInstance())
                        .commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
