package com.loicteillard.easytabs.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_icon);

        EasyTabs easyTabs = (EasyTabs) findViewById(R.id.easytabs);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        MyFragmentAdapter pagerAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        easyTabs.setViewPager(viewpager);
    }
}
