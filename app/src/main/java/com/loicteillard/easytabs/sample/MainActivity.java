package com.loicteillard.easytabs.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void EasyTabText(View v) { startActivity(new Intent(this,EasyTabTextActivity.class)); }

    public void EasyTabIcon(View v) {
        startActivity(new Intent(this,EasyTabIconActivity.class));
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public void TabLayoutText(View v) {
        startActivity(new Intent(this,TabLayoutTextActivity.class));
    }

    public void TabLayoutIcon(View v) {
        startActivity(new Intent(this,TabLayoutIconActivity.class));
    }




}