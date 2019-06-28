package com.lanfairy.elly.androidsummary.UI.LoadView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import java.security.cert.TrustAnchor;

public class CircleLoadingView extends View {
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mLength;
    private Path mDst;
    private float mAnimatorValue;
    private Path mPath;

    private Boolean isFirstLoad;

    public CircleLoadingView(Context context) {
        this(context, null);
    }

    public CircleLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isFirstLoad = true;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);

        mPath = new Path();

        mPathMeasure = new PathMeasure();


        mDst = new Path();

       final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        if (isFirstLoad) {
            mPath.addCircle(mViewWidth * 0.5f, mViewHeight * 0.5f, Math.min(mViewWidth, mViewHeight) * 0.4f, Path.Direction.CW);
            mPathMeasure.setPath(mPath, true);
            mLength = mPathMeasure.getLength();

            isFirstLoad = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        mDst.reset();
        mDst.lineTo(0,0);

        float stop = mAnimatorValue * mLength;
        float start = (float) (stop - (0.5 - Math.abs(mAnimatorValue-0.5))*mLength);
        mPathMeasure.getSegment(start, stop, mDst, true);

        canvas.drawPath(mDst, mPaint);
    }
}
