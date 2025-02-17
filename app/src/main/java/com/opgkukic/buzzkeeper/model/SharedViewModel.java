package com.opgkukic.buzzkeeper.model;

import android.widget.MultiAutoCompleteTextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel
{
    private MutableLiveData<String> nazivPčelinjaka = new MutableLiveData<>();
    private MutableLiveData<String> datumKreiranja = new MutableLiveData<>();
    private MutableLiveData<String> adresa = new MutableLiveData<>();
    private MutableLiveData<String> latituda = new MutableLiveData<>();
    private MutableLiveData<String> longituda = new MutableLiveData<>();
    private MutableLiveData<String> tipPcelinjaka = new MutableLiveData<>();
    private MutableLiveData<String> tipMjestaPcelinjaka = new MutableLiveData<>();

    public void setNazivPčelinjaka(String nazivPčelinjaka1) {
        nazivPčelinjaka.setValue(nazivPčelinjaka1);
    }

    public LiveData<String> getNazivPčelinjaka() {
        return nazivPčelinjaka;
    }

    public void setDatumKreiranja(String datumKreiranja1) {
        datumKreiranja.setValue(datumKreiranja1);
    }

    public LiveData<String> getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setAdresa(String adresa1) {
        adresa.setValue(adresa1);
    }

    public LiveData<String> getAdresa() {
        return adresa;
    }

    public void setLatituda(String latituda1) {
        latituda.setValue(latituda1);
    }

    public LiveData<String> getLatituda() {
        return latituda;
    }

    public void setLongituda(String longituda1) {
        longituda.setValue(longituda1);
    }

    public LiveData<String> getLongituda() {
        return longituda;
    }

    public void setTipPcelinjaka(String tipPcelinjaka1) {
        tipPcelinjaka.setValue(tipPcelinjaka1);
    }

    public LiveData<String> getTipPcelinjaka() {
        return tipPcelinjaka;
    }

    public void setTipMjestaPcelinjaka(String tipMjestaPcelinjaka1) {
        tipMjestaPcelinjaka.setValue(tipMjestaPcelinjaka1);
    }

    public LiveData<String> getTipMjestaPcelinjaka() {
        return tipMjestaPcelinjaka;
    }
    //KOŠNICE
    private MutableLiveData<String> nazivKosnice = new MutableLiveData<>();
    private MutableLiveData<String> brojKosnice = new MutableLiveData<>();
    private MutableLiveData<String> slika = new MutableLiveData<>();
    private MutableLiveData<String> tipKosnice = new MutableLiveData<>();
    private MutableLiveData<String> brojSanduka = new MutableLiveData<>();
    private MutableLiveData<String> brojOkvira = new MutableLiveData<>();
    private MutableLiveData<String> brojOkviraGrdanjaka = new MutableLiveData<>();
    private MutableLiveData<String> brojoKviraleglka = new MutableLiveData<>();
    private MutableLiveData<String> dbrojokvirapolena = new MutableLiveData<>();
    private MutableLiveData<String> brojokvirameda = new MutableLiveData<>();
    private MutableLiveData<String> maticnaresetka = new MutableLiveData<>();
    private MutableLiveData<String> startostmatice = new MutableLiveData<>();
    private MutableLiveData<String> kolLegla = new MutableLiveData<>();


    public void setNazivPčelinjaka(MutableLiveData<String> nazivPčelinjaka) {
        this.nazivPčelinjaka = nazivPčelinjaka;
    }

    public MutableLiveData<String> getNazivKosnice() {
        return nazivKosnice;
    }

    public void setNazivKosnice(MutableLiveData<String> nazivKosnice) {
        this.nazivKosnice = nazivKosnice;
    }

    public MutableLiveData<String> getBrojKosnice() {
        return brojKosnice;
    }

    public void setBrojKosnice(MutableLiveData<String> brojKosnice) {
        this.brojKosnice = brojKosnice;
    }

    public MutableLiveData<String> getSlika() {
        return slika;
    }

    public void setSlika(MutableLiveData<String> slika) {
        this.slika = slika;
    }

    public MutableLiveData<String> getTipKosnice() {
        return tipKosnice;
    }

    public void setTipKosnice(MutableLiveData<String> tipKosnice) {
        this.tipKosnice = tipKosnice;
    }

    public MutableLiveData<String> getBrojSanduka() {
        return brojSanduka;
    }

    public void setBrojSanduka(MutableLiveData<String> brojSanduka) {
        this.brojSanduka = brojSanduka;
    }

    public MutableLiveData<String> getBrojOkvira() {
        return brojOkvira;
    }

    public void setBrojOkvira(MutableLiveData<String> brojOkvira) {
        this.brojOkvira = brojOkvira;
    }

    public MutableLiveData<String> getBrojOkviraGrdanjaka() {
        return brojOkviraGrdanjaka;
    }

    public void setBrojOkviraGrdanjaka(MutableLiveData<String> brojOkviraGrdanjaka) {
        this.brojOkviraGrdanjaka = brojOkviraGrdanjaka;
    }

    public MutableLiveData<String> getBrojoKviraleglka() {
        return brojoKviraleglka;
    }

    public void setBrojoKviraleglka(MutableLiveData<String> brojoKviraleglka) {
        this.brojoKviraleglka = brojoKviraleglka;
    }

    public MutableLiveData<String> getDbrojokvirapolena() {
        return dbrojokvirapolena;
    }

    public void setDbrojokvirapolena(MutableLiveData<String> dbrojokvirapolena) {
        this.dbrojokvirapolena = dbrojokvirapolena;
    }

    public MutableLiveData<String> getBrojokvirameda() {
        return brojokvirameda;
    }

    public void setBrojokvirameda(MutableLiveData<String> brojokvirameda) {
        this.brojokvirameda = brojokvirameda;
    }

    public MutableLiveData<String> getMaticnaresetka() {
        return maticnaresetka;
    }

    public void setMaticnaresetka(MutableLiveData<String> maticnaresetka) {
        this.maticnaresetka = maticnaresetka;
    }

    public MutableLiveData<String> getStartostmatice() {
        return startostmatice;
    }

    public void setStartostmatice(MutableLiveData<String> startostmatice) {
        this.startostmatice = startostmatice;
    }

    public MutableLiveData<String> getKolLegla() {
        return kolLegla;
    }

    public void setKolLegla(MutableLiveData<String> kolLegla) {
        this.kolLegla = kolLegla;
    }
}


