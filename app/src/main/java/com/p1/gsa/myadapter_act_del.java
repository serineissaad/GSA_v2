package com.p1.gsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter_act_del extends RecyclerView.Adapter<myadapter_act_del.myviewholder> {

    ArrayList<assure> asslist;
    ArrayList<assure> asslistsear;
    //LinearLayout lnlyt;
    ImageView imgdelete;
    OnItemClickListener listener;
    String emailatxt;

    public myadapter_act_del(ArrayList<assure> asslist) {
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ass_row_ad,parent,false);
        myviewholder v=new myviewholder(view,listener);
        return  v;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.noma.setText(asslist.get(position).getNoma());
        holder.emaila.setText(asslist.get(position).getEmaila());
        holder.adressea.setText(asslist.get(position).getAdressea());
        holder.agencea.setText(asslist.get(position).getAgencea());
        holder.martyv.setText(asslist.get(position).getMartyv());
        holder.numpolice.setText(asslist.get(position).getNumpolice());
        holder.immatriv.setText(asslist.get(position).getImmatriv());
        holder.prenoma.setText(asslist.get(position).getPrenoma());
        holder.datevala.setText(asslist.get(position).getDatevala());
        holder.datevald.setText(asslist.get(position).getDatevald());
        holder.steassurance.setText(asslist.get(position).getSteassurance());

        /*sea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }

        });

        */

        //emailatxt= (String) holder.emaila.getText();
    }

    /*private void filter(String text) {
        ArrayList<assure> filteredList = new ArrayList<>();

        for (assure item : asslist) {
            if (item.getNoms().toLowerCase().contains(text.toLowerCase())||
                    item.getNoms().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        filterList(filteredList);
    }*/

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

            imgdelete=itemView.findViewById(R.id.imgdelete);

           imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lst != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            lst.ondelete(position);
                        }
                    }
                }
            });
        }
    }

    public void filterList(ArrayList<assure> filteredList) {
        asslist = filteredList;
        notifyDataSetChanged();
    }

}