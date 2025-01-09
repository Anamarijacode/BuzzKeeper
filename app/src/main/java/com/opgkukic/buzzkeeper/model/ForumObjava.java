package com.opgkukic.buzzkeeper.model;

public class ForumObjava {
    private String autorId;
    private int brojDisLikova;
    private int brojLikova;
    private String datumKreiranja;
    private String nazivObjvae;
    private String sadržajObjave;

    public ForumObjava() {}

    public String getNazivObjvae() { return nazivObjvae; }
    public String getSadržajObjave() { return sadržajObjave; }
    public String getAutorId() { return autorId; }
    public String getDatumKreiranja() { return datumKreiranja; }
}
