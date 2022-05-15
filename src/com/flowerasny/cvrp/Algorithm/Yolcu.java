package com.flowerasny.cvrp.Algorithm;

public class Yolcu extends Ilceler {
    public int yolcuSayi;

    public Yolcu(String isim, double longitude, double latitude, int yolcuSayi) {
        super(isim, longitude, latitude);
        this.yolcuSayi = yolcuSayi;
    }

    public int getYolcuSayi() {
        return yolcuSayi;
    }

    public void setYolcuSayi(int yolcuSayi) {
        this.yolcuSayi = yolcuSayi;
    }
}
