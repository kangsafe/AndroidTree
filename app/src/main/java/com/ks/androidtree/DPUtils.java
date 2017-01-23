package com.ks.androidtree;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Admin on 2017/1/18 0018 09:27.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class DPUtils {
    /**
     * dp2px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据设备信息获取当前分辨率下指定单位对应的像素大小；
     * px,dip,sp -> px
     */
    public float getRawSize(Context c, int unit, float size) {
        Resources r;
        if (c == null) {
            r = Resources.getSystem();
        } else {
            r = c.getResources();
        }
        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }
}
