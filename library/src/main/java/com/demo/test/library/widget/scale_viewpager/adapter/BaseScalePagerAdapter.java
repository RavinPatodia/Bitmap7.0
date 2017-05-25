package com.demo.test.library.widget.scale_viewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.demo.test.library.widget.DrawableResource;

import java.util.List;

/**
 * Created by WuLe
 */
public abstract class BaseScalePagerAdapter extends PagerAdapter {

    protected abstract View extendViewItem(ViewGroup container, int position);

    protected List<DrawableResource> data;

    public BaseScalePagerAdapter(List<DrawableResource> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        return extendViewItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
