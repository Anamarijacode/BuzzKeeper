package com.opgkukic.buzzkeeper.model;

public class Košnica {
    private String nazivKosnice;
    private String vrstaKosnice;
    private int brojSanduka;
    private int brojOkvira;
    private int brojOkviraGradnjaka;
    private int brojOkviraLegla;
    private int brojOkviraMeda;
    private String starostMatice;
    private boolean maticnaResetka;
    private String datumKreiranja;
    private String userId;

    public Košnica() {}

    public Košnica(String nazivKosnice, String vrstaKosnice, int brojSanduka, int brojOkvira,
                   int brojOkviraGradnjaka, int brojOkviraLegla, int brojOkviraMeda,
                   String starostMatice, boolean maticnaResetka, String datumKreiranja, String userId) {
        this.nazivKosnice = nazivKosnice;
        this.vrstaKosnice = vrstaKosnice;
        this.brojSanduka = brojSanduka;
        this.brojOkvira = brojOkvira;
        this.brojOkviraGradnjaka = brojOkviraGradnjaka;
        this.brojOkviraLegla = brojOkviraLegla;
        this.brojOkviraMeda = brojOkviraMeda;
        this.starostMatice = starostMatice;
        this.maticnaResetka = maticnaResetka;
        this.datumKreiranja = datumKreiranja;
        this.userId = userId;
    }

    // Getteri i setteri
    public String getNazivKosnice() { return nazivKosnice; }
    public String getVrstaKosnice() { return vrstaKosnice; }
    public int getBrojSanduka() { return brojSanduka; }
    public int getBrojOkvira() { return brojOkvira; }
    public int getBrojOkviraGradnjaka() { return brojOkviraGradnjaka; }
    public int getBrojOkviraLegla() { return brojOkviraLegla; }
    public int getBrojOkviraMeda() { return brojOkviraMeda; }
    public String getStarostMatice() { return starostMatice; }
    public boolean isMaticnaResetka() { return maticnaResetka; }
    public String getDatumKreiranja() { return datumKreiranja; }
    public String getUserId() { return userId; }
}
