package com.loicteillard.easytabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import loic.teillard.easytabs.R;

public class EasyTabs extends LinearLayout {

    private View mIndicator;
    private ArrayList<TextView> mTabs;
    private int mSelectedColor, mUnselectedColor;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

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

        // Read custom values
        TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyTabsAttrs, 0, 0);
        initAttributesArray(attrsArray);
        attrsArray.recycle();

        // Prepare current viewgroup layout
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lParams);


        // Prepare layout for tabs
        final RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(rParams);
        final LinearLayout layoutTabs = new LinearLayout(getContext());
        layoutTabs.setOrientation(HORIZONTAL);
        layoutTabs.setLayoutParams(lParams);

        // Add childs views
        post(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < getChildCount(); i++) {

                    View view = getChildAt(i);
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        addTab(prepareTab(textView));
                    } else if (view instanceof ViewPager) {
                        mViewPager = (ViewPager) view;
                        mViewPager.setAdapter(getPagerAdapter());
                    }
                }

                // Clear views (childs can have only one parent)
                removeAllViews();

                // Create custom stuff
                mIndicator = createIndicator();

                // Add tabs items
                int index = 0;
                for (TextView textView : getTabs()) {
                    layoutTabs.addView(textView);
                    layoutTabs.addView(createSeparator());

                    final int finalIndex = index;
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switchState(finalIndex);
                        }
                    });

                    index++;

                }

                // Listener to change state
                getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switchState(position);

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                // Initial state on the first item
                switchState(0);

                // Add views
                relativeLayout.addView(layoutTabs);

                // At the end, add views to the main viewgroup
                addView(relativeLayout);
                addView(mIndicator);
                addView(mViewPager);
            }
        });
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public ViewPager getViewPager() {
        if (mViewPager == null) throw new IllegalStateException("No ViewPager found, please add a viewpager as a child of the layout !");
        return mViewPager;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public PagerAdapter getPagerAdapter() {
        if (mPagerAdapter == null) throw new IllegalStateException("No Adapter found for this viewpager, please set one !");
        if (mPagerAdapter.getCount() != getTabs().size()) throw new IllegalStateException("Adapter must have the same number of items than tabs !");
        return mPagerAdapter;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public void setViewPagerAdapter(PagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void initAttributesArray(TypedArray attrsArray) {

        if (attrsArray == null) return;

        mSelectedColor = attrsArray.getColor(R.styleable.EasyTabsAttrs_selected_color, Color.BLACK);
        mUnselectedColor = attrsArray.getColor(R.styleable.EasyTabsAttrs_unselected_color, Color.BLACK);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void switchState(int selected) {

        mIndicator.setBackgroundColor(mSelectedColor);

        for (int i = 0; i < getPagerAdapter().getCount(); i++) {
            final TextView tab = getTabs().get(i);
            tab.setTextColor((i == selected) ? mSelectedColor : mUnselectedColor);

            if (i == selected) {
                tab.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                mIndicator.animate().translationX(tab.getX()).setDuration(200);
                                int padding = ETUtils.getTextWidth(tab);
                                int tabWidth = tab.getMeasuredWidth();
                                ETUtils.setDimensionLayout(mIndicator, padding, -1);
                                ETUtils.setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
            }
        }

        getViewPager().setCurrentItem(selected, true);
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

    private View createSeparator() {

        View view = new View(getContext());
        view.setBackgroundColor(Color.parseColor("#b7b7b7"));

        LinearLayout.LayoutParams params = new LayoutParams(ETUtils.dpToPx(1), ETUtils.dpToPx(15));
        params.gravity = Gravity.RIGHT;
        view.setLayoutParams(params);

        return view;
    }

    // ---------------------------------------------------------------------------------------------------------------------

//    private View createSeparator() {
//        View view = new View(getContext());
//        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(ETUtils.dpToPx(1), ETUtils.dpToPx(15));
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
