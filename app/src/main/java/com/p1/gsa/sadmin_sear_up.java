package com.p1.gsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class sadmin_sear_up extends Fragment implements View.OnClickListener{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView2;
    myadapter_sear_up adpt2;
    SearchView searchtxt;
    ArrayList<assure> filterdlist;

    DatabaseReference ref;

    private String mParam1;
    private String mParam2;

    public sadmin_sear_up() {
    }


    public static sadmin_sear_up newInstance(String param1, String param2) {
        sadmin_sear_up fragment = new sadmin_sear_up();
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
        View v=inflater.inflate(R.layout.fragment_sadmin_sear_up, container, false);

        recyclerView2=v.findViewById(R.id.recyclerView2);
        searchtxt=v.findViewById(R.id.sear);

        ref = FirebaseDatabase.getInstance().getReference().child("assure");

        filterdlist=new ArrayList<assure>();

        recyclerView2.setOnClickListener((View.OnClickListener) this);

        /*searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
                buildRecyclerView();
            }
        });*/
        return v;
    }

    private void filter(String text) {
        StringRequest str=new StringRequest(Request.Method.GET, Constants.URL_GETASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        filterdlist.clear();
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
                                    String  martyv= String.valueOf(obj.getInt("martyv"));
                                    String immatriv= String.valueOf(obj.getInt("immatriv"));
                                    String agencea=obj.getString("agencea");
                                    String emaila=obj.getString("emaila");
                                    assure ass=new assure(noma,prenoma,adressea,steassurance,numpolice,datevald,datevala,martyv,immatriv,agencea,emaila);
                                    String t=text.toLowerCase();
                                        if (noma.toLowerCase().contains(t)||emaila.toLowerCase().contains(t)||
                                                prenoma.toLowerCase().contains(t)||agencea.toLowerCase().contains(t)||
                                                datevala.toLowerCase().contains(t)||datevald.toLowerCase().contains(t)||
                                                immatriv.toLowerCase().contains(t)||martyv.toLowerCase().contains(t)||
                                                numpolice.toLowerCase().contains(t)||steassurance.toLowerCase().contains(t)||
                                                adressea.toLowerCase().contains(t)
                                        ) {
                                            filterdlist.add(ass);
                                        }
                                }
                            }
                            else {
                                Toast.makeText(getContext(),objt.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //buildRecyclerView();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error listener",Toast.LENGTH_SHORT).show();
            }
        });
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
    }

//    private void buildRecyclerView() {
//        recyclerView2.setHasFixedSize(true);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
//        adpt2=new myadapter_sear_up(filterdlist);
//        recyclerView2.setAdapter(adpt2);}


    @Override
    public void onStart() {
        super.onStart();

        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        filterdlist=new ArrayList<>();
                        for(DataSnapshot data:snapshot.getChildren()){
                            assure as=data.getValue(assure.class);
                            as.setId(data.getKey());
                            //data.getValue(assure.class).setId(data.getKey());
                            //Toast.makeText(getContext(),as.getId(),Toast.LENGTH_LONG).show();
                            //Toast.makeText(getContext(),data.getValue(assure.class).getId(),Toast.LENGTH_LONG).show();
                            filterdlist.add(as);
                        }
                        myadapter_sear_up adpt= new myadapter_sear_up(filterdlist);
                        recyclerView2.setAdapter(adpt);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
        if(searchtxt!=null){
            searchtxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String t){
        ArrayList<assure> mylist=new ArrayList<>();
        for(assure object: filterdlist){
            if (object.getNoma().toLowerCase().contains(t)||object.getEmaila().toLowerCase().contains(t)||
                    object.getPrenoma().toLowerCase().contains(t)||object.getAgencea().toLowerCase().contains(t)||
                    object.getDatevald().toLowerCase().contains(t)||object.getDatevala().toLowerCase().contains(t)||
                    object.getImmatriv().toLowerCase().contains(t)||object.getMartyv().toLowerCase().contains(t)||
                    object.getNumpolice().toLowerCase().contains(t)||object.getSteassurance().toLowerCase().contains(t)||
                    object.getAdressea().toLowerCase().contains(t)
            ){
                mylist.add(object);
            }
            else{
                Toast.makeText(getContext(),"Pas d'assure avec les criteres demandes",Toast.LENGTH_LONG).show();
            }
            myadapter_sear_up adpt3=new myadapter_sear_up(mylist);
            recyclerView2.setAdapter(adpt3);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == recyclerView2){

        }
    }
}
