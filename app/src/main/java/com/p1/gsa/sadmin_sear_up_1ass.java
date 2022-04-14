package com.p1.gsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sadmin_sear_up_1ass extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView noma,prenoma,emaila,adressea,immatriv;
    EditText steassurance,numpol,datevald,datevala,martyv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btndelete,btnupdate;

    public sadmin_sear_up_1ass() {
    }

    // TODO: Rename and change types and number of parameters
    public static sadmin_sear_up_1ass newInstance(String param1, String param2) {
        sadmin_sear_up_1ass fragment = new sadmin_sear_up_1ass();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sadmin_sear_up_1ass, container, false);

        noma=v.findViewById(R.id.noma);
        prenoma=v.findViewById(R.id.prenoma);
        emaila=v.findViewById(R.id.emaila);
        adressea=v.findViewById(R.id.adressea);
        immatriv=v.findViewById(R.id.immatriv);
        martyv=v.findViewById(R.id.martyv);
        datevala=v.findViewById(R.id.datevala);
        datevald=v.findViewById(R.id.datevald);
        steassurance=v.findViewById(R.id.steassurance);
        numpol=v.findViewById(R.id.numpol);
        btndelete=v.findViewById(R.id.btndelete);
        btnupdate=v.findViewById(R.id.btnupdate);

        btnupdate.setOnClickListener(this::onClick);
        btndelete.setOnClickListener(this::onClick);

        setass();

        return v;
    }

    private  void setass(){
        //if(getArguments().getBoolean("noma") && getArguments().getBoolean("prenoma") && getArguments().getBoolean("emaila") &&
        //      getArguments().getBoolean("noma") && getArguments().getBoolean("noma") && getArguments().getBoolean("noma"))

        noma.setText(getArguments().getString("noma"));
        prenoma.setText(getArguments().getString("prenoma"));
        emaila.setText(getArguments().getString("emaila"));
        steassurance.setText(getArguments().getString("steassurance"));
        immatriv.setText(getArguments().getString("immatriv"));
        martyv.setText(getArguments().getString("martyv"));
        adressea.setText(getArguments().getString("adressea"));
        datevald.setText(getArguments().getString("datevald"));
        datevala.setText(getArguments().getString("datevala"));
        numpol.setText(getArguments().getString("numpolice"));
    }

    private void updateass(){
        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_UPDATEASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject job=new JSONObject(response);
                    if(!job.getBoolean("error")) {
                        Toast.makeText(getContext(), job.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(), job.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error listener", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                //Toast.makeText(signupas.this, "in params", Toast.LENGTH_SHORT).show();
                //params.put("noma", String.valueOf(noma));
                //params.put("emaila", String.valueOf(emaila));
                //params.put("prenoma", String.valueOf(prenoma));
                //params.put("adressea", String.valueOf(adressea));
                //params.put("immatriv", String.valueOf(immatriv));
                params.put("martyv", String.valueOf(martyv.getText()));
                params.put("numpolice", String.valueOf(numpol.getText()));
                params.put("steassurance", String.valueOf(steassurance.getText()));
                params.put("datevald", String.valueOf(datevald.getText()));
                params.put("datevala", String.valueOf(datevala.getText()));
                params.put("emaila", String.valueOf(emaila.getText()));
                return params;
            }
        };
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
    }

    private void deleteass(){

        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_DELETEASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject job=new JSONObject(response);
                    if(!job.getBoolean("error")) {
                        Toast.makeText(getContext(), job.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(), job.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error listener", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();

                params.put("emaila", String.valueOf(emaila.getText()));
                return params;
            }
        };
        requesthandler.getInstance(getContext()).addToRequestQueue(str);

    }



    public void onClick(View view) {
        if(view==btnupdate){
            updateass();
        }

        if(view==btndelete){
            deleteass();
        }
    }
}