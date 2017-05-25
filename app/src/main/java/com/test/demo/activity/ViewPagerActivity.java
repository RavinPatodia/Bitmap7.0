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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;

import com.demo.test.library.widget.DrawableResource;
import com.demo.test.library.widget.scale_viewpager.adapter.ScalePagerAdapter;
import com.demo.test.library.widget.scale_viewpager.viewpager.ScaleViewPager;
import com.test.demo.ScanImagePath;
import com.test.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {
    private static List<DrawableResource> mListPhoto;
    private static int mPosition;
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
            return ScanImagePath.getInstance().getImagesPath(ViewPagerActivity.this, getImageUri(), getSelection(), selectionArgs);
        }
        return mListPhoto;
    }

    private Uri getImageUri() {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    private String getSelection(){
        return MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";
    }


}
