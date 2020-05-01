package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,chartFragment).commit();

    }
}
