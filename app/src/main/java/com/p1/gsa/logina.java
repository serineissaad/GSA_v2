package com.p1.gsa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    RadioButton assurer;
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
        assurer=findViewById(R.id.assurer);

        //progressDialog=(progressDialog) findViewById(R.id.);

    }

    @Override
    public void onClick(View view) {
//        int radioid= raddgrp.getCheckedRadioButtonId();
//        radbtn=findViewById(radioid);
//        selected.setText(radbtn.getText());
        if(view==btnsignup ){
            if (assurer.isChecked()) {startActivity(new Intent(this,signupass.class));}
            else Toast.makeText(getApplicationContext(),"Seuls les assurés peuvent s'inscrire.",Toast.LENGTH_SHORT).show();}
        switch (view.getId()){
         //   case R.id.btnsignup:
          //      startActivity(new Intent(this,signupa.class));
           //     break;
            case R.id.btnlogin:
                login();
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, com.p1.gsa.pageaccass.class));
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
            emaila.setError("Ce champ est requis");
            emaila.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailatxt).matches()){
            emaila.setError("Email invalide");
            emaila.requestFocus();
            return;
        }

        if(passtxt.isEmpty()){
            pass.setError("Ce champs est requis");
            pass.requestFocus();
            return;
        }
        if(passtxt.length()<6){
            pass.setError("Un mot de passe doit être composé d'au moins 6 caractères");
            pass.requestFocus();
            return;
        }

        //progressDialog.setVisibilirty..

        mAuth.signInWithEmailAndPassword(emailatxt,passtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("admin");
                    String userid=user.getUid();

                    if(user.isEmailVerified()){
                        radbtn=findViewById(raddgrp.getCheckedRadioButtonId());
                        switch (radbtn.getText().toString()){
                            case "Admin":
                                startActivity(new Intent(logina.this,nav_draw.class));
                                finish();
                                break;
                            case"Assuré":
                                startActivity(new Intent(logina.this,signupa.class));
                                finish();
                                break;
                            case"Expert":
                                startActivity(new Intent(logina.this,signupa.class));
                                finish();
                                break;
                        }
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(),"Veuillez vérifiez votre boite e-mail.",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"L’e-mail ou le mot de passe que vous avez saisi n’est pas associé à un compte.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}