package com.lanfairy.elly.androidsummary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.UI.Acvivity.LoadViewActivity;
import com.lanfairy.elly.androidsummary.UI.Acvivity.RevealDrawableActivity;

import java.util.ArrayList;

public class UIActivity extends AppCompatActivity {
    private PullRefreshRecyclerView mPullRefreshRecyclerView;

    private ArrayList<Body> mBodies;

    private LinearLayoutManager mLayoutManager;
    private MyAdapter mMyAdapter;

    Handler mHandler = new Handler();

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        initData();
        initView();

    }

    private void initView() {
        mPullRefreshRecyclerView = findViewById(R.id.real_pull_refresh_view);
        mLayoutManager = new LinearLayoutManager(this);
        mMyAdapter = new MyAdapter(this, mBodies);
        mMyAdapter.setViewHolderOnClickListener(new MyAdapter.ViewHolderOnClickListener() {
            @Override
            public void onItemViewClick(View v, int position) {
                if (position == 0)
                    UIActivity.this.startActivity(new Intent(UIActivity.this, LoadViewActivity.class));
                if (position == 1) {
                    UIActivity.this.startActivity(new Intent(UIActivity.this, RevealDrawableActivity.class));
                }

            }
        });
        mPullRefreshRecyclerView.setLayoutManager(mLayoutManager);
        mPullRefreshRecyclerView.setAdapter(mMyAdapter);
        mPullRefreshRecyclerView.setOnPullListener(new PullRefreshRecyclerView.OnPullListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBodies.add(0, new Body("下拉刷新数据 ", 66));
                        mPullRefreshRecyclerView.refreshFinish();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            mBodies.add(new Body("上拉加载的数据", 666));
                        }
                        mPullRefreshRecyclerView.loadMroeFinish();
                    }
                }, 1500);
            }
        });
    }

    private void initData() {
        mBodies = new ArrayList<>();
        for (int j = 0; j < 17; j++) {
            mBodies.add(new Body("test" + j * 5, 100));
        }


    }
}
