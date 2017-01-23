package com.ks.aliwufu;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Admin on 2017/1/23 0023 10:10.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class FlipAnimation extends Animation {
    private Camera camera;
    private View fromView;
    private View toView;

    private float centerX;
    private float centerY;

    private boolean forward = true;
    private int DEFAULT_ANIMATION_DURATION = 500;

    public FlipAnimation(View fromView, View toView, int duration) {
        this.fromView = fromView;
        this.toView = toView;

        setDuration(duration);
        setFillAfter(false);
        setInterpolator(new LinearInterpolator());
    }

    public FlipAnimation(View fromView, View toView) {
        this.fromView = fromView;
        this.toView = toView;

        setDuration(DEFAULT_ANIMATION_DURATION);
        setFillAfter(false);
        setInterpolator(new LinearInterpolator());
    }

    public void reverse() {
        forward = false;
        View switchView = toView;
        toView = fromView;
        fromView = switchView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final double radians = Math.PI * interpolatedTime;
        float degrees = (float) (180.0 * radians / Math.PI);

        if (interpolatedTime <= 0.05f) {
            fromView.setScaleX(1 - interpolatedTime);
            fromView.setScaleY(1 - interpolatedTime);
            toView.setScaleX(1 - interpolatedTime);
            toView.setScaleY(1 - interpolatedTime);
        }

        if (interpolatedTime >= 0.5f) {
            degrees -= 180.f;
            toView.bringToFront();
            toView.getParent().requestLayout();
            ((View) toView.getParent()).invalidate();
            fromView.setVisibility(View.GONE);
            toView.setVisibility(View.VISIBLE);
        }

        if (interpolatedTime >= 0.95f) {
            fromView.setScaleX(interpolatedTime);
            fromView.setScaleY(interpolatedTime);
            toView.setScaleX(interpolatedTime);
            toView.setScaleY(interpolatedTime);
        }

        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.translate(0, 0, Math.abs(degrees) * 2);
        camera.getMatrix(matrix);
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}