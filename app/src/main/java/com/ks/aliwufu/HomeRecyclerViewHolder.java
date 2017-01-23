package com.ks.aliwufu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ks.androidtree.R;

/**
 * Created by Monkey on 2015/6/29.
 */
public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;
    public ImageView mImageView;

    public HomeRecyclerViewHolder(View itemView) {
        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.subtxt);
        mImageView = (ImageView) itemView.findViewById(R.id.subimg);
    }
}
