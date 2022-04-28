package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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



public class signupass_vehic extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText prenoma,noma,emaila,passworda,steassurance,agencea,immatriv,marque,type,adressea,numpolice,datevald,datevala;
    Button btnext,btnsubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupass_vehic);


        //if(sharedrefmanager.getInstance(this).isloggedin()){
        //  finish();
        //startActivity(new Intent(this,sadminpage.class));
        //return;

        mAuth = FirebaseAuth.getInstance();


        steassurance= findViewById(R.id.steassurance);
        immatriv=findViewById(R.id.immatriv);
        marque=findViewById(R.id.marque);
        type=findViewById(R.id.martype);
        numpolice=findViewById(R.id.numpolice);
        datevald=findViewById(R.id.datevald);
        datevala=findViewById(R.id.datevala);

        btnext=findViewById(R.id.btnext);
        btnsubmit=findViewById(R.id.btnsubmit);
        btnext.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);

        ///////delete from auth, but use another idea thiss wrong////////
        String id=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String key=FirebaseDatabase.getInstance().getReference().child("assure")
                .child(id).getKey();
        if(key.equals(id))
            Toast.makeText(getApplicationContext(),key,Toast.LENGTH_SHORT).show();
        //////////////////////////////////////////////////////////////

    }

    public  void  registerass(String rep){
        Bundle bundle = getIntent().getExtras();
//        String emailatxt = bundle.getString("emaila");
//        String nomatxt = bundle.getString("noma");
//        String prenomatxt = bundle.getString("prenoma");
//        String adresseatxt = bundle.getString("adressea");
//        String passwordatxt = bundle.getString("passworda");
        String iduser=bundle.getString("iduser");
        int i=bundle.getInt("i");

        String datevaldtxt=datevald.getText().toString().trim();
        String datevalatxt=datevala.getText().toString().trim();
        String steassurancetxt=steassurance.getText().toString().trim();
        String numpolicetxt=numpolice.getText().toString().trim();
        String marquetxt=marque.getText().toString().trim();
        String typetxt=type.getText().toString().trim();
        String immatrivtxt=immatriv.getText().toString().trim();

        if(steassurancetxt.isEmpty()){
            steassurance.setError("steassurance is required");steassurance.requestFocus();return;
        }
        if(numpolicetxt.isEmpty()){
            numpolice.setError("numpolice is required");numpolice.requestFocus();return;
        }
        if(marquetxt.isEmpty()){
            marque.setError("marque is required");marque.requestFocus();return;
        }
        if(typetxt.isEmpty()){
            type.setError("type is required");type.requestFocus();return;
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


        String nomvehic="vehic"+i;
            vehicule vehic=new vehicule(steassurancetxt,numpolicetxt,immatrivtxt,marquetxt,
                    typetxt,datevaldtxt,datevalatxt);
            final Query vehicQuery = FirebaseDatabase.getInstance().getReference().child("assure").child(iduser).child("vehicule");//.equalTo(emailatxt);//.orderByChild("admin/emaila").equalTo(emailatxt);
            vehicQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Get the node from the datasnapshot
                    vehic.setId(dataSnapshot.getKey());
                    Toast.makeText(getApplicationContext(),"id vehic: "+vehic.getId(),Toast.LENGTH_SHORT).show();
                    //String data = dataSnapshot.toString();
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
                    .child(iduser).child("vehicule")
                    .child(nomvehic)
                    .setValue(vehic).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Vehicule ajoute",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"vehicule failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if(rep=="next"){
                i++;
                if(i<3){
            Intent intent = new Intent(signupass_vehic.this, signupass_vehic.class);
//            bundle.putString("datevala",datevalatxt);
//            bundle.putString("datevald",datevaldtxt);
//            bundle.putString("immatriv",immatrivtxt);
//            bundle.putString("marque",marquetxt);
//            bundle.putString("type",typetxt);
//            bundle.putString("steassurance",steassurancetxt);
//            bundle.putString("numpol",numpolicetxt);
            bundle.putInt("i",i);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();}
                else{
                    Toast.makeText(getApplicationContext(),"Maximum nombre de vehicule est 4",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                startActivity(new Intent(signupass_vehic.this,logina.class));
            }

    }

    @Override
    public void onClick(View view) {
//        if(view==btnsignup)
        switch (view.getId()){
            case R.id.btnsubmit: registerass("sub");
                break;
            case R.id.btnext: registerass("next");
                break;
        }
    }

}