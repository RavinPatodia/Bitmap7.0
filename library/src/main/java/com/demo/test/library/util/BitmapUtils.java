package com.demo.test.library.util;

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


    public static Bitmap zoomImg(String img, int newWidth, int newHeight) {
        // 图片源
        Bitmap bmp = getBitmapFromFile(img);
        if (null != bmp) {
            return zoomBitmap(bmp, newWidth, newHeight);
        }
        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Bitmap getBitmapFromFile(String imagePath) {

        return decodeFile(imagePath, null);
    }

    public static Bitmap decodeBitmapFromFile(String path, int viewWidth, int viewHeight) {
        BitmapFactory.Options options = createOptions();
        options.inSampleSize = computeScale(options, viewWidth, viewHeight);

        return decodeFile(path, options);
    }

    private static Bitmap decodeFile(String path, BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(path, options);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static BitmapFactory.Options createOptions() {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        return newOpts;
    }

    //计算Bitmap缩放比例
    private static int computeScale(BitmapFactory.Options options, int newWidth, int newHeight) {
        int inSampleSize = 1;
        if (newWidth == 0 || newHeight == 0) {
            return inSampleSize;
        }

        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        if (oldWidth > newWidth || oldHeight > newHeight) {
            int scaleWidth = Math.round((float) oldWidth / (float) newWidth);
            int scaleHeight = Math.round((float) oldHeight / (float) newHeight);

            inSampleSize = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
        }
        return inSampleSize;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Drawable zoomDrawable(Drawable drawable, int width, int height) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        Bitmap oldbmp = drawableToBitmap(drawable);
        float sx = (float) w / width;
        float sy = (float) h / height;
        Bitmap newbmp = createNewBitmap(oldbmp, width, height, createMatrix(sx, sy));
        return new BitmapDrawable(newbmp);
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
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 缩放bitmap
     */
    public static Bitmap zoomBitmap(Bitmap oldBitmap, int newWidth, int newHeight) {

        int oldWidth = oldBitmap.getWidth();
        int oldHeight = oldBitmap.getHeight();

        float scaleWidth = ((float) newWidth) / oldWidth;
        float scaleHeight = ((float) newHeight) / oldHeight;

        // 得到新的图片
        Bitmap newbmp = createNewBitmap(oldBitmap, oldWidth, oldHeight, createMatrix(scaleWidth, scaleHeight));
        return newbmp;
    }

    private static Bitmap createNewBitmap(Bitmap bitmap, int width, int height, Matrix matrix) {
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    private static Matrix createMatrix(float scaleWidth, float scaleHeight) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return matrix;
    }
}
