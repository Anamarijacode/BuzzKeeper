package com.opgkukic.buzzkeeper.model;

public class Lokacija {
    private double latitude;
    private double longitude;

    // Konstruktor
    public Lokacija(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getteri i setteri (potrebni za Firebase)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Prazan konstruktor (potreban za Firebase)
    public Lokacija() {}
}
