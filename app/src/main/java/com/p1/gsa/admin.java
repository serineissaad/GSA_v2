package com.p1.gsa;

public class admin {
    private String prenoma,noma,emaila;//pass;

    public admin() { }

    public admin(String prenoma,String noma,String emaila) {
        this.emaila = emaila;
        this.prenoma=prenoma;
        this.noma=noma;
        //this.pass=pass;
    }

    public String getEmaila() {
        return emaila;
    }

    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    public String getNoma() {
        return noma;
    }

    public void setNoma(String noma) {
        this.noma = noma;
    }

//    public String getPass() {
//        return pass;
//    }

//    public void setPass(String pass) {
//        this.pass = pass;
//    }

    public String getPrenoma() {
        return prenoma;
    }

    public void setPrenoma(String prenoma) {
        this.prenoma = prenoma;
    }
}
