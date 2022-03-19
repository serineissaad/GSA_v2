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
        private  static final  String key_noms="noms";
        private  static final  String key_emaila="emaila";
        private  static final  String key_id="id";


    private sharedrefmanager(Context context) {
            ctx = context;
        }

        public static synchronized sharedrefmanager getInstance(Context context) {
            if (instance == null) {
                instance = new sharedrefmanager(context);
            }
            return instance;
        }

        public boolean sadminlogin(int id,String noms,String emaila){
            SharedPreferences shps=ctx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
            SharedPreferences.Editor edt=shps.edit();
            edt.putInt(key_id,id);
            edt.putString(key_noms,noms);
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

}
