package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Deaths extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deaths);
        DeathFragment deathFragment = new DeathFragment();
        Bundle bundle = new Bundle();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container2,deathFragment).commit();
    }
}
