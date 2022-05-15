package com.flowerasny.cvrp;

import com.flowerasny.cvrp.Data.SehirVeArac;
import com.flowerasny.cvrp.Algorithm.Yolcu;
import com.flowerasny.cvrp.Algorithm.GenetigiGelismisYol;
import com.flowerasny.cvrp.Algorithm.Araclar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class BelirliArac {

    private static int abcd = 0;

    private static GenetigiGelismisYol enIyiYol;


    public static void main(String[] args) {

        List<GenetigiGelismisYol> anaPop = populasyonuDoldur();

        while (!populasyonSinir()) {
            List<GenetigiGelismisYol> geciciPop = reproduction(anaPop);
            List<GenetigiGelismisYol> azalanPop = makeGeneticOperations(geciciPop);

            anaPop = succession(geciciPop, azalanPop);

        }

        double kmcost = enIyiYol.getDistance() / 10.0;

        enIyiCozum(enIyiYol);
        System.out.println(kmcost);
    }

    private static List<GenetigiGelismisYol> populasyonuDoldur() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {

            List<Yolcu> yolcus = new ArrayList<>(SehirVeArac.Yolcular);
            yolcus.get(0).setYolcuSayi(39);
            yolcus.get(1).setYolcuSayi(0);
            yolcus.get(2).setYolcuSayi(0);

            yolcus.get(3).setYolcuSayi(0);
            yolcus.get(4).setYolcuSayi(0);
            yolcus.get(5).setYolcuSayi(30);

            yolcus.get(6).setYolcuSayi(30);
            yolcus.get(7).setYolcuSayi(0);
            yolcus.get(8).setYolcuSayi(0);

            yolcus.get(9).setYolcuSayi(0);
            yolcus.get(10).setYolcuSayi(10);
            yolcus.get(11).setYolcuSayi(0);
            yolcus.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclars = SehirVeArac.aracListesiAl();

            System.out.println("ALLAHINI SİKYİM");
            System.out.println(yolcus.get(0).getYolcuSayi());
            int whichV = 0;
            while (!yolcus.isEmpty()) {

                System.out.println(yolcus);
                Yolcu yolcu = yolcus.get(0);
                if (yolcu.getYolcuSayi() == 0 && yolcus.size() > 1) {
                    yolcu = yolcus.get(1);
                }else{
                    yolcu = yolcus.get(0);
                }

                Araclar araclar;

                if (whichV % 3 == 0) {
                    araclar = araclars.get(2);
                } else if (whichV % 3 == 1) {
                    araclar = araclars.get(1);
                } else {
                    araclar = araclars.get(0);
                }
                whichV += 1;

                boolean sehirSilindiMi = false;
                while (!sehirSilindiMi) {
                    if (yolcu.yolcuSayi == 0) {
                        sehirSilindiMi = true;
                    } else {
                        if (yolcu.getYolcuSayi() + araclar.getKullanilanKapasite() > araclar.aracKapasite) {

                            araclar.gidilenYol.add(yolcu);
                            yolcu.setYolcuSayi((yolcu.getYolcuSayi() - (araclar.aracKapasite - araclar.getKullanilanKapasite())));
                            araclar.kapasiteBosalt();
                            araclar.setKullanilanKapasite(0);

                        } else if (yolcu.getYolcuSayi() + araclar.getKullanilanKapasite() == araclar.aracKapasite) {

                            araclar.gidilenYol.add(yolcu);
                            araclar.kapasiteBosalt();
                            araclar.setKullanilanKapasite(0);
                            yolcu.setYolcuSayi(0);
                            if (yolcu.getYolcuSayi() == 0) {
                                sehirSilindiMi = true;
                            }

                        } else {
                            araclar.gidilenYol.add(yolcu);
                            araclar.setKullanilanKapasite(yolcu.getYolcuSayi() + araclar.getKullanilanKapasite());
                            yolcu.setYolcuSayi(0);

                            if (yolcu.getYolcuSayi() == 0) {
                                sehirSilindiMi = true;
                            }
                        }
                    }
                }
                if (yolcus.get(0).getYolcuSayi() == 0) {
                    yolcus.remove(0);
                }

            }

            for (Araclar araclar : araclars) {
                if (araclar.gidilenYol.get(araclar.gidilenYol.size() - 1).isim == "Umuttepe") {
                    continue;
                } else
                    araclar.kapasiteBosalt();
            }
            result.add(new GenetigiGelismisYol(araclars));
        }

        System.out.println(result);
        return result;

    }

    private static List<GenetigiGelismisYol> reproduction(List<GenetigiGelismisYol> genetigiGelismisYols) {
        genetigiGelismisYols.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));
        return genetigiGelismisYols.subList(0, 20);
    }

    private static List<GenetigiGelismisYol> makeGeneticOperations(List<GenetigiGelismisYol> temporaryPopulation) {
        return mutation(crossing(temporaryPopulation));
    }

    private static List<GenetigiGelismisYol> crossing(List<GenetigiGelismisYol> temporaryPopulation) {

        GenetigiGelismisYol genetigiGelismisYol1 = temporaryPopulation.get(0);
        GenetigiGelismisYol genetigiGelismisYol2 = temporaryPopulation.get(1);

        List<GenetigiGelismisYol> result = new ArrayList<>();
        int allClients = genetigiGelismisYol1.tumYolcular().size();
        int bound = allClients - 1;
        int firstCrossingPoint = ThreadLocalRandom.current().nextInt(bound);
        int origin = firstCrossingPoint + 1;
        int secondCrossingPoint = ThreadLocalRandom.current().nextInt(origin, allClients);

        List<GenetigiGelismisYol> crossed = cross(genetigiGelismisYol1, genetigiGelismisYol2, firstCrossingPoint, secondCrossingPoint);

        for (int i = 0; i < 5; i++) {
            result.addAll(crossed);
        }

        return result;
    }

    private static List<GenetigiGelismisYol> cross(GenetigiGelismisYol genetigiGelismisYol1, GenetigiGelismisYol genetigiGelismisYol2, int start, int end) {

        GenetigiGelismisYol child1 = genetigiGelismisYol1.copy();
        GenetigiGelismisYol child2 = genetigiGelismisYol2.copy();

        for (int index = start; index < end; index++) {
            child1.replace(index, genetigiGelismisYol2.tumYolcular().get(index));
            child2.replace(index, genetigiGelismisYol1.tumYolcular().get(index));
        }

        return Arrays.asList(child1, child2);
    }

    private static List<GenetigiGelismisYol> mutation(List<GenetigiGelismisYol> temporaryPopulation) {
        return temporaryPopulation.stream()
                .map(BelirliArac::mutate)
                .collect(Collectors.toList());
    }

    private static GenetigiGelismisYol mutate(GenetigiGelismisYol genetigiGelismisYol) {
        GenetigiGelismisYol newGenetigiGelismisYol = genetigiGelismisYol.copy();
        int firstItemIndex;
        int secondItemIndex;
        int maxIterations = 10;
        do {
            maxIterations--;
            int allClients = newGenetigiGelismisYol.tumYolcular().size();
            int bound = allClients - 1;
            firstItemIndex = ThreadLocalRandom.current().nextInt(bound);
            int origin = firstItemIndex + 1;
            secondItemIndex = ThreadLocalRandom.current().nextInt(origin, allClients);
        } while (!genetigiGelismisYol.replace(firstItemIndex, secondItemIndex) && maxIterations > 0);

        return newGenetigiGelismisYol;

    }

    private static List<GenetigiGelismisYol> succession(List<GenetigiGelismisYol> geciciPop, List<GenetigiGelismisYol> azalanPop) {
        geciciPop.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));
        azalanPop.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));

        List<GenetigiGelismisYol> yeniPop = new ArrayList<>();
        yeniPop.addAll(geciciPop.subList(0, 10));
        yeniPop.addAll(azalanPop.subList(0, 10));

        yeniPop.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));

        GenetigiGelismisYol enIyiYolIter = yeniPop.get(0);

        if (enIyiYol == null || enIyiYolIter.getDistance() < enIyiYol.getDistance()) {
            enIyiYol = enIyiYolIter;
            abcd = 0;
        } else {
            abcd++;
        }
        
        return yeniPop;
    }

    private static void enIyiCozum(GenetigiGelismisYol enIyiYol) {

        System.out.println("\n\n");
        System.out.println(enIyiYol);

    }

    private static boolean populasyonSinir() {
        return abcd >= 3000;
    }
}
