package com.opgkukic.buzzkeeper.model;

public class Lokacija {
    private String latitude;
    private String longitude;

    // Konstruktor
    public Lokacija(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getteri i setteri (potrebni za Firebase)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // Prazan konstruktor (potreban za Firebase)
    public Lokacija() {}
}
