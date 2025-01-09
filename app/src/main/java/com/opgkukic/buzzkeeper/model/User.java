package com.opgkukic.buzzkeeper.model;

public class User {
    private String datumKreiranja;
    private String email;
    private String ime;
    private String prezime;
    private String username;
    private String role;

    public User() {}

    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
