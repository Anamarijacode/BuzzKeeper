package com.opgkukic.buzzkeeper.model;

import android.app.Application;

import java.time.format.DateTimeFormatter;

public class User extends Application {
    private String datumKreiranja;
    private String email;
    private String ime;
    private String img;
    private String jezik;
    private String prezime;
    private String spol;
    private String username;


    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public String getEmail()
{
    return email;
}
public String getIme()
{
    return ime;
}
public String getPrezime()
{
    return prezime;
}
    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getJezik() {
        return jezik;
    }

    public void setJezik(String jezik) {
        this.jezik = jezik;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String datumKreiranja, String email, String ime, String img, String jezik, String prezime, String spol, String username) {
        this.datumKreiranja = datumKreiranja;
        this.email = email;
        this.ime = ime;
        this.img = img;
        this.jezik = jezik;
        this.prezime = prezime;
        this.spol = spol;
        this.username = username;
    }


}
