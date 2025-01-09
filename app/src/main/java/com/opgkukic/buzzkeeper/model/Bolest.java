package com.opgkukic.buzzkeeper.model;

public class Bolest {
    private String img;
    private String naziv;
    private String uzrok;
    private String zarazno;

    public Bolest() {}

    public Bolest(String img, String naziv, String uzrok, String zarazno) {
        this.img = img;
        this.naziv = naziv;
        this.uzrok = uzrok;
        this.zarazno = zarazno;
    }

    public String getImg() { return img; }
    public String getNaziv() { return naziv; }
    public String getUzrok() { return uzrok; }
    public String getZarazno() { return zarazno; }
}
