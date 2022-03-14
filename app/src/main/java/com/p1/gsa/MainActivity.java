package com.p1.gsa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,email,dob;
    Button insert,update,delete,view,next;
    DB1Helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        email= findViewById(R.id.email);
        dob= findViewById(R.id.dob);

        insert=findViewById(R.id.btninsert);
        update=findViewById(R.id.btnupdate);
        delete=findViewById(R.id.btndelete);
        view=findViewById(R.id.btnview);
        next=(Button) findViewById(R.id.btnext);
        DB= new DB1Helper(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignupas();
            }
        });

        insert.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) {

            String nametxt=name.getText().toString();
            String emailtxt=email.getText().toString();
            String dobtxt=dob.getText().toString();
            Boolean checkinsert= DB.insertassure(nametxt,emailtxt,dobtxt);
            if(checkinsert==true)
                Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,"Error while inserting",Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        update.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) {
                                          String nametxt=name.getText().toString();
                                          String emailtxt=email.getText().toString();
                                          String dobtxt=dob.getText().toString();
                                          Boolean checkupdate= DB.updateassure(nametxt,emailtxt,dobtxt);
                                          if(checkupdate==true)
                                              Toast.makeText(MainActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                                          else

                                              Toast.makeText(MainActivity.this,"Error while updating",Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        delete.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) {
                                          String nametxt=name.getText().toString();
                                          Boolean checkdelete= DB.deleteassure(nametxt);
                                          if(checkdelete==true)
                                              Toast.makeText(MainActivity.this,"Entry deleted",Toast.LENGTH_SHORT).show();
                                          else
                                              Toast.makeText(MainActivity.this,"Error while deleting",Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr=DB.getassurelist();
                if(cr.getCount()==0){
                    Toast.makeText(MainActivity.this, "Pas d'assurés ennregistrés", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer bfr=new StringBuffer();
                while(cr.moveToNext()){
                    bfr.append("Name :"+cr.getString(0)+"\t");
                    bfr.append("Email :"+cr.getString(1)+"\t");
                    bfr.append("Date of Birth :"+cr.getString(2)+"\n");
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Liste des assurés:");
                builder.setMessage(bfr.toString());
                builder.show();

            }
        });
    }
    public void opensignupas(){
        Intent intent= new Intent(this,signupas.class);
        startActivity(intent);
    }
}