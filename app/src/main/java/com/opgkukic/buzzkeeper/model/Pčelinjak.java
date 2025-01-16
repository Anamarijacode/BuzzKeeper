package com.opgkukic.buzzkeeper.model;

public class Pčelinjak {
    private String datumKreiranja;
    private Lokacija lokacija;
    private String nazivPčelinjaka;
    private String tipMjesta;
    private String tipPčelinjaka;
    private String userId;


    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public String getNazivPčelinjaka() {
        return nazivPčelinjaka;
    }

    public void setNazivPčelinjaka(String nazivPčelinjaka) {
        this.nazivPčelinjaka = nazivPčelinjaka;
    }

    public String getTipMjesta() {
        return tipMjesta;
    }

    public void setTipMjesta(String tipMjesta) {
        this.tipMjesta = tipMjesta;
    }

    public String getTipPčelinjaka() {
        return tipPčelinjaka;
    }

    public void setTipPčelinjaka(String tipPčelinjaka) {
        this.tipPčelinjaka = tipPčelinjaka;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Pčelinjak(String datumKreiranja, Lokacija lokacija, String nazivPčelinjaka, String tipMjesta, String tipPčelinjaka, String userId) {
        this.datumKreiranja = datumKreiranja;
        this.lokacija = lokacija;
        this.nazivPčelinjaka = nazivPčelinjaka;
        this.tipMjesta = tipMjesta;
        this.tipPčelinjaka = tipPčelinjaka;
        this.userId = userId;
    }
    public Pčelinjak(){}
}

