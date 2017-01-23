package com.ks.androidtree;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Admin on 2017/1/23 0023 11:21.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = ImageLoaderConfigurationUtil.getCustomImageLoaderConfiguration(getApplicationContext());

        ImageLoader.getInstance().init(config);
    }
}
