package com.loicteillard.easytabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import loic.teillard.easytabs.R;

import static com.loicteillard.easytabs.ETUtils.setMarginsLayout;

public class EasyTabs extends LinearLayout {

    private View mIndicator;
    private ArrayList<TextView> mTabs;
    private int mSelectedColor, mUnselectedColor;
    private ViewPager mViewPager;

    // ---------------------------------------------------------------------------------------------------------------------

    public EasyTabs(Context context) {
        super(context);
        initialize(null);
    }

    public EasyTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public EasyTabs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void initialize(AttributeSet attrs) {

        TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyTabsAttrs, 0, 0);
        initAttributesArray(attrsArray);
        attrsArray.recycle();

        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lParams);

        //--------------

        final RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(rParams);
        final LinearLayout layoutTabs = new LinearLayout(getContext());
        layoutTabs.setOrientation(HORIZONTAL);
        layoutTabs.setLayoutParams(lParams);

        //--------------

        post(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < getChildCount(); i++) {
                    TextView textView = (TextView) getChildAt(i);
                    addTab(prepareTab(textView));
                }

//        View separator = createSeparator(); // separator in middle (for 2 tabs)
                mIndicator = createIndicator();

                int index = 0;
                for (TextView textView : getTabs()) {
                    layoutTabs.addView(textView);

                    final int finalIndex = index;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switchState(finalIndex);
                        }
                    });

                    index++;

                }

                switchState(0);

                relativeLayout.addView(layoutTabs);
                //        relativeLayout.addView(separator); // separator in middle (for 2 tabs)

                addView(relativeLayout);
                addView(mIndicator);
            }
        });
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void initAttributesArray(TypedArray attrsArray) {

        if (attrsArray == null) return;

        mSelectedColor = attrsArray.getColor(R.styleable.EasyTabsAttrs_selected_color, Color.BLACK);
        mUnselectedColor = attrsArray.getColor(R.styleable.EasyTabsAttrs_unselected_color, Color.BLACK);
        mViewPager = (ViewPager) findViewById(attrsArray.getResourceId(R.styleable.EasyTabsAttrs_attached_viewpager,0));
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void switchState(int tab) {

        final TextView tab1 = getTabs().get(0);
        final TextView tab2 = getTabs().get(1);
        final TextView tab3 = getTabs().get(2);

        switch (tab) {
            case 0:
                tab1.setTextColor(mSelectedColor);
                tab2.setTextColor(mUnselectedColor);
                tab3.setTextColor(mUnselectedColor);
                mIndicator.setBackgroundColor(mSelectedColor);

                tab1.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(tab1.getX()).setDuration(200);
                                int padding = ETUtils.getTextWidth(tab1);
                                int tabWidth = tab1.getMeasuredWidth();
                                ETUtils.setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );

                break;

            case 1:
                tab1.setTextColor(mUnselectedColor);
                tab2.setTextColor(mSelectedColor);
                tab3.setTextColor(mUnselectedColor);
                mIndicator.setBackgroundColor(mSelectedColor);
                tab2.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(tab2.getX()).setDuration(200);
                                int padding = ETUtils.getTextWidth(tab2);
                                int tabWidth = tab2.getMeasuredWidth();
                                ETUtils.setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
                break;

            case 2:
                tab1.setTextColor(mUnselectedColor);
                tab2.setTextColor(mUnselectedColor);
                tab3.setTextColor(mSelectedColor);
                mIndicator.setBackgroundColor(mSelectedColor);
                tab3.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(tab3.getX()).setDuration(200);
                                int padding = ETUtils.getTextWidth(tab3);
                                int tabWidth = tab3.getMeasuredWidth();
                                ETUtils.setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
                break;
        }

        mViewPager.setCurrentItem(tab, true);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private TextView prepareTab(TextView tab) {
        tab.setGravity(Gravity.CENTER);
        tab.setPadding(0, 0, 0, ETUtils.dpToPx(5));

        LinearLayout.LayoutParams textViewParams1 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams1.weight = 1f;
        textViewParams1.gravity = Gravity.CENTER;
        tab.setLayoutParams(textViewParams1);

        return tab;
    }

    // ---------------------------------------------------------------------------------------------------------------------

//    private TextView createTab() {
//        TextView tab = new TextView(getContext());
//        tab.setGravity(Gravity.CENTER);
//        tab.setPadding(0, 0, 0, ETUtils.dpToPx(5));
//
//        LinearLayout.LayoutParams textViewParams1 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textViewParams1.weight = 1f;
//        textViewParams1.gravity = Gravity.CENTER;
//        tab.setLayoutParams(textViewParams1);
//
//        tab.setAllCaps(true);
//        tab.setTextSize(17f);
//
//        return tab;
//    }

    // ---------------------------------------------------------------------------------------------------------------------

//    private View createSeparator() {
//        View view = new View(context);
//        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
//        view.setBackgroundColor(Color.parseColor("#b7b7b7"));
//
//        sepParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//        view.setLayoutParams(sepParams);
//
//        return view;
//    }

    // ---------------------------------------------------------------------------------------------------------------------

    private View createIndicator() {
        View view = new View(getContext());
        LinearLayout.LayoutParams indicatorParams = new LayoutParams(0, ETUtils.dpToPx(3));
        indicatorParams.gravity = Gravity.TOP;
        view.setLayoutParams(indicatorParams);

        return view;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public void addTab(TextView textView) {
        if (textView == null) return;
        getTabs().add(textView);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public ArrayList<TextView> getTabs() {
        if (mTabs == null) mTabs = new ArrayList<>();
        return mTabs;
    }

    // ---------------------------------------------------------------------------------------------------------------------
}
