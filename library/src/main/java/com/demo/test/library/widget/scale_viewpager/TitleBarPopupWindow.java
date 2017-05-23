package com.demo.test.library.widget.scale_viewpager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.demo.test.library.R;


/**
 * Created by WuLe
 *
 */
public class TitleBarPopupWindow {
    private TitleBarPopupWindow instance;
    private PopupWindow popupWindow;
    private static Context mContext;


    public TitleBarPopupWindow(Context context) {
        mContext = context;
        initPopup();
    }

    private void initPopup() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.titlebar_uicomponent, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popup_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);

    }

    public void toggle(View view) {
        if (isShowing()) {
            hide();
        } else {
            show(view);
        }
    }

    private boolean isShowing() {
        if (popupWindow != null && popupWindow.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    private void hide() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private void show(View view) {
        if (popupWindow != null) {
            popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        } else {
            initPopup();
            show(view);
        }
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    interface OnClickListener {
        void onClick();


    }


}
