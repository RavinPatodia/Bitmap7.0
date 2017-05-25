package com.test.demo.widget.scale_viewpager.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.test.demo.util.GlideUtil;
import com.test.demo.widget.DrawableResource;
import com.test.demo.widget.scale_viewpager.listener.OnShowTitleBarListener;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by mac on 2017/5/25.
 */

public class ScalePagerAdapter extends BaseScalePagerAdapter{
    public ScalePagerAdapter(List<DrawableResource> data) {
        super(data);
    }

    @Override
    protected View extendViewItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());

        photoView.setOnPhotoTapListener(new OnShowTitleBarListener(container,position));

        GlideUtil.setDrawable(photoView, data.get(position));
//        ImageUtil.setDrawable(photoView,data.get(position));

        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }
}
