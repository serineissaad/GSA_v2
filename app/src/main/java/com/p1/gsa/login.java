package com.p1.gsa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener{
    TextView btnsignup;
    EditText emaila,pass;
    Button btnlogin;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(sharedrefmanager.getInstance(this).isloggedin()){
            finish();
            startActivity(new Intent(this,sadminpage.class));
            return;
        }

        emaila=(EditText) findViewById(R.id.emaila);
        pass=(EditText) findViewById(R.id.pass);
        btnsignup =(TextView) findViewById(R.id.title3);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        btnsignup.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);

        btnlogin.setOnClickListener(this);
    }

    private void loging(){
        final String emailatxt=emaila.getText().toString().trim();
        final String passtxt=pass.getText().toString().trim();

        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                sharedrefmanager.getInstance(getApplicationContext()).sadminlogin(
                                        obj.getInt("id"),
                                        obj.getString("noms"),
                                        obj.getString("emaila")
                                );

                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),sadminpage.class));
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "error listener", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"error listener login",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            //@Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params=new HashMap<String, String>();
            params.put("emaila",emailatxt);
            params.put("pass",passtxt);
            return params;
            }
        };
        requesthandler.getInstance(this).addToRequestQueue(str);
    }

    @Override
    public void onClick(View view) {
        if(view==btnsignup){
            startActivity(new Intent(this,signupas.class));}
        if(view==btnlogin){
            loging();
        }
    }
}