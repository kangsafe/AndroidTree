package com.ks.aliwufu;

import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Admin on 2017/1/23 0023 12:02.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class MyValueAnimation {
    /**
     * 数字滚动动画
     *
     * @param view  用于展示的TextView控件
     * @param start 开始的数字
     * @param end   结束的数字
     */
    public static void numberTimer(final TextView view, int start, int end, final String format) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setText(toStringSplit((int) valueAnimator.getAnimatedValue()) + format);
            }
        });
        valueAnimator.setDuration(5000);
        valueAnimator.start();
    }

    public static String toStringSplit(int num) {
        String s = "";
        while (num % 1000 != 0) {
            s = "," + (num / 1000 > 1000 ? leftPaddingZero(num % 1000 + "", 3) : num % 1000) + s;
            num = num / 1000;
        }
        if (num != 0) {
            s = num + s;
        }
        return s.startsWith(",") ? s.substring(1) : s;
    }

    //将给定字符串str左填充"0",直到str的位数等于len
    public static String leftPaddingZero(String str, int len) {

        //判断str字符串是否为空或者null
        if (str != null && !"".equals(str)) {
            if (str.length() < len) {//字符串长度小于指定长度，需要左填充
                //1.使用字符串的格式化，先左填充空格
                String format = "%" + len + "s";
                String tempResult = String.format(format, str);

                //2.使用String的replace函数将空格转换为指定字符即可
                String finalResult = tempResult.replace(" ", "0");

                return finalResult;
            } else {
                return str;
            }
        } else {
            return "左填充的字符串不能为空！";
        }
    }
}
