package com.lanfairy.elly.androidsummary.ui.Acvivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.ui.Drawable.RevealDrawable;
import com.lanfairy.elly.androidsummary.ui.View.GallaryHorizonalScrollView;

public class RevealDrawableActivity extends AppCompatActivity {
    private int[] mImgIds = new int[]{
        R.drawable.avft,
        R.drawable.box_stack,
        R.drawable.bubble_frame,
        R.drawable.bubbles,
        R.drawable.bullseye,
        R.drawable.circle_filled,
        R.drawable.circle_outline,

        R.drawable.avft,
        R.drawable.box_stack,
        R.drawable.bubble_frame,
        R.drawable.bubbles,
        R.drawable.bullseye,
        R.drawable.circle_filled,
        R.drawable.circle_outline
    };

    private int[] mImgIds_active = new int[]{
        R.drawable.avft_active,
        R.drawable.box_stack_active,
        R.drawable.bubble_frame_active,
        R.drawable.bubbles_active,
        R.drawable.bullseye_active,
        R.drawable.circle_filled_active,
        R.drawable.circle_outline_active,

        R.drawable.avft_active,
        R.drawable.box_stack_active,
        R.drawable.bubble_frame_active,
        R.drawable.bubbles_active,
        R.drawable.bullseye_active,
        R.drawable.circle_filled_active,
        R.drawable.circle_outline_active
    };

    private Drawable[] mRevealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revealdrawable);
        initData();
        initView();
    }

    private void initData() {
        mRevealDrawables = new Drawable[mImgIds.length];
    }

    private void initView() {
        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable rd = new RevealDrawable(
                getResources().getDrawable(mImgIds[i]),
                getResources().getDrawable(mImgIds_active[i]),
                RevealDrawable.HORIZONTAL);
            mRevealDrawables[i] = rd;
        }
        hzv = findViewById(R.id.hsv);
        hzv.addImageViews(mRevealDrawables);
    }
}
