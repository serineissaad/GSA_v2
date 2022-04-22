package com.p1.gsa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signupas extends AppCompatActivity {


    EditText prenoma, noma, emaila, pass, emails, noms;
    Button btnsignup,btnupdate,btndelete;

    private ProgressDialog progressDialog;

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference dbref=db.getReference(admin.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_signupas);

//        if (sharedrefmanager.getInstance(this).isloggedin()) {
//            finish();
//            startActivity(new Intent(this, sadminpage.class));
//            return;
//        }

        noms = findViewById(R.id.noms);
        emails = findViewById(R.id.emails);
        prenoma = findViewById(R.id.prenoma);
        noma = findViewById(R.id.noma);
        emaila = findViewById(R.id.emaila);
        pass = findViewById(R.id.pass);

        btnsignup = findViewById(R.id.btnsignup);
        //btnupdate = findViewById(R.id.btnupdate);

        aud_admin daa = new aud_admin();

//        btnsignup.setOnClickListener(v ->{
//            admin adm=new admin(prenoma.getText().toString(),noma.getText().toString(),emaila.getText().toString(),
//                    pass.getText().toString());
//
//            daa.adda(adm).addOnSuccessListener(er->{
//                Toast.makeText(this,"Admin enregistre",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//
//        });

//        btnsignup.setOnClickListener(v ->{
//            HashMap<String,Object> hashmap=new HashMap<>();
//            hashmap.put("noma",noma.getText().toString());
//            hashmap.put("prenoma",prenoma.getText().toString());
//            hashmap.put("emaila",emaila.getText().toString());
//            hashmap.put("pass",pass.getText().toString());
//
//            daa.update("-N05t_0CZUE-gfspipo2",hashmap).addOnSuccessListener(er->{
//                Toast.makeText(this,"Account updated",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//
//        });

//        btnsignup.setOnClickListener(v ->{
//            HashMap<String,Object> hashmap=new HashMap<>();
//            hashmap.put("noma",noma.getText().toString());
//            hashmap.put("prenoma",prenoma.getText().toString());
//            hashmap.put("emaila",emaila.getText().toString());
//            hashmap.put("pass",pass.getText().toString());
//
//            daa.remove("-N00Q6GiqCK67Hr1Kfhv").addOnSuccessListener(er->{
//                Toast.makeText(this,"Account removed",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//
//        });
    }

}