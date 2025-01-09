package com.opgkukic.buzzkeeper.model;

public class TipKošnice {
    private String img;
    private String naziv;
    private String opis;
    private String skračenica;

    public TipKošnice() {}

    public TipKošnice(String img, String naziv, String opis, String skračenica) {
        this.img = img;
        this.naziv = naziv;
        this.opis = opis;
        this.skračenica = skračenica;
    }

    public String getNaziv() { return naziv; }
    public String getOpis() { return opis; }
    public String getImg() { return img; }
    public String getSkračenica() { return skračenica; }
}
