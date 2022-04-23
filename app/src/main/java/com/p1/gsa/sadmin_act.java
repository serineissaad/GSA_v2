package com.p1.gsa;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class sadmin_act extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    myadapter_act_del adpt;
    ImageView btndelete,btnactivate;
    String emailass;
    ArrayList<assure> asslist;

    private String mParam1;
    private String mParam2;

    public sadmin_act() {
    }

    public static sadmin_act newInstance(String param1, String param2) {
        sadmin_act fragment = new sadmin_act();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nav_draw);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sadmin_acc, container, false);

        View lnview = inflater.inflate(R.layout.fragment_sadmin_act, container, false);
        recyclerView=lnview.findViewById(R.id.recycview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        btndelete=lnview.findViewById(R.id.btndelete);
        btnactivate=lnview.findViewById(R.id.imgactivate);
        //asslist=new ArrayList<assure>();

        //getass();

        FirebaseRecyclerOptions<assure> options= new FirebaseRecyclerOptions.Builder<assure>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("assure").orderByChild("activate").equalTo("0"),assure.class)
                .build();

        adpt=new myadapter_act_del(options);
        recyclerView.setAdapter(adpt);

        Toast.makeText(getContext(),"returning VIEW",Toast.LENGTH_SHORT).show();
        return lnview;

    }

    /*public boolean getass(){

        StringRequest str=new StringRequest(Request.Method.GET, Constants.URL_GETASS,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objt=new JSONObject(response);

                    if(!objt.getBoolean("error")){
                        JSONArray array= objt.getJSONArray("list");

                        for (int i = 0; i<array.length(); i++){
                            JSONObject obj=array.getJSONObject(i);
                            String noma =obj.getString("noma");
                            String prenoma=obj.getString("prenoma");
                            String adressea=obj.getString("adressea");
                            String steassurance=obj.getString("steassurance");
                            String numpolice= String.valueOf(obj.getInt("numpolice"));
                            String datevald=obj.getString("datevald");
                            String datevala=obj.getString("datevala");
                            String martyv= String.valueOf(obj.getInt("martyv"));
                            String immatriv= String.valueOf(obj.getInt("immatriv"));
                            String agencea=obj.getString("agencea");
                            String emaila=obj.getString("emaila");
                            assure ass=new assure(noma,prenoma,adressea,steassurance,numpolice,datevald,datevala,martyv,immatriv,agencea,emaila);
                            asslist.add(ass);
                        }
                    }
                    else {
                        Toast.makeText(getContext(),objt.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                adpt=new myadapter_act_del(asslist);
                recyclerView.setAdapter(adpt);
                adpt.setOnItemClickListener(new myadapter_act_del.OnItemClickListener(){
                    @Override
                    public void ondelete(int position) {
                        //emailass= asslist.get(position).getEmaila();
                        emailass =asslist.get(position).getEmaila();
                        deleteass(emailass);
                        asslist.remove(position);
                        adpt.notifyItemRemoved(position);
                    }

                    @Override
                    public void onactivate(int position) {
                        emailass =asslist.get(position).getEmaila();
                        activateass(emailass);
                        asslist.remove(position);
                        adpt.notifyItemRemoved(position);
                    }
                });
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error listener",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext().getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
        return true;
    }*/

    public void deleteass(String emailass){
        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_DELETEASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject job=new JSONObject(response);
                    if(!job.getBoolean("error")) {
                        Toast.makeText(getContext(),"job false",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), job.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"job true",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(),job.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "error listener", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("emaila",emailass);
                return params;
            }
        };
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
    }

    public void activateass(String emailass){
        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_ACTIVATEASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject job=new JSONObject(response);
                    if(!job.getBoolean("error")) {
                        Toast.makeText(getContext(),"job false",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), job.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"job true",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(),job.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "error listener", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("emaila",emailass);
                return params;
            }
        };
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
    }

    @Override
    public void onClick(View view) {
           /*if(view==btndelete){
               Toast.makeText(getContext(),"btn clicked",Toast.LENGTH_SHORT).show();
               deleteass();
            }*/
    }

    @Override
    public void onStart(){
        super.onStart();
        adpt.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adpt.stopListening();
    }
}