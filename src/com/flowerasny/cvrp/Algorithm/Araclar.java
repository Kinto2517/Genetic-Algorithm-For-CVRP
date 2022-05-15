package com.flowerasny.cvrp.Algorithm;

import java.util.ArrayList;
import java.util.List;

import static com.flowerasny.cvrp.Hesaplama.UzaklikHesaplaLatlng.distFrom;

public class Araclar {
    public final String aracIsim;
    public final int aracKapasite;
    public int kullanilanKapasite;

    public final List<Ilceler> gidilenYol = new ArrayList<>();

    public Araclar(String aracIsim, int aracKapasite, Depot baslangic, int kullanilanKapasite) {
        this.aracIsim = aracIsim;
        this.aracKapasite = aracKapasite;
        this.kullanilanKapasite = kullanilanKapasite;
        gidilenYol.add(baslangic);
    }

    private Araclar(String aracIsim, int aracKapasite, List<Ilceler> gidilenYol, int kullanilanKapasite) {
        this.aracIsim = aracIsim;
        this.aracKapasite = aracKapasite;
        this.kullanilanKapasite = kullanilanKapasite;
        this.gidilenYol.addAll(gidilenYol);
    }

    boolean yolcuVarMi(Yolcu yolcu) {
        return gidilenYol.contains(yolcu);
    }

    public boolean yolcuyaGidilirMi(Yolcu yolcu) {
        return (aracKapasite - getAracKullanilanKapasite() - yolcu.yolcuSayi) >= 0;
    }

    public void yolcuyuZiyaretEt(Yolcu yolcu) {
        gidilenYol.add(yolcu);
    }

    public int getKullanilanKapasite() {
        return kullanilanKapasite;
    }

    public void setKullanilanKapasite(int a) {
        this.kullanilanKapasite = a;
    }

    public void kapasiteBosalt() {
        gidilenYol.add(gidilenYol.get(0));
    }


    private int getAracKullanilanKapasite() {
        return gidilenYol.stream()
                .filter(ilceler -> ilceler instanceof Yolcu)
                .mapToInt(visitedClient -> ((Yolcu) visitedClient).yolcuSayi)
                .sum();

    }

    double getEuclidianUzaklik() {
        double gidilenKm = 0;

        for (int currentCityIndex = 0, nextCityIndex = 1; nextCityIndex < gidilenYol.size(); currentCityIndex++, nextCityIndex++) {
            Ilceler currentIlceler = gidilenYol.get(currentCityIndex);
            Ilceler nextIlceler = gidilenYol.get(nextCityIndex);
            gidilenKm += distFrom(currentIlceler.latitude, currentIlceler.longitude, nextIlceler.latitude, nextIlceler.longitude);
        }

        return gidilenKm;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + aracIsim + '\'' +
                ", startOpacity=" + aracKapasite +
                ", useOpacity=" + getAracKullanilanKapasite() +
                ", path=" + gidilenYol +
                "}\n";
    }

    void yenidenOptimize(Yolcu yolcu1, Yolcu yolcu2) {
        int client1Index = gidilenYol.indexOf(yolcu1);
        int client2Index = gidilenYol.indexOf(yolcu2);

        gidilenYol.set(client1Index, yolcu2);
        gidilenYol.set(client2Index, yolcu1);
    }

    boolean gaDegisim(Yolcu yolcu1, Yolcu yolcu2) {
        return aracKapasite - (getAracKullanilanKapasite() - yolcu1.yolcuSayi + yolcu2.yolcuSayi) >= 0;
    }

    void replace(Yolcu yolcu1, Yolcu yolcu2) {
        gidilenYol.set(gidilenYol.indexOf(yolcu1), yolcu2);
    }

    Araclar aracKopya() {
        return new Araclar(aracIsim, aracKapasite, gidilenYol, 0);
    }
}
