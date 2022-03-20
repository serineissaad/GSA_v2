package com.p1.gsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class sadminpage extends AppCompatActivity {

    private TextView email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nav_header);
        setContentView(R.layout.activity_nav_draw);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!sharedrefmanager.getInstance(this).isloggedin()){
            finish();
            startActivity(new Intent(this,login.class));
        }


        /*email=(TextView) findViewById(R.id.email);
        name=(TextView) findViewById(R.id.name);

        email.setText(sharedrefmanager.getInstance(this).getemaila());
        name.setText(sharedrefmanager.getInstance(this).getnoms());*/
    }


}