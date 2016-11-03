package text.bawei.com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/27.
 */

public class CostomView extends View{
    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Paint mPaint;
    private Rect mBound;

    public CostomView(Context context) {
        this(context,null);
    }

    public CostomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CostomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CostomView,defStyleAttr,0);

        mText = a.getString(R.styleable.CostomView_text);
        mTextColor = a.getInt(R.styleable.CostomView_textColor, Color.RED);

        int px =(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());

        mTextSize = a.getDimensionPixelSize(R.styleable.CostomView_textSize,px);

        a.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);

        mBound = new Rect();
//        mPaint.getTextBounds(mText, 0, mText.length(), mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = randomText();

                requestLayout();
                postInvalidate();
            }
        });
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);

            float textWidth = mBound.width();

            int desired = (int) (getPaddingLeft()+textWidth+getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textheight = mBound.height();
            int desired = (int) (getPaddingTop()+textheight+getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(getWidth()-16,16,8 ,mPaint);

        mPaint.setColor(mTextColor);

        canvas.drawText(mText, getWidth() / 2-mBound.width()/2 ,
                getHeight() / 2 + mBound.height()/2, mPaint);
    }
}

