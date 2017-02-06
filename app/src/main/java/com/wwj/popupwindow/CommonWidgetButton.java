package com.wwj.popupwindow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by wangrui on 2017/2/6.
 */

public class CommonWidgetButton extends Button {

    private float boarderSize;
    private int boarderColor;
    private int fillColor;
    private float cornerRadius;

    private Paint mPaint;

    public CommonWidgetButton(Context context) {
        super(context);
    }

    public CommonWidgetButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.CommonWidgetButton);
        boarderColor = ta.getColor(R.styleable.CommonWidgetButton_boarder_color,0);
        fillColor = ta.getColor(R.styleable.CommonWidgetButton_fill_color,0);
        boarderSize = ta.getDimension(R.styleable.CommonWidgetButton_boarder_size,0);
        cornerRadius = ta.getFloat(R.styleable.CommonWidgetButton_corner_radius,0);
        mPaint= new Paint();

    }

    public CommonWidgetButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(boarderSize>0&&fillColor==0) {//空心圆角带边框矩形
            mPaint.setAntiAlias(true);   //抗锯齿
            mPaint.setStrokeWidth(boarderSize);
            mPaint.setStyle(Paint.Style.STROKE);   // 设置样式-空心矩形
            mPaint.setColor(boarderColor);

            RectF rectF = new RectF(cornerRadius,cornerRadius,this.getWidth()-cornerRadius,this.getHeight()-cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        } else if(boarderSize>0&& fillColor!=0){//实心圆角带边框矩形
            mPaint.setAntiAlias(true);

            mPaint.setStyle(Paint.Style.FILL);//实心
            mPaint.setColor(fillColor);
            RectF rectF2 = new RectF(cornerRadius,cornerRadius,
                    this.getWidth()-cornerRadius,this.getHeight()-cornerRadius);

            canvas.drawRoundRect(rectF2, cornerRadius-boarderSize, cornerRadius-boarderSize, mPaint);

            mPaint.setStyle(Paint.Style.STROKE);//空心
            mPaint.setStrokeWidth(boarderSize);
            mPaint.setColor(boarderColor);
            RectF rectF = new RectF(cornerRadius,cornerRadius,this.getWidth()-cornerRadius,this.getHeight()-cornerRadius);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);

        } else if(boarderSize==0&&fillColor!=0){//实心圆角不带边框矩形
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(fillColor);
            mPaint.setAntiAlias(true);
            RectF rectF = new RectF(cornerRadius,cornerRadius,this.getWidth(),this.getHeight());
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        }else{//默认白色填充按钮
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#FFFFFF"));
            mPaint.setAntiAlias(true);
            RectF rectF = new RectF(cornerRadius,cornerRadius,this.getWidth(),this.getHeight());
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, mPaint);
        }

    }
}
