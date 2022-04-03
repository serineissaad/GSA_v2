package com.p1.gsa;

public class assure {
    private String noma,prenoma,adressea,datevald,datevala,agencea,steassurance,emaila;
    private String numpolice,martyv,immatriv;


    public String getAdressea() {
        return adressea;
    }

    public void setAdressea(String adressea) {
        this.adressea = adressea;
    }

    public String getAgencea() {
        return agencea;
    }

    public void setAgencea(String agencea) {
        this.agencea = agencea;
    }

    public String getPrenoma() {
        return prenoma;
    }

    public void setPrenoma(String prenoma) {
        this.prenoma = prenoma;
    }

    public assure (String noma, String prenoma, String adressea, String steassurance, String numpolice,
                   String datevald, String datevala, String martyv, String immatriv, String agencea, String emaila){

        this.emaila = emaila;this.prenoma = prenoma;this.adressea = adressea;
        this.noma = noma;this.steassurance = steassurance;
        this.numpolice = numpolice;this.datevala = datevala;
        this.datevald = datevald;this.martyv = martyv;
        this.immatriv = immatriv;this.agencea = agencea;
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

    public String getMartyv() {
        return martyv;
    }

    public void setMartyv(String martyv) {
        this.martyv = martyv;
    }

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

    public void setImmatriv(String immatriv) {
        this.immatriv = immatriv;
    }

    public String getEmaila() {
        return emaila;
    }

    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    public String getNoma() { return noma; }

    public void setNoma(String noma) {
        this.noma = noma;
    }
}
