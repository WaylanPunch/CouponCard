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

public class TriangleCouponCard extends LinearLayout {

    private Paint mPaint;
    private float triangle_gap;  //三角形之间的间距
    private float triangle_width;   //三角形宽
    private float triangle_height;   //三角形高
    private int triangleNum;  //三角形数量

    private float remain;

    public TriangleCouponCard(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public TriangleCouponCard(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);//该方法作用是抗锯齿
        mPaint.setDither(true);//该方法是设置防抖动
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TriangleCouponCard, 0, 0);
        triangle_gap = typedArray.getDimension(R.styleable.TriangleCouponCard_triangle_gap, 8);
        triangle_width = typedArray.getDimension(R.styleable.TriangleCouponCard_triangle_width, 10);
        triangle_height = typedArray.getDimension(R.styleable.TriangleCouponCard_triangle_height, 10);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {//remain只初始化一次
            remain = (int) (w - triangle_gap) % (triangle_width + triangle_gap);//两边的留白
        }
        triangleNum = (int) ((w - triangle_gap) / (triangle_width + triangle_gap));//三角形的个数
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < triangleNum + 1; i++) {
            float x = 0 + ((triangle_gap + triangle_width) * i);
//            canvas.drawCircle(x, 0, radius, mPaint);//顶部画圆
            Path pathTop = new Path();
            pathTop.moveTo(x, 0);// 此点为多边形的起点
            pathTop.lineTo(x + triangle_width, 0);
            pathTop.lineTo(x, triangle_height);
            pathTop.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(pathTop, mPaint);
            //canvas.drawCircle(x, getHeight(), radius, mPaint);//底部画圆
            Path pathBottom = new Path();
            pathBottom.moveTo(x, getHeight());// 此点为多边形的起点
            pathBottom.lineTo(x + triangle_width, getHeight());
            pathBottom.lineTo(x, getHeight() - triangle_height);
            pathBottom.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(pathBottom, mPaint);
        }
    }

}
