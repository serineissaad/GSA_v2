package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;



public class signupass extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText prenoma,noma,emaila,passworda,steassurance,agencea,immatriv,martyv,adressea,numpolice,datevald,datevala;
    Button btnsignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupass);


        //if(sharedrefmanager.getInstance(this).isloggedin()){
          //  finish();
            //startActivity(new Intent(this,sadminpage.class));
            //return;

        mAuth = FirebaseAuth.getInstance();

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
        btnsignup.setOnClickListener(this);

        ///////delete from auth, but use another idea thiss wrong////////
        String id=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String key=FirebaseDatabase.getInstance().getReference().child("assure")
                .child(id).getKey();
        if(key.equals(id))
        Toast.makeText(getApplicationContext(),key,Toast.LENGTH_SHORT).show();
        //////////////////////////////////////////////////////////////

    }

    public  void  registerass(){
        String nomatxt=noma.getText().toString().trim();
        String prenomatxt=prenoma.getText().toString().trim();
        String adresseatxt=adressea.getText().toString().trim();
        String datevaldtxt=datevald.getText().toString().trim();
        String datevalatxt=datevala.getText().toString().trim();
        String steassurancetxt=steassurance.getText().toString().trim();
        String numpolicetxt=numpolice.getText().toString().trim();
        String martyvtxt=martyv.getText().toString().trim();
        String immatrivtxt=immatriv.getText().toString().trim();
        String agenceatxt=agencea.getText().toString().trim();
        String emailatxt=emaila.getText().toString().trim();
        String passwordatxt=passworda.getText().toString().trim();


        if(nomatxt.isEmpty()){
            noma.setError("Name is required");noma.requestFocus();return;
        }
        if(prenomatxt.isEmpty()){
            prenoma.setError("Prenom is required");prenoma.requestFocus();return;
        }
        if(steassurancetxt.isEmpty()){
            steassurance.setError("steassurance is required");steassurance.requestFocus();return;
        }
        if(numpolicetxt.isEmpty()){
            numpolice.setError("numpolice is required");numpolice.requestFocus();return;
        }
        if(martyvtxt.isEmpty()){
            martyv.setError("martyv is required");martyv.requestFocus();return;
        }
        if(immatrivtxt.isEmpty()){
            immatriv.setError("immatriv is required");immatriv.requestFocus();return;
        }
        if(datevaldtxt.isEmpty()){
            datevald.setError("date is required");datevald.requestFocus();return;
        }
        if(datevalatxt.isEmpty()){
            datevala.setError("date is required");datevala.requestFocus();return;
        }
        if(adresseatxt.isEmpty()){
            adressea.setError("adresse is required");adressea.requestFocus();return;
        }
        if(prenomatxt.isEmpty()){
            agencea.setError("agence name is required");agencea.requestFocus();return;
        }

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

        if(passwordatxt.isEmpty()){
            passworda.setError("Password is required");
            passworda.requestFocus();
            return;
        }
        if(passwordatxt.length()<6){
            passworda.setError("Password must contain 6 at least 6 characters");
            passworda.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailatxt,passwordatxt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            assure ass=new assure(nomatxt,prenomatxt,adresseatxt,steassurancetxt,numpolicetxt,
                    datevaldtxt,datevalatxt,martyvtxt,
                    immatrivtxt,agenceatxt,emailatxt);

                            final Query userQuery = FirebaseDatabase.getInstance().getReference().child("assure");//.equalTo(emailatxt);//.orderByChild("admin/emaila").equalTo(emailatxt);
                            userQuery.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    //Get the node from the datasnapshot
                                    String myParentNode = dataSnapshot.getKey();
                                    ass.setId(myParentNode);
                                    String data = dataSnapshot.toString();
                                    Toast.makeText(getApplicationContext(),"parent: "+myParentNode,Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(),"parent: "+ass.getId(),Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            });


                            FirebaseDatabase.getInstance().getReference("assure")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(ass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
//        if(view==btnsignup)
        switch (view.getId()){
            case R.id.btnsignup: registerass();
                break;
        }
    }

}