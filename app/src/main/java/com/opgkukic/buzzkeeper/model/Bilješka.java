package com.opgkukic.buzzkeeper.model;

public class Bilješka {
    private String datumKreiranja;
    private String naziv;
    private String relatedId;
    private String sadržaj;

    public Bilješka() {}

    public Bilješka(String datumKreiranja, String naziv, String relatedId, String sadržaj) {
        this.datumKreiranja = datumKreiranja;
        this.naziv = naziv;
        this.relatedId = relatedId;
        this.sadržaj = sadržaj;
    }

    public String getDatumKreiranja() { return datumKreiranja; }
    public String getNaziv() { return naziv; }
    public String getRelatedId() { return relatedId; }
    public String getSadržaj() { return sadržaj; }
}
