package com.loicteillard.easytabs;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EasyTabs extends LinearLayout {

    private Context context;
    private TextView tab1,tab2;
    private View indicator;

    // ---------------------------------------------------------------------------------------------------------------------

    public EasyTabs(Context context) {
        super(context);
        this.context = context;
        initialize();
    }

    public EasyTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        TypedArray attrsArray 	= context.obtainStyledAttributes(attrs,R.styleable.FancyButtonsAttrs, 0, 0);
//        initAttributesArray(attrsArray);
//        attrsArray.recycle();
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

        tab1 = createTab();
        tab2 = createTab();
        View separator = createSeparator();
        indicator = createIndicator();

        // add views
        // -------------

        layoutTabs.addView(tab1);
        layoutTabs.addView(tab2);

        relativeLayout.addView(layoutTabs);
        relativeLayout.addView(separator);

        addView(relativeLayout);
        addView(indicator);
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

    private View createSeparator() {
        View view = new View(context);
        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
        view.setBackgroundColor(Color.parseColor("#b7b7b7"));

        sepParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        view.setLayoutParams(sepParams);

        return view;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    private View createIndicator() {
        View view = new View(context);
        LinearLayout.LayoutParams indicatorParams = new LayoutParams(0, Utils.dpToPx(3));
        indicatorParams.gravity = Gravity.TOP;
        view.setLayoutParams(indicatorParams);

        return view;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public TextView getTab1() {
        return tab1;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public TextView getTab2() {
        return tab2;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public View getIndicator() {
        return indicator;
    }

    // ---------------------------------------------------------------------------------------------------------------------


}
