package com.demo.test.library.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demo.test.library.widget.DrawableResource;

/**
 *
 */

public final class GlideUtil {

    private GlideUtil() {
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
            case Integer:
                Glide.with(imageView.getContext()).load(drawableResource.getInteger()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                break;
            case File:
                Glide.with(imageView.getContext()).load(drawableResource.getFile()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                break;
            case URL:
                Glide.with(imageView.getContext()).load(drawableResource.getURL()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                break;
            case Uri:
                Glide.with(imageView.getContext()).load(drawableResource.getUri()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                break;
            case String:
                Glide.with(imageView.getContext()).load(drawableResource.getString()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                break;
            case INVALID:
                break;
            default:
                break;
        }
    }
}

