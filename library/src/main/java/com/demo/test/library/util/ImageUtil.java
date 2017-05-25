package com.demo.test.library.util;

import android.widget.ImageView;

import com.demo.test.library.ImageLoader;
import com.demo.test.library.widget.DrawableResource;

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
