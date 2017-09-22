package com.lengjiye.toolkit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lengjiye.toolkit.R;
import com.lengjiye.tools.LogTool;

/**
 * 实现遮罩效果的进度条
 * Created by lz on 2016/6/28.
 */
public class MaskTextView extends View {

    private static int DEFAULT_HEIGHT = 60;// 默认高度
    private static int DEFAULT_MAX_PROGRESS = 100;// 默认最大进度
    private static float TEXT_TO_LIFT = 20; // text距左边的距离

    private Context mContext;
    private Paint.FontMetrics fm;
    private int mWidth, mHeight;
    private Paint mPaint;
    private float strokeWidth = 5; // 线的宽度
    private float textSize = 30; // 字体的大小
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
        initAttributeFromXml(attrs);
        setMaxProgress(DEFAULT_MAX_PROGRESS);// 默认最大进度是100
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setTextSize(float textSize) {
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
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 设置高度
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = DEFAULT_HEIGHT;
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingTop() + getPaddingBottom();
    }

    /**
     * 设置宽度
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                // result = mTextBound.width();
                result = val;
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingLeft() + getPaddingRight();
    }

    private float countProgress() {
        if (mWidth != 0 && maxProgress != 0) {
            return (float) mWidth / (float) maxProgress;
        }
        return 1;
    }

    /**
     * 设置xml属性
     *
     * @param attrs
     */
    private void initAttributeFromXml(AttributeSet attrs) {
        TypedArray typed = this.mContext.obtainStyledAttributes(attrs, R.styleable.MaskTextView);
        if (typed == null) {
            return;
        }
        for (int i = 0; i < typed.getIndexCount(); i++) {
            int attr = typed.getIndex(i);
            switch (attr) {
                case R.styleable.MaskTextView_max_progress:
                    setMaxProgress(typed.getInt(R.styleable.MaskTextView_max_progress, DEFAULT_MAX_PROGRESS));
                    break;
                case R.styleable.MaskTextView_progress:
                    setProgress(typed.getInt(R.styleable.MaskTextView_progress, 0));
                    break;
                case R.styleable.MaskTextView_stroke_width:
                    setTextSize(typed.getDimension(R.styleable.MaskTextView_stroke_width, 30));
                    break;
                case R.styleable.MaskTextView_text_size:
                    setStrokeWidth(typed.getDimension(R.styleable.MaskTextView_text_size, 5));
                    break;
            }
        }
        typed.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        first(canvas);
        second(canvas, (int) (progress * countProgress()));
    }

    /**
     * 绘制第一个view
     */
    private void first(Canvas mCanvas) {
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
        fm = mPaint.getFontMetrics();
        float textPlace = mHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        mCanvas.drawText(this.progress + "/" + maxProgress, TEXT_TO_LIFT, textPlace, mPaint);
    }

    /**
     * 绘制第二个view
     */
    private void second(Canvas mCanvas, int progress) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#FF7E00"));
        mPaint.setStrokeWidth(strokeWidth);
        mCanvas.saveLayer(0, 0, mWidth, mHeight, mPaint);
        RectF oval1 = new RectF(strokeWidth / 2, strokeWidth / 2, mWidth - strokeWidth / 2, mHeight - strokeWidth / 2);// 设置个长方形，扫描测量
        mCanvas.drawRoundRect(oval1, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.parseColor("#ffffff"));
        float textPlace = mHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        mCanvas.drawText(this.progress + "/" + maxProgress, TEXT_TO_LIFT, textPlace, mPaint);

        /**、
         * 绘制透明遮罩
         */
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT)); // 设置转换模式，取下层绘制非交集部分。
        // （setXfermode方法之前表示下层，方法之后表示上层）
        Path path = new Path();
        path.moveTo(progress - mHeight / 2, 0);
        path.lineTo(mWidth + mHeight / 2, 0);
        path.lineTo(mWidth + mHeight / 2, mHeight);
        path.lineTo(progress - mHeight / 2, mHeight);
        RectF oval3 = new RectF(progress - mHeight, 0, progress, mHeight);// 设置个长方形，扫描测量
        path.addArc(oval3, -90, 180);
        path.setFillType(Path.FillType.EVEN_ODD);
        mCanvas.drawPath(path, mPaint);
        mCanvas.restore();
    }
}
