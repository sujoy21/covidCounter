package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecoveredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovered);
        RecoveredFragment recoveredFragment = new RecoveredFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container3,recoveredFragment).commit();
    }
}
