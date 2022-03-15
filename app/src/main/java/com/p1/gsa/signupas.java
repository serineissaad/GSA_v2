package com.p1.gsa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signupas extends AppCompatActivity implements View.OnClickListener {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "param-1";
                field[1] = "param-2";
                //Creating array for data
                String[] data = new String[2];
                data[0] = "data-1";
                data[1] = "data-2";
                PutData putData = new PutData("https://projects.vishnusivadas.com/AdvancedHttpURLConnection/putDataTest.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("PutData", result);
                    }
                }
                //End Write and Read data with URL
            }
        });*/

    EditText prenoma,noma,emaila,pass,emails,noms;
    Button btnsignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupas);

        noms= findViewById(R.id.noms);
        emails= findViewById(R.id.emails);
        prenoma= findViewById(R.id.prenoma);
        noma= findViewById(R.id.noma);
        emaila= findViewById(R.id.emaila);
        pass=findViewById(R.id.pass);

        btnsignup=findViewById(R.id.btnsignup);

        progressDialog=new ProgressDialog(this);

        btnsignup.setOnClickListener(this);
    }

    private void registeras(){
        final String nomstxt=noms.getText().toString().trim();
        final String emailstxt=emails.getText().toString().trim();
        final String nomatxt=noma.getText().toString().trim();
        final String prenomatxt=prenoma.getText().toString().trim();
        final String emailatxt=emaila.getText().toString().trim();
        final String passtxt=pass.getText().toString().trim();

        progressDialog.setMessage("Regestering user..");
        progressDialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Toast.makeText(signupas.this, "enter listener", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    try {
                        JSONObject job=new JSONObject(response);
                        Toast.makeText(signupas.this, "before json toast", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), job.getString("message"),Toast.LENGTH_SHORT).show();
                        Toast.makeText(signupas.this, "after json toast", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(signupas.this, "in catch jason", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
            }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(signupas.this, "error listener", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                Toast.makeText(signupas.this, "in params", Toast.LENGTH_SHORT).show();
                params.put("noms",nomstxt);
                params.put("emails",emailstxt);
                params.put("prenoma",prenomatxt);
                params.put("noma",nomatxt);
                params.put("emaila",emailatxt);
                params.put("pass",passtxt);
            return params;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(signupas.this);
        Toast.makeText(signupas.this, "about to add", Toast.LENGTH_SHORT).show();
        rq.add(str);
    }

    @Override
    public void onClick(View view) {
        if(view==btnsignup)
            registeras();
    }
}