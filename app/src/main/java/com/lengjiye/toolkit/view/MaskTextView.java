package com.lengjiye.toolkit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 实现遮罩效果的进度条
 * Created by lz on 2016/6/28.
 */
public class MaskTextView extends View {

    private Context mContext;
    private Canvas mCanvas;
    private int mWidth, mHeight;
    private Paint mPaint;
    private Bitmap mBitmap1; // mCanvas绘制在这上面
    private Bitmap mBitmap2; // mCanvas绘制在这上面
    private Bitmap mBitmap3; // mCanvas绘制在这上面

    public MaskTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MaskTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MaskTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        if (isInEditMode()) {
            return;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mBitmap1 = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#ffffff"));
        first();
        second();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        if (mBitmap1 != null) {
            canvas.drawBitmap(mBitmap1, new Rect(0, 0, mBitmap1.getWidth(), mBitmap1.getHeight()),
                    new Rect(0, 0, mBitmap1.getWidth(), mBitmap1.getHeight()), mPaint);
        }
        if (mBitmap2 != null) {
            canvas.drawBitmap(mBitmap2, new Rect(0, 0, mBitmap2.getWidth(), mBitmap2.getHeight()),
                    new Rect(0, 0, mBitmap2.getWidth(), mBitmap2.getHeight()), mPaint);
        }
    }

    /**
     * 绘制第一个view
     */
    private void first() {
        int strokeWidth = 5; // 线的高度
        mCanvas = new Canvas(mBitmap1);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);
//        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mHeight + strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
//        mCanvas.drawArc(oval1, 90, 180, false, mPaint); // 画圆弧
//        RectF oval2 = new RectF(mWidth - mHeight + strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
//        mCanvas.drawArc(oval2, -90, 180, false, mPaint); // 画圆弧
//        mCanvas.drawLine(mHeight / 2, strokeWidth / 2, mWidth - mHeight / 2, strokeWidth / 2, mPaint); // 划线
//        mCanvas.drawLine(mHeight / 2, mHeight - strokeWidth / 2, mWidth - mHeight / 2, mHeight - strokeWidth / 2, mPaint);

        RectF oval3 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval3, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.parseColor("#000000"));
        mCanvas.drawText("500/600", 50, mHeight / 2 + 15, mPaint);

    }

    /**
     * 绘制第二个view
     */
    private void second() {
        int strokeWidth = 5; // 线的高度
        mCanvas = new Canvas(mBitmap2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);
//        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mHeight + strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
//        mCanvas.drawArc(oval1, 90, 180, true, mPaint); // 画圆弧
//        RectF oval2 = new RectF(mWidth - mHeight + strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
//        mCanvas.drawArc(oval2, -90, 180, true, mPaint); // 画圆弧
//        mCanvas.drawRect(mHeight / 2, 0, mWidth - mHeight / 2, mHeight, mPaint);

        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval1, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mCanvas.drawText("500/600", 50, mHeight / 2 + 15, mPaint);

        mPaint.setColor(Color.parseColor("#009999"));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Path path = new Path();
        path.moveTo(mHeight / 2, 0);
        path.lineTo(mWidth, 0);
        path.lineTo(mWidth, mHeight);
        path.lineTo(mHeight / 2, mHeight);
        RectF oval3 = new RectF(0, 0, mHeight, mHeight);// 设置个长方形，扫描测量
        path.addArc(oval3, -90, 180);
        path.setFillType(Path.FillType.EVEN_ODD);
        mCanvas.drawPath(path, mPaint);

    }

}
