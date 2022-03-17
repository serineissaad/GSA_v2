package com.p1.gsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener{
    private TextView btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin=(TextView) findViewById(R.id.title3);
        btnsignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btnsignin)
            startActivity(new Intent(this,signupas.class));
    }
}