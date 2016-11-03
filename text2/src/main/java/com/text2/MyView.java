package com.text2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${Duxiaoke} on 2016/11/2.
 */

public class MyView extends View {


    private Paint mpaint;
    private float mradius;
    private int width;
    private int height;
    private int mradius2;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mradius = 30;
        mradius2 = 60;
        mpaint = new Paint();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthCurr = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightCurr = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthCurr;
        } else {
            width = (int) mradius2 * 2 + getPaddingLeft() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightCurr;
        } else {
            height = (int) mradius2 * 2 + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth() / 2;
        height = getHeight() / 2;
        mpaint.setColor(Color.GRAY);
        canvas.drawCircle(width, height, mradius2, mpaint);
        mpaint.setColor(Color.BLACK);
        canvas.drawCircle(width, height, mradius, mpaint);
        mpaint.setColor(Color.GREEN);
        String s = "我";
        Rect rect = new Rect();
        mpaint.getTextBounds(s, 0, s.length(), rect);
        canvas.drawText("我", width - rect.width() / 2, height + rect.height() / 2, mpaint);

    }


}
