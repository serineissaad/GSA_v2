package com.p1.gsa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity{
    TextView btnsignup;
    EditText emaila,pass;
    Button btnlogin;
    Switch admin;
    private ProgressDialog progressDialog;

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference dbref=db.getReference().child("admin");//getReference(admin.class.getSimpleName())  getReferenceFromUrl("https://gsa0-ba489-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login);

//        if(sharedrefmanager.getInstance(this).isloggedin()){
//            finish();
//            startActivity(new Intent(this,sadminpage.class));
//            return;
//        }

        aud_admin daa = new aud_admin();

        emaila=findViewById(R.id.emaila);
        pass=findViewById(R.id.pass);
        btnsignup =(TextView) findViewById(R.id.btnsignup);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        //btnsignup.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);

        //btnlogin.setOnClickListener(this);

        btnlogin.setOnClickListener(v->{
            //if(emaila.isEmpty())
            String emailatxt=emaila.getText().toString().trim();
            String passtxt=pass.getText().toString().trim();
            dbref.child(emailatxt).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //final String passdb=snapshot.child(emailatxt).child("pass").getValue(String.class);

//                    admin a=snapshot.getValue(admin.class);
//                    Toast.makeText(getApplicationContext(),a.toString(),Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getApplicationContext(),a.getEmaila(),Toast.LENGTH_SHORT).show();
                    if(snapshot.child(emailatxt).exists() /*/hasChild(emailatxt)*/){
                        final String passdb=snapshot.child(emailatxt).child("pass").getValue(String.class);
                        if(passdb.equals(passtxt)){
                            Toast.makeText(getApplicationContext(),"logged in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),signupas.class));
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"wrongpassword",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"no such email",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }


    /*private void loging(){
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
                                startActivity(new Intent(getApplicationContext(),nav_draw.class));
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
    }*/

   /* private void logingass(){ final String emailatxt=emaila.getText().toString().trim();
        final String passtxt=pass.getText().toString().trim();

        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_LOGINASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Toast.makeText(getApplicationContext(),"GETTING OBJ",Toast.LENGTH_SHORT).show();
                            JSONObject obj=new JSONObject(response);
                            Toast.makeText(getApplicationContext(),"obj gotten",Toast.LENGTH_SHORT).show();
                            if(!obj.getBoolean("error")){
                                sharedrefmanager.getInstance(getApplicationContext()).assurelogin(
                                        obj.getInt("mat"),
                                        obj.getString("noma"),
                                        obj.getString("prenoma")
                                );

                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),signupass.class));
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
                params.put("passworda",passtxt);
                return params;
            }
        };
        requesthandler.getInstance(this).addToRequestQueue(str);
    }*/

   // @Override
  //  public void onClick(View view) {
//        if(view==btnsignup){
//            startActivity(new Intent(this,signupas.class));}
//        if(view==btnlogin){
//            if(admin.isChecked())
//                loging();
//            else
//                logingass();
//        }
 //   }
}