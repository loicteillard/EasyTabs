package com.loicteillard.easytabs.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabLayoutTextFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_1 = 0;
    private static final int TAB_2 = 1;
    private static final int TAB_3 = 2;

    private int nbTabs;

    public TabLayoutTextFragmentAdapter(FragmentManager fragmentManager, int nbTabs) {

        super(fragmentManager);

        this.nbTabs = nbTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_1:
                TabLayoutTextFragmentTab1 tab1 = new TabLayoutTextFragmentTab1();
                return tab1;

            case TAB_2:
                TabLayoutTextFragmentTab2 tab2 = new TabLayoutTextFragmentTab2();
                return tab2;

            case TAB_3:
                TabLayoutTextFragmentTab3 tab3 = new TabLayoutTextFragmentTab3();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nbTabs;
    }
}