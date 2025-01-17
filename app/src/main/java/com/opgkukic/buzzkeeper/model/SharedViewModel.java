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
}


