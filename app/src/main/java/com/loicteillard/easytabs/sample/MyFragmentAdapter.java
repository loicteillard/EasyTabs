package com.loicteillard.easytabs.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_1 = 0;
    private static final int TAB_2 = 1;
    private static final int TAB_3 = 2;

    private static final int NB_TABS = 3;

    public MyFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_1:
                EasyTabTextFragmentTab1 tab1 = new EasyTabTextFragmentTab1();
                return tab1;

            case TAB_2:
                EasyTabTextFragmentTab2 tab2 = new EasyTabTextFragmentTab2();
                return tab2;

            case TAB_3:
                EasyTabTextFragmentTab3 tab3 = new EasyTabTextFragmentTab3();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NB_TABS;
    }
}