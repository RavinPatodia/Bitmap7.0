package com.test.demo.util;

import android.widget.ImageView;

import com.test.demo.ImageLoader;
import com.test.demo.widget.DrawableResource;

/**
 * Created by mac on 2017/5/25.
 */

public class ImageUtil {
    private ImageUtil() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setDrawable(ImageView imageView, DrawableResource drawableResource) {
        if (imageView == null) {
            return;
        }
        if (drawableResource == null) {
            return;
        }
        switch (drawableResource.getType()) {
            case String:
                ImageLoader.getInstance().loadNativeImage(drawableResource.getString(), imageView);
                break;

            case INVALID:
                break;
            default:
                break;
        }
    }
}
