package com.zyl.androidinfiniteloopviewpager;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

/**
 * 无限循环viewpager切换监听器
 * Created by zyl on 16/3/15.
 */
public class DefaultPageChangeListener implements ViewPager.OnPageChangeListener {
    /**
     * viewpager 指示器
     */
    private ViewPagerLoopIndicator galleryIndicator;
    /**
     * 设置的viewpager
     */
    private ViewPager viewpager;

    /**
     * 构造器
     * @param viewpager ui控件
     */
    public DefaultPageChangeListener(ViewPager viewpager) {
        this.viewpager = viewpager;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 实际viewpager大小
        PagerAdapter adapter = viewpager.getAdapter();
        int size = adapter.getCount() - 2;
        // viewpager监听处理
        int pageIndex = position;

        if (position == 0) {
            // 当视图在第一个时，将页面号设置为图片的最后一张。
            pageIndex = size;
        } else if (position == size + 1) {
            // 当视图在最后一个是,将页面号设置为图片的第一张。
            pageIndex = 1;
        } else if (position >= 1 && position <= size){
            int index = position - 1;
            if (galleryIndicator != null){// 设置指示器
                int count = galleryIndicator.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView imageView = (ImageView) galleryIndicator.getChildAt(i);
                    if (index == i){// 设置高亮
                        GradientDrawable gdOvalHight = galleryIndicator.getGdOvalHight();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imageView.setBackground(gdOvalHight);
                        } else {
                            imageView.setBackgroundDrawable(gdOvalHight);
                        }
                    } else {// 设置正常
                        GradientDrawable gdOval = galleryIndicator.getGdOval();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imageView.setBackground(gdOval);
                        } else {
                            imageView.setBackgroundDrawable(gdOval);
                        }
                    }
                }
            }
        }
        if (position != pageIndex) {
            viewpager.setCurrentItem(pageIndex, false);
            return;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public ViewPagerLoopIndicator getGalleryIndicator() {
        return galleryIndicator;
    }

    public void setGalleryIndicator(ViewPagerLoopIndicator galleryIndicator) {
        this.galleryIndicator = galleryIndicator;
    }
}
