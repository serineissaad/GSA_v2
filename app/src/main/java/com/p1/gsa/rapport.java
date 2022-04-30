package com.p1.gsa;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class rapport implements Parcelable {
    String dommage,reparation,ltot,obs,id;
    Double moeuv,mpeint,mtot,txhr,txvt;
    int mhr,mimmob,nbphotos;

    public rapport() {
    }
    public rapport(String dommage,String reparation) {
        this.dommage=dommage; this.reparation=reparation;
    }

    public rapport(String dommage, String reparation, String ltot, String obs, Double moeuv, Double mpeint, Double mtot, Double txhr, Double txvt, int mhr, int mimmob, int nbphotos) {
        this.dommage = dommage;
        this.reparation = reparation;
        this.ltot = ltot;
        this.obs = obs;
        this.moeuv = moeuv;
        this.mpeint = mpeint;
        this.mtot = mtot;
        this.txhr = txhr;
        this.txvt = txvt;
        this.mhr = mhr;
        this.mimmob = mimmob;
        this.nbphotos = nbphotos;
    }


    protected rapport(Parcel in) {
        dommage = in.readString();
        reparation = in.readString();
        ltot = in.readString();
        obs = in.readString();
        id = in.readString();
        if (in.readByte() == 0) {
            moeuv = null;
        } else {
            moeuv = in.readDouble();
        }
        if (in.readByte() == 0) {
            mpeint = null;
        } else {
            mpeint = in.readDouble();
        }
        if (in.readByte() == 0) {
            mtot = null;
        } else {
            mtot = in.readDouble();
        }
        if (in.readByte() == 0) {
            txhr = null;
        } else {
            txhr = in.readDouble();
        }
        if (in.readByte() == 0) {
            txvt = null;
        } else {
            txvt = in.readDouble();
        }
        mhr = in.readInt();
        mimmob = in.readInt();
        nbphotos = in.readInt();
    }

    public static final Creator<rapport> CREATOR = new Creator<rapport>() {
        @Override
        public rapport createFromParcel(Parcel in) {
            return new rapport(in);
        }

        @Override
        public rapport[] newArray(int size) {
            return new rapport[size];
        }
    };

    public String getDommage() {
        return dommage;
    }

    public void setDommage(String dommage) {
        this.dommage = dommage;
    }

    public String getReparation() {
        return reparation;
    }

    public void setReparation(String reparation) {
        this.reparation = reparation;
    }

    public String getLtot() {
        return ltot;
    }

    public void setLtot(String ltot) {
        this.ltot = ltot;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getMoeuv() {
        return moeuv;
    }

    public void setMoeuv(Double moeuv) {
        this.moeuv = moeuv;
    }

    public Double getMpeint() {
        return mpeint;
    }

    public void setMpeint(Double mpeint) {
        this.mpeint = mpeint;
    }

    public Double getMtot() {
        return mtot;
    }

    public void setMtot(Double mtot) {
        this.mtot = mtot;
    }

    public Double getTxhr() {
        return txhr;
    }

    public void setTxhr(Double txhr) {
        this.txhr = txhr;
    }

    public Double getTxvt() {
        return txvt;
    }

    public void setTxvt(Double txvt) {
        this.txvt = txvt;
    }

    public int getMhr() {
        return mhr;
    }

    public void setMhr(int mhr) {
        this.mhr = mhr;
    }

    public int getMimmob() {
        return mimmob;
    }

    public void setMimmob(int mimmob) {
        this.mimmob = mimmob;
    }

    public int getNbphotos() {
        return nbphotos;
    }

    public void setNbphotos(int nbphotos) {
        this.nbphotos = nbphotos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dommage);
        parcel.writeString(reparation);
        parcel.writeString(ltot);
        parcel.writeString(obs);
        parcel.writeString(id);
        if (moeuv == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(moeuv);
        }
        if (mpeint == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(mpeint);
        }
        if (mtot == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(mtot);
        }
        if (txhr == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(txhr);
        }
        if (txvt == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(txvt);
        }
        parcel.writeInt(mhr);
        parcel.writeInt(mimmob);
        parcel.writeInt(nbphotos);
    }
}