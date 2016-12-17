package com.loicteillard.easytabs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EasyTabs extends LinearLayout {

    private Context context;
    private View indicator;
    private ArrayList<TextView> mTabs;

    // ---------------------------------------------------------------------------------------------------------------------

    public EasyTabs(Context context) {
        super(context);
        this.context = context;
        initialize();
    }

    public EasyTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialize();
    }

    public EasyTabs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private void initialize() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lParams);

//        create views
        //--------------

        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(rParams);
        LinearLayout layoutTabs = new LinearLayout(context);
        layoutTabs.setOrientation(HORIZONTAL);
        layoutTabs.setLayoutParams(lParams);

        //--------------

        for (int i = 0; i < getChildCount(); i++) {
            TextView textView = (TextView) getChildAt(i);
            addTab(prepareTab(textView));
        }

//        View separator = createSeparator(); // separator in middle (for 2 tabs)
        indicator = createIndicator();

        // add views
        // -------------

        for (TextView textView : getTabs()) {
            layoutTabs.addView(textView);
        }

        relativeLayout.addView(layoutTabs);
//        relativeLayout.addView(separator); // separator in middle (for 2 tabs)

        addView(relativeLayout);
        addView(indicator);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private TextView prepareTab(TextView tab) {
        tab.setGravity(Gravity.CENTER);
        tab.setPadding(0, 0, 0, Utils.dpToPx(5));

        LinearLayout.LayoutParams textViewParams1 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams1.weight = 1f;
        textViewParams1.gravity = Gravity.CENTER;
        tab.setLayoutParams(textViewParams1);

        return tab;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private TextView createTab() {
        TextView tab = new TextView(context);
        tab.setGravity(Gravity.CENTER);
        tab.setPadding(0, 0, 0, Utils.dpToPx(5));

        LinearLayout.LayoutParams textViewParams1 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams1.weight = 1f;
        textViewParams1.gravity = Gravity.CENTER;
        tab.setLayoutParams(textViewParams1);

        tab.setAllCaps(true);
        tab.setTextSize(17f);

        return tab;
    }

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
        View view = new View(context);
        LinearLayout.LayoutParams indicatorParams = new LayoutParams(0, Utils.dpToPx(3));
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

    public View getIndicator() {
        return indicator;
    }

    // ---------------------------------------------------------------------------------------------------------------------


}
