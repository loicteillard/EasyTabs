package com.loicteillard.easytabs.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabTextExample1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text_ex1);

        EasyTabs easyTabs = (EasyTabs) findViewById(R.id.easytabs);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        MyFragmentAdapter pagerAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        easyTabs.setViewPager(viewpager);
    }


}
