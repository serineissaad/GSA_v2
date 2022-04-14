package com.p1.gsa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myadapter_sear_up extends RecyclerView.Adapter<myadapter_sear_up.myviewholder> {

    ArrayList<assure> asslist;
    ArrayList<assure> asslistsear;
    Button btnupdate;
    OnItemClickListener listener;

    public myadapter_sear_up(ArrayList<assure> asslist) {
        this.asslist = asslist;
        asslistsear=new ArrayList<>(asslist);
    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    };

    public interface OnItemClickListener{
        void ondelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener lst) {
        listener = lst;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ass_row_su,parent,false);
        myviewholder v=new myviewholder(view,listener);
        return  v;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.noma.setText(asslist.get(position).getNoma());
        holder.emaila.setText(asslist.get(position).getEmaila());
        //holder.adressea.setText(asslist.get(position).getAdressea());
        //holder.agencea.setText(asslist.get(position).getAgencea());
        //holder.martyv.setText(asslist.get(position).getMartyv());
        //holder.numpolice.setText(asslist.get(position).getNumpolice());
        holder.immatriv.setText(asslist.get(position).getImmatriv());
        holder.prenoma.setText(asslist.get(position).getPrenoma());
        //holder.datevala.setText(asslist.get(position).getDatevala());
        //holder.datevald.setText(asslist.get(position).getDatevald());
        //holder.steassurance.setText(asslist.get(position).getSteassurance());

        holder.lncontainor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment nextfrag = new sadmin_sear_up_1ass();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,nextfrag).addToBackStack(null)
                        .commit();

                int position=holder.getAdapterPosition();

                Bundle bundle = new Bundle();
                bundle.putString("noma",asslist.get(position).getNoma());
                bundle.putString("adressea",asslist.get(position).getAdressea());
                bundle.putString("steassurance",asslist.get(position).getSteassurance());
                bundle.putString("martyv",asslist.get(position).getMartyv());
                bundle.putString("numpolice",asslist.get(position).getNumpolice());
                bundle.putString("immatriv",asslist.get(position).getImmatriv());
                bundle.putString("emaila",asslist.get(position).getEmaila());
                bundle.putString("datevala",asslist.get(position).getDatevala());
                bundle.putString("datevald",asslist.get(position).getDatevald());
                nextfrag.setArguments(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asslist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView emaila,noma,prenoma,agencea,immatriv,numpolice,adressea,datevald,datevala,steassurance,martyv;
        RelativeLayout lncontainor;
        public myviewholder(@NonNull View itemView,final OnItemClickListener lst) {
            super(itemView);
            emaila=itemView.findViewById(R.id.emailass);
            noma=itemView.findViewById(R.id.nomass);
            immatriv=itemView.findViewById(R.id.immatriv);
            numpolice=itemView.findViewById(R.id.numpol);
            martyv=itemView.findViewById(R.id.martyv);
            datevala=itemView.findViewById(R.id.datea);
            datevald=itemView.findViewById(R.id.dated);
            agencea=itemView.findViewById(R.id.agencea);
            prenoma=itemView.findViewById(R.id.prenomass);
            adressea=itemView.findViewById(R.id.adressea);
            steassurance=itemView.findViewById(R.id.steassurance);


            lncontainor=itemView.findViewById(R.id.asscontainor);

//            btnupdate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
        }
    }

//    private void updateass(){
////        final String nomstxt=noms.getText().toString().trim();
////        final String emailstxt=emails.getText().toString().trim();
////        final String nomatxt=noma.getText().toString().trim();
////        final String prenomatxt=prenoma.getText().toString().trim();
////        final String emailatxt=emaila.getText().toString().trim();
////        final String passtxt=pass.getText().toString().trim();
//
//        StringRequest str=new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject job=new JSONObject(response);
//                    if(!job.getBoolean("error")) {
//                        //Toast.makeText(signupas.this, "in if", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(btnupdate.getContext(), job.getString("message"), Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        //Toast.makeText(signupas.this, "in else", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(btnupdate.getContext(), job.getString("message"),Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(btnupdate.getContext(), "error listener", Toast.LENGTH_SHORT).show();
//                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<String, String>();
//                //Toast.makeText(signupas.this, "in params", Toast.LENGTH_SHORT).show();
//                params.put("noma",noma);
//                params.put("emaila",emailstxt);
//                params.put("prenoma",prenomatxt);
//                params.put("adressea",nomatxt);
//                params.put("immatriv",emailatxt);
//                params.put("martyv",passtxt);
//                params.put("numpolice",passtxt);
//                params.put("numpolice",passtxt);
//                return params;
//            }
//        };
//        //RequestQueue rq= Volley.newRequestQueue(signupas.this);
//        //Toast.makeText(signupas.this, "about to add", Toast.LENGTH_SHORT).show();
//        //rq.add(str);
//        requesthandler.getInstance(this).addToRequestQueue(str);
//    }

    public void filterList(ArrayList<assure> filteredList) {
        asslist = filteredList;
        notifyDataSetChanged();
    }

}
