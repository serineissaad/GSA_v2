package com.p1.gsa;

public class sinistre {
    private String temoinsacci, lieuacci, dateacci, heureacci, degatsr, id;

    public sinistre() { }

    public sinistre (String temoinsacci, String lieuacci, String dateacci, String degatsr, String heureacci){

        this.temoinsacci = temoinsacci;
        this.lieuacci = lieuacci;
        this.dateacci = dateacci;
        this.heureacci = heureacci;
        this.degatsr = degatsr;

    }

    public String getTemoinsacci() {
        return temoinsacci;
    }
    public void setTemoinsacciacci(String temoinsacci) {
        this.temoinsacci = temoinsacci;
    }

    public String getLieuacci() {
        return lieuacci;
    }
    public void setLieuacci(String lieuacci) {
        this.lieuacci = lieuacci;
    }

    public String getHeureacci() {
        return heureacci;
    }
    public void setHeureacci(String heureacci) {
        this.heureacci = heureacci;
    }

    public String getDateacci() {
        return dateacci;
    }
    public void setDateacci(String dateacci) {
        this.dateacci = dateacci;
    }

    public String getDegatsr() {
        return degatsr;
    }
    public void setDegatsr(String degatsr) {
        this.degatsr = degatsr;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }




}
