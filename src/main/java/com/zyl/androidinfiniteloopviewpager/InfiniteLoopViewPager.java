package com.zyl.androidinfiniteloopviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 无限循环viewpager
 * Created by zyl on 16/3/15.
 */
public class InfiniteLoopViewPager extends ViewPager {
    public InfiniteLoopViewPager(Context context) {
        super(context);
    }

    public InfiniteLoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置adapter
     * @param adapter 数据adapter
     * @param galleryIndicator 指示器
     */
    public void setAdapter(ViewPagerLoopAdapter adapter, ViewPagerLoopIndicator galleryIndicator) {
        super.setAdapter(adapter);
        // 设置当前起始点
        this.setCurrentItem(1);
        // 实际大小
        int size = adapter.getCount() - 2;
        galleryIndicator.setIndicator(size);
        // 设置监听
        DefaultPageChangeListener defaultPageChangeListener = new DefaultPageChangeListener(this);
        defaultPageChangeListener.setGalleryIndicator(galleryIndicator);
        this.addOnPageChangeListener(defaultPageChangeListener);
    }

    /**
     * 不依赖指示器
     * @param adapter 数据adapter
     */
    public void setAdapter(ViewPagerLoopAdapter adapter) {
        this.setAdapter(adapter, null);
    }
}
