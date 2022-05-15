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

public class SinirsizArac {

    private static int abcd = 0;

    private static GenetigiGelismisYol bestSolution;


    public static void main(String[] args) {
        List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);
        List<Araclar> araclar = SehirVeArac.aracListesiAl();

        int b = 0;
        for (int i = 0; i < yolcular.size(); i++) {
            b += yolcular.get(i).yolcuSayi;
        }

        List<GenetigiGelismisYol> anaPop = null;
        if (b <= 95) {
            anaPop = populasyonVeAracInit();
        } else if (b <= 135) {
            anaPop = populasyonVeAracInit2();
        } else if (b <= 175) {
            anaPop = populasyonVeAracInit3();

        } else if (b <= 215) {
            anaPop = populasyonVeAracInit4();
        } else if (b <= 255) {
            anaPop = populasyonVeAracInit5();
        }

        while (!populasyonSinir()) {
            List<GenetigiGelismisYol> geciciPop = reproduction(anaPop);
            List<GenetigiGelismisYol> azalanPop = gaOperate(geciciPop);

            anaPop = basariliGen(geciciPop, azalanPop);

        }

        double kmcost = 0;
        if (bestSolution.araclar.size() > 3) {
            kmcost = bestSolution.getDistance();
            for (int i = 0; i < bestSolution.araclar.size() - 3; i++) {
                kmcost += 40;
            }
        }
        enIyiYazdir(bestSolution);
        System.out.println(kmcost);
    }

    private static List<GenetigiGelismisYol> populasyonVeAracInit() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);
            yolcular.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclar1 = SehirVeArac.aracListesiAl();

            for (int j = 0; j < yolcular.size(); j++) {
                if(yolcular.get(j).getYolcuSayi()==0){
                    yolcular.remove(j);
                }
            }

            while (!yolcular.isEmpty()) {

                Yolcu yolcu = yolcular.get(0);

                int vehicleNumber;
                Araclar araclar;
                do {
                    vehicleNumber = ThreadLocalRandom.current().nextInt(araclar1.size());
                    araclar = araclar1.get(vehicleNumber);
                } while (!araclar.yolcuyaGidilirMi(yolcu));

                araclar.yolcuyuZiyaretEt(yolcu);
                yolcular.remove(0);
            }

            for (Araclar araclar : araclar1) {
                araclar.kapasiteBosalt();
            }
            result.add(new GenetigiGelismisYol(araclar1));
        }

        return result;

    }

    private static List<GenetigiGelismisYol> populasyonVeAracInit2() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);

            yolcular.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclar2 = SehirVeArac.aracListesiAl1();

            for (int j = 0; j < yolcular.size(); j++) {
                if(yolcular.get(yolcular.size()-1).getYolcuSayi()==0){
                    yolcular.remove(yolcular.remove(yolcular.size()-1));
                }
                if(yolcular.size()>3){
                    if(yolcular.get(yolcular.size()-2).getYolcuSayi()==0){
                        yolcular.remove(yolcular.remove(yolcular.size()-2));
                    }
                }
                if(yolcular.size()>4){
                    if(yolcular.get(yolcular.size()-3).getYolcuSayi()==0){
                        yolcular.remove(yolcular.remove(yolcular.size()-3));
                    }
                }
            }



            while (!yolcular.isEmpty()) {
                Yolcu yolcu = yolcular.get(0);

                int vehicleNumber;

                Araclar araclar;

                do {
                    vehicleNumber = ThreadLocalRandom.current().nextInt(araclar2.size());
                    araclar = araclar2.get(vehicleNumber);
                } while (!araclar.yolcuyaGidilirMi(yolcu));

                araclar.yolcuyuZiyaretEt(yolcu);
                yolcular.remove(0);
            }

            for (Araclar araclar : araclar2) {
                araclar.kapasiteBosalt();
            }

            result.add(new GenetigiGelismisYol(araclar2));

        }
        return result;

    }


    private static List<GenetigiGelismisYol> populasyonVeAracInit3() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);

            yolcular.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclar3 = SehirVeArac.aracListesiAl2();


            for (int j = 0; j < yolcular.size(); j++) {
                if(yolcular.get(j).getYolcuSayi()==0){
                    System.out.println(yolcular.get(j).isim);

                    yolcular.remove(j);
                }
            }

            while (!yolcular.isEmpty()) {
                Yolcu yolcu = yolcular.get(0);

                int vehicleNumber;

                Araclar araclar;

                do {
                    vehicleNumber = ThreadLocalRandom.current().nextInt(araclar3.size());
                    araclar = araclar3.get(vehicleNumber);
                } while (!araclar.yolcuyaGidilirMi(yolcu));

                araclar.yolcuyuZiyaretEt(yolcu);
                yolcular.remove(0);
            }

            for (Araclar araclar : araclar3) {
                araclar.kapasiteBosalt();
            }

            result.add(new GenetigiGelismisYol(araclar3));

        }

        return result;

    }

    private static List<GenetigiGelismisYol> populasyonVeAracInit4() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);

            yolcular.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclar4 = SehirVeArac.aracListesiAl3();


            for (int j = 0; j < yolcular.size(); j++) {
                if(yolcular.get(j).getYolcuSayi()==0){
                    yolcular.remove(j);
                }
            }
            while (!yolcular.isEmpty()) {
                Yolcu yolcu = yolcular.get(0);

                int vehicleNumber;

                Araclar araclar;

                do {
                    vehicleNumber = ThreadLocalRandom.current().nextInt(araclar4.size());
                    araclar = araclar4.get(vehicleNumber);
                } while (!araclar.yolcuyaGidilirMi(yolcu));

                araclar.yolcuyuZiyaretEt(yolcu);
                yolcular.remove(0);
            }

            for (Araclar araclar : araclar4) {
                araclar.kapasiteBosalt();
            }

            result.add(new GenetigiGelismisYol(araclar4));

        }

        return result;

    }

    private static List<GenetigiGelismisYol> populasyonVeAracInit5() {
        List<GenetigiGelismisYol> result = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Yolcu> yolcular = new ArrayList<>(SehirVeArac.Yolcular);

            yolcular.sort((client1, client2) -> client2.yolcuSayi - client1.yolcuSayi);
            List<Araclar> araclar5 = SehirVeArac.aracListesiAl4();


            for (int j = 0; j < yolcular.size(); j++) {
                if(yolcular.get(j).getYolcuSayi()==0){
                    System.out.println(yolcular.get(j).isim);
                    yolcular.remove(j);
                }
            }
            while (!yolcular.isEmpty()) {
                Yolcu yolcu = yolcular.get(0);

                int vehicleNumber;

                Araclar araclar;

                do {
                    vehicleNumber = ThreadLocalRandom.current().nextInt(araclar5.size());
                    araclar = araclar5.get(vehicleNumber);
                } while (!araclar.yolcuyaGidilirMi(yolcu));

                araclar.yolcuyuZiyaretEt(yolcu);
                yolcular.remove(0);
            }

            for (Araclar araclar : araclar5) {
                araclar.kapasiteBosalt();
            }

            result.add(new GenetigiGelismisYol(araclar5));

        }

        return result;

    }

    private static List<GenetigiGelismisYol> reproduction(List<GenetigiGelismisYol> genetigiGelismisYols) {
        genetigiGelismisYols.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));
        return genetigiGelismisYols.subList(0, 20);
    }

    private static List<GenetigiGelismisYol> gaOperate(List<GenetigiGelismisYol> temporaryPopulation) {
        return gaMutation(gaCross(temporaryPopulation));
    }

    private static List<GenetigiGelismisYol> gaCross(List<GenetigiGelismisYol> temporaryPopulation) {

        GenetigiGelismisYol genetigiGelismisYol1 = temporaryPopulation.get(0);
        GenetigiGelismisYol genetigiGelismisYol2 = temporaryPopulation.get(1);

        List<GenetigiGelismisYol> result = new ArrayList<>();
        int allClients = genetigiGelismisYol1.tumYolcular().size();
        int bound = allClients - 1;
        int firstCrossingPoint = ThreadLocalRandom.current().nextInt(bound);
        int origin = firstCrossingPoint + 1;
        int secondCrossingPoint = ThreadLocalRandom.current().nextInt(origin, allClients);

        List<GenetigiGelismisYol> crossed = gaCrossover(genetigiGelismisYol1, genetigiGelismisYol2, firstCrossingPoint, secondCrossingPoint);

        for (int i = 0; i < 5; i++) {
            result.addAll(crossed);
        }

        return result;
    }

    private static List<GenetigiGelismisYol> gaCrossover(GenetigiGelismisYol genetigiGelismisYol1, GenetigiGelismisYol genetigiGelismisYol2, int start, int end) {

        GenetigiGelismisYol child1 = genetigiGelismisYol1.copy();
        GenetigiGelismisYol child2 = genetigiGelismisYol2.copy();

        for (int index = start; index < end; index++) {
            child1.replace(index, genetigiGelismisYol2.tumYolcular().get(index));
            child2.replace(index, genetigiGelismisYol1.tumYolcular().get(index));
        }

        return Arrays.asList(child1, child2);
    }

    private static List<GenetigiGelismisYol> gaMutation(List<GenetigiGelismisYol> temporaryPopulation) {
        return temporaryPopulation.stream()
                .map(SinirsizArac::gaMutateChromosome)
                .collect(Collectors.toList());
    }

    private static GenetigiGelismisYol gaMutateChromosome(GenetigiGelismisYol genetigiGelismisYol) {
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

    private static List<GenetigiGelismisYol> basariliGen(List<GenetigiGelismisYol> temporaryPopulation, List<GenetigiGelismisYol> descendantPopulation) {
        temporaryPopulation.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));
        descendantPopulation.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));

        List<GenetigiGelismisYol> newBasePopulation = new ArrayList<>();
        newBasePopulation.addAll(temporaryPopulation.subList(0, 10));
        newBasePopulation.addAll(descendantPopulation.subList(0, 10));

        newBasePopulation.sort(Comparator.comparingDouble(GenetigiGelismisYol::getDistance));

        GenetigiGelismisYol bestSolutionInIteration = newBasePopulation.get(0);

        if (bestSolution == null || bestSolutionInIteration.getDistance() < bestSolution.getDistance()) {
            bestSolution = bestSolutionInIteration;
            abcd = 0;
        } else {
            abcd++;
        }

        System.out.println("BEST SOLUTION    -->      " + bestSolution.getDistance() + "           " + abcd);
        return newBasePopulation;
    }

    private static void enIyiYazdir(GenetigiGelismisYol bestSolution) {

        System.out.println("<--FINISHED WITH-->");
        System.out.println(bestSolution);

    }

    private static boolean populasyonSinir() {
        return abcd >= 5000;
    }
}
