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

import java.util.ArrayList;
import java.util.HashMap;



public class rapport3 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText moeuv,mpeint,mtot,mhr,mimmob,ltot,txhr,txvt,nbp,observ;
    Button btnsubmit;
    ArrayList<fourniture> listfourn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport3);

        //if(sharedrefmanager.getInstance(this).isloggedin()){
        //  finish();
        //startActivity(new Intent(this,sadminpage.class));
        //return;

        mAuth = FirebaseAuth.getInstance();

        moeuv= findViewById(R.id.moeuv);
        mpeint=findViewById(R.id.mpeint);
        mtot=findViewById(R.id.mtot);
        mhr=findViewById(R.id.mhr);
        mimmob=findViewById(R.id.mimmob);
        ltot=findViewById(R.id.ltot);
        txhr=findViewById(R.id.txhr);
        txvt=findViewById(R.id.txvt);
        nbp=findViewById(R.id.nbp);
        observ=findViewById(R.id.observ);

        btnsubmit=findViewById(R.id.btnsubmit);
        btnsubmit=findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);

    }

    public  void  registerass(){
        Bundle bundle = getIntent().getExtras();
        rapport rapport=getIntent().getParcelableExtra("rapport");
        String idrapport=bundle.getString("idrapport");
        listfourn=bundle.getParcelableArrayList("fourniture");

        String moeuvtxt=moeuv.getText().toString().trim();
        String mpeinttxt=mpeint.getText().toString().trim();
        String mtottxt=mtot.getText().toString().trim();
        String mhrtxt=mhr.getText().toString().trim();
        String mimmobtxt=mimmob.getText().toString().trim();
        String ltottxt=ltot.getText().toString().trim();
        String txhrtxt=txhr.getText().toString().trim();
        String txvttxt=txvt.getText().toString().trim();
        String nbptxt=nbp.getText().toString().trim();
        String observtxt=observ.getText().toString().trim();

       // Toast.makeText(getApplicationContext(),"rapport DOMMAGE "+rapport.getDommage(),Toast.LENGTH_SHORT).show();
       // Toast.makeText(getApplicationContext(),"rapport ltot "+rapport.getLtot(),Toast.LENGTH_SHORT).show();
         Toast.makeText(getApplicationContext(),idrapport,Toast.LENGTH_SHORT).show();
        for (fourniture var : listfourn)
        {Toast.makeText(getApplicationContext(),var.getId(),Toast.LENGTH_SHORT).show();
        }

//        if(steassurancetxt.isEmpty()){
//            steassurance.setError("steassurance is required");steassurance.requestFocus();return;
//        }
//        if(numpolicetxt.isEmpty()){
//            numpolice.setError("numpolice is required");numpolice.requestFocus();return;
//        }
//        if(marquetxt.isEmpty()){
//            marque.setError("marque is required");marque.requestFocus();return;
//        }
//        if(typetxt.isEmpty()){
//            type.setError("type is required");type.requestFocus();return;
//        }
//        if(immatrivtxt.isEmpty()){
//            immatriv.setError("immatriv is required");immatriv.requestFocus();return;
//        }
//        if(datevaldtxt.isEmpty()){
//            datevald.setError("date is required");datevald.requestFocus();return;
//        }
//        if(datevalatxt.isEmpty()){
//            datevala.setError("date is required");datevala.requestFocus();return;
//        }

        rapport.setLtot(ltottxt); rapport.setMhr(Integer.parseInt(mhrtxt)); rapport.setMimmob(Integer.parseInt(mimmobtxt));
        rapport.setMoeuv(Double.parseDouble(moeuvtxt));rapport.setMpeint(Double.parseDouble(mpeinttxt));
        rapport.setMtot(Double.parseDouble(mtottxt)); rapport.setNbphotos(Integer.parseInt(nbptxt)); rapport.setObs(observtxt);
        rapport.setTxhr(Double.parseDouble(txhrtxt)); rapport.setTxvt(Double.parseDouble(txvttxt));

//        final Query rapportQuery = FirebaseDatabase.getInstance().getReference().child("assure").child("rapport");//.equalTo(emailatxt);//.orderByChild("admin/emaila").equalTo(emailatxt);
//        rapportQuery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                //Get the node from the datasnapshot
//                rapport.setId(dataSnapshot.getKey());
//                Toast.makeText(getApplicationContext(),"id rapport: "+rapport.getId(),Toast.LENGTH_SHORT).show();
//                //String data = dataSnapshot.toString();
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) { }
//        });

        FirebaseDatabase.getInstance().getReference("assure")
                .child("rapport").child(idrapport)
                .setValue(rapport).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Rapport ajoute",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Rapport failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //////////+fourniture
        for (fourniture var : listfourn)
        { fourniture var2=var;
            FirebaseDatabase.getInstance().getReference("assure")
                .child("rapport").child(idrapport).child("fourniture")
                    .child(var.getId())
                .setValue(var2);
        }
        //.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),"Rapport ajoute",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Rapport failed",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

                Intent intent = new Intent(rapport3.this, rapport1.class);
                startActivity(intent);
                finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsubmit: registerass();
                break;

        }
    }

}