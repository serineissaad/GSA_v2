package com.p1.gsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class sadminpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadminpage);

        if(!sharedrefmanager.getInstance(this).isloggedin()){
            finish();
            startActivity(new Intent(this,login.class));
        }
    }
}