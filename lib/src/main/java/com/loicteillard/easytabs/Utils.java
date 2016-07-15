package com.loicteillard.easytabs;

import android.content.res.Resources;

/**
 * Created by loic on 12/07/2016.
 */
public class Utils {

    public static int pxToSp(float px) {
        return Math.round(px / Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(float sp) {
        return Math.round(sp * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}
