package com.p1.gsa;

public class vehicule {
//,steassurancetxt,numpolicetxt,
//                    datevaldtxt,datevalatxt,martyvtxt,
//                    immatrivtxt,agenceatxt,

    private String numpolice,immatriv,marque,type,datevala,datevald,steassurance,id;


    public vehicule(){};

    public vehicule (String steassurance,String numpolice,String immatriv, String marque, String type,String datevald, String datevala){

        //this.id=getId();
        this.numpolice = numpolice;this.immatriv = immatriv;this.marque = marque;
        this.type = type;this.datevald=datevald;this.datevala=datevala;this.steassurance=steassurance;
        this.id=id;
    }

    public String getDatevala() {
        return datevala;
    }
    public void setDatevala(String datevala) {
        this.datevala = datevala;
    }

    public String getDatevald() {
        return datevald;
    }
    public void setDatevald(String datevald) {
        this.datevald = datevald;
    }

    public String getImmatriv() {
        return immatriv;
    }
    public void setImmatriv(String immatriv) {
        this.immatriv = immatriv;
    }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNumpolice() {
        return numpolice;
    }
    public void setNumpolice(String numpolice) {
        this.numpolice = numpolice;
    }

    public String getSteassurance() {
        return steassurance;
    }
    public void setSteassurance(String steassurance) {
        this.steassurance = steassurance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
