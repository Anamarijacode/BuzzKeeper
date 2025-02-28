package com.opgkukic.buzzkeeper.model;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Enciklopedija {
    private String idEnciklopedija;
    private String naslov;
    private String url;
    private List<String> tags;
    private String sadrzaj;

    public Enciklopedija(String idEnciklopedija, String naslov, String url, List<String> tags, String sadrzaj) {
        this.idEnciklopedija = idEnciklopedija;
        this.naslov = naslov;
        this.url = url;
        this.tags = tags;
        this.sadrzaj = sadrzaj;
    }

    public Enciklopedija(String idEnciklopedija) {
        this.idEnciklopedija = idEnciklopedija;
    }

    public Enciklopedija(String idEnciklopedija,String naslov, List<String> tags, String sadrzaj) {
        this.idEnciklopedija= idEnciklopedija;
        this.naslov = naslov;
        this.tags = tags;
        this.sadrzaj = sadrzaj;
    }


    public String getIdEnciklopedija() {
        return idEnciklopedija;
    }

    public void setIdEnciklopedija(String idEnciklopedija) {
        this.idEnciklopedija = idEnciklopedija;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
}
