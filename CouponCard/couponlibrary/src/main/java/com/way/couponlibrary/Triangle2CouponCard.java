package com.way.couponlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by pc on 2016/8/15.
 */

public class Triangle2CouponCard extends LinearLayout {

    private Paint mPaint;
    private float gap;  //圆之间的间距
    private float radius;   //半径
    private float height;
    private int circleNum;  //圆数量

    private float remain;

    public Triangle2CouponCard(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public Triangle2CouponCard(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);//该方法作用是抗锯齿
        mPaint.setDither(true);//该方法是设置防抖动
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Triangle2CouponCard, 0, 0);
        gap = typedArray.getDimension(R.styleable.Triangle2CouponCard_triangle2_gap, 8);
        radius = typedArray.getDimension(R.styleable.Triangle2CouponCard_triangle2_radius, 10);
        height = typedArray.getDimension(R.styleable.Triangle2CouponCard_triangle2_height, 8);

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
            float x = radius + remain / 2 + ((gap + radius * 2) * i);
//            canvas.drawCircle(x, 0, radius, mPaint);//顶部画圆
//            canvas.drawCircle(x, getHeight(), radius, mPaint);//底部画圆

            Path pathTop = new Path();
            pathTop.moveTo(x, 0);// 此点为多边形的起点
            pathTop.lineTo(x + radius * 2, 0);
            pathTop.lineTo(x + radius, height);
            pathTop.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(pathTop, mPaint);

            Path pathBottom = new Path();
            pathBottom.moveTo(x, getHeight());// 此点为多边形的起点
            pathBottom.lineTo(x + radius * 2, getHeight());
            pathBottom.lineTo(x + radius, getHeight() - height);
            pathBottom.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(pathBottom, mPaint);
        }
    }

}
