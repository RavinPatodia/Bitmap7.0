package com.test.demo.widget.scale_viewpager.viewpager;

import android.content.Context;
import android.util.AttributeSet;

import com.test.demo.widget.scale_viewpager.ScalePageTransformer;
import com.test.demo.widget.scale_viewpager.listener.ScalePagerListener;

/**
 * Created by WuLe
 */
public class ScaleViewPager extends HackyViewPager {

    public ScaleViewPager(Context context) {
        super(context);
    }

    public ScaleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setPageTransformer(true, new ScalePageTransformer());
        addOnPageChangeListener(new ScalePagerListener(this));

    }
}
