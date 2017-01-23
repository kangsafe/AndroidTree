package com.ks.aliwufu;

import android.graphics.Bitmap;

import com.ks.androidtree.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Admin on 2017/1/23 0023 11:16.
 * Author: kang
 * Email: kangsafe@163.com
 */
public class DisplayImageOptionsUtil {

    public static DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.card_bg_gray).showImageForEmptyUri(R.drawable.card_bg_gray)
                .showImageOnFail(R.drawable.card_bg_gray).resetViewBeforeLoading(false).cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).build();
        return options;
    }

    public static DisplayImageOptions getDisplayImageOptionsNoCache(int resid){
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(resid).showImageForEmptyUri(resid)
                .showImageOnFail(resid).resetViewBeforeLoading(false).cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).build();
        return options;
    }
}
