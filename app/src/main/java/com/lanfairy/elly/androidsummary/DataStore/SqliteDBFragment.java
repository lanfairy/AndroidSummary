package com.lanfairy.elly.androidsummary.DataStore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lanfairy.elly.androidsummary.DataStore.model.InfoDao;
import com.lanfairy.elly.androidsummary.DataStore.model.InfoDao.InfoBean;
import com.lanfairy.elly.androidsummary.DataStore.util.SqliteOpenHelperUtil;
import com.lanfairy.elly.androidsummary.R;


public class SqliteDBFragment extends Fragment implements View.OnClickListener {

    private TextView tvName;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnUpdata;
    private Button btnQuery;
    private TextView tvPhone;
    private Context mContext;

    //创建一个dao对象做增删改查
    private InfoDao infoDao;

    public InfoDao getInfoDao() {
        if (infoDao == null) {
            infoDao = new InfoDao(mContext);
        }
        return infoDao;
    }

    public SqliteDBFragment() {
        // Required empty public constructor

    }


    public static SqliteDBFragment newInstance() {
        SqliteDBFragment fragment = new SqliteDBFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sqlite_db, container, false);
        tvName = root.findViewById(R.id.tv_name);

        tvPhone = root.findViewById(R.id.tv_phone);
        btnAdd = root.findViewById(R.id.btn_add);
        btnDelete = root.findViewById(R.id.btn_delete);
        btnUpdata = root.findViewById(R.id.btn_update);
        btnQuery = root.findViewById(R.id.btn_query);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdata.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }


    @Override
    public void onClick(View v) {
        String name = tvName.getText().toString().trim();
        String phone = tvPhone.getText().toString().trim();

        InfoBean infoBean = new InfoBean(name, phone);
        switch (v.getId()) {
            case R.id.btn_add:
                getInfoDao().add(infoBean);
                break;
            case R.id.btn_delete:
                getInfoDao().del(name);
                break;
            case R.id.btn_update:
                getInfoDao().update(infoBean);
                break;
            case R.id.btn_query:
                getInfoDao().query(name);
                break;
        }
    }
}
