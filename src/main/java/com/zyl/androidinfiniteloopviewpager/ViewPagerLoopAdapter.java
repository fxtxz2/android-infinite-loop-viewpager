package com.zyl.androidinfiniteloopviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * 无限循环adapter
 * Created by zyl on 16/3/11.
 */
public class ViewPagerLoopAdapter extends PagerAdapter {

    /**
     * view list
     */
    private ArrayList<ImageView> viewList;

    @Override
    public int getCount() {
        if (viewList == null){
            return 0;
        }
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (viewList != null){
            ImageView view = viewList.get(position);
            container.addView(view);
            return view;
        }
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList != null){
            ImageView view = viewList.get(position);
            container.removeView(view);
        }
    }

    /**
     * 设置ImageView
     * @param dataList String数据列表
     * @param context 当前上下文
     * @param onClickListener 点击监听
     * @param defalutImage 默认图片id
     */
    public void setDataList(final ArrayList<String> dataList,Context context,View.OnClickListener onClickListener, int defalutImage) {
        if (dataList != null){
            int size = dataList.size();
            int length = size + 2;
            ArrayList<ImageView> viewList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (i == 0){// 第一个
                    int index = size - 1;// 放最后一个
                    ImageView imageView = getImageView(dataList, context, index, onClickListener, defalutImage);
                    viewList.add(imageView);
                } else if (i == (length - 1)){ // 最后一个
                    int index = 0;// 放第一个
                    ImageView imageView = getImageView(dataList, context, index, onClickListener, defalutImage);
                    viewList.add(imageView);
                } else if (i > 0 && i < (length - 1)){// 中间的
                    int index = i - 1;// 放前面一个
                    ImageView imageView = getImageView(dataList, context, index, onClickListener, defalutImage);
                    viewList.add(imageView);
                }
            }
            this.viewList = viewList;
        }
    }

    /**
     * bannerItem to ImageView
     * @param dataList 列表数据
     * @param context 当前上下文
     * @param index 获取位置
     * @return imageview 控件
     */
    @NonNull
    private ImageView getImageView(ArrayList<String> dataList, Context context, int index,View.OnClickListener onClickListener, int defalutImage) {
        String url = dataList.get(index);
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams viewPagerImageViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(viewPagerImageViewParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setTag(index);
        imageView.setOnClickListener(onClickListener);
        if (url != null ){ //判断是不是伪造数据
            Picasso.with(context)
                    .load(url).placeholder(defalutImage)
                    .error(defalutImage)
                    .into(imageView);
        } else {
            imageView.setImageResource(defalutImage);
        }
        return imageView;
    }
}
