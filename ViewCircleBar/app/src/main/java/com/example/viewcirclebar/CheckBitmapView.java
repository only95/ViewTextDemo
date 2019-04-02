package com.example.viewcirclebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CheckBitmapView extends View {


    private int currentPage = -1;
    private int maxPage = 22;
    private int status=0;//判断开启或关闭的状态
    private Paint paint;
    private Bitmap bitmap;
    private int animDuration=500;//动画时长
    private Handler handler;

    private boolean checkBox=false;

    public CheckBitmapView(Context context) {
        this(context,null);
        init(context);
    }

    public CheckBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
    }

    public CheckBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.checkmark);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (currentPage < maxPage && currentPage >= 0) {
                    if (status==0){
                        return;
                    }
                    if (status==1){
                        currentPage++;
                    }else if (status==2){
                        currentPage--;
                    }
                    invalidate();
                    this.sendEmptyMessageDelayed(0,animDuration/maxPage);
                    Log.e("AAA", "current=" + currentPage);
                }else{
                    if (checkBox){
                        currentPage=maxPage-1;
                    }else{
                        currentPage=-1;
                    }
                    invalidate();
                    status=0;
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, 240, paint);
        int sideLength = bitmap.getHeight();
        int x = (getWidth() - getHeight() / 2) / 2;
        int y = getHeight() / 4;
        Rect rect = new Rect(sideLength * currentPage, 0, sideLength * (currentPage + 1), sideLength);
        Rect rect1 = new Rect(-200, -200, 200, 200);
        canvas.drawBitmap(bitmap, rect, rect1, null);


    }


    public void check(){
        if (status!=0||checkBox){
            return;
        }
        status=1;
        currentPage=0;
        handler.sendEmptyMessageDelayed(0,animDuration/maxPage);
        checkBox=true;
    }

    public void unCheck(){
        if (status!=0||!checkBox){
            return;
        }
        status=2;
        currentPage=maxPage-1;
        handler.sendEmptyMessageDelayed(0,animDuration/maxPage);
        checkBox=false;
    }
}
