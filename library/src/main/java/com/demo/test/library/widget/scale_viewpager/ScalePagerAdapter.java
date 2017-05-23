package com.demo.test.library.widget.scale_viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.demo.test.library.util.GlideUtil;
import com.demo.test.library.widget.DrawableResource;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by WuLe
 */
public class ScalePagerAdapter extends PagerAdapter {

    private List<DrawableResource> data;

    public ScalePagerAdapter(List<DrawableResource> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());

        photoView.setOnPhotoTapListener(new OnShowTitleBarListener(container,position));

        GlideUtil.setDrawable(photoView, data.get(position));

        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
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
