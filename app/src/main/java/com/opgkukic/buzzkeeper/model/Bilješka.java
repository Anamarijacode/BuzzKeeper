package com.opgkukic.buzzkeeper.model;

public class Bilješka {
    private String datumKreiranja;
    private String naziv;
    private String relatedId;
    private String sadržaj;
    private String id; // The unique ID for each Bilješka

    // Default constructor (needed for Firebase to deserialize data)
    public Bilješka() {}

    // Constructor with parameters
    public Bilješka(String datumKreiranja, String naziv, String relatedId, String sadržaj) {
        this.datumKreiranja = datumKreiranja;
        this.naziv = naziv;
        this.relatedId = relatedId;
        this.sadržaj = sadržaj;
    }

    // Getter and setter for datumKreiranja
    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    // Getter and setter for naziv
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    // Getter and setter for relatedId
    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }

    // Getter and setter for sadržaj
    public String getSadržaj() {
        return sadržaj;
    }

    public void setSadržaj(String sadržaj) {
        this.sadržaj = sadržaj;
    }

    // Getter and setter for id (unique identifier for the Bilješka)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
