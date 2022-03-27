package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signupas extends AppCompatActivity implements View.OnClickListener {




    EditText prenoma,noma,emaila,pass,emails,noms;
    Button btnsignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupas);

        if(sharedrefmanager.getInstance(this).isloggedin()){
            finish();
            startActivity(new Intent(this,sadminpage.class));
            return;
        }

        noms= findViewById(R.id.noms);
        emails= findViewById(R.id.emails);
        prenoma= findViewById(R.id.prenoma);
        noma= findViewById(R.id.noma);
        emaila= findViewById(R.id.emaila);
        pass=findViewById(R.id.pass);

        btnsignup=findViewById(R.id.btnsignup);

        progressDialog=new ProgressDialog(this);

        btnsignup.setOnClickListener(this);
    }

    private void registeras(){
        final String nomstxt=noms.getText().toString().trim();
        final String emailstxt=emails.getText().toString().trim();
        final String nomatxt=noma.getText().toString().trim();
        final String prenomatxt=prenoma.getText().toString().trim();
        final String emailatxt=emaila.getText().toString().trim();
        final String passtxt=pass.getText().toString().trim();

        progressDialog.setMessage("Regestering user..");
        progressDialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject job=new JSONObject(response);
                        if(!job.getBoolean("error")) {
                            //Toast.makeText(signupas.this, "in if", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), job.getString("message"), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),login.class));
                            finish();
                        }
                        else{
                            //Toast.makeText(signupas.this, "in else", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),job.getString("message"),Toast.LENGTH_LONG).show();
                        }
                        } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(signupas.this, "Cet utilisateur existe déja.", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                //Toast.makeText(signupas.this, "in params", Toast.LENGTH_SHORT).show();
                params.put("noms",nomstxt);
                params.put("emails",emailstxt);
                params.put("prenoma",prenomatxt);
                params.put("noma",nomatxt);
                params.put("emaila",emailatxt);
                params.put("pass",passtxt);
            return params;
            }
        };
        //RequestQueue rq= Volley.newRequestQueue(signupas.this);
        //Toast.makeText(signupas.this, "about to add", Toast.LENGTH_SHORT).show();
        //rq.add(str);
        requesthandler.getInstance(this).addToRequestQueue(str);
    }

    @Override
    public void onClick(View view) {
        if(view==btnsignup)
            registeras();
    }
}