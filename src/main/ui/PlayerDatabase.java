package ui;

import model.*;

import java.util.LinkedList;
import java.util.List;

public class PlayerDatabase {
    public static final List<Player> QUARTERBACKS = new LinkedList<>();
//    public static final List<Player> LIST_OF_ALL_PLAYERS = new LinkedList<>();

    static {
        Quarterback mahomes = new Quarterback("Patrick Mahomes", 15);
        Quarterback russellWilson = new Quarterback("Russell Wilson", 3);
        Quarterback lamar = new Quarterback("Lamar Jackson", 8);
        Quarterback aaronRodgers = new Quarterback("Aaron Rodgers", 12);
        Quarterback joeBurrow = new Quarterback("Joe Burrow", 9);
        Quarterback blondey = new Quarterback("Trevor Lawrence", 16);
        Quarterback tomBrady = new Quarterback("Tom Brady", 12);
        Quarterback justinFields = new Quarterback("Justin Fields", 1);
        Quarterback justinHerbert = new Quarterback("Justin Herbert", 10);
        Quarterback tua = new Quarterback("Tua Tagovailoa", 1);
        Quarterback drewLock = new Quarterback("Jalen Hurts", 2);
        Quarterback joshAllen = new Quarterback("Josh Allen", 17);

        QUARTERBACKS.add(mahomes);
        QUARTERBACKS.add(russellWilson);
        QUARTERBACKS.add(lamar);
        QUARTERBACKS.add(aaronRodgers);
        QUARTERBACKS.add(joeBurrow);
        QUARTERBACKS.add(blondey);
        QUARTERBACKS.add(tomBrady);
        QUARTERBACKS.add(justinFields);
        QUARTERBACKS.add(justinFields);
        QUARTERBACKS.add(justinHerbert);
        QUARTERBACKS.add(tua);
        QUARTERBACKS.add(drewLock);
        QUARTERBACKS.add(joshAllen);
    }

//    static {
//        RunningBack saquon = new RunningBack("Saquon Barkley", 26);
//        RunningBack zeke = new RunningBack("Ezekiel Elliot", 21);
//        RunningBack christianMcCaffrey = new RunningBack("Christian McCraffey", 22);
//        RunningBack nickChubb = new RunningBack("Nick Chubb", 24);
//        RunningBack alvinKamara = new RunningBack("Alvin Kamara", 41);
//    }

//    //RunningBacks
//    RunningBack saquon = new RunningBack("Saquon Barkley", 26);
//    RunningBack zeke = new RunningBack("Ezekiel Elliot", 21);
//    RunningBack christianMcCaffrey = new RunningBack("Christian McCraffey", 22);
//    RunningBack nickChubb = new RunningBack("Nick Chubb", 24);
//    RunningBack alvinKamara = new RunningBack("Alvin Kamara", 41);
//
//    //WideReceiver
//    WideReceiver obj = new WideReceiver("Odell Beckham Jr.", 13);
//    WideReceiver dhop = new WideReceiver("DeAndre Hopkins", 10);
//    WideReceiver slantBoy = new WideReceiver("Michael Thomas", 13);
//    WideReceiver julioJones = new WideReceiver("Julio Jones", 11);
//    WideReceiver devante = new WideReceiver("Devante Adams", 17);

}



