package com.loicteillard.easytabs.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EasyTabTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tab_text);

    }

    public void example1(View v) { startActivity(new Intent(this,EasyTabTextExample1Activity.class)); }
    public void example2(View v) { startActivity(new Intent(this,EasyTabTextExample2Activity.class)); }
    public void example3(View v) { startActivity(new Intent(this,EasyTabTextExample3Activity.class)); }


}
