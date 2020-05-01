package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchState extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    OurRetrofitClient ourRetrofitClient;
    RecyclerView recyclerView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;



    List<Statewise> list = new ArrayList<>();

    ProgressBar pro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_state);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawerLayout3);
        navigationView = findViewById(R.id.nav_view3);
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        pro = findViewById(R.id.pro);



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_statewise);

        checkConnection();

        submitApi();

    }

    private  void submitApi(){
        //pro.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ourRetrofitClient = retrofit.create(OurRetrofitClient.class);
        Call<Response> call = ourRetrofitClient.getData();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {



                if(response.isSuccessful()){
                    try{
                        Log.e("message","in the success block of state");
                        com.example.covid.Response resp = response.body();
                        Log.e("message","in the response");

                        recyclerView.setAdapter(new PostAdapter(FetchState.this,resp.getStatewise()));

                        list = resp.getStatewise();

                        for(Statewise obj:list){
                            Log.d("message",obj.getActive());



                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }


    public void checkConnection(){

        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if(null!=activeNetwork){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                //Toast.makeText(this,"WIFI enabled", Toast.LENGTH_LONG).show();
            }else if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                //Toast.makeText(this,"Mobile Data enabled", Toast.LENGTH_LONG).show();
            }
        }else{
            pro.setVisibility(View.VISIBLE);
            Toast.makeText(this,"No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            Intent intent = new Intent(FetchState.this,Dash.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            super.onBackPressed();
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(FetchState.this,Dash.class);
                startActivity(intent);
                break;
            case R.id.nav_daily:
                Intent intent2 = new Intent(FetchState.this,Fetch.class);
                startActivity(intent2);
                break;
            case R.id.nav_statewise:
//                Intent intent2 = new Intent(Dash.this,FetchState.class);
//                startActivity(intent2);
                break;
            case R.id.nav_real:
                Intent intent3 = new Intent(FetchState.this,TestActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_stats:
                Intent intent4 = new Intent(FetchState.this,SelectionActivity.class);
                startActivity(intent4);
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
