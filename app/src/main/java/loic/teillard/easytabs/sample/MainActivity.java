package loic.teillard.easytabs.sample;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import loic.teillard.easytabs.EasyTabs;


public class MainActivity extends AppCompatActivity {

    private TextView mTab1, mTab2;
    private View mIndicator;
    private ViewPager mViewPager;
    private EasyTabs mEasyTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mEasyTabs = (EasyTabs) findViewById(R.id.easytabs);
//        mTab1 = (TextView) findViewById(R.id.tab_1);
        mTab1 = mEasyTabs.getTab1();
//        mTab2 = (TextView) findViewById(R.id.tab_2);
        mTab2 = mEasyTabs.getTab2();
//        mIndicator = findViewById(R.id.indicator);
        mIndicator = mEasyTabs.getIndicator();

        mTab1.setText(EasyTabsPagerEnum.TAB_1.getTitleResId());
        mTab2.setText(EasyTabsPagerEnum.TAB_2.getTitleResId());

//        final ViewGroup layout = (ViewGroup) ((ViewGroup) this
//                .findViewById(android.R.id.content)).getChildAt(0);

//        EasyTabs easyTabs = new EasyTabs(this);

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

        final EasyTabsAdapter pagerAdapter = new EasyTabsAdapter(this, new EasyTabsAdapter.PagerInterface() {
            @Override
            public void isInstancied(ViewGroup layout, EasyTabsPagerEnum pagerEnum) {

                switch (pagerEnum) {
                    case TAB_1:
                        populateTab1(layout);
                        break;
                    case TAB_2:
                        populateTab2(layout);
                        break;
                }
            }
        });

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

        // Init tabs
        switchState(EasyTabsPagerEnum.TAB_1);
    }

    private void populateTab1(ViewGroup layout) {
        TextView content = (TextView) layout.findViewById(R.id.content);
        content.setText("This is Tab 1");
    }

    private void populateTab2(ViewGroup layout) {
        TextView content = (TextView) layout.findViewById(R.id.content);
        content.setText("This is Tab 2");
    }

    private void switchState(EasyTabsPagerEnum pagerEnum) {

        int unselected, selected;

        switch (pagerEnum) {
            case TAB_1:
                unselected = ContextCompat.getColor(this, R.color.dark_grey);
                selected = ContextCompat.getColor(this, R.color.purple);
                mTab1.setTextColor(selected);
                mTab2.setTextColor(unselected);
                mIndicator.setBackgroundColor(selected);
                mIndicator.animate().translationX(mTab1.getX()).setDuration(200);
                mTab1.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                int padding = getTextWidth(mTab1);
                                int tabWidth = mTab1.getMeasuredWidth();
                                setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );

                break;

            case TAB_2:
                unselected = ContextCompat.getColor(this, R.color.dark_grey);
                selected = ContextCompat.getColor(this, R.color.purple);
                mTab1.setTextColor(unselected);
                mTab2.setTextColor(selected);
                mIndicator.setBackgroundColor(selected);
                mIndicator.animate().translationX(mTab2.getX()).setDuration(200);
                mTab2.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                int padding = getTextWidth(mTab2);
                                int tabWidth = mTab2.getMeasuredWidth();
                                setDimensionLayout(mIndicator, padding, -1);
                                setMarginsLayout(mIndicator, (tabWidth - padding) >> 1, -1, (tabWidth - padding) >> 1, -1);
                            }
                        }
                );
                break;
        }

        mViewPager.setCurrentItem(pagerEnum.ordinal(), true);
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
