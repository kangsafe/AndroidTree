package com.ks.aliwufu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cardflip.ui.CardFlip;
import com.ks.androidtree.R;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class LuckyCardAdapter extends PagerAdapter {
    private final int DEFAULT_ANIMATION_DURATION = 1000;
    private final int[] havePics = {R.drawable.have_aiguo_280,
            R.drawable.have_fuqiang_280, R.drawable.have_hexie_280, R.drawable.have_youshan_280, R.drawable.have_youshan_280};
    private final int[] noPics = {R.drawable.no_aiguo_280,
            R.drawable.no_fuqiang_500, R.drawable.no_hexie_280, R.drawable.no_youshan_280, R.drawable.no_jingye_280};


    public void setFiles(List<LuckyModel> images) {
        this.images = images;
    }

    private List<LuckyModel> images = new ArrayList<LuckyModel>();
    private Context context;

    public LuckyCardAdapter(List<LuckyModel> files, Context context) {
        this.context = context;
        this.images = files;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final LuckyModel m = images.get(position);
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        final View mRootView = mLayoutInflater.inflate(R.layout.item_main, null);
        final View mFrontView = mRootView.findViewById(R.id.vfront);
        final View mBackView = mRootView.findViewById(R.id.vback);
        setCameraDistance(mRootView);
        ImageView imgFrontView = (ImageView) mRootView.findViewById(R.id.vfront_turn);
        ImageView imgBackView = (ImageView) mRootView.findViewById(R.id.vback_turn);
        imgFrontView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHelper.setRotationY(mBackView, 180f);//先翻转180，转回来时就不是反转的了
                Rotatable rotatable = new Rotatable.Builder(mRootView)
                        .sides(R.id.vfront, R.id.vback)
                        .direction(Rotatable.ROTATE_Y)
                        .rotationCount(2)
                        .build();
                rotatable.setTouchEnable(false);
                rotatable.rotate(Rotatable.ROTATE_Y, -180, 1500);
            }
        });
        imgBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rotatable rotatable = new Rotatable.Builder(mRootView)
                        .sides(R.id.vfront, R.id.vback)
                        .direction(Rotatable.ROTATE_Y)
                        .rotationCount(2)
                        .build();
                rotatable.setTouchEnable(false);
                rotatable.rotate(Rotatable.ROTATE_Y, 0, 1500);
            }
        });
        container.addView(mRootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return mRootView;
    }

    /**
     * 翻牌
     */
    public void cardTurnover(View rlCardRoot, View cardFrontView, View cardBackView) {
        if (View.GONE == cardBackView.getVisibility()) {
            ViewHelper.setRotationY(cardFrontView, 180f);//先翻转180，转回来时就不是反转的了
            Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
                    .sides(R.id.vback, R.id.vfront)
                    .direction(Rotatable.ROTATE_Y)
                    .rotationCount(1)
                    .build();
            rotatable.setTouchEnable(false);
            rotatable.rotate(Rotatable.ROTATE_Y, -180, 1500);
        } else if (View.VISIBLE == cardFrontView.getVisibility()) {
            Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
                    .sides(R.id.vback, R.id.vfront)
                    .direction(Rotatable.ROTATE_Y)
                    .rotationCount(1)
                    .build();
            rotatable.setTouchEnable(false);
            rotatable.rotate(Rotatable.ROTATE_Y, 0, 1500);
        }
    }

    /**
     * 改变视角距离, 贴近屏幕
     */
    private void setCameraDistance(View rlCardRoot) {
        int distance = 10000;
        float scale = context.getResources().getDisplayMetrics().density * distance;
        rlCardRoot.setCameraDistance(scale);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    private int mChildCount = 0;

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.75f;
    }


}