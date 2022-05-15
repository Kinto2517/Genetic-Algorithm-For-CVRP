package com.flowerasny.cvrp.Data;

import com.flowerasny.cvrp.Algorithm.Yolcu;
import com.flowerasny.cvrp.Algorithm.Depot;
import com.flowerasny.cvrp.Algorithm.Araclar;

import java.util.List;

public class SehirVeArac {

    private final static Depot baslangicKonumu = new Depot("Umuttepe", 40.824151, 29.9259134);
    private final static Depot baslangicKonumu1 = new Depot("Başiskele", 40.71495046455262, 29.941688470127534);
    private final static Depot baslangicKonumu2 = new Depot("Çayırova", 40.8286176989135, 29.39793387572701);
    private final static Depot baslangicKonumu3 = new Depot("Darıca", 40.77569858837939, 29.37767783324654);
    private final static Depot baslangicKonumu4 = new Depot("Derince", 40.7609035987885, 29.85022723819549);
    private final static Depot baslangicKonumu5 = new Depot("Dilovası", 40.78304288051337, 29.54041277668858);
    private final static Depot baslangicKonumu6 = new Depot("Gebze", 40.79800193051952, 29.450187498288702);
    private final static Depot baslangicKonumu7 = new Depot("Gölcük", 40.712686525190755, 29.84473399031396);
    private final static Depot baslangicKonumu8 = new Depot("Kandıra", 41.06254596888521, 30.15674568391825);
    private final static Depot baslangicKonumu9 = new Depot("Karamürsel", 40.69149108370398, 29.608308371907093);
    private final static Depot baslangicKonumu10 = new Depot("Kartepe", 40.7454357, 30.0112827);
    private final static Depot baslangicKonumu11 = new Depot("Körfez", 40.769497355774206, 29.767342302783042);
    private final static Depot baslangicKonumu12 = new Depot("İzmit", 40.7718818, 29.9497846);

    public final static List<Yolcu> Yolcular = List.of(
            new Yolcu("Başiskele", 40.71495046455262, 29.941688470127534, 10),
            new Yolcu("Çayırova", 40.8286176989135, 29.39793387572701, 10),
            new Yolcu("Darıca", 40.77569858837939, 29.37767783324654, 10),
            new Yolcu("Derince", 40.7609035987885, 29.85022723819549, 5),
            new Yolcu("Dilovası", 40.78304288051337, 29.540412776688587, 10),
            new Yolcu("Gebze", 40.79800193051952, 29.450187498288702, 5),
            new Yolcu("Gölcük", 40.712686525190755, 29.84473399031396, 5),
            new Yolcu("Kandıra", 41.06254596888521, 30.15674568391825, 0),
            new Yolcu("Karamürsel", 40.69149108370398, 29.608308371907093, 10),
            new Yolcu("Kartepe", 40.7454357, 30.0112827, 0),
            new Yolcu("Körfez", 40.769497355774206, 29.767342302783042, 10),
            new Yolcu("İzmit", 40.7718818, 29.9497846, 0)
    );

    public static List<Araclar> aracListesiAl() {
        return List.of(
                new Araclar("25 C", 25, baslangicKonumu, 0),
                new Araclar("30 C", 30, baslangicKonumu, 0),
                new Araclar("40 C", 40, baslangicKonumu, 0)
        );
    }

    public static List<Araclar> aracListesiAl1() {
        return List.of(
                new Araclar("25 C", 25, baslangicKonumu, 0),
                new Araclar("30 C", 30, baslangicKonumu, 0),
                new Araclar("40 C", 40, baslangicKonumu, 0),
                new Araclar("40 C - 2", 40, baslangicKonumu, 0)

        );
    }

    public static List<Araclar> aracListesiAl2() {
        return List.of(
                new Araclar("25 C", 25, baslangicKonumu, 0),
                new Araclar("30 C", 30, baslangicKonumu, 0),
                new Araclar("40 C", 40, baslangicKonumu, 0),
                new Araclar("40 C - 2", 40, baslangicKonumu, 0),
                new Araclar("40 C - 3", 40, baslangicKonumu, 0)

        );
    }

    public static List<Araclar> aracListesiAl3() {
        return List.of(
                new Araclar("25 C", 25, baslangicKonumu, 0),
                new Araclar("30 C", 30, baslangicKonumu, 0),
                new Araclar("40 C", 40, baslangicKonumu, 0),
                new Araclar("40 C - 2", 40, baslangicKonumu, 0),
                new Araclar("40 C - 3", 40, baslangicKonumu, 0),
                new Araclar("40 C - 4", 40, baslangicKonumu, 0)
        );
    }

    public static List<Araclar> aracListesiAl4() {
        return List.of(
                new Araclar("25 C", 25, baslangicKonumu, 0),
                new Araclar("30 C", 30, baslangicKonumu, 0),
                new Araclar("40 C", 40, baslangicKonumu, 0),
                new Araclar("40 C - 2", 40, baslangicKonumu, 0),
                new Araclar("40 C - 3", 40, baslangicKonumu, 0),
                new Araclar("40 C - 4", 40, baslangicKonumu, 0),
                new Araclar("40 C - 5", 40, baslangicKonumu, 0)

        );
    }
}
