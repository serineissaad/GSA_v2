package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
//import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class signupass extends AppCompatActivity implements View.OnClickListener {

    EditText prenoma,noma,emaila,passworda,steassurance,agencea,immatriv,martyv,adressea,numpolice,datevald,datevala;
    EditText passcode;
    Button btnsignup;
    static String emailatxt,adresseatxt,agenceatxt,nomatxt,prenomatxt,numpolicetxt,steassurancetxt,passwordatxt
            ,martyvtxt,immatrivtxt,datevaldtxt,datevalatxt;
    private TextView t5;
    private ProgressDialog progressDialog;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupass);

        t5=findViewById(R.id.t5);
        t5.setOnClickListener(this);

        //if(sharedrefmanager.getInstance(this).isloggedin()){
          //  finish();
            //startActivity(new Intent(this,sadminpage.class));
            //return;

        noma= findViewById(R.id.noma);
        prenoma= findViewById(R.id.prenoma);
        emaila= findViewById(R.id.emaila);
        passworda= findViewById(R.id.passworda);
        steassurance= findViewById(R.id.steassurance);
        agencea=findViewById(R.id.agencea);
        immatriv=findViewById(R.id.immatriv);
        martyv=findViewById(R.id.martyv);
        adressea=findViewById(R.id.adressea);
        numpolice=findViewById(R.id.numpolice);
        datevald=findViewById(R.id.datevald);
        datevala=findViewById(R.id.datevala);
        btnsignup=findViewById(R.id.btnsignup);
        progressDialog=new ProgressDialog(this);
        //btnsignup.setOnClickListener(this);

        passcode=findViewById(R.id.code);

        passcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0){
                    passcode.requestFocus();
                }
            }
        });

    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    }


    @Override
    public void onClick(View view) {
//        if(view==btnsignup)
//            registeras();
        //if(view==t5)
          //  startActivity(new Intent(this,login.class));
    }

    public void sendverifyemail(View view) {
        Random random=new Random();
        code=random.nextInt(100)*random.nextInt(100)-1;

        agenceatxt=agencea.getText().toString().trim();
        adresseatxt=adressea.getText().toString().trim();
        nomatxt=noma.getText().toString().trim();
        prenomatxt=prenoma.getText().toString().trim();
        emailatxt=emaila.getText().toString().trim();
        passwordatxt=passworda.getText().toString().trim();
        datevaldtxt=datevald.getText().toString().trim();
        datevalatxt=datevala.getText().toString().trim();
        immatrivtxt=immatriv.getText().toString().trim();
        martyvtxt=martyv.getText().toString().trim();
        steassurancetxt=steassurance.getText().toString().trim();
        numpolicetxt=numpolice.getText().toString().trim();

        findViewById(R.id.box1).setVisibility(View.GONE);
        findViewById(R.id.box2).setVisibility(View.VISIBLE);

        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_VERIFYEMAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject job=new JSONObject(response);
                    if(!job.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), job.getString("message"),Toast.LENGTH_LONG).show();
                    //finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),job.getString("message"),Toast.LENGTH_LONG).show();
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error listener", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params= new HashMap<String, String>();
                params.put("emaila", emailatxt);
                params.put("code", String.valueOf(code));
                return params;
            }
        };
            requesthandler.getInstance(this).addToRequestQueue(str);
    }

    public void verify(View view) {
        if(passcode.getText().toString().equals(String.valueOf(code))){
            Toast.makeText(this,"Email Verified",Toast.LENGTH_SHORT).show();

            /////////******************************************///////////
            progressDialog.setMessage("Inscription en cours d'enregistrement...");
            progressDialog.show();
            findViewById(R.id.box2).setVisibility(View.VISIBLE);
            StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_REGISTERASS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject job=new JSONObject(response);
                        if(!job.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), job.getString("message"),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),login.class));
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),job.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), "Un utiisateur avac le meme matricule ou email existe deja", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> params= new HashMap<String, String>();
                    params.put("noma",nomatxt);
                    params.put("prenoma",prenomatxt);
                    params.put("adressea",adresseatxt);
                    params.put("steassurance",steassurancetxt);
                    params.put("numpolice",numpolicetxt);
                    params.put("datevald",datevaldtxt);
                    params.put("datevala",datevalatxt);
                    params.put("martyv",martyvtxt);
                    params.put("immatriv",immatrivtxt);
                    params.put("agencea",agenceatxt);
                    params.put("emaila", emailatxt);
                    params.put("passworda",passwordatxt);
                    return params;
                }
            };
            requesthandler.getInstance(this).addToRequestQueue(str);

            /////////******************************************///////////
        }
        else{
            Toast.makeText(this,"Verification Failed",Toast.LENGTH_SHORT).show();
        }
    }
}