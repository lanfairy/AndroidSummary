package com.lanfairy.elly.androidsummary.UI.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

public class RevealDrawable extends Drawable {
    private final String TAG = getClass().getSimpleName();
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;

    private Rect mTmpRect = new Rect();
    private Drawable mSelectedDrawable;
    private Drawable mUnSelectedDrawable;
    private int mOrientation;

    public RevealDrawable(Drawable mSelectedDrawable, Drawable mUnSelectedDrawable, int mOrientation) {
        this.mSelectedDrawable = mSelectedDrawable;
        this.mUnSelectedDrawable = mUnSelectedDrawable;
        this.mOrientation = mOrientation;
    }

    @Override
    public void draw (Canvas canvas) {
        // 绘制
        int level = getLevel();//from 0 (minimum) to 10000
        //三个区间
        //右边区间和左边区间--设置成灰色
        if (level==10000 || level==0){
            mUnSelectedDrawable.draw(canvas);
        }
        else if (level==5000){
            mSelectedDrawable.draw(canvas);
        }
        else {
            //混合效果的Drawable
            /**
             * 将画板切割成两块-左边和右边
             */
            Rect r = mTmpRect;
            //得到当前自身Drawable的矩形区域
            Rect bounds = getBounds();
            {
                //1.先绘制灰色部分
                //level 0~5000~10000
                //比例
                //4680 / 5000   -1f
                float ratio = (level/5000f) - 1f;
                int w = bounds.width();
                if (mOrientation==HORIZONTAL){
                    //我们要抠的宽度
                    w = (int) (w*Math.abs(ratio));
                }
                int h = bounds.height();
                if (mOrientation==VERTICAL){
                    h = (int) (h*Math.abs(ratio));
                }

                int gravity = ratio<0 ? Gravity.LEFT : Gravity.RIGHT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                    gravity,//从左边还是右边开始抠
                    w,//目标矩形的宽
                    h, //目标矩形的高
                    bounds, //被抠出来的rect
                    r);//目标rect

                canvas.save();//保存画布
                canvas.clipRect(r);//切割
                mUnSelectedDrawable.draw(canvas);//画
                canvas.restore();//恢复之前保存的画布
            }
            {
                //2.再绘制彩色部分
                //level 0~5000~10000
                //比例
                float ratio = (level/5000f) - 1f;
                int w = bounds.width();
                int h = bounds.height();
                if (mOrientation==HORIZONTAL){
                    w -= (int) (w*Math.abs(ratio));
                }
                if (mOrientation==VERTICAL){
                    h -= (int) (h*Math.abs(ratio));
                }

                int gravity = ratio<0 ? Gravity.RIGHT : Gravity.LEFT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                    gravity,//从左边还是右边开始抠
                    w,//目标矩形的宽
                    h, //目标矩形的高
                    bounds, //被抠出来的rect
                    r);//目标rect
                canvas.save();
                canvas.clipRect(r);
                mSelectedDrawable.draw(canvas);
                canvas.restore();
            }

        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        //定好两个Drawable图片的宽高----边界bounds
        mUnSelectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicWidth() {
        //得到Drawble的实际宽度
        return Math.max(mSelectedDrawable.getIntrinsicWidth(),mUnSelectedDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(mSelectedDrawable.getIntrinsicHeight(),
            mUnSelectedDrawable.getIntrinsicHeight());
    }

    @Override
    protected boolean onLevelChange(int level) {
        // 当设置level的时候回调---提醒自己重新绘制
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
