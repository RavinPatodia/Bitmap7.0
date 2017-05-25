package com.demo.test.library.widget.scale_viewpager.listener;

import android.graphics.Matrix;
import android.support.v4.view.ViewPager;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by WuLe
 */
public class ScalePagerListener implements ViewPager.OnPageChangeListener {

    private ViewPager pager;

    public ScalePagerListener(ViewPager pager) {
        this.pager = pager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i <= pager.getChildCount(); i++) {
            PhotoView photoView = (PhotoView) pager.getChildAt(i);
            if (photoView!=null){
                Matrix matrix = photoView.getDisplayMatrix();
                matrix.setScale(photoView.getMinimumScale(), photoView.getMinimumScale());
                photoView.setDisplayMatrix(matrix);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
