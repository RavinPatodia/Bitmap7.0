/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.test.demo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.demo.test.library.widget.DrawableResource;
import com.demo.test.library.widget.scale_viewpager.ScalePagerAdapter;
import com.demo.test.library.widget.scale_viewpager.ScaleViewPager;
import com.test.demo.InsertImagePath;
import com.test.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {
    private static List<DrawableResource> mListPhoto;
    private static int mPosition;
    String selection = MediaStore.Images.Media.MIME_TYPE + "=? or "
            + MediaStore.Images.Media.MIME_TYPE + "=?";//只查询jpeg和png的图片
    String[] selectionArgs = new String[]{"image/jpeg", "image/png"};


    public static void start(Activity context, List<DrawableResource> data, int position) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
        mListPhoto = data;
        mPosition = position;
    }

    public static void start(Activity context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
        mListPhoto = new ArrayList<>();
        mPosition = 0;
    }


    private ScaleViewPager mViewPager;
    private ScalePagerAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        requestPermission();
        initViews();

    }

    private void initViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mViewPager = (ScaleViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new ScalePagerAdapter(getData());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mPosition, true);
    }

    private List<DrawableResource> getData() {
        if (mListPhoto.size() == 0) {
            return InsertImagePath.getInstance().getImagesPath(ViewPagerActivity.this, getImageUri(), selection, selectionArgs);
        }
        return mListPhoto;
    }

    private Uri getImageUri() {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You agree the permission", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
