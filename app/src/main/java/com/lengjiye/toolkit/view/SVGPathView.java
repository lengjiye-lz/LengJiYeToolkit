package com.lengjiye.toolkit.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.utils.SVGUtil;
import com.lengjiye.toolkit.utils.SVGUtil.FragmentPath;

import java.util.ArrayList;

/**
 * 仿toolbar箭头旋转效果
 * Created by HuaChao on 2016/6/17.
 */
public class SVGPathView extends SurfaceView implements SurfaceHolder.Callback {

    // 动画起始Path数据
    private ArrayList<FragmentPath> svgStartDataList;
    // 动画结束时的Path数据
    private ArrayList<FragmentPath> svgEndDataList;

    private ArrayList<String> svgStartStrList;
    private ArrayList<String> svgEndStrList;

    private SurfaceHolder surfaceHolder;
    // 用于SurfaceView显示的对象
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    // view的宽高
    private int mWidth;
    private int mHeight;
    // SVG path里面的数据中参考的宽高
    private int mViewWidth;
    private int mViewHeight;
    // 绘制线条的宽度
    private int mPaintWidth;

    // 用于等比放缩
    private float widthFactor;
    private float heightFactor;
    private int mPaintColor;

    private float rotateDegree = 0;

    public SVGPathView(Context context) {
        super(context);
        init();
    }

    public SVGPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.SVGPathView);
        // 读取布局文件设置的起始Path数据和结束Path数据
        String svgStartPath = ta
                .getString(R.styleable.SVGPathView_svg_start_path);
        String svgEndPath = ta.getString(R.styleable.SVGPathView_svg_end_path);
        // 如果二者有一个没有设置，就将没有设置的那个设定为已经设置的数据
        if (svgStartPath == null && svgEndPath != null) {
            svgStartPath = svgEndPath;
        } else if (svgStartPath != null && svgEndPath == null) {
            svgEndPath = svgStartPath;
        }
        // 读取布局文件的配置
        mViewWidth = ta.getInteger(R.styleable.SVGPathView_svg_view_width, -1);
        mViewHeight = ta
                .getInteger(R.styleable.SVGPathView_svg_view_height, -1);
        mPaintWidth = ta.getInteger(R.styleable.SVGPathView_svg_paint_width, 5);
        mPaintColor = ta.getColor(R.styleable.SVGPathView_svg_color,
                Color.BLACK);

        SVGUtil svgUtil = SVGUtil.getInstance();
        // 将原始数据做预处理
        svgStartStrList = svgUtil.extractSvgData(svgStartPath);
        svgEndStrList = svgUtil.extractSvgData(svgEndPath);

        // 将经过预处理后的path数据，转为FragmentPath列表
        svgStartDataList = svgUtil.strListToFragList(svgStartStrList);
        svgEndDataList = svgUtil.strListToFragList(svgEndStrList);

        ta.recycle();
        init();
    }

    // 初始化
    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        mPaint = new Paint();
        mPaint.setStrokeJoin(Join.ROUND);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setColor(mPaintColor);

    }

    public void setRotateDegree(int rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    private boolean isAnim = false;

    public void startTransform() {
        if (!isAnim) {
            isAnim = true;
            ValueAnimator va = ValueAnimator.ofFloat(0, 1f);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatorFactor = (float) animation.getAnimatedValue();
                    Path path = SVGUtil.getInstance().parseFragList(
                            svgStartDataList, svgEndDataList, widthFactor,
                            heightFactor, animatorFactor);
                    //
                    rotateDegree = animatorFactor * 360;
                    drawPath(path);
                }
            });
            va.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ArrayList<FragmentPath> tmp = svgStartDataList;
                    svgStartDataList = svgEndDataList;
                    svgEndDataList = tmp;
                    isAnim = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAnim = false;
                }
            });
            va.setDuration(800).start();
        }
    }

    // 开始绘制
    public void drawPath(Path path) {
        Log.d("test", rotateDegree + "-----");
        clearCanvas();
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(mPaintColor);

        mCanvas.save();
        mCanvas.rotate(rotateDegree, mWidth / 2, mHeight / 2);
        mCanvas.drawPath(path, mPaint);
        mCanvas.restore();

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    // 清屏
    private void clearCanvas() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Style.FILL);
        mCanvas.drawRect(0, 0, mWidth, mHeight, mPaint);

    }

    // 调用invalidate时，把Bitmap对象绘制到View中
    @Override
    public void invalidate() {
        super.invalidate();
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // 保存当前的View宽高
        mWidth = width;
        mHeight = height;
        // 如果没有设置Path的参考宽高，默认设置为View的宽高
        if (mViewWidth <= 0) {
            mViewWidth = width;
        }
        if (mViewHeight <= 0) {
            mViewHeight = height;
        }
        // 计算放缩倍数
        widthFactor = 1.f * width / mViewWidth;
        heightFactor = 1.f * height / mViewHeight;
        // 创建Bitmap对象，用于绘制到屏幕中
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        // 将画笔绘制线条的宽度设置为经过放缩后的宽度
        mPaint.setStrokeWidth(mPaintWidth * widthFactor);
        Path path = SVGUtil.getInstance().parsePath(svgStartStrList,
                widthFactor, heightFactor);
        drawPath(path);
        invalidate();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
