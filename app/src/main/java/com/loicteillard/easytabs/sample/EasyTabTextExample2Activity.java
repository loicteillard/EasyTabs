package com.loicteillard.easytabs.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabTextExample2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text_ex2);

        EasyTabs easyTabs = (EasyTabs) findViewById(R.id.easytabs);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        MyFragmentAdapter pagerAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        easyTabs.setViewPager(viewpager,1);

        easyTabs.setPagerListener(new EasyTabs.PagerListener() {
            @Override
            public void onTabSelected(int position) {
                Toast.makeText(EasyTabTextExample2Activity.this, "tab selected:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        
    }


}
