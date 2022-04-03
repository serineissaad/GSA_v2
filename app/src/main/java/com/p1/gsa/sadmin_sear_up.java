package com.p1.gsa;

import android.os.Bundle;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class sadmin_sear_up extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView2;
    myadapter_sear_up adpt2;
    EditText searchtxt;
    ArrayList<assure> filterdlist;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sadmin_sear_up() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sadmin_sear_up, container, false);

        recyclerView2=v.findViewById(R.id.recyclerView2);
        searchtxt=v.findViewById(R.id.sear);
        /*recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(adpt2);*/
        filterdlist=new ArrayList<assure>();

        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
                /*recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                adpt2=new myadapter(filterdlist);
              //adpt2.filterList(filterdlist);

                recyclerView2.setAdapter(adpt2);*/
              //  adpt2.notifyDataSetChanged();
                buildRecyclerView();
            }

        });
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
                        buildRecyclerView();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error listener",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext().getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requesthandler.getInstance(getContext()).addToRequestQueue(str);
    }

    private void buildRecyclerView() {
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        adpt2=new myadapter_sear_up(filterdlist);
        recyclerView2.setAdapter(adpt2);}
}
