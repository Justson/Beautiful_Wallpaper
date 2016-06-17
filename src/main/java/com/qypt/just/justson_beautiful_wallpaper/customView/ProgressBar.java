package com.qypt.just.justson_beautiful_wallpaper.customView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2016/6/16.
 */
public class ProgressBar extends View {

    private int defWidth;
    private int defHeight;
    private static final int DEFAULTVALUE = 100;
    private Paint mPaint;
    private RectF rectF = new RectF();
    private Matrix matrix;
    private ObjectAnimator objectAnimator;
    private int lineLong;

    public ProgressBar(Context context) {
        this(context, null);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        Log.i("Info", "density:" + context.getResources().getDisplayMetrics().density);
        //获取宽高的默认值
        defHeight = defWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULTVALUE, context.getResources().getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5* context.getResources().getDisplayMetrics().density);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        matrix = new Matrix();

        lineLong= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        objectAnimator = ObjectAnimator.ofFloat(this,"Rotation",0,359).setDuration(2500);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        objectAnimator.cancel();
        super.onDetachedFromWindow();

    }

    @Override  //测量大小
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        this.setMeasuredDimension(widthMode == MeasureSpec.AT_MOST ? defWidth : MeasureSpec.getSize(widthMeasureSpec),
                heightMode == MeasureSpec.AT_MOST ? defHeight : MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = .5f;
        rectF.top = .5f;
        rectF.bottom = h - .10f;
        rectF.right = w - .10f;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=this.getWidth();
        int height=this.getHeight();
        mPaint.setShader(new SweepGradient(width/2,height/2, Color.WHITE,Color.BLACK));

        Log.i("ProgressBar","width:"+width+"   height:"+height);
        int j=0;
        for(int i=0;i<20;i++){

            canvas.drawLine(width/2,rectF.top+lineLong,width/2,rectF.top+2,mPaint);
            canvas.rotate(18,width/2,height/2);
        }

    }
}
