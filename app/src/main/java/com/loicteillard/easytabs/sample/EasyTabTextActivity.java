package com.loicteillard.easytabs.sample;

import android.os.Bundle;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabTextActivity extends BaseActivity {

    private EasyTabs mEasyTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text);

        mEasyTabs = (EasyTabs) findViewById(R.id.easytabs);

        MyFragmentAdapter pagerAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mEasyTabs.setViewPagerAdapter(pagerAdapter);
    }


}
