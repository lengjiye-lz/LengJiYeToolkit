package com.lengjiye.toolkit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.utils.StringUtils;


/**
 * 可以伸缩的TextView
 * 参考http://blog.csdn.net/qiaoidea/article/details/45568653
 * Created by lz on 2016/3/28.
 */
public class StretchTextView extends RelativeLayout {

    private Context mContext;
    private TextView tvContent;
    private TextView tvMore;
    private LinearLayout llMore;
    private ImageView ivView;
    private boolean isUnfold = true; // 是否打开
    private int maxHeight; // 实际最大高度
    private int defaultHeight; // 默认高度
    private int defaultLine; // 默认最大3行
    private long animationTime;// 动画持续时间  单位毫秒
    private int defaultPadding;// textview默认padding
    private String foldText; // 折叠显示文字
    private String unFoldText; // 展开显示文字

    public StretchTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public StretchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public StretchTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (isInEditMode()) {
            return;
        }
        View view = View.inflate(context, R.layout.stretch_text_view_layout, this);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvMore = (TextView) view.findViewById(R.id.tv_more);
        llMore = (LinearLayout) view.findViewById(R.id.ll_more);
        ivView = (ImageView) view.findViewById(R.id.iv_view);
        initValues();
        initAttributeFromXml(attrs);
        tvContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // addOnGlobalLayoutListener监听只执行一次
                tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                maxHeight = tvContent.getHeight();
                defaultHeight = tvContent.getLineHeight() * defaultLine;
                defaultPadding = maxHeight - (tvContent.getLineHeight() * tvContent.getLineCount());
                if (tvContent.getLineCount() > defaultLine) {
                    llMore.setVisibility(VISIBLE);
                } else {
                    llMore.setVisibility(GONE);
                }
                setDefaultLine(defaultLine);
            }
        });

        llMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setTvContentAnimation();
            }
        });
    }

    /**
     * 设置默认值
     */
    private void initValues() {
        defaultLine = 3;
        animationTime = 1000;
        foldText = "收起";
        unFoldText = "更多";
    }

    /**
     * 设置xml属性
     *
     * @param attrs
     */
    private void initAttributeFromXml(AttributeSet attrs) {
        TypedArray typed = this.mContext.obtainStyledAttributes(attrs, R.styleable.StretchTextView);
        if (typed == null) {
            return;
        }
        for (int i = 0; i < typed.getIndexCount(); i++) {
            int attr = typed.getIndex(i);
            switch (attr) {
                case R.styleable.StretchTextView_defaultLine:
                    defaultLine = typed.getInt(R.styleable.StretchTextView_defaultLine, defaultLine);
                    break;
                case R.styleable.StretchTextView_content:
                    setContent(typed.getResourceId(R.styleable.StretchTextView_content, R.string.app_name));
                    break;
                case R.styleable.StretchTextView_animation_time:
                    animationTime = typed.getInt(R.styleable.StretchTextView_animation_time, (int) animationTime);
                    setAnimationTime(animationTime);
                    break;
                case R.styleable.StretchTextView_fold_text:
                    foldText = typed.getString(R.styleable.StretchTextView_fold_text);
                    setFoldText(foldText);
                    break;
                case R.styleable.StretchTextView_unfold_text:
                    unFoldText = typed.getString(R.styleable.StretchTextView_unfold_text);
                    setUnFoldText(unFoldText);
                    break;
                case R.styleable.StretchTextView_arrows_image:
                    setArrowsImage(typed.getResourceId(R.styleable.StretchTextView_arrows_image, R.mipmap.action_bar_down));
                    break;
            }
        }
        typed.recycle();
    }

    /**
     * 设置动画
     */
    private void setTvContentAnimation() {
        ivView.clearAnimation();
        if (isUnfold) {
            isUnfold = false;
            tvMore.setText(foldText);
            RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    super.applyTransformation(interpolatedTime, t);
                    tvContent.setHeight((int) (defaultPadding + defaultHeight + (maxHeight - defaultHeight) * interpolatedTime));
                }
            };
            animation.setDuration(animationTime);
            animation.setFillAfter(true);
            ivView.startAnimation(animation);
        } else {
            isUnfold = true;
            tvMore.setText(unFoldText);
            RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    super.applyTransformation(interpolatedTime, t);
                    tvContent.setHeight((int) (defaultPadding + maxHeight - (maxHeight - defaultHeight) * interpolatedTime));
                }
            };
            animation.setDuration(animationTime);
            animation.setFillAfter(true);
            ivView.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ivView.clearAnimation();
                    setDefaultLine(defaultLine);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }


    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(String content) {
        tvContent.setText(content);
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(int content) {
        tvContent.setText(content);
    }

    /**
     * 设置默认行数
     *
     * @param line
     */
    public void setDefaultLine(int line) {
        if (tvContent == null) {
            return;
        }
        tvContent.setMaxLines(line);
    }

    /**
     * 设置动画持续时间
     *
     * @param time
     */
    public void setAnimationTime(long time) {
        animationTime = time;
    }

    /**
     * 设置动画持续时间
     *
     * @param time
     */
    public void setAnimationTime(int time) {
        animationTime = time;
    }

    /**
     * 设置箭头图片
     *
     * @param resId
     */
    public void setArrowsImage(int resId) {
        if (ivView == null) {
            return;
        }
        ivView.setImageResource(resId);
    }

    /**
     * 设置折叠显示的文字
     *
     * @param foldText
     */
    public void setFoldText(String foldText) {
        if (StringUtils.isBlank(foldText)) {
            return;
        }
        this.foldText = foldText;
    }

    /**
     * 设置展开显示的文字
     *
     * @param unFoldText
     */
    public void setUnFoldText(String unFoldText) {
        if (StringUtils.isBlank(unFoldText)) {
            return;
        }
        this.unFoldText = unFoldText;
        tvMore.setText(unFoldText);
    }
}
