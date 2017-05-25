package com.demo.test.library.widget.scale_viewpager.viewpager;

import android.content.Context;
import android.util.AttributeSet;

import com.demo.test.library.widget.scale_viewpager.ScalePageTransformer;
import com.demo.test.library.widget.scale_viewpager.listener.ScalePagerListener;

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
