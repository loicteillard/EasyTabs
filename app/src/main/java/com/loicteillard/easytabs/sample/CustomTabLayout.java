package com.loicteillard.easytabs.sample;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by loic on 16/07/2016.
 */
public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void createTabsWithIcons(int drawableId, int descriptionId) { // ex : R.drawable.tab_piano_tutor, R.string.about_tutor
//        addTab(R.drawable.tab_piano_tutor, R.string.about_tutor);
//        addTab(R.drawable.tab_piano_courses, R.string.courses);
    }

    public void createTabsWithText(String tab1, String tab2) {
        addTab(tab1);
        addTab(tab2);
    }

    private void addTab(String text) {
        View tabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_text, null);
        TextView textView = (TextView) tabView.findViewById(R.id.tab_text);
        textView.setText(text);
        Tab tab = newTab();
        tab.setCustomView(tabView);
        addTab(tab);
    }

    private void addTab(@DrawableRes int iconId, @StringRes int contentDescriptionId) {
        View tabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_icon, null);
        ImageView imageView = (ImageView) tabView.findViewById(R.id.tab_icon);
        imageView.setImageResource(iconId);
        Tab tab = newTab();
        tab.setCustomView(tabView).setContentDescription(contentDescriptionId);
        addTab(tab);
    }
}
