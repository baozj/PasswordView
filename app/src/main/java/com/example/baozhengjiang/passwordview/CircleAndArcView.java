package com.example.baozhengjiang.passwordview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleAndArcView extends View {

    private String text;
    private int textolor;
    private float textSize;
    private int circleColor;
    private int arcColor;
    private float startAngle;
    private float sweepAngle;

    private Paint circlePaint;
    private Paint arcPaint;
    private Paint textPaint;
    private int width;


    public CircleAndArcView(Context context) {
        this(context,null);

    }

    public CircleAndArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleAndArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CircleAndArcView);
        text = ta.getString(R.styleable.CircleAndArcView_text);
        textolor = ta.getColor(R.styleable.CircleAndArcView_textColor,0);
        textSize = ta.getDimension(R.styleable.CircleAndArcView_textSize,0);
        circleColor = ta.getColor(R.styleable.CircleAndArcView_circleColor,0);
        arcColor = ta.getColor(R.styleable.CircleAndArcView_arcColor,0);
        startAngle =ta.getFloat(R.styleable.CircleAndArcView_startAngle,0);
        sweepAngle = ta.getFloat(R.styleable.CircleAndArcView_sweepAngle,90);

        ta.recycle();





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(arcColor);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(width*0.1f);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textolor);
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        RectF rectF = new RectF(width*0.1f,width*0.1f,width*0.9f,width*0.9f);
        canvas.drawArc(rectF,startAngle,sweepAngle,false,arcPaint);
        //绘制圆
        int circleXY = width/2;
        int radius = width/2/2;
        canvas.drawCircle(circleXY,circleXY,radius,circlePaint);
        //绘制文字
        canvas.drawText(text,0,text.length(),circleXY,circleXY,textPaint);
    }

}
