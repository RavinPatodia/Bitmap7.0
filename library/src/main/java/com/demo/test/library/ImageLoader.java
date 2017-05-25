package com.demo.test.library;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.demo.test.library.util.BitmapUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */

public class ImageLoader {
    private static ImageLoader mInstance = new ImageLoader();
    private ExecutorService mImageThreadPool = Executors.newFixedThreadPool(1);


    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return mInstance;
    }


    public void loadNativeImage(final String path, final LoadImageCallBack mCallBack) throws ExecutionException {
        final Handler mHander = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mCallBack.onImageLoader((Bitmap) msg.obj, path);
            }

        };
        mImageThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                //先获取图片的缩略图
                Bitmap mBitmap = BitmapUtils.getBitmapFromFile(path);
                Message msg = mHander.obtainMessage();
                msg.obj = mBitmap;
                mHander.sendMessage(msg);

            }
        });


    }

    public void loadNativeImage(String path, ImageView imageView) {
        Bitmap bitmap = BitmapUtils.getBitmapFromFile(path);
        if (bitmap == null) {
            Log.e("Error", "获取图片失败");
        }
        imageView.setImageBitmap(bitmap);
    }


    /**
     * 加载本地图片的回调接口
     */
    public interface LoadImageCallBack {
        public void onImageLoader(Bitmap bitmap, String path);
    }
}
