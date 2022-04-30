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
import android.widget.Button;
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
import android.app.SearchManager;
//import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class HomeFragment extends Fragment implements View.OnClickListener{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView2;
    myadapter_addsini adpt2;
   // SearchView searchtxt;
    ArrayList<sinistre> filterdlist;
    Button addsini;

    DatabaseReference ref;

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_home, container, false);
      //  recview=v.findViewById(R.id.recview);
      //  recyclerView2= v.findViewById(R.id.recycview);
     //   searchtxt=v.findViewById(R.id.sear);
        // recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
       // sinilist=new ArrayList<sinistre>();
        //recview=v.findViewById(R.id.recview);
        recyclerView2=v.findViewById(R.id.recyclerView2);
      //  searchtxt=v.findViewById(R.id.sear);

        ref = FirebaseDatabase.getInstance().getReference().child("sinistres");

        filterdlist=new ArrayList<sinistre>();

        recyclerView2.setOnClickListener((View.OnClickListener) this);

        addsini=v.findViewById(R.id.addsini);

        addsini.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(getActivity(), decsini.class);
                startActivity(intent);
            }

        });

      //  filterdlist=new ArrayList<sinistre>();

        // recview.setLayoutManager(new LinearLayoutManager(getContext()));


        //    searchtxt.addTextChangedListener(new TextWatcher() {
        //         @Override
        //        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        //       }

        //      @Override
        //      public void onTextChanged(CharSequence s, int start, int before, int count) {

        //      }

        //      @Override
        //      public void afterTextChanged(Editable editable) {
        //          filter(editable.toString());
                /*recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                adpt2=new myadapter(filterdlist);
              //adpt2.filterList(filterdlist);

                recyclerView2.setAdapter(adpt2);*/
        //  adpt2.notifyDataSetChanged();
        //        buildRecyclerView();
        //      }

        //  });

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"on start",Toast.LENGTH_LONG).show();
        if(ref!=null){
           // Toast.makeText(getContext(),"not null",Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(),ref.toString(),Toast.LENGTH_LONG).show();
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Toast.makeText(getContext(),"on datachange",Toast.LENGTH_LONG).show();
                    if(snapshot.exists()){
                        Toast.makeText(getContext(),"snap exists",Toast.LENGTH_LONG).show();
                        filterdlist=new ArrayList<>();
                        for(DataSnapshot data:snapshot.getChildren()){

                            sinistre sini =data.getValue(sinistre.class);
                            sini.setId(data.getKey());
                            //data.getValue(assure.class).setId(data.getKey());
                            //Toast.makeText(getContext(),as.getId(),Toast.LENGTH_LONG).show();
                            //Toast.makeText(getContext(),data.getValue(assure.class).getId(),Toast.LENGTH_LONG).show();
                            filterdlist.add(sini);
                            Toast.makeText(getContext(),sini.getLieuacci(),Toast.LENGTH_LONG).show();
                        }
                        myadapter_addsini adpt= new myadapter_addsini(filterdlist);
                        recyclerView2.setAdapter(adpt);
                    } else {  Toast.makeText(getContext(),"snap doesn't exist",Toast.LENGTH_LONG).show();}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        } else { Toast.makeText(getContext(),"ref null",Toast.LENGTH_LONG).show();}
     //   if(searchtxt!=null){
     //       searchtxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      //          @Override
      //          public boolean onQueryTextSubmit(String query) {
      //              return false;
       //         }

        //        @Override
         //       public boolean onQueryTextChange(String newText) {
          //          search(newText);
          //          return true;
         //       }
         //   });
     //   }
    }

  /*  private void search(String t){
        ArrayList<sinistre> mylist=new ArrayList<>();
        for(sinistre object: filterdlist){
            if (object.getHeureacci().toLowerCase().contains(t)|| object.getDateacci().toLowerCase().contains(t)||object.getDegatsr().toLowerCase().contains(t)||
                    object.getTemoinsacci().toLowerCase().contains(t)|| object.getLieuacci().toLowerCase().contains(t)){
                mylist.add(object);
            }
            else{
                Toast.makeText(getContext(),"Pas d'assure avec les criteres demandes",Toast.LENGTH_LONG).show();
            }
            myadapter_addsini adpt3=new myadapter_addsini(mylist);
            recyclerView2.setAdapter(adpt3);
        }
    }*/

    @Override
    public void onClick(View view) {
        if(view == recyclerView2){

        }
    }
}


