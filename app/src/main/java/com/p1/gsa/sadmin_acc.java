package com.p1.gsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sadmin_acc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sadmin_acc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView email, nom,id;
    Button btnlogout;
    Button btnaddadmin;
    public sadmin_acc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sadmin_acc.
     */
    // TODO: Rename and change types and number of parameters
    public static sadmin_acc newInstance(String param1, String param2) {
        sadmin_acc fragment = new sadmin_acc();
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sadmin_acc, container, false);

        View v=inflater.inflate(R.layout.fragment_sadmin_acc,container,false);
        id=v.findViewById(R.id.id);
        email=v.findViewById(R.id.email);
        nom=v.findViewById(R.id.nom);
        btnlogout=v.findViewById(R.id.btnlogout);
        btnaddadmin=v.findViewById(R.id.btnaddadmin);
        btnlogout.setOnClickListener(this::onClick);

        email.setText(sharedrefmanager.getInstance(getContext()).getemaila());
        nom.setText(sharedrefmanager.getInstance(getContext()).getnoma());

        return v;
    }

    public void onClick(View view) {
        if(view==btnlogout){
            sharedrefmanager.getInstance(getContext()).logout();
            Intent intent=new Intent(view.getContext(),login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getActivity().finish();
        }

        if(view==btnaddadmin){
            Intent intent=new Intent(view.getContext(),signupas.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

}