package com.loicteillard.easytabs.sample;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loicteillard.easytabs.EasyTabs;

public class EasyTabTextActivity extends BaseActivity {

    private TextView mTab1, mTab2, mTab3;
    private View mIndicator;
    private ViewPager mViewPager;
    private EasyTabs mEasyTabs;

//    private EasyTabsPagerEnum mEasyTabsPagerEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mEasyTabs = (EasyTabs) findViewById(R.id.easytabs);

        mTab1 = mEasyTabs.getTab1();
        mTab2 = mEasyTabs.getTab2();
        mTab3 = mEasyTabs.getTab3();
        mIndicator = mEasyTabs.getIndicator();

        mTab1.setText(EasyTabsPagerEnum.TAB_1.getTitleResId());
        mTab2.setText(EasyTabsPagerEnum.TAB_2.getTitleResId());
        mTab3.setText(EasyTabsPagerEnum.TAB_3.getTitleResId());

        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState(EasyTabsPagerEnum.TAB_1);
            }
        });

        mTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState(EasyTabsPagerEnum.TAB_2);
            }
        });
        mTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState(EasyTabsPagerEnum.TAB_3);
            }
        });

        EasyTabTextFragmentAdapter pagerAdapter = new EasyTabTextFragmentAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchState(EasyTabsPagerEnum.values()[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        switchState(EasyTabsPagerEnum.TAB_1);
    }

    private void switchState(EasyTabsPagerEnum pagerEnum) {

        int unselected, selected;

        switch (pagerEnum) {
            case TAB_1:
                unselected = ContextCompat.getColor(this, R.color.md_grey_700);
                selected = ContextCompat.getColor(this, R.color.md_deep_orange_A200);
                mTab1.setTextColor(selected);
                mTab2.setTextColor(unselected);
                mTab3.setTextColor(unselected);
                mIndicator.setBackgroundColor(selected);

                mTab1.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(mTab1.getX()).setDuration(200);
                                int padding = getTextWidth(mTab1);
                                int tabWidth = mTab1.getMeasuredWidth();
                                setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );

                break;

            case TAB_2:
                unselected = ContextCompat.getColor(this, R.color.md_grey_700);
                selected = ContextCompat.getColor(this, R.color.md_deep_orange_A200);
                mTab1.setTextColor(unselected);
                mTab2.setTextColor(selected);
                mTab3.setTextColor(unselected);
                mIndicator.setBackgroundColor(selected);
                mTab2.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(mTab2.getX()).setDuration(200);
                                int padding = getTextWidth(mTab2);
                                int tabWidth = mTab2.getMeasuredWidth();
                                setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
                break;

            case TAB_3:
                unselected = ContextCompat.getColor(this, R.color.md_grey_700);
                selected = ContextCompat.getColor(this, R.color.md_deep_orange_A200);
                mTab1.setTextColor(unselected);
                mTab2.setTextColor(unselected);
                mTab3.setTextColor(selected);
                mIndicator.setBackgroundColor(selected);
                mTab3.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(mTab3.getX()).setDuration(200);
                                int padding = getTextWidth(mTab3);
                                int tabWidth = mTab3.getMeasuredWidth();
                                setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
                break;
        }

        mViewPager.setCurrentItem(pagerEnum.ordinal(), true);
        mViewPager.setTag(R.integer.key_viewpager_tag, pagerEnum);
    }

    private int getTextWidth(TextView tv) {
        Rect bounds = new Rect();
        Paint textPaint = tv.getPaint();
        textPaint.getTextBounds(tv.getText().toString().toUpperCase(), 0, tv.getText().toString().length(), bounds);
        return bounds.width();
    }

    public static void setDimensionLayout(View layout, int width, int height) {

        if (layout == null) return;

        try {

            ViewGroup.LayoutParams params = layout.getLayoutParams();

            if (width < 0) width = ViewGroup.LayoutParams.MATCH_PARENT;
            if (width == 0) width = ViewGroup.LayoutParams.WRAP_CONTENT;
            if (width == -1) width = params.width;
            if (height < 0) height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (height == 0) height = ViewGroup.LayoutParams.WRAP_CONTENT;
            if (height == -1) height = params.height;

            params.width = width;
            params.height = height;
            layout.setLayoutParams(params);
            layout.requestLayout();
        } catch (Exception e) {
            Log.e("Error", "change dimension");
        }
    }

    public static void setMarginsLayout(View layout, int left, int top, int right, int bottom) {
        if (layout == null) return;
        try {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            if (left == -1) left = mlp.leftMargin;
            if (right == -1) right = mlp.rightMargin;
            if (top == -1) top = mlp.topMargin;
            if (bottom == -1) bottom = mlp.bottomMargin;
            mlp.setMargins(left, top, right, bottom);
            layout.requestLayout();
        } catch (Exception e) {
            Log.e("Error", "to change dimension");
        }
    }
}
