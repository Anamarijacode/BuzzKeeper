package com.opgkukic.buzzkeeper.model;

public class Pčelinjak {
    private String datumKreiranja;
    private Lokacija lokacija;
    private String nazivPčelinjaka;
    private String tipMjesta;
    private String tipPčelinjaka;

    public Pčelinjak() {}

    public Pčelinjak(String nazivPcelinjaka, Lokacija lokacija, String tipMjesta, String tipPcelinjaka, String userId1) {
    }

    public String getDatumKreiranja() { return datumKreiranja; }
    public Lokacija getLokacija() { return lokacija; }
    public String getNazivPčelinjaka() { return nazivPčelinjaka; }
    public String getTipMjesta() { return tipMjesta; }
    public String getTipPčelinjaka() { return tipPčelinjaka; }

    @Override
    public String toString() {
        return "Pčelinjak{" +
                "nazivPčelinjaka='" + nazivPčelinjaka + '\'' +
                ", tipPčelinjaka='" + tipPčelinjaka + '\'' +
                '}';
    }
}

