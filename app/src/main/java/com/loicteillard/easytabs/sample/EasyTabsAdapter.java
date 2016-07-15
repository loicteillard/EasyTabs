package com.loicteillard.easytabs.sample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EasyTabsAdapter extends PagerAdapter {

    private Context mContext;
    private PagerInterface mPagerInterface;
    private EasyTabsPagerEnum[] enums;

    public EasyTabsAdapter(Context context, PagerInterface pagerInterface) {
        mContext = context;
        mPagerInterface = pagerInterface;
        enums = EasyTabsPagerEnum.values();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(enums[position].getLayoutResId(), collection, false);
        collection.addView(layout);
        if (mPagerInterface != null) mPagerInterface.isInstancied(layout, enums[position]);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return enums.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(enums[position].getTitleResId());
    }

    public interface PagerInterface {
        void isInstancied(ViewGroup layout, EasyTabsPagerEnum pagerEnum);
    }

}
