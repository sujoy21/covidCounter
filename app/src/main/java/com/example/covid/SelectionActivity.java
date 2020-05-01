package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SelectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    DrawerLayout drawerLayout;
    NavigationView navigationView;

    CardView card1;
    CardView card2;
    CardView card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout5);
        navigationView = findViewById(R.id.nav_view5);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_stats);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this,ChartActivity.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this,Deaths.class);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectionActivity.this,RecoveredActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            Intent intent = new Intent(SelectionActivity.this,Dash.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(SelectionActivity.this,Dash.class);
                startActivity(intent);
                break;
            case R.id.nav_daily:
                Intent intent2 = new Intent(SelectionActivity.this,Fetch.class);
                startActivity(intent2);
                break;
            case R.id.nav_statewise:
                Intent intent3 = new Intent(SelectionActivity.this,FetchState.class);
                startActivity(intent3);
                break;
            case R.id.nav_real:
                Intent intent4 = new Intent(SelectionActivity.this,TestActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_stats:
                break;
            case R.id.nav_feed:
                Toast.makeText(this,"Please access from Dashboard",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Please access from Dashboard",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_help:
                Toast.makeText(this,"Please access from Dashboard",Toast.LENGTH_LONG).show();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
