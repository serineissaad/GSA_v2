package com.p1.gsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class nav_draw extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView email, name;
    private View headview;
    private DrawerLayout drawer;
    Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_draw);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView naview = (NavigationView) findViewById(R.id.nav_view);

        headview = naview.getHeaderView(0);

        email = (TextView) headview.findViewById(R.id.email);
        name = (TextView) headview.findViewById(R.id.name);
        drawer = findViewById(R.id.drawer_layout);

        btnlogout=findViewById(R.id.btnlogout);
        //btnlogout.setOnClickListener((View.OnClickListener) this);

        email.setText(sharedrefmanager.getInstance(this).getemaila());
        name.setText(sharedrefmanager.getInstance(this).getnoms());
        naview.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(savedInstanceState==null){

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new sadmin_acc()).addToBackStack(null).commit();

        naview.setCheckedItem(R.id.account);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new sadmin_acc()).addToBackStack(null).commit();
                break;
            case R.id.add:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new sadmin_act()).addToBackStack(null).commit();
                break;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new sadmin_sear_up()).addToBackStack(null).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}