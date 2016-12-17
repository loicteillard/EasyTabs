package com.loicteillard.easytabs.sample;


public enum EasyTabsPagerEnum {

    TAB_1(R.string.tab_1, R.layout.layout_tab_1),
    TAB_2(R.string.tab_2, R.layout.layout_tab_2),
    TAB_3(R.string.tab_3, R.layout.layout_tab_3);

    private int mTitleResId;
    private int mLayoutResId;

    EasyTabsPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
