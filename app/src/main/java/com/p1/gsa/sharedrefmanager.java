package com.p1.gsa;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class sharedrefmanager {
        private static sharedrefmanager instance;
        private static Context ctx;
        private  static final  String shared_pref_name="mysharedref";
        private  static final  String key_noma="noma";
        private  static final  String key_mat="immatriv";
        private  static final  String key_emaila="emaila";
        //private  static final  String key_id="id";


    private sharedrefmanager(Context context) {
            ctx = context;
        }

        public static synchronized sharedrefmanager getInstance(Context context) {
            if (instance == null) {
                instance = new sharedrefmanager(context);
            }
            return instance;
        }

        public boolean sadminlogin(int immatriv,String noma,String emaila){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            SharedPreferences.Editor edt=shps.edit();
            edt.putInt(key_mat,immatriv);
            edt.putString(key_noma,noma);
            edt.putString(key_emaila,emaila);
            edt.apply();
            return true;
        }

        public boolean assurelogin(int immatriv,String noma,String prenoma){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            SharedPreferences.Editor edt=shps.edit();
            edt.putInt(key_mat,immatriv);
            edt.putString(key_noma,noma);
            edt.putString(key_emaila,prenoma);
            edt.apply();
            return true;
        }

        public boolean getass(String noms,String emails,String prenoma,String noma, String emaila){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            SharedPreferences.Editor edt=shps.edit();
                                                                                                                                                                                                                                                                                                                                                                              edt.putString(key_emaila,emaila);
            edt.apply();
            return true;
        }

        public boolean isloggedin(){
        SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
        if(shps.getString(key_emaila,null)!=null){

            return true;
            } return false;
        }
        public boolean logout(){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            SharedPreferences.Editor edt=shps.edit();
            edt.clear();
            edt.apply();
            return true;
        }

        String getemaila(){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            return shps.getString(key_emaila,null);
        }
        String getnoma(){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            return shps.getString(key_noma,null);
        }
    }
