package com.p1.gsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB1Helper extends SQLiteOpenHelper{

    public DB1Helper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create table assure(name TEXT primary key,email TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists assure");
    }

    public Boolean insertassure(String name, String email, String dob){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("dob",dob);
        long result=DB.insert("assure",null,cv);
        if(result==-1)
            return false;
        return true;
    }

    public Boolean updateassure(String name, String email, String dob){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("dob",dob);
        Cursor cr=DB.rawQuery("select * from assure where name=?", new String []{name});
        if(cr.getCount()>0) {
            long result = DB.update("assure", cv, "name=?", new String[]{name});
            if (result == -1)
                return false;
            return true;
        }
        else return false;
    }
    public Boolean deleteassure(String name){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cr=DB.rawQuery("select * from assure where name=?", new String []{name});
        if(cr.getCount()>0) {
            long result = DB.delete("assure", "name=?", new String[]{name});
            if (result == -1)
                return false;
            return true;
        }
        else return false;
    }
        //test
    public Cursor getassurelist(){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cr=DB.rawQuery("select * from assure",null);
        return cr;
    }

}
