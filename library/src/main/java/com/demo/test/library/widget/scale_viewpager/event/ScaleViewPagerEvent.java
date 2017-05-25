package com.demo.test.library.widget.scale_viewpager.event;

/**
 * Created by WuLe
 *
 */
public class ScaleViewPagerEvent {
    private int position;

    public ScaleViewPagerEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
