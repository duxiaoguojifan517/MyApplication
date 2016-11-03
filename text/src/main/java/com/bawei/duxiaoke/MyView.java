package com.bawei.duxiaoke;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${Duxiaoke} on 2016/11/1.
 */


public class MyView extends View{

    private Paint mpaint;
    private int mradius;
    private int mcolor;
    private String mtext;
    private int width;
    private int height;


    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mradius = 30;
        mcolor = Color.RED;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthCurr = MeasureSpec.getSize(widthMeasureSpec);


        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightCurr = MeasureSpec.getSize(heightMeasureSpec);

        width = 0;
        height = 0;
        if (widthMode==MeasureSpec.EXACTLY){
            width = widthCurr;
        }else{
            width = (int) mradius*2+getPaddingLeft()+getPaddingRight();
        }
        if (heightMode==MeasureSpec.EXACTLY){
            height = heightCurr;
        }else{
            height = (int) mradius*2+getPaddingBottom()+getPaddingTop();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth()/2;
        height = getHeight()/2;
        //new 一个画笔
        mpaint = new Paint();
        //设置画笔的颜色

//        mpaint.setColor(Color.GREEN);
//        canvas.drawCircle(weith,height,mradius,mpaint);
        mpaint.setColor(mcolor);
        //设置圆形
//        canvas.drawCircle(width,height,mradius,mpaint);
        //画一个线条
        canvas.drawLine(0,0,100,100,mpaint);
        //设置字体
        mpaint.setColor(Color.BLUE);
        mtext = "A";
        //测量文字的大小
        Rect rect = new Rect();
        mpaint.getTextBounds(mtext,0,mtext.length(),rect);
        //设置文本
        canvas.drawText(mtext,width- rect.width()/2,height+ rect.height()/2,mpaint);



    }
}
