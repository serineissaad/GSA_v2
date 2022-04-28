package com.p1.gsa;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sadmin_sear_up_1ass extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    DatabaseReference ref;

    TextView noma,prenoma,emaila,adressea,immatriv,martyv,etat;
    EditText steassurance,numpol,datevald,datevala;
    ImageView deactivate;
    private FirebaseAuth mAuth;

    Button btndelete,btnupdate;

    static String idtxt,prenomtxt,nomtxt,adressetxt,immatrivtxt,steassurancetxt,emailtxt,numpoltxt,datevaldtxt,
    datevalatxt,martyvtxt;
    static int activate;

    public sadmin_sear_up_1ass() {
    }
    public sadmin_sear_up_1ass(String id,int activate,String prenomtxt,String nomtxt,String adressetxt,String steassurancetxt,
                               String martyvtxt,String numpoltxt,String immatrivtxt,String emailtxt,
                               String datevaldtxt,String datevalatxt) {
        this.idtxt=id;this.activate=activate;
        this.prenomtxt=prenomtxt;this.martyvtxt=martyvtxt;
        this.nomtxt=nomtxt;this.numpoltxt=numpoltxt;
        this.adressetxt=adressetxt;this.immatrivtxt=immatrivtxt;
        this.emailtxt=emailtxt;this.datevaldtxt=datevaldtxt;
        this.datevalatxt=datevalatxt; this.steassurancetxt=steassurancetxt;
    }

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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_sadmin_sear_up_1ass, container, false);

        ref= FirebaseDatabase.getInstance().getReference().child("assure");//.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
        deactivate=v.findViewById(R.id.imgactivate);
        etat=v.findViewById(R.id.etat);

        btnupdate.setOnClickListener(this::onClick);
        btndelete.setOnClickListener(this::onClick);
        deactivate.setOnClickListener(this::onClick);

        setass();

        return v;
    }

    private  void setass(){
        //if(getArguments().getBoolean("noma") && getArguments().getBoolean("prenoma") && getArguments().getBoolean("emaila") &&
        //      getArguments().getBoolean("noma") && getArguments().getBoolean("noma") && getArguments().getBoolean("noma"))

//        steassurancetxt=steassurance.getText().toString();
//        numpoltxt=numpol.getText().toString();
//        datevaldtxt=datevald.getText().toString();
//        datevalatxt=datevala.getText().toString();

        prenoma.setText(prenomtxt);
        noma.setText(nomtxt);
        emaila.setText(emailtxt);
        steassurance.setText(steassurancetxt);
        immatriv.setText(immatrivtxt);
        martyv.setText(martyvtxt);
        adressea.setText(adressetxt);
        datevald.setText(datevaldtxt);
        datevala.setText(datevalatxt);
        numpol.setText(numpoltxt);

        if(activate==1){
            etat.setText("Active");
        }else{etat.setText("Desactive");}

        steassurancetxt=steassurance.getText().toString();
        numpoltxt=numpol.getText().toString();
        datevaldtxt=datevald.getText().toString();
        datevalatxt=datevala.getText().toString();

        //Glide.with(getContext()).load(purl).into(image);//image is a findviewbyid
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

    private void update(){

        //Toast.makeText(getContext(),datevalatxt,Toast.LENGTH_SHORT).show();
        if ((steassurancechanged()+numpolchanged()+datevalachanged()+datevaldchanged())>=1) {
            Toast.makeText(getContext(),"Assure modifie",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(),"Aucune information n'a change",Toast.LENGTH_SHORT).show();
        }
    }

    public int steassurancechanged(){
        //Toast.makeText(getContext(),steassurance.getText(),Toast.LENGTH_SHORT).show();

        if(!steassurancetxt.equals(steassurance.getText().toString())){
            ref.child(idtxt).child("steassurance").setValue(steassurance.getText().toString());
            steassurancetxt=steassurance.getText().toString();
            steassurance.setText(steassurancetxt);
            Toast.makeText(getContext(),"steass",Toast.LENGTH_SHORT).show();
            return 1;}
        else
            return 0;
    }
    public int numpolchanged(){
        if(!numpoltxt.equals(numpol.getText().toString())){
            ref.child(idtxt).child("numpolice").setValue(numpol.getText().toString());
            numpoltxt=numpol.getText().toString();
            numpol.setText(numpoltxt);
            return 1;}
        else return 0;
    }
    public int datevaldchanged(){
        if(!datevaldtxt.equals(datevald.getText().toString())){
            ref.child(idtxt).child("datevald").setValue(datevald.getText().toString());
            datevaldtxt=datevald.getText().toString();
            datevald.setText(datevaldtxt);
            return 1;}
        else return 0;
    }
    public int datevalachanged(){
        if(!datevalatxt.equals(datevala.getText().toString())){
            ref.child(idtxt).child("datevala").setValue(datevala.getText().toString());
            datevalatxt=datevala.getText().toString();
            datevala.setText(datevalatxt);
            return 1;}
        else return 0;
    }

    private Boolean delete() {
         ref.child(idtxt).removeValue();
                // have toadd something to go back similar to clicking on go back
                Toast.makeText(getContext(),"Assure supprime", Toast.LENGTH_SHORT).show();
                return true;

        }

        private void del_builder(View view){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Alert!");
        builder.setMessage("Voulez vous vraiment supprimer cet assure definitivement?");
        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(delete())
                    dialogInterface.cancel();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment nextfrag = new sadmin_sear_up();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,nextfrag).addToBackStack(null)
                        .commit();
                //getActivity().finish();
            }
        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
        .show();
    }

    public void onClick(View view) {
        if(view==btnupdate){
            update();
        }

        if(view==btndelete){
            del_builder(view);

        }

        if(view==deactivate){
            if(etat.getText().equals("Active")){
                ref.child(idtxt).child("activate").setValue(0);
                etat.setText("Desactive");}
            else {ref.child(idtxt).child("activate").setValue(1);
                etat.setText("Active");}
        }
    }

//    public void onBackPreseed(){
//        AppCompatActivity activity = (AppCompatActivity)getContext();
//        Fragment nextfrag = new sadmin_sear_up();
//        activity.getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container,nextfrag).addToBackStack(null)
//                .commit();
//    }
}