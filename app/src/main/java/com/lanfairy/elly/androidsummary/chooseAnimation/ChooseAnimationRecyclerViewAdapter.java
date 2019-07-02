package com.lanfairy.elly.androidsummary.chooseAnimation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.lanfairy.elly.androidsummary.R;

import com.lanfairy.elly.androidsummary.chooseAnimation.ChooseAnimationFragment.OnListFragmentInteractionListener;
import com.lanfairy.elly.androidsummary.chooseAnimation.dummy.DummyContent.DummyItem;

import java.util.List;


public class ChooseAnimationRecyclerViewAdapter extends RecyclerView.Adapter<ChooseAnimationRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ChooseAnimationRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_chooseanimation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).details);
        holder.mIconImg.setImageResource(mValues.get(position).iconImg);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mIconImg;
        public final ScrollView mScrollView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mScrollView = (ScrollView)view.findViewById(R.id.scrollView);
            mIdView = (TextView) view.findViewById(R.id.textView);
//            mIdView.setMovementMethod(ScrollingMovementMethod.getInstance());
            mIconImg = (ImageView) view.findViewById(R.id.iconImg);
        }
//        public boolean isTouchInTextView(float x,float y)
//        {
//            int[] pos = new int[2];
//            mScrollView.getLocationOnScreen(pos);
//            int width = mScrollView.getMeasuredWidth();
//            int height = mScrollView.getMeasuredHeight();
//            return x >= pos[0] && x <= pos[0] + width && y >= pos[1] && y <= pos[1] + height;
//        }
        public boolean isTouchInTextView(float x,float y)
        {
            int[] pos = new int[2];
            mIdView.getLocationOnScreen(pos);
            int width = mIdView.getMeasuredWidth();
            int height = mIdView.getMeasuredHeight();
            return x >= pos[0] && x <= pos[0] + width && y >= pos[1] && y <= pos[1] + height;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIconImg.getResources() + "'";
        }
    }
}
