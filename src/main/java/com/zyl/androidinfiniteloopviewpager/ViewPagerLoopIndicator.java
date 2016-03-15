package com.zyl.androidinfiniteloopviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 *  无限循环viewpager指示器
 * Created by zyl on 16/3/15.
 */
public class ViewPagerLoopIndicator extends LinearLayout{
    /**
     * 正常点
     */
    private GradientDrawable gdOval;
    /**
     * 高亮点
     */
    private GradientDrawable gdOvalHight;

    public ViewPagerLoopIndicator(Context context) {
        super(context);
    }

    public ViewPagerLoopIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerLoopIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerLoopIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 指示器点数
     * @param size 数据实际大小
     */
    public void setIndicator(int size) {
        // 设置指示器
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // prepare
        int strokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm); // 3px not dp
        int strokeColor = Color.WHITE;

        // 透明的圆点
        gdOval = new GradientDrawable();
        gdOval.setShape(GradientDrawable.OVAL);
        gdOval.setColor(Color.TRANSPARENT);
        gdOval.setStroke(strokeWidth, strokeColor);

        // 高亮圆点
        gdOvalHight = new GradientDrawable();
        gdOvalHight.setShape(GradientDrawable.OVAL);
        gdOvalHight.setColor(Color.WHITE);
        gdOvalHight.setStroke(strokeWidth, strokeColor);

        // 清除原有的view
        this.removeAllViews();
        // 动态添加imageview
        for (int i = 0; i < size; i++) {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
            int margins = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, dm);
            LayoutParams params = new LayoutParams(height, height);
            params.setMargins(margins, 0, margins, 0);
            ImageView imageView = new ImageView(getContext());
            if (i == 0){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView.setBackground(gdOvalHight);
                } else {
                    imageView.setBackgroundDrawable(gdOvalHight);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    imageView.setBackground(gdOval);
                } else {
                    imageView.setBackgroundDrawable(gdOval);
                }
            }
            imageView.setTag(i);
            imageView.setLayoutParams(params);
            this.addView(imageView);
        }
    }

    public GradientDrawable getGdOval() {
        return gdOval;
    }

    public void setGdOval(GradientDrawable gdOval) {
        this.gdOval = gdOval;
    }

    public GradientDrawable getGdOvalHight() {
        return gdOvalHight;
    }

    public void setGdOvalHight(GradientDrawable gdOvalHight) {
        this.gdOvalHight = gdOvalHight;
    }
}
