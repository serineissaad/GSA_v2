package com.p1.gsa;

public class assure {
    private String noma,prenoma,adressea,emaila,id;
    //private String numpolice,martyv,immatriv,id;
    private int activate;


    public String getAdressea() {
        return adressea;
    }

    public void setAdressea(String adressea) {
        this.adressea = adressea;
    }

    public String getPrenoma() {
        return prenoma;
    }

    public void setPrenoma(String prenoma) {
        this.prenoma = prenoma;
    }

    public assure(){};

    public assure (String prenoma,String noma, String adressea, String emaila){

        //this.id=getId();
        this.emaila = emaila;this.prenoma = prenoma;this.adressea = adressea;
        this.noma = noma;
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

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public int getActivate() { return activate; }

    public void setActivate(int activate) {
        this.activate = activate;
    }
}
