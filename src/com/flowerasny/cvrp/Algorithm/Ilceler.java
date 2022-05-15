package com.flowerasny.cvrp.Algorithm;

abstract public class Ilceler {
    public final String isim;
    public final double longitude;
    public final double latitude;

    Ilceler(String isim, double longitude, double latitude) {
        this.isim = isim;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return isim;
    }
}