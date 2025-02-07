package com.opgkukic.buzzkeeper.model;

import java.util.List;

public class Enciklopedija {
    private String authorId;

    public List<String> getLiteratura() {
        return literatura;
    }

    public void setLiteratura(List<String> literatura) {
        this.literatura = literatura;
    }

    private int idEnciklopedija;
    private String datumKreiranja;
    private String naslov;
    private List<String> sadrzaj;
    private String javno;
    private List<String> literatura;

    public Enciklopedija(){}

    public Enciklopedija(String authorId, int idEnciklopedija, String datumKreiranja, String naslov, List<String> sadrzaj, String javno, List<String> literatura) {
        this.authorId = authorId;
        this.idEnciklopedija = idEnciklopedija;
        this.datumKreiranja = datumKreiranja;
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
        this.javno = javno;
        this.literatura = literatura;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getIdEnciklopedija() {
        return idEnciklopedija;
    }

    public void setIdEnciklopedija(int idEnciklopedija) {
        this.idEnciklopedija = idEnciklopedija;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<String> getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(List<String> sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getJavno() {
        return javno;
    }

    public void setJavno(String javno) {
        this.javno = javno;
    }
}
