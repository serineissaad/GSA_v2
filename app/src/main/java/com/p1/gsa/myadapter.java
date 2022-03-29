package com.p1.gsa;

import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>{

    ArrayList<assure> asslist;
    //LinearLayout lnlyt;
    ImageView imgdelete;
    OnItemClickListener listener;
    String emailatxt;

    public myadapter(ArrayList<assure> asslist) {
        this.asslist = asslist;
    }

    public interface OnItemClickListener{
        void ondelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener lst) {
        listener = lst;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ass_row,parent,false);
        myviewholder v=new myviewholder(view,listener);
        return  v;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.noms.setText(asslist.get(position).getNoms());
        holder.emaila.setText(asslist.get(position).getEmaila());
        //emailatxt= (String) holder.emaila.getText();



    }

    @Override
    public int getItemCount() {
        return asslist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView emaila,noms;
        RelativeLayout lncontainor;
        public myviewholder(@NonNull View itemView,final OnItemClickListener lst) {
            super(itemView);
            emaila=itemView.findViewById(R.id.emailass);
            noms=itemView.findViewById(R.id.nomass);
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


}
