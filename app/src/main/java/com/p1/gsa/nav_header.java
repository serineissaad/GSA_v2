package com.p1.gsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class nav_header extends AppCompatActivity {
    private TextView email, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_header);
        //setContentView(R.layout.activity_nav_draw);
        //setContentView(R.layout.activity_sadminpage);

        email=(TextView)findViewById(R.id.email);
        name=(TextView)findViewById(R.id.name);

        email.setText(sharedrefmanager.getInstance(this).getemaila());
        name.setText(sharedrefmanager.getInstance(this).getnoms());
    }
}