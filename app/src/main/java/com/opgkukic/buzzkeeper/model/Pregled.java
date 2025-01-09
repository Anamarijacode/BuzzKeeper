package com.opgkukic.buzzkeeper.model;

import java.util.List;

import java.util.List;
import java.util.Map;

public class Pregled {
    private String datumPregleda;
    private String bilješka;
    private List<String> bolestiId;
    private String količinaTrutova;
    private String količinaVaroe;
    private String populacijaKošnice;
    private String trenutnaIspaša;
    private Vrijeme trenutnoVrijeme;
    private String unsoPeluda;
    private String userId;

    // Prazan konstruktor potreban za Firebase
    public Pregled() {}

    // Getteri i setteri za sve atribute
    public String getDatumPregleda() { return datumPregleda; }
    public void setDatumPregleda(String datumPregleda) { this.datumPregleda = datumPregleda; }

    public String getBilješka() { return bilješka; }
    public void setBilješka(String bilješka) { this.bilješka = bilješka; }

    public List<String> getBolestiId() { return bolestiId; }
    public void setBolestiId(List<String> bolestiId) { this.bolestiId = bolestiId; }

    public String getKoličinaTrutova() { return količinaTrutova; }
    public void setKoličinaTrutova(String količinaTrutova) { this.količinaTrutova = količinaTrutova; }

    public String getKoličinaVaroe() { return količinaVaroe; }
    public void setKoličinaVaroe(String količinaVaroe) { this.količinaVaroe = količinaVaroe; }

    public String getPopulacijaKošnice() { return populacijaKošnice; }
    public void setPopulacijaKošnice(String populacijaKošnice) { this.populacijaKošnice = populacijaKošnice; }

    public String getTrenutnaIspaša() { return trenutnaIspaša; }
    public void setTrenutnaIspaša(String trenutnaIspaša) { this.trenutnaIspaša = trenutnaIspaša; }

    public Vrijeme getTrenutnoVrijeme() { return trenutnoVrijeme; }
    public void setTrenutnoVrijeme(Vrijeme trenutnoVrijeme) { this.trenutnoVrijeme = trenutnoVrijeme; }

    public String getUnsoPeluda() { return unsoPeluda; }
    public void setUnsoPeluda(String unsoPeluda) { this.unsoPeluda = unsoPeluda; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    // Inner klasa za TrenutnoVrijeme
    public static class Vrijeme {
        private int temperatura;
        private String vrijeme;

        public Vrijeme() {}

        public int getTemperatura() { return temperatura; }
        public void setTemperatura(int temperatura) { this.temperatura = temperatura; }

        public String getVrijeme() { return vrijeme; }
        public void setVrijeme(String vrijeme) { this.vrijeme = vrijeme; }
    }
}
