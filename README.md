# A CouponCard Demo #

----------

# A CouponCard Demo #

----------

## 前言

![Screenshot](https://github.com/WaylanPunch/CouponCard/blob/master/Screenshot001.jpg?raw=true)

创建几种优惠券的花边样式，我画了3种：
- 一种是圆形花边

![Circle Border](https://github.com/WaylanPunch/CouponCard/blob/master/Screenshot003.jpg?raw=true)

- 一种是直角花边

![Triangle Border](https://github.com/WaylanPunch/CouponCard/blob/master/Screenshot004.jpg?raw=true)

- 还有一种是倒三角花边

![Reversed Triangle Border](https://github.com/WaylanPunch/CouponCard/blob/master/Screenshot005.jpg?raw=true)

## 自定义View

需要覆写以下两个方法：

    onSizeChanged(int, int, int, int)	//Called when the size of this view has changed.
    //View的尺寸发生改变时调用

    onDraw(android.graphics.Canvas)	 //Called when the view should render its content.
    //View需要绘制内容时调用

**以绘制三角形为例：**
在构造函数里初始化画笔，

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //mPaint.setAntiAlias(true);//该方法作用是抗锯齿
    mPaint.setDither(true);//该方法是设置防抖动
    mPaint.setColor(Color.WHITE);
    mPaint.setStyle(Paint.Style.FILL);

- 关于**setAntiAlias()**方法官方的解释为：


    void    setAntiAlias(boolean aa)
    
    //Helper for setFlags(), setting or clearing the ANTI_ALIAS_FLAG bit AntiAliasing smooths out the edges of what is being drawn, but is has no impact on the interior of the shape.
    //我翻译一下：setFlags()的辅助方法，用于设置或清除ANTI_ALIAS_FLAG的标志位，绘制形状的抗锯齿光滑边缘效果，但是在形状内部不起作用。

- 关于**setDither()**方法官方的解释为：


    void	setDither(boolean dither)
    
    //Helper for setFlags(), setting or clearing the DITHER_FLAG bit Dithering affects how colors that are higher precision than the device are down-sampled.
    //setFlags()的辅助方法，用于设置或清除DITHER_FLAG的标志位，比一般设备更高颜色精度的抖动效果。

在**onDraw()**方法里绘制边缘图形，

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float x = radius + remain / 2 + ((gap + radius * 2) * i);
            // canvas.drawCircle(x, 0, radius, mPaint);//顶部画圆
            // canvas.drawCircle(x, getHeight(), radius, mPaint);//底部画圆

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

>Path类用于构建多边形图形，drawPath()方法有两个参数

    void	drawPath(Path path, Paint paint)
    // Draw the specified path using the specified paint.
    // 用指定的画笔对象绘制指定图形

## 我说完了