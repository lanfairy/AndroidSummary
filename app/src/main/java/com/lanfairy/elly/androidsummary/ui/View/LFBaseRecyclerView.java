package com.lanfairy.elly.androidsummary.ui.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class LFBaseRecyclerView extends RecyclerView {
    private static final String TAG = LFBaseRecyclerView.class.getSimpleName();
    public interface OnItemClickListener{
        void onItemClick(int position, Object ob);
    }

    protected List mListArray = new ArrayList();
    protected RecyclerView.Adapter mAdapter = null;
    private OnItemClickListener mListener = null;
    private RecyclerView.LayoutManager mLayoutManager;
    protected int mLayoutId = 0;

    public LFBaseRecyclerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public LFBaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LFBaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }
    private void initView() {

    }

}
