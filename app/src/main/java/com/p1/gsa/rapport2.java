package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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



public class rapport2 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText qte,nom,prix;
    Button btnext,btnadd;
    rapport rapport;
    ArrayList<fourniture> listfourn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport2);


        //if(sharedrefmanager.getInstance(this).isloggedin()){
        //  finish();
        //startActivity(new Intent(this,sadminpage.class));
        //return;

        mAuth = FirebaseAuth.getInstance();


        nom= findViewById(R.id.nom1);
        qte=findViewById(R.id.qte1);
        prix=findViewById(R.id.prix1);

        btnext=findViewById(R.id.btnext);
        btnadd=findViewById(R.id.btnadd);
        btnext.setOnClickListener(this);
        btnadd.setOnClickListener(this);

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
        rapport=getIntent().getParcelableExtra("rapport");
        //rapport=getIntent().getSerializableExtra("rapport");
        String idrapport=bundle.getString("idrapport");
       listfourn=bundle.getParcelableArrayList("fourniture");

        int i=bundle.getInt("i");

        Toast.makeText(getApplicationContext(),"dommage2 :"+rapport.getDommage(),Toast.LENGTH_SHORT).show();


        String nomtxt=nom.getText().toString().trim();
        int qtetxt=Integer.parseInt(qte.getText().toString());
        float prixtxt=Float.parseFloat(prix.getText().toString().trim());

//        if(steassurancetxt.isEmpty()){
//            steassurance.setError("steassurance is required");steassurance.requestFocus();return;
//        }
//        if(numpolicetxt.isEmpty()){
//            numpolice.setError("numpolice is required");numpolice.requestFocus();return;
//        }
//        if(marquetxt.isEmpty()){
//            marque.setError("marque is required");marque.requestFocus();return;
//        }


        String fourniture="Fourniture"+i;
        fourniture fourn=new fourniture(nomtxt,qtetxt,prixtxt);
        fourn.setId(fourniture);
        listfourn.add(fourn);
        for (fourniture var : listfourn)
        {Toast.makeText(getApplicationContext(),var.getNom(),Toast.LENGTH_SHORT).show();
        }

//        final Query vehicQuery = FirebaseDatabase.getInstance().getReference().child("assure").child("rapport");//.equalTo(emailatxt);//.orderByChild("admin/emaila").equalTo(emailatxt);
//        vehicQuery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                //Get the node from the datasnapshot
//                fourn.setId(dataSnapshot.getKey());
//                Toast.makeText(getApplicationContext(),"id fourn: "+fourn.getId(),Toast.LENGTH_SHORT).show();
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
//
//
//        FirebaseDatabase.getInstance().getReference("assure")
//                .child("rapport").child(idrapport)
//                .setValue(rapport).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),"Vehicule ajoute",Toast.LENGTH_SHORT).show();
//
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"vehicule failed",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        if(rep=="add"){
            i++;
            if(i<3){
                Intent intent = new Intent(rapport2.this, rapport2.class);
                bundle.putInt("i",i);
                bundle.putString("idrapport",idrapport);
                bundle.putParcelableArrayList("fourniture", listfourn);
                intent.putExtras(bundle);
                intent.putExtra("rapport",rapport);
                startActivity(intent);
                finish();}
            else{
                Toast.makeText(getApplicationContext(),"Maximum nombre de vehicule est 4",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent intent = new Intent(rapport2.this, rapport3.class);
            //bundle.putInt("i",i);
            bundle.putString("idrapport",idrapport);
            bundle.putParcelableArrayList("fourniture", listfourn);
            intent.putExtras(bundle);
            intent.putExtra("rapport",rapport);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
//        if(view==btnsignup)
        switch (view.getId()){
            case R.id.btnext: registerass("next");
                break;
            case R.id.btnadd: registerass("add");
                break;
        }
    }

}