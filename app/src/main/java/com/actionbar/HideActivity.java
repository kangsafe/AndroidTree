package com.actionbar;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ks.androidtree.R;

public class HideActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener {

    private static final String TAG = "HideActivity";
    private WebView vweb;
    private Toolbar toolbar;
    private LinearLayout vbottom;
    private float mStartY = 0, mLastY = 0, mLastDeltaY;
    private float mBottomTrans;
    private ObservableScrollView vscroll;
    private int mLastDy = 0;
    private int mTotalDy = 0;
    private boolean isAlreadyHide = false, isAlreadyShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vweb = (WebView) findViewById(R.id.vweb);
        vbottom = (LinearLayout) findViewById(R.id.vbottom);
        vscroll = (ObservableScrollView) findViewById(R.id.vscroll);
        vweb.loadUrl("http://m.blog.csdn.net/article/details?id=41410113");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            vscroll.setOnScrollChangeListener(this);
//        } else {
//        vscroll.setOnTouchListener(this);
//        }
        mBottomTrans = vbottom.getTranslationY();
        vscroll.setScrollViewListener(this);
    }

    //    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final float y = event.getY();
        float translationY = toolbar.getTranslationY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG, "Down");
                mStartY = y;
                mLastY = mStartY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (translationY <= toolbar.getHeight()) {
                    Log.d(TAG, translationY + "");
                }
//                float mDeltaY = y - mLastY;

//                float newTansY = translationY + mDeltaY;
//                if (newTansY <= 0 && newTansY >= -toolbar.getHeight()) {
//                    toolbar.setTranslationY(newTansY);
//                    animateHide(toolbar);
//                }
//
//                float newBottom = vbottom.getTranslationY() - mDeltaY;
//                //下滑
//                if (newBottom >= mBottomTrans && newBottom <= mBottomTrans + vbottom.getHeight()) {
//                    vbottom.setTranslationY(newBottom);
////                    vweb.setScaleY(1 + (vbottom.getTranslationY() - mBottomTrans) / vbottom.getHeight());
//                    ViewGroup.LayoutParams linearParams = (ViewGroup.LayoutParams) vweb.getLayoutParams();
////                    linearParams.height += 2 * mDeltaY;
////                    vweb.setLayoutParams(linearParams);
//                }
//                mLastY = y;
//                mLastDeltaY = mDeltaY;

                Log.v(TAG, "Move");
                break;
            case MotionEvent.ACTION_UP:
                //下滑
                if (y - mStartY < 0) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(toolbar, "translationY", 0,
                            -toolbar.getHeight());
                    animator.start();
                    animator.setDuration(100);
                    animator.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear));
//                    toolbar.setVisibility(View.GONE);
//                    vbottom.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    vbottom.setVisibility(View.VISIBLE);
                }
//                ObjectAnimator animator = null;
//                if (y - mStartY < 0) {
//                    animator = ObjectAnimator.ofFloat(appbar, "translationY", appbar.getTranslationY(), -appbar.getHeight());
//                } else {
//                    animator = ObjectAnimator.ofFloat(appbar, "translationY", appbar.getTranslationY(), 0);
//                }
//                animator.setDuration(100);
//                animator.start();
//                animator.setInterpolator(AnimationUtils.loadInterpolator(HideActivity.this, android.R.interpolator.linear));
//                ObjectAnimator animator1 = null;
//                if (y - mStartY < 0) {
//                    animator1 = ObjectAnimator.ofFloat(vbottom, "translationY", vbottom.getTranslationY(), vbottom.getTranslationY() + vbottom.getHeight());
//                } else {
//                    animator1 = ObjectAnimator.ofFloat(vbottom, "translationY", vbottom.getTranslationY(), vbottom.getTranslationY() - vbottom.getHeight());
//                }
//                animator1.setDuration(100);
//                animator1.start();
//                animator1.setInterpolator(AnimationUtils.loadInterpolator(HideActivity.this, android.R.interpolator.linear));
                break;
            case MotionEvent.ACTION_SCROLL:
//                ObjectAnimator animator1 = null;
//                Log.d(TAG, "mLastDeltaY=" + mLastDeltaY);
//                animator1 = ObjectAnimator.ofFloat(appbar, "translationY", appbar.getTranslationY(), -appbar.getHeight());
//                animator1.setDuration(100);
//                animator1.start();
//                animator1.setInterpolator(AnimationUtils.loadInterpolator(HideActivity.this, android.R.interpolator.linear));
//                ObjectAnimator animator2 = ObjectAnimator.ofFloat(vbottom, "translationY", vbottom.getTranslationY(), 0);
//                animator2.setDuration(100);
//                animator2.start();
//                animator2.setInterpolator(AnimationUtils.loadInterpolator(HideActivity.this, android.R.interpolator.linear));
//
//                Log.v(TAG, "Up");
                break;
        }
        return false;
    }


    private ObjectAnimator animateHide(View targetView) {
        int distance = -targetView.getBottom();
        return animationFromTo(targetView, targetView.getTranslationY(), distance);
    }

    private ObjectAnimator animateShow(View targetView) {
        return animationFromTo(targetView, targetView.getTranslationY(), 0);
    }

    private ObjectAnimator animationFromTo(View view, float start, float end) {
        String propertyName = "translationY";
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, propertyName, start, end);
        animator.start();
        return animator;
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int dx = x - oldx;
        int dy = y - oldy;
        Log.d(TAG, dx + "," + dy);
        if (Math.abs(dy) > Math.abs(dx)) {
//        toolbar.setTitle(toolbar.getTranslationY() + "," + dy);
            float newp = toolbar.getTranslationY() - dy;
            if (newp <= 0 && newp >= -toolbar.getHeight()) {
                toolbar.setTranslationY(newp);
//                vscroll.setTranslationY(vscroll.getTranslationY() - dy);
                ViewGroup.LayoutParams lp = scrollView.getLayoutParams();
                lp.height = getScreenHeight() - (int) Math.abs(toolbar.getTranslationY());
                vscroll.setLayoutParams(lp);
                vscroll.requestLayout();
                vbottom.setTranslationY(vbottom.getTranslationY() + dy);
//                toolbar.setVisibility(View.GONE);
            } else {
//                toolbar.setVisibility(View.VISIBLE);
            }
        }
    }

    private int getScreenHeight() {
        return getWindowManager().getDefaultDisplay().getHeight();
    }
}
