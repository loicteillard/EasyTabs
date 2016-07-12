package loic.teillard.easytabs;

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

    private void initialize() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lParams);

//        layout
        //--------------

        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(rParams);
        LinearLayout layoutTabs = new LinearLayout(context);
        layoutTabs.setOrientation(HORIZONTAL);
        layoutTabs.setLayoutParams(lParams);

//        textviews
        //--------------

        tab1 = new TextView(context);
        tab1.setGravity(Gravity.CENTER);
//        if (tab1.getPaddingLeft() == 0 && tab1.getPaddingRight() == 0 && tab1.getPaddingTop() == 0 && tab1.getPaddingBottom() == 0) {
//        tab1.setPadding(0, Utils.dpToPx(10), 0, Utils.dpToPx(5));
        tab1.setPadding(0, 0, 0, Utils.dpToPx(5));
//        }

        LinearLayout.LayoutParams textViewParams1 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams1.weight = 1f;
        textViewParams1.gravity = Gravity.CENTER;
        tab1.setLayoutParams(textViewParams1);

        tab1.setAllCaps(true);
        tab1.setTextSize(17f);

        //--------------

        tab2 = new TextView(context);
        tab2.setGravity(Gravity.CENTER);
//        if (tab2.getPaddingLeft() == 0 && tab2.getPaddingRight() == 0 && tab2.getPaddingTop() == 0 && tab2.getPaddingBottom() == 0) {
//        tab2.setPadding(0, Utils.dpToPx(10), 0, Utils.dpToPx(5));
        tab2.setPadding(0, 0, 0, Utils.dpToPx(5));
//        }

        LinearLayout.LayoutParams textViewParams2 = new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams2.weight = 1f;
        textViewParams2.gravity = Gravity.CENTER;
        tab2.setLayoutParams(textViewParams2);

        tab2.setAllCaps(true);
        tab2.setTextSize(17f);

        layoutTabs.addView(tab1);
        layoutTabs.addView(tab2);

//        sep
        // -------------

//        View separator = new View(context);
        TextView separator = new TextView(context);
//        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
//        LayoutParams sepParams = new LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
//        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
        RelativeLayout.LayoutParams sepParams = new RelativeLayout.LayoutParams(Utils.dpToPx(1), Utils.dpToPx(15));
        separator.setBackgroundColor(Color.parseColor("#b7b7b7"));
//        separator.setBackgroundColor(Color.RED);


        sepParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        separator.setLayoutParams(sepParams);

        // indicator
        // -------------

        indicator = new View(context);
        LinearLayout.LayoutParams indicatorParams = new LayoutParams(0, Utils.dpToPx(3));
        indicatorParams.gravity = Gravity.TOP;
        indicator.setLayoutParams(indicatorParams);

        // add views
        // -------------

        relativeLayout.addView(layoutTabs);
        relativeLayout.addView(separator);

        addView(relativeLayout);
        addView(indicator);
    }

    public TextView getTab1() {
        return tab1;
    }

    public TextView getTab2() {
        return tab2;
    }

    public View getIndicator() {
        return indicator;
    }

    //    <LinearLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:orientation="vertical">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content">
//
//    <LinearLayout
//    android:id="@+id/layout_tabs"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:orientation="horizontal"
//    android:paddingBottom="0dp"
//    android:paddingTop="0dp"
//            >
//
//    <TextView
//    android:id="@+id/tab_1"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_gravity="center"
//    android:layout_weight="1"
//    android:gravity="center"
//    android:paddingBottom="5dp"
//    android:paddingTop="10dp"
//    android:text="1"
//    android:textAllCaps="true"
//    android:textSize="17sp"/>
//
//    <TextView
//    android:id="@+id/tab_2"
//    android:layout_gravity="center"
//    android:layout_weight="1"
//    android:gravity="center"
//    android:paddingBottom="5dp"
//    android:paddingTop="10dp"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:textAllCaps="true"
//    android:textSize="17sp"
//    android:text="2"/>
//
//    </LinearLayout>
//
//    <View
//    android:layout_width="1dp"
//    android:layout_height="15dp"
//    android:layout_centerInParent="true"
//    android:background="@color/grey"
//            />
//
//
//    </RelativeLayout>
//
//    <View
//    android:id="@+id/indicator"
//    android:layout_width="50dp"
//    android:layout_height="3dp"
//    android:layout_gravity="top"
//    android:layout_marginBottom="0dp"
//    android:layout_marginTop="0dp"
//            />
//
//    </LinearLayout>



}
