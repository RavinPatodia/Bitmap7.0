package com.test.demo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 *
 */

public class BitmapUtils {

    public static Drawable zoomDrawable(Drawable drawable, int width, int height) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float sx = (float) w / width;
        float sy = (float) h / height;
        matrix.postScale(sx, sy);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(newbmp);
    }

    /**
     * 缩放bitmap
     */
    public static Bitmap zoomBitmap(Bitmap oldBitmap, int newWidth, int newHeight) {
        // 获得图片的宽高
        int oldWidth = oldBitmap.getWidth();
        int oldHeight = oldBitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / oldWidth;
        float scaleHeight = ((float) newHeight) / oldHeight;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbmp = Bitmap.createBitmap(oldBitmap, 0, 0, oldWidth, oldHeight, matrix,
                true);
        return newbmp;
    }

    public static Bitmap zoomImg(String img, int newWidth, int newHeight) {
        // 图片源
        Bitmap bmp = BitmapFactory.decodeFile(img);
        if (null != bmp) {
            return zoomBitmap(bmp, newWidth, newHeight);
        }
        return null;
    }

    private Bitmap getImage(String imagePath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        return BitmapFactory.decodeFile(imagePath, newOpts);
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;

        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
