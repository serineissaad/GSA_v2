package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class signupass extends AppCompatActivity implements View.OnClickListener {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        /
        });*/

    EditText prenoma,noma,emaila,passworda,steassurance,agencea,immatriv,martyv,adressea,numpolice,datevald,datevala;
    Button btnsignup;
    private TextView t5;
    private ProgressDialog progressDialog;



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
        btnsignup.setOnClickListener(this);
    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    }

    private void registeras(){

        final String agenceatxt=agencea.getText().toString().trim();
        final String adresseatxt=adressea.getText().toString().trim();
        final String nomatxt=noma.getText().toString().trim();
        final String prenomatxt=prenoma.getText().toString().trim();
        final String emailatxt=emaila.getText().toString().trim();
        final String passwordatxt=passworda.getText().toString().trim();
        final String datevaldtxt=datevald.getText().toString().trim();
        final String datevalatxt=datevala.getText().toString().trim();
        final String immatrivtxt=immatriv.getText().toString().trim();
        final String martyvtxt=martyv.getText().toString().trim();
        final String steassurancetxt=steassurance.getText().toString().trim();
        final String numpolicetxt=numpolice.getText().toString().trim();

        progressDialog.setMessage("Inscription en cours d'enregistrement...");
        progressDialog.show();
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

                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params= new HashMap<String, String>();
                //Toast.makeText(signupas.this, "in params", Toast.LENGTH_SHORT).show();
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
        //RequestQueue rq= Volley.newRequestQueue(signupas.this);
        //Toast.makeText(signupas.this, "about to add", Toast.LENGTH_SHORT).show();
        //rq.add(str);
        requesthandler.getInstance(this).addToRequestQueue(str);
    }

    @Override
    public void onClick(View view) {
        if(view==btnsignup)
            registeras();
        //if(view==t5)
          //  startActivity(new Intent(this,login.class));
    }
}