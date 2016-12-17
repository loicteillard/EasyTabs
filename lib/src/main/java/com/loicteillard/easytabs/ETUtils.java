package com.loicteillard.easytabs;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by loic on 12/07/2016.
 */
public class ETUtils {

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

    public static int getTextWidth(TextView tv) {
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
