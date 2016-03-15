# android-infinite-loop-viewpager
android的viewpager是不支持左右无限浏览的，参照[用最简单最实用的方式实现ViewPager无限循环两种方式](http://blog.csdn.net/Just_Sanpark/article/details/17436037)中的第一种思路，左右多使用一张ImageView作为过渡，达到无限循环浏览的效果，还自定义了viewpager的指示器，该指示器可以不依赖viewpager，是需要设置好数据大小，以及在layout中设置好即可。

# 指南
## viewpager和指示器在layout中的布局使用
```xml
<!--使用FrameLayout就可以让指示器在viewpager上面-->
<FrameLayout
        android:layout_width="match_parent"
        android:layout_height="150dp">
        <!-- viewpager  -->
        <com.zyl.androidinfiniteloopviewpager.InfiniteLoopViewPager
            android:id="@+id/home_gallery"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
        <!-- viewpager指示器 -->
        <com.zyl.androidinfiniteloopviewpager.ViewPagerLoopIndicator
            android:id="@+id/gallery_indicator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom"
            android:layout_marginBottom="4dp"
            android:orientation="horizontal"
            android:gravity="center_horizontal"/>

    </FrameLayout>
```
## viewpager设置Adapter
```java
// adapter必须为ViewPagerLoopAdapter
InfiniteLoopViewPager viewpager = (InfiniteLoopViewPager)findViewById(R.id.home_gallery);
ViewPagerLoopIndicator galleryIndicator = (ViewPagerLoopIndicator)findViewById(R.id.gallery_indicator);
// 设置adapter
viewpager.setAdapter(adapter,galleryIndicator);
// 无需指示器
viewpager.setAdapter(adapter);
```
## 定义Adapter
```java
ViewPagerLoopAdapter adapter = new ViewPagerLoopAdapter();
ArrayList<String> stringArrayList = new ArrayList<>();
for (int i = 0; i < size; i++) {
  stringArrayList.add(i, picUrl);
}
// 设置adapter
adapter.setDataList(stringArrayList, getContext(), new OnClickListener() {// 点击事件
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        // position是原始数据list中位置
    }
}, R.drawable.ic_launcher);// 缺省图片资源
```

# Gradle
