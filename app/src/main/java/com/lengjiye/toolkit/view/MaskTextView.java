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

    private static int DEFAULT_HEIGHT = 60;// 默认高度
    private static int DEFAULT_MAX_PROGRESS = 100;// 默认最大进度

    private Context mContext;
    private Canvas mCanvas;
    private int mWidth, mHeight;
    private Paint mPaint;
    private Bitmap mBitmap1; // mCanvas绘制在这上面
    private Bitmap mBitmap2; // mCanvas绘制在这上面
    private int strokeWidth = 5; // 线的高度
    private int textSize = 30; // 字体的大小
    private int progress = 0; // 进度
    private int maxProgress;// 最大进度

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
        setMaxProgress(DEFAULT_MAX_PROGRESS);// 默认最大进度是100
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setProgress(int progress) {
        if (maxProgress != 0 && progress > maxProgress) {
            progress = maxProgress;
        } else if (progress > DEFAULT_MAX_PROGRESS) {
            progress = DEFAULT_MAX_PROGRESS;
        }
        this.progress = progress;
        postInvalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setDefaultWidthHeight(widthMeasureSpec, heightMeasureSpec);
        mBitmap1 = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);

    }

    private float countProgress() {
        if (mWidth != 0 && maxProgress != 0) {
            return (float) mWidth / (float) maxProgress;
        }
        return 1;
    }


    /**
     * 设置默认高度
     */
    private void setDefaultWidthHeight(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);// 得到模式
        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = DEFAULT_HEIGHT;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        first();
        second((int) (progress * countProgress()));

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
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.parseColor("#000000"));
        mCanvas.drawText(this.progress + "/" + maxProgress, 20, mHeight / 2 + textSize / 2, mPaint);

    }

    /**
     * 绘制第二个view
     */
    private void second(int progress) {
        mCanvas = new Canvas(mBitmap2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);

        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval1, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mCanvas.drawText(this.progress + "/" + maxProgress, 20, mHeight / 2 + textSize / 2, mPaint);

        /**
         * 绘制透明遮罩
         */
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT)); // 设置转换模式，取下层绘制非交集部分。
        // （setXfermode方法之前表示下层，方法之后表示上层）
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
