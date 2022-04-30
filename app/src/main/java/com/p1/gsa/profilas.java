package com.p1.gsa;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class profilas extends AppCompatActivity {

    private TextView prenoma,noma,emaila,steassurance,agencea,immatriv,martyv,adressea,numpolice,datevald,datevala;
    private final String TAG = this.getClass().getName().toUpperCase();
  //  private FirebaseDatabase database;
  //  private DatabaseReference mDatabase;
  //  private Map<String, String> userMap;
    private String email;
  //  private String userid;
    private static final String USERS = "assure";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilas);
        //receive data from login screen
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());

        noma= findViewById(R.id.noma);
        prenoma= findViewById(R.id.prenoma);
        emaila= findViewById(R.id.emaila);
        steassurance= findViewById(R.id.steassurance);
        agencea=findViewById(R.id.agencea);
        immatriv=findViewById(R.id.immatriv);
        martyv=findViewById(R.id.martyv);
        adressea=findViewById(R.id.adressea);
        numpolice=findViewById(R.id.numpolice);
        datevald=findViewById(R.id.datevald);
        datevala=findViewById(R.id.datevala);

        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            String prenoma,noma,emaila,steassurance,agencea,immatriv,martyv,adressea,numpolice,datevald,datevala;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        noma = keyId.child("noma").getValue(String.class);
                        prenoma = keyId.child("prenoma").getValue(String.class);
                        agencea = keyId.child("agencea").getValue(String.class);
                   //     phone = keyId.child("phone").getValue(String.class);
                        break;
                    }
                }
         /*       noma.setText(noma);
                prenoma.setText(prenoma);
                immatriv.setText(immatriv);
                datevald.setText(datevald);
                datevala.setText(datevala);
                agencea.setText(agencea);
                adressea.setText(adressea);
                emaila.setText(emaila);
                steassurance.setText(steassurance);
                numpolice.setText(numpolice);
                martyv.setText(martyv);
*/
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}