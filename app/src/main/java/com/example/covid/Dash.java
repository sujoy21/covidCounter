package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Dash extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    CardView daily;
    CardView statew;
    CardView news;
    CardView stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        daily = findViewById(R.id.daily);
        statew = findViewById(R.id.statew);
        news = findViewById(R.id.news);
        stats = findViewById(R.id.stats);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);




        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dash.this,Fetch.class);
                startActivity(intent);
            }
        });

        statew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dash.this,FetchState.class);
                startActivity(intent);
            }
        });


        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dash.this,TestActivity.class);
                startActivity(intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dash.this,SelectionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_daily:
                Intent intent = new Intent(Dash.this,Fetch.class);
                startActivity(intent);
                break;
            case R.id.nav_statewise:
                Intent intent2 = new Intent(Dash.this,FetchState.class);
                startActivity(intent2);
                break;
            case R.id.nav_real:
                Intent intent3 = new Intent(Dash.this,TestActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_stats:
                Intent intent4 = new Intent(Dash.this,SelectionActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_feed:
                Intent intent5=new Intent(Intent.ACTION_SEND);
                String[] recipients={"sujoy.dazz@gmail.com"};
                intent5.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent5.setType("text/html");
                intent5.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent5, "Send mail"));
                break;
            case R.id.nav_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "can't be shared as this is a personal project....plz send your feedback if you are enjoying this";
                //String sub = "Your Subject";
                //myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                break;
            case R.id.nav_help:
                Intent intent7 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.investindia.gov.in/bip/resources/state-and-central-control-rooms"));
                startActivity(intent7);
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
