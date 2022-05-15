package com.flowerasny.cvrp.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenetigiGelismisYol {
    public final List<Araclar> araclar;

    public GenetigiGelismisYol(List<Araclar> araclar) {
        this.araclar = araclar;
    }

    public double getDistance() {
        return araclar.stream()
                .mapToDouble(Araclar::getEuclidianUzaklik)
                .sum();
    }

    private Yolcu indexYolcu(int index) {
        return tumYolcular().get(index);
    }

    public List<Yolcu> tumYolcular() {
        return tumSehirler().stream()
                .filter(ilceler -> ilceler instanceof Yolcu)
                .map(ilceler -> (Yolcu) ilceler)
                .collect(Collectors.toList());
    }

    private List<Ilceler> tumSehirler() {
        return araclar.stream()
                .map(vehicle -> vehicle.gidilenYol)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public boolean replace(int client1Index, int client2Index) {

        Yolcu yolcu2 = indexYolcu(client2Index);
        return replace(client1Index, yolcu2);
    }

    public boolean replace(int clientIndex, Yolcu yolcu2) {

        Yolcu yolcu1 = indexYolcu(clientIndex);

        Araclar sehir1YolculuArac = araclar.stream()
                .filter(vehicle -> vehicle.yolcuVarMi(yolcu1))
                .findFirst()
                .get();
        Araclar sehir2YolculuArac = araclar.stream()
                .filter(vehicle -> vehicle.yolcuVarMi(yolcu2))
                .findFirst()
                .get();

        if (sehir1YolculuArac == sehir2YolculuArac) {
            sehir1YolculuArac.yenidenOptimize(yolcu1, yolcu2);
            return true;
        } else {
            boolean yolcu1AlirMi = sehir1YolculuArac.gaDegisim(yolcu1, yolcu2);
            boolean yolcu2AlirMi = sehir2YolculuArac.gaDegisim(yolcu2, yolcu1);

            if (yolcu1AlirMi && yolcu2AlirMi) {
                sehir1YolculuArac.replace(yolcu1, yolcu2);
                sehir2YolculuArac.replace(yolcu2, yolcu1);
                return true;
            } else {
                return false;
            }
        }
    }

    public GenetigiGelismisYol copy() {
        List<Araclar> arabaKopya = new ArrayList<>();

        for (Araclar araclar : araclar) {
            arabaKopya.add(araclar.aracKopya());
        }

        return new GenetigiGelismisYol(new ArrayList<>(arabaKopya));
    }

    @Override
    public String toString() {
        return "Vehicles = " + araclar + " Distance = " + getDistance() + "\n";
    }
}
