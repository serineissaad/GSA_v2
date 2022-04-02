package com.p1.gsa;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    ArrayList<assure> asslist;
    ArrayList<assure> asslistsear;
    //LinearLayout lnlyt;
    ImageView imgdelete;
    OnItemClickListener listener;
    String emailatxt;

    public myadapter(ArrayList<assure> asslist) {
        this.asslist = asslist;
        asslistsear=new ArrayList<>(asslist);
    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    };

    /*@Override
    public Filter getFilter() {
        return searfilter;
    }

    private Filter searfilter=new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<assure> filteredlist=new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredlist.addAll(asslistsear);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (assure item : asslistsear) {
                    if (item.getEmaila().toLowerCase().contains(filterPattern)
                    || item.getNoms().toLowerCase().contains(filterPattern)) {
                        filteredlist.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            asslist.clear();
            asslist.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };*/

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

    public void filterList(ArrayList<assure> filteredList) {
        asslist = filteredList;
        notifyDataSetChanged();
    }

}
