package com.example.viewcirclebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CircleBarView extends View {

    private Paint paint;
    Path path;

    public CircleBarView(Context context) {
        this(context, null);
    }

    public CircleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CircleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setTextSize(dp2px(20));
        paint.setStrokeWidth(10);
        path = new Path();
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
//        line(canvas);
//        Arc(canvas);
        bitmap(canvas);
    }

    private void bitmap(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        //移动坐标到中心位置
        canvas.translate(getWidth()/2,getHeight()/2);
        // 指定图片绘制区域(左上角的四分之一)
        Rect rect=new Rect(0,0,bitmap.getWidth()/2,getHeight()/2);
        RectF rectF=new RectF(0,0,200,400);
        canvas.drawBitmap(bitmap,rect,rectF,null);
    }

    private void line(Canvas canvas){
        canvas.drawLine(100,100,100,200,paint);
    }



    private void Arc(Canvas canvas){
        float x=(getWidth()-getHeight()/2)/2;
        float y=getHeight()/4;
        RectF rectF=new RectF(x,y,getWidth()-x,getHeight()-y);
        canvas.drawArc(rectF,180,140,true,paint);
    }
}
