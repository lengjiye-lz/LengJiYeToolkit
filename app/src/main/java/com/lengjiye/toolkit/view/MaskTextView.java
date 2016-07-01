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
    private int strokeWidth = 5; // 线的高度
    private int textSice = 30; // 字体的大小
    private int progress = 0; // 进度
    private String textContent; // 字体内容

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

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setTextSice(int textSice) {
        this.textSice = textSice;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (mWidth != 0 && progress > mWidth) {
            this.progress = mWidth;
        }
        postInvalidate();
    }

    /**
     * 设置百分比进度
     * progress的取值范围是0到1
     *
     * @param progress
     */
    public void setProgress(double progress) {
        if (progress <= 0) {
            progress = 0;
        }
        if (progress >= 1) {
            progress = 1;
        }
        if (mWidth != 0) {
            this.progress = (int) (mWidth * progress);
        } else {
            return;
        }
        postInvalidate();
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
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
        mCanvas = new Canvas(mBitmap1);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);

        RectF oval3 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval3, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(textSice);
        mPaint.setColor(Color.parseColor("#000000"));
        mCanvas.drawText(textContent, 20, mHeight / 2 + textSice / 2, mPaint);

    }

    /**
     * 绘制第二个view
     */
    private void second() {
        mCanvas = new Canvas(mBitmap2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);

        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval1, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(textSice);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mCanvas.drawText(textContent, 20, mHeight / 2 + textSice / 2, mPaint);

        mPaint.setColor(Color.parseColor("#009999"));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Path path = new Path();
        path.moveTo(0 + progress, 0);
        path.lineTo(mWidth, 0);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0 + progress, mHeight);
        RectF oval3 = new RectF(progress - mHeight / 2, 0, progress + mHeight / 2, mHeight);// 设置个长方形，扫描测量
        path.addArc(oval3, -90, 180);
        path.setFillType(Path.FillType.EVEN_ODD);
        mCanvas.drawPath(path, mPaint);
    }

}
