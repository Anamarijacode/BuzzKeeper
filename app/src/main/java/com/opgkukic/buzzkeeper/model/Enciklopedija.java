package com.opgkukic.buzzkeeper.model;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Enciklopedija {
    private String authorId;
    private int idEnciklopedija;
    private String datumKreiranja;
    private String naslov;
    private List<String> sadrzaj;
    private String javno;
    private List<String> literatura;
    private List<String> tags;

    public Enciklopedija() {
        this.sadrzaj = new ArrayList<>();
        this.literatura = new ArrayList<>();
    }

    public Enciklopedija(String authorId, int idEnciklopedija, String datumKreiranja, String naslov,
                         List<String> sadrzaj, String javno, List<String> literatura, List<String> tags) {
        this.authorId = authorId;
        this.idEnciklopedija = idEnciklopedija;
        this.datumKreiranja = datumKreiranja;
        this.naslov = naslov;
        this.sadrzaj = (sadrzaj != null) ? sadrzaj : new ArrayList<>();
        this.javno = javno;
        this.literatura = (literatura != null) ? literatura : new ArrayList<>();
        this.tags = (tags != null) ? tags : new ArrayList<>();
    }
    public Enciklopedija(String naslov, List<String> sadrzaj, List<String> tags) {
        this.naslov = naslov;
        this.sadrzaj = (sadrzaj != null) ? sadrzaj : new ArrayList<>();
        this.tags = (tags != null) ? tags : new ArrayList<>();
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
        return (sadrzaj != null) ? sadrzaj : new ArrayList<>(); // Prevent null
    }

    public void setSadrzaj(List<String> sadrzaj) {
        this.sadrzaj = (sadrzaj != null) ? sadrzaj : new ArrayList<>();
    }

    public String getJavno() {
        return javno;
    }

    public void setJavno(String javno) {
        this.javno = javno;
    }

    public List<String> getLiteratura() {
        return (literatura != null) ? literatura : new ArrayList<>();
    }

    public void setLiteratura(List<String> literatura) {
        this.literatura = (literatura != null) ? literatura : new ArrayList<>();
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
