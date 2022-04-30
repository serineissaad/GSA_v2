package com.p1.gsa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signupa extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    EditText emaila,pass,prenoma,noma;
    Button btnsignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupa);

        mAuth = FirebaseAuth.getInstance();

        btnsignup=(Button) findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);

        prenoma = findViewById(R.id.prenoma);
        noma = findViewById(R.id.noma);
        emaila=(EditText) findViewById(R.id.emaila);
        pass=(EditText) findViewById(R.id.pass);

        //progressDialog=(progressDialog) findViewById(R.id.);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignup: register();
                break;
        }

    }

    public  void  register(){
        String emailatxt=emaila.getText().toString().trim();
        String passtxt=pass.getText().toString().trim();
        String nomatxt=noma.getText().toString().trim();
        String prenomatxt=prenoma.getText().toString().trim();

        if(nomatxt.isEmpty()){
            noma.setError("Name is required");
            noma.requestFocus();
            return;
        }

        if(prenomatxt.isEmpty()){
            prenoma.setError("Prenom is required");
            prenoma.requestFocus();
            return;
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

        mAuth.createUserWithEmailAndPassword(emailatxt,passtxt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            admin adm=new admin(prenomatxt,nomatxt,emailatxt);

                            FirebaseDatabase.getInstance().getReference("admin")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(adm).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
//                                        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();
//                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                                new sadmin_acc()).addToBackStack(null).commit();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"failed to register",Toast.LENGTH_SHORT).show();
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

/*************************************
 * {
  "rules": {
    ".read": "now < 1652911200000",  // 2022-5-19
    ".write": "now < 1652911200000",  // 2022-5-19
  }
}
 ************************************/
}
