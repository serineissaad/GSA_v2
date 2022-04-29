package com.p1.gsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;



public class rapport1 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText dommage,reparation;
    Button btnext;

    ArrayList<fourniture> listfourn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport1);


        //if(sharedrefmanager.getInstance(this).isloggedin()){
        //  finish();
        //startActivity(new Intent(this,sadminpage.class));
        //return;

        dommage= findViewById(R.id.dommage);
        reparation= findViewById(R.id.reparation);

        btnext=findViewById(R.id.btnext);
        btnext.setOnClickListener(this);

        listfourn=new ArrayList<fourniture>();

        ///////delete from auth, but use another idea thiss wrong////////
//        String id=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
//        String key=FirebaseDatabase.getInstance().getReference().child("assure")
//                .child(id).getKey();
//        if(key.equals(id))
//            Toast.makeText(getApplicationContext(),key,Toast.LENGTH_SHORT).show();
        //////////////////////////////////////////////////////////////

    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    public  void  registerass(){
        String dommagetxt=dommage.getText().toString().trim();
        String reparationtxt=reparation.getText().toString().trim();

//        if(dommagetxt.isEmpty()){
//            dommage.setError("Ce champs est obligatoire");dommage.requestFocus();return;
//        }
//        if(reparationtxt.isEmpty()){
//            reparation.setError("Ce champs est obligatoire");reparation.requestFocus();return;
//        }

                            rapport rapport=new rapport();
                            rapport.setDommage(dommagetxt);
                            rapport.setReparation(reparationtxt);

//                            final Query userQuery = FirebaseDatabase.getInstance().getReference().child("assure").child("rapport");//.equalTo(emailatxt);//.orderByChild("admin/emaila").equalTo(emailatxt);
//                            userQuery.addChildEventListener(new ChildEventListener() {
//                                @Override
//                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                    //Get the node from the datasnapshot
//                                    String myParentNode = dataSnapshot.getKey();
//                                    rapport.setId(myParentNode);
////                                    String data = dataSnapshot.toString();
//                                }
//                                @Override
//                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//
//                                @Override
//                                public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
//
//                                @Override
//                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) { }
//                            });


//                            FirebaseDatabase.getInstance().getReference("assure")
//                                    .child("rapport").child(LocalDate.now().toString())
//                                    .setValue(rapport).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"dommage1 :"+rapport.getDommage(),Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(rapport1.this, rapport2.class);
                                        Bundle bundle = new Bundle();
                                        //bundle.put("rapport",rapport);
                                        bundle.putString("idrapport","rapport1");
                                        bundle.putInt("i",1);
                                        bundle.putParcelableArrayList("fourniture",listfourn);
                                        //intent.putExtra("rapport",rapport);
                                        intent.putExtra("rapport",rapport);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();

//                                    }
//                                    else{
//                                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
                        }


    @Override
    public void onClick(View view) {
//        if(view==btnsignup)
        switch (view.getId()){
            case R.id.btnext://{
               // if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    {registerass();}
                //}
                break;
        }
    }

}