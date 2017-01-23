package com.ks.aliwufu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ks.SuperActivity;
import com.ks.androidtree.GetData;
import com.ks.androidtree.R;
import com.smallnew.fucardpager.view.CenterViewPager;
import com.smallnew.fucardpager.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class LuckyCardActivity extends SuperActivity implements CenterViewPager.OnPageChangeListener {

    private CenterViewPager viewPager;
    private LuckyCardAdapter mViewAdapter;
    private List<LuckyModel> datas = new ArrayList<LuckyModel>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeRecyclerViewAdapter mRecyclerViewAdapter;
    private TextView vfront_totalnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_card);
        GetData.init(this);
        initView();
    }

    private void initView() {
        viewPager = (CenterViewPager) findViewById(R.id.vpager);
        mRecyclerView = (RecyclerView) findViewById(R.id.subjectsview);
        vfront_totalnum = (TextView) findViewById(R.id.vfront_totalnum);
        MyValueAnimation.numberTimer(vfront_totalnum, 10000, 299520, "人已集齐，1月27日22:18开奖");
        datas = GetData.getAllLuckyCard();
        // 初始化Adapter
        mViewAdapter = new LuckyCardAdapter(datas, this);
        viewPager.setAdapter(mViewAdapter);
        // 绑定回调
        viewPager.setOnPageChangeListener(this);
        viewPager.enableCenterLockOfChilds();
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewAdapter = new HomeRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    // 当前页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {

    }

    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
