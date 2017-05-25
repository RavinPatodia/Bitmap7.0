package com.test.demo.widget.scale_viewpager.listener;

import android.view.View;

import com.test.demo.widget.scale_viewpager.TitleBarPopupWindow;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by WuLe
 */
public class OnShowTitleBarListener implements PhotoViewAttacher.OnPhotoTapListener {
    View view;
    int position;

    public OnShowTitleBarListener(View view, int position) {
        this.view = view;
        this.position = position;
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        TitleBarPopupWindow popupWindow = new TitleBarPopupWindow(view.getContext());
        popupWindow.toggle(view);
    }

    @Override
    public void onOutsidePhotoTap() {
    }


}
