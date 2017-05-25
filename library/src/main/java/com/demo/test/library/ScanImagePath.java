package com.demo.test.library;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.demo.test.library.widget.DrawableResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询图片在相册的路径
 */

public class ScanImagePath {
    private static ScanImagePath mInstance = new ScanImagePath();

    private ScanImagePath() {
    }

    public static ScanImagePath getInstance() {
        return mInstance;
    }

    private List<DrawableResource> mPath = new ArrayList<>();


    public List<DrawableResource> getImagesPath(final Activity context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;

        try {

            cursor = context.getContentResolver().query(uri, null, selection,
                    selectionArgs, MediaStore.Images.Media.DATE_MODIFIED);
            if (cursor == null) {
                return null;
            }

            while (cursor.moveToNext()) {
                //获取图片的路径
                String path = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));

                DrawableResource drawablePath = new DrawableResource(path);

                if (!mPath.contains(drawablePath)) {
                    mPath.add(drawablePath);
                }
            }


        } finally {
            try {
                if (cursor != null)
                    cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mPath;
    }
}
