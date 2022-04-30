package com.p1.gsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.RadioGroup;


import java.util.ArrayList;
import java.util.HashMap;

public class decsini extends AppCompatActivity implements  View.OnClickListener {


    FirebaseDatabase rootNode;
    DatabaseReference reference;


    EditText temoinsacci, lieuacci, dateacci, heureacci;
    Button btnvaliderd;
    RadioGroup radiog;
    RadioButton radiob;
    String selected;

   /* CheckBox checkc1; checkc2, checkc3,
            checkc4, checkc5, checkc6,
            checkc7, checkc8, checkc9,
            checkc10, checkc11, checkc12,
            checkc13, checkc14, checkc15,
            checkc16, checkc17, checkc18,
            checkc19, checkc20, checkc21,
            checkc22, checkc23;*/


    private ProgressDialog progressDialog;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("sinistres");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decsini);

        btnvaliderd=(Button) findViewById(R.id.btnvaliderd);
        btnvaliderd.setOnClickListener(this);
        lieuacci = findViewById(R.id.lieuacci);
        dateacci = findViewById(R.id.dateacci);
        heureacci= findViewById(R.id.heureacci);
        temoinsacci= findViewById(R.id.temoinsacci);
        radiog= (RadioGroup) findViewById(R.id.radiog);

      /*  btnvaliderd.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                                register();
                                           }
                                       });*/
      /*  checkc1=  findViewById(R.id.checkc1);
       checkc2=  findViewById(R.id.checkc2);
        checkc3=  findViewById(R.id.checkc3);
        checkc4=  findViewById(R.id.checkc4);
        checkc5=  findViewById(R.id.checkc5);
        checkc6=  findViewById(R.id.checkc6);
        checkc7=  findViewById(R.id.checkc7);
        checkc8=  findViewById(R.id.checkc8);
        checkc9=  findViewById(R.id.checkc9);
        checkc10=  findViewById(R.id.checkc10);
        checkc11=  findViewById(R.id.checkc11);
        checkc12=  findViewById(R.id.checkc12);
        checkc13=  findViewById(R.id.checkc13);
        checkc14=  findViewById(R.id.checkc14);
        checkc15=  findViewById(R.id.checkc15);
        checkc16=  findViewById(R.id.checkc16);
        checkc17=  findViewById(R.id.checkc17);
        checkc18=  findViewById(R.id.checkc18);
        checkc19=  findViewById(R.id.checkc19);
        checkc20=  findViewById(R.id.checkc20);
        checkc21=  findViewById(R.id.checkc21);
        checkc22=  findViewById(R.id.checkc22);
        checkc23=  findViewById(R.id.checkc23); */




       // radioButton.clearCheck();

        //progressDialog=(progressDialog) findViewById(R.id.);
    }
   public void checkbtn(View v){
        int radioid= radiog.getCheckedRadioButtonId();
        radiob=findViewById(radioid);
        Toast.makeText(this,"selected: "+radiob.getText(),Toast.LENGTH_SHORT).show(); }

   /* public void saveprefs(String key, boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, String.valueOf(value));
        edit.commit();
    } */








   // @Override
  //  public void onClick(View view) {
   //     switch (view.getId()){
   //         case R.id.btnvaliderd: register(); // saveprefs("checkc1", checkc1.isChecked());
   //             break;
   //     }

  //  }
    public void register() {
            String lieuaccitxt = lieuacci.getText().toString();
            String dateaccitxt = dateacci.getText().toString();
            String heureaccitxt = heureacci.getText().toString();
            String temoinsaccitxt = temoinsacci.getText().toString();
      //  Toast.makeText(decsini.this, radiog.getCheckedRadioButtonId().getText().toString, Toast.LENGTH_SHORT).show();
            radiob=findViewById(radiog.getCheckedRadioButtonId());
            String selectedb=  radiob.getText().toString();




        switch (selectedb) {
            case "checkdnon": {
                selected = "non";
            }
            break;
            case "checkdoui": {
                selected = "oui";
            }
        }


      //  FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//       String idas=user.getUid();

        if(lieuaccitxt.isEmpty()){
            lieuacci.setError("Ce champ est requis.");
            lieuacci.requestFocus();
            return;
        }

       if(dateaccitxt.isEmpty()){
            dateacci.setError("Ce champ est requis.");
            dateacci.requestFocus();
            return;
        }

        if(heureaccitxt.isEmpty()){
            heureacci.setError("Ce champ est requis.");
            heureacci.requestFocus();
            return;
        }
        if(temoinsaccitxt.isEmpty()){
            temoinsacci.setError("Ce champ est requis.");
            temoinsacci.requestFocus();
            return;
        }


            HashMap<String , String> userMap = new HashMap<>();

            userMap.put("lieuacci" , lieuaccitxt);
            userMap.put("dateacci" , dateaccitxt);
            userMap.put("heureacci" , heureaccitxt);
            userMap.put("temoinsacci" , temoinsaccitxt);
            userMap.put("degatsr" , selected);
         //  userMap.put("idas" , idas);


        root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
              Toast.makeText(decsini.this, "Votre déclaration est enregistrée.", Toast.LENGTH_SHORT).show();
           }
        });
        }


    @Override
    public void onClick(View view) {
        if(view==btnvaliderd){
            register();}
    }
}












/*************************************
 * {
 "rules": {
 ".read": "now < 1652911200000",  // 2022-5-19
 ".write": "now < 1652911200000",  // 2022-5-19
 }
 }
 ************************************/




