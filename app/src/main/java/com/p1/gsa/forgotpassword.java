package com.p1.gsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class forgotpassword extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email;
    Button btnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        auth=FirebaseAuth.getInstance();

        email=(EditText) findViewById(R.id.emaila);
        btnext=(Button) findViewById(R.id.btnext);
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }

    public void reset(){
        String emailatxt=email.getText().toString().trim();

        if(emailatxt.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailatxt).matches()){
            email.setError("Invalid email");
            email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(emailatxt).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this,"check your email to reset your password",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(forgotpassword.this,"THis email might not be registered..try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}