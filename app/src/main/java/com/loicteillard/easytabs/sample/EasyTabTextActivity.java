package com.loicteillard.easytabs.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabTextActivity extends BaseActivity {

    private TextView mTab1, mTab2, mTab3;
    private View mIndicator;
    private ViewPager mViewPager;
    private EasyTabs mEasyTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text);

//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mEasyTabs = (EasyTabs) findViewById(R.id.easytabs);

        MyFragmentAdapter pagerAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mEasyTabs.setViewPagerAdapter(pagerAdapter);


    }




}
