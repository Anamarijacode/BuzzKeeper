package com.opgkukic.buzzkeeper.model;

public class Košnica {
    private String brojOkvira;
    private String brojOkviraGradnjaka;
    private String brojOkviraLegla;
    private String brojOkviraMeda;
    private String brojSanduka;
    private String datumKreiranja;
    private String količinaLegla;
    private boolean matičnaRešetka;
    private String nazivKošnice;
    private String starostMatice;
    private String vrstaKošnice;

    public Košnica() {}

    public String getNazivKošnice() { return nazivKošnice; }
    public String getVrstaKošnice() { return vrstaKošnice; }
    public String getBrojOkvira() { return brojOkvira; }
    public String getDatumKreiranja() { return datumKreiranja; }
}
