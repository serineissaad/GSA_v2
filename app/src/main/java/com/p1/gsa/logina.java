package com.p1.gsa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class logina extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    //FirebaseDatabase db=FirebaseDatabase.getInstance();
    //DatabaseReference dbref=db.getReference().child("admin");

    private FirebaseUser user;
    private DatabaseReference ref;
    private  String userid;

    EditText emaila, pass;
    Button btnlogin;
    TextView btnsignup,selected,forgotpassword;
    RadioGroup raddgrp;
    RadioButton radbtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        (sharedrefmanager.getInstance(this).isloggedin()){
//              finish();
//              startActivity(new Intent(this,sadminpage.class));
//              return;

        mAuth = FirebaseAuth.getInstance();


        btnlogin=(Button) findViewById(R.id.btnlogin);
        btnsignup=(TextView) findViewById(R.id.btnsignup);
        forgotpassword=(TextView) findViewById(R.id.forgotpassword);
        btnlogin.setOnClickListener(this);
        btnsignup.setOnClickListener(this);
        forgotpassword.setOnClickListener(this);

        emaila=(EditText) findViewById(R.id.emaila);
        pass=(EditText) findViewById(R.id.pass);

        raddgrp=findViewById(R.id.radiogroup);
        selected=(TextView) findViewById(R.id.selected);


        //progressDialog=(progressDialog) findViewById(R.id.);

    }

    @Override
    public void onClick(View view) {
//        int radioid= raddgrp.getCheckedRadioButtonId();
//        radbtn=findViewById(radioid);
//        selected.setText(radbtn.getText());
        switch (view.getId()){
            case R.id.btnsignup:
                startActivity(new Intent(this,signupa.class));
                break;
            case R.id.btnlogin:
                login();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, com.p1.gsa.forgotpassword.class));
                break;
        }
    }

    public void checkbtn(View v){
        int radioid= raddgrp.getCheckedRadioButtonId();
        radbtn=findViewById(radioid);
        selected.setText(radbtn.getText());
        Toast.makeText(this,"selected: "+radbtn.getText(),Toast.LENGTH_SHORT).show();
    }

    public void login(){
        String emailatxt=emaila.getText().toString().trim();
        String passtxt=pass.getText().toString().trim();

        if(emailatxt.isEmpty()){
            emaila.setError("Email is required");
            emaila.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailatxt).matches()){
            emaila.setError("Invalid email");
            emaila.requestFocus();
            return;
        }

        if(passtxt.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if(passtxt.length()<6){
            pass.setError("Password must contain 6 at least 6 characters");
            pass.requestFocus();
            return;
        }

        //progressDialog.setVisibilirty..

        mAuth.signInWithEmailAndPassword(emailatxt,passtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    radbtn=findViewById(raddgrp.getCheckedRadioButtonId());
                    String type=radbtn.getText().toString();
                    String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference v= FirebaseDatabase.getInstance().getReference().child(type).child(userid);//c t get reference de admin, c tt
/*                    userQuery.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            //Get the node from the datasnapshot
                            String myParentNode = dataSnapshot.getKey();
                            String data = dataSnapshot.toString();
                            Toast.makeText(getApplicationContext(),"parent: "+myParentNode,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"data: "+data,Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });*/


                    //if(user.isEmailVerified()){
                        //radbtn=findViewById(raddgrp.getCheckedRadioButtonId());\
                    if(v!=null){
                    v.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            switch (type){
                            case "admin": {
                                if (snapshot.getValue(admin.class)!=null) {
                                    startActivity(new Intent(logina.this, nav_draw.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "login failed..Check user type selected", Toast.LENGTH_SHORT).show();
                                }
                            }
                                break;
                            case"assure":{
                                if (snapshot.getValue(assure.class)!=null) {
                                    //Toast.makeText(getApplicationContext(),snapshot.getValue(assure.class).getEmaila() , Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(logina.this,signupass.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "login failed..Check user type selected", Toast.LENGTH_SHORT).show();
                                }
                            }
                                break;
                            case"expert":{

                            }
                                break;
                    //    }
                    //}else{
                    //    user.sendEmailVerification();
                    //    Toast.makeText(getApplicationContext(),"Check your email to verify your account!",Toast.LENGTH_SHORT).show();
                    }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(),"Something wrong",Toast.LENGTH_SHORT).show();
                        }
                    });}
                    else{
                        Toast.makeText(getApplicationContext(), "login failed..Check user type selected", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Failed to login",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}