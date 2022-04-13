package com.p1.gsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
}