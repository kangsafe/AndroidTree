package com.ks.aliwufu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ks.androidtree.R;

/**
 * Created by Monkey on 2015/6/29.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {

    public AdapterView.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private final int[] ids = {R.drawable.fuzi_wufu, R.drawable.fuzi_aiguo,
            R.drawable.fuzi_fuqiang, R.drawable.fuzi_hexie, R.drawable.fuzi_youshan, R.drawable.fuzi_jingye};
    public Context mContext;
    public String[] mDatas;
    public LayoutInflater mLayoutInflater;

    public HomeRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        // 这里是模拟数据。
        mDatas = mContext.getResources().getStringArray(R.array.subjects);
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.fragment_home_subject_item, parent, false);
        HomeRecyclerViewHolder mViewHolder = new HomeRecyclerViewHolder(mView);
        return mViewHolder;
    }

    /**
     * 绑定ViewHoler，给item中的控件设置数据
     */
    @Override
    public void onBindViewHolder(final HomeRecyclerViewHolder holder, final int position) {
        //点击事件在这里实现，主要是利用RecyclerView中填充的布局控件可以被点击这个原理
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        holder.mImageView.setImageDrawable(mContext.getResources().getDrawable(ids[position]));
        holder.mTextView.setText(mDatas[position]);
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }
}