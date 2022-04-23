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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class myadapter_act_del extends FirebaseRecyclerAdapter<assure,myadapter_act_del.myviewholder> /*RecyclerView.Adapter<myadapter_act_del.myviewholder> */{

    //ArrayList<assure> asslist;
    //ArrayList<assure> asslistsear;
    //LinearLayout lnlyt;
    ImageView imgdelete,imgactivate;
    OnItemClickListener listener;
    String emailatxt;

//    public myadapter_act_del(ArrayList<assure> asslist) {
//        this.asslist = asslist;
//        asslistsear=new ArrayList<>(asslist);
//    }

    public  myadapter_act_del(@NonNull FirebaseRecyclerOptions<assure> options){
        super(options);
    }

    public static Toast makeText (Context context, CharSequence text, int duration){
        return makeText(context,text,duration);
    };


    public interface OnItemClickListener{
        void ondelete(int position);
        void onactivate(int position);
    }

    public void setOnItemClickListener(OnItemClickListener lst) {
        listener = lst;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ass_row_ad,parent,false);
        //myviewholder v=new myviewholder(view,listener);
        myviewholder v=new myviewholder(view);
        return  v;
    }

//    @Override
//    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
//        holder.noma.setText(asslist.get(position).getNoma());
//        holder.emaila.setText(asslist.get(position).getEmaila());
//        holder.adressea.setText(asslist.get(position).getAdressea());
//        holder.agencea.setText(asslist.get(position).getAgencea());
//        holder.martyv.setText(asslist.get(position).getMartyv());
//        holder.numpolice.setText(asslist.get(position).getNumpolice());
//        holder.immatriv.setText(asslist.get(position).getImmatriv());
//        holder.prenoma.setText(asslist.get(position).getPrenoma());
//        holder.datevala.setText(asslist.get(position).getDatevala());
//        holder.datevald.setText(asslist.get(position).getDatevald());
//        holder.steassurance.setText(asslist.get(position).getSteassurance());
//
//        /*sea.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
//            }
//
//        });
//
//        */
//
//        //emailatxt= (String) holder.emaila.getText();
//    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull assure model) {
        holder.noma.setText(model.getNoma());
        holder.emaila.setText(model.getEmaila());
        holder.adressea.setText(model.getAdressea());
        holder.agencea.setText(model.getAgencea());
        holder.martyv.setText(model.getMartyv());
        holder.numpolice.setText(model.getNumpolice());
        holder.immatriv.setText(model.getImmatriv());
        holder.prenoma.setText(model.getPrenoma());
        holder.datevala.setText(model.getDatevala());
        holder.datevald.setText(model.getDatevald());
        holder.steassurance.setText(model.getSteassurance());

    //Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
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

    //@Override
//    public int getItemCount() {
//        return asslist.size();
//    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView emaila,noma,prenoma,agencea,immatriv,numpolice,adressea,datevald,datevala,steassurance,martyv;
        RelativeLayout lncontainor;
        public myviewholder(@NonNull View itemView/*,final OnItemClickListener lst*/) {
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
            imgactivate=itemView.findViewById(R.id.imgactivate);

//           imgdelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (lst != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            lst.ondelete(position);
//                        }
//                    }
//                }
//            });
//
//           imgactivate.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
//                   if (lst != null) {
//                       int position = getAdapterPosition();
//                       if (position != RecyclerView.NO_POSITION) {
//                           lst.onactivate(position);
//                       }
//                   }
//               }
//           });
        }
    }

    public void filterList(ArrayList<assure> filteredList) {
        //asslist = filteredList;
        notifyDataSetChanged();
    }

}
