package com.wwj.popupwindow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * 自定义Button，可以实现空心，实心，带边框的实心按钮
 * Created by wangrui on 2017/2/6.
 */

public class CommonWidgetButton extends Button {

    private float boarderSize;
    private int boarderColor;
    private int fillColor;
    private int pressColor;
    private float cornerRadius;
    private RectF rectF;
    private Paint mPaint;

    public CommonWidgetButton(Context context) {
        super(context);
    }

    public CommonWidgetButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonWidgetButton);
        boarderColor = ta.getColor(R.styleable.CommonWidgetButton_boarder_color, 0);
        fillColor = ta.getColor(R.styleable.CommonWidgetButton_fill_color, 0);
        boarderSize = ta.getDimension(R.styleable.CommonWidgetButton_boarder_size, 0);
        cornerRadius = ta.getFloat(R.styleable.CommonWidgetButton_corner_radius, 0);
        pressColor = ta.getColor(R.styleable.CommonWidgetButton_press_color, 0);
        mPaint = new Paint();
        rectF = new RectF();
        mPaint.setAntiAlias(true);   //抗锯齿

    }

    public CommonWidgetButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (boarderSize > 0 && fillColor == 0) {//空心圆角带边框矩形

            mPaint.setStrokeWidth(boarderSize);
            mPaint.setStyle(Paint.Style.STROKE);   // 设置样式-空心矩形
            mPaint.setColor(boarderColor);
            rectF.set(cornerRadius, cornerRadius, this.getWidth() - cornerRadius, this.getHeight() - cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);

        } else if (boarderSize > 0 && fillColor != 0) {//实心圆角带边框矩形

            mPaint.setStyle(Paint.Style.FILL);//实心
            mPaint.setColor(fillColor);
            rectF.set(cornerRadius, cornerRadius, this.getWidth() - cornerRadius, this.getHeight() - cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius - boarderSize, cornerRadius - boarderSize, mPaint);

            mPaint.setStyle(Paint.Style.STROKE);//空心
            mPaint.setStrokeWidth(boarderSize);
            mPaint.setColor(boarderColor);
            rectF.set(cornerRadius, cornerRadius, this.getWidth() - cornerRadius, this.getHeight() - cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);

        } else if (boarderSize == 0 && fillColor != 0) {//实心圆角不带边框矩形
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(fillColor);
            rectF.set(cornerRadius, cornerRadius, this.getWidth(), this.getHeight());
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);

        } else {//默认白色填充按钮
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#FFFFFF"));
            rectF.set(cornerRadius, cornerRadius, this.getWidth(), this.getHeight());
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        }

        if (ACTION.equals("ACTION_DOWN")) {
            mPaint.setStyle(Paint.Style.FILL);
            if(pressColor==0){//点击颜色默认是黑色半透明
                pressColor=getResources().getColor(R.color.black);
            }
            mPaint.setColor(pressColor);
            mPaint.setAlpha(100);
            rectF.set(cornerRadius, cornerRadius, this.getWidth() - cornerRadius, this.getHeight() - cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        } else if (ACTION.equals("ACTION_UP")) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(getResources().getColor(R.color.white));
            mPaint.setAlpha(0);
            rectF.set(cornerRadius, cornerRadius, this.getWidth(), this.getHeight());
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        }

    }

    private String ACTION = "";

    @Override
    public void setOnTouchListener(OnTouchListener l) {

        l = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ACTION = "ACTION_DOWN";
                    invalidate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ACTION = "ACTION_UP";
                    invalidate();
                }

                return false;
            }
        };

        super.setOnTouchListener(l);
    }
}
