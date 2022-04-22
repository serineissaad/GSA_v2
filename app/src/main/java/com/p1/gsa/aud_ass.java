package com.p1.gsa;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

    public class aud_ass {
        private DatabaseReference dbref;

        public aud_ass(){
            FirebaseDatabase db=FirebaseDatabase.getInstance();
            dbref=db.getReference(assure.class.getSimpleName());
        }


        public Task<Void> addass(assure ass){
            return dbref.push().setValue(ass);
        }

        public Task<Void> update(String key, HashMap<String,Object> hashmap){
            return dbref.child(key).updateChildren(hashmap);
        }


    }

