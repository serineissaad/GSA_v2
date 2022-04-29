package com.p1.gsa;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class fourniture implements Parcelable {
    String nom,id; int qte; float prix;

    public fourniture() {
    }

    public fourniture(String nom,int qte,float prix) {
        this.nom = nom;this.qte=qte; this.prix=prix;
    }

    protected fourniture(Parcel in) {
        nom = in.readString();
        id = in.readString();
        qte = in.readInt();
        prix = in.readFloat();
    }

    public static final Creator<fourniture> CREATOR = new Creator<fourniture>() {
        @Override
        public fourniture createFromParcel(Parcel in) {
            return new fourniture(in);
        }

        @Override
        public fourniture[] newArray(int size) {
            return new fourniture[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nom);
        parcel.writeString(id);
        parcel.writeInt(qte);
        parcel.writeFloat(prix);
    }
}
