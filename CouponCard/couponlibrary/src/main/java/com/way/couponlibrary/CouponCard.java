package com.way.couponlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by pc on 2016/8/15.
 */

public class CouponCard extends LinearLayout {

    private Paint mPaint;
    private float gap;  //圆之间的间距
    private float radius;   //半径
    private int circleNum;  //圆数量

    private float remain;

    public CouponCard(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public CouponCard(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);//该方法作用是抗锯齿
        mPaint.setDither(true);//该方法是设置防抖动
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CouponCard, 0, 0);
        gap = typedArray.getDimension(R.styleable.CouponCard_gap, 8);
        radius = typedArray.getDimension(R.styleable.CouponCard_radius, 10);

        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {//remain只初始化一次
            remain = (int) (w - gap) % (2 * radius + gap);//两边的留白
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));//圆形的个数
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, 0, radius, mPaint);//顶部画圆
            canvas.drawCircle(x, getHeight(), radius, mPaint);//底部画圆
        }
    }

}
