package com.lanfairy.elly.androidsummary.UI.LoadView;

import android.arch.lifecycle.CompositeGeneratedAdaptersObserver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.View;

import com.lanfairy.elly.androidsummary.R;


public class LoadView extends View {
    private float mCurrentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float[] mTan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private float[] mPos;                // 当前点的实际位置
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private Paint mCirclePaint;

    private Matrix mMatrix; // 矩阵,用于对图片进行一些操作
    private Bitmap mBitmap; // 箭头图片


    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(this.getContext());
    }

    private void init(Context context) {
        mTan = new float[2];
        mPos = new float[2];
        mMatrix = new Matrix();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;//缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCurrentValue += 0.005;
        if (mCurrentValue >= 1)
            mCurrentValue = 0;

        canvas.drawColor(Color.WHITE);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        Path path = new Path();
        float radius = Math.min(mViewWidth, mViewHeight) * 0.4f;
        path.addCircle(0, 0, radius, Path.Direction.CW);
//        path.addRect(mViewWidth*0.1f,mViewHeight*0.1f, mViewWidth*0.8f, mViewHeight*0.8f, Path.Direction.CCW);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        pathMeasure.getPosTan(pathMeasure.getLength()*mCurrentValue, mPos, mTan);
        float degress = (float)(Math.atan2(mTan[1], mTan[0]) * 180 / Math.PI);

        mMatrix.reset();
        mMatrix.postRotate(degress, mBitmap.getWidth()/2, +mBitmap.getHeight()/2);
        mMatrix.postTranslate(mPos[0]-mBitmap.getWidth()/2, mPos[1]-mBitmap.getHeight()/2);

        canvas.drawPath(path, mCirclePaint);
        canvas.drawBitmap(mBitmap,mMatrix, mPaint);

        invalidate();
    }
}
