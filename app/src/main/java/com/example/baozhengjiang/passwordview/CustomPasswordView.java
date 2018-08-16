package com.example.baozhengjiang.passwordview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomPasswordView extends AppCompatEditText {

    private int passwordCount = 6;//密码位数
    private float lineWidth;// 线的宽度
    private int lineColor;// 线的颜色
    private float passwordWith;//密码框的宽度
    private float passwordHeight;//密码框的高度
    private Paint mPaint;// 画圆角边框画笔
    private Paint mLinePain;//画线画笔
    private Paint mPwdPaint;//画密码的画笔
    private int passwordColor; //  密码的颜色
    private float payPasswordWidth;//密码的直径
    private boolean isComplete;
    private OnPassWordInputListener onPassWordInputListener;




    public CustomPasswordView(Context context) {
        this(context,null);
    }

    public CustomPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        lineWidth = 1;
        lineColor = getResources().getColor(R.color.line_h_02);

        passwordColor = getResources().getColor(R.color.Global_B);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setStrokeWidth(lineWidth);
        mLinePain = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePain.setStyle(Paint.Style.STROKE);
        mLinePain.setColor(lineColor);
        mLinePain.setStrokeWidth(lineWidth);
        mPwdPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPwdPaint.setColor(passwordColor);

        payPasswordWidth = dip2px(context,16);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultWidth = 100;
        int defaultHeight = getMeasuredWidth()/passwordCount;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else if (widthMode == MeasureSpec.AT_MOST){
            width = Math.min(defaultWidth,widthSize);
        }else {
            width = defaultWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else if (widthMode == MeasureSpec.AT_MOST){
            height = Math.min(defaultHeight,heightSize);
        }else {
            height = defaultHeight;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();
        passwordWith = width/passwordCount;
        passwordHeight = passwordWith;


        //画圆角边框

        RectF rectF = new RectF(lineWidth+10,lineWidth+10,width-lineWidth-10,passwordHeight-10);

        canvas.drawRoundRect(rectF,8,8,mPaint);
        canvas.drawRoundRect(rectF,8,8,mLinePain);

        //画线


        for (int i=1;i<=passwordCount-1;i++){
            float lineX = rectF.left + passwordWith*i;
            canvas.drawLine(lineX,0,lineX,passwordWith,mLinePain);
        }

        String text = getText().toString();
        for (int i=0;i<text.length();i++){

            float cx = rectF.left +passwordWith*i+passwordWith/2;
            canvas.drawCircle(cx,passwordHeight/2,payPasswordWidth/2,mPwdPaint);

        }

        if (passwordCount == text.length()){
            if (isComplete){

            }else {
                if (onPassWordInputListener !=null){
                    isComplete = true;
                    onPassWordInputListener.complete(text);
                }
            }
        }else {
            isComplete = false;
        }
    }

    interface OnPassWordInputListener{
        void complete(String pwd);
    }

    public void setOnPassWordInputListener(OnPassWordInputListener l){
        onPassWordInputListener = l;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
