package com.andreafilice.lavorami;

import android.content.Context;

import com.andreafilice.lavorami.stationsDB.MetroStationsDB;
import com.andreafilice.lavorami.stationsDB.SuburbanStationsDB;
import com.andreafilice.lavorami.stationsDB.RegionalStationsDB;
import com.andreafilice.lavorami.stationsDB.RegioExpStationsDB;
import com.andreafilice.lavorami.stationsDB.TILOStationsDB;
import com.andreafilice.lavorami.stationsDB.MPXStationsDB;
import com.andreafilice.lavorami.stationsDB.TramStationsDB;
import com.andreafilice.lavorami.stationsDB.BusStationsDB;
import com.andreafilice.lavorami.stationsDB.FilobusStationsDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StationDB {
    private static List<MetroStation> CACHED_STATIONS = null;

    public static List<InterchangeInfo> getBusInterchanges() {
        List<InterchangeInfo> interchanges = new ArrayList<>();
        interchanges.add(new InterchangeInfo(
                "Molino Dorino MM",
                new String[]{"M1", "NM1", "35", "69", "80", "424", "528", "z601", "z617", "z620", "z621", "z649"},
                new String[] {"z601", "z617", "z620", "z621", "z649"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Milano Cadorna FN",
                new String[]{"M1", "NM1", "M2", "NM2", "S3", "S4", "R22", "R27", "RE1", "RE7", "MXP2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"},
                new String[]{"z602", "z603", "z6C3"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Parabiago",
                new String[]{"z611", "z644", "z643"},
                new String[]{"z644", "z643"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Rho FS",
                new String[]{"S5", "S6", "S11", "z616", "z618"},
                new String[]{"z616", "z618"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Busto Garolfo",
                new String[]{"z625", "z627", "z644", "z649"},
                new String[]{"z625"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Legnano",
                new String[]{"z601", "z602", "z611", "z612", "z627", "z636", "z642"},
                new String[]{"z611", "z612", "z642", "z627", "z636"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Bisceglie MM",
                new String[]{"M1", "NM1", "47", "58", "63", "76", "78", "321", "322", "323", "327", "433", "z551", "z560"},
                new String[]{"z551", "z560"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Romolo FS",
                new String[]{"M2", "NM2", "S9", "S19", "R31", "47", "Filobus 90", "Filobus 91", "71", "324", "325", "z553"},
                new String[] {"z553"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Santo Stefano Ticino - Corbetta",
                new String[]{"S6"},
                new String[] {"z552"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Magenta FS",
                new String[]{"S6", "RV", "z559", "z641", "z646"},
                new String[]{"z641", "z646", "z559"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Abbiategrasso V. Veneto",
                new String[]{"z551", "z552", "z553", "z555", "z556", "z560"},
                new String[] {"z555", "z556"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Melzo FS",
                new String[]{"R4", "S5", "S6", "z401", "z404", "z411"},
                new String[] {"z401", "z404", "z411"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Pioltello Limito FS",
                new String[]{"R4", "RE2", "RE6", "S5", "S6", "z402"},
                new String[] {"z402"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Gorgonzola M2",
                new String[]{"M2", "z403", "z407", "z419"},
                new String[] {"z403", "z407", "z419"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Gessate M2",
                new String[]{"M2", "z404", "z405", "z406"},
                new String[] {"z404", "z405", "z406"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Linate Aereoporto",
                new String[]{"M4", "NM4", "183", "901", "903", "923", "973", "z409"},
                new String[] {"z409"},
                "airplane.departure"
        ));

        interchanges.add(new InterchangeInfo(
                "San Donato M3",
                new String[]{"M3", "NM3", "45", "77", "121", "130", "132", "901", "902", "903", "z410", "z411", "z412", "z413", "z415", "z420"},
                new String[] {"z410", "z412", "z413", "z415", "z420"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Melegnano FS",
                new String[]{"REG", "S1", "S12", "z431", "z432"},
                new String[] {"z431", "z432"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Monza FS",
                new String[]{"R7", "R13", "R14", "RE8", "RE80", "S7", "S8", "S9", "S11", "z203", "z205", "z219", "z221", "z222", "z228"},
                new String[] {"z203", "z205", "z219", "z221", "z222", "z228"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Sesto San Giovanni FS M1",
                new String[]{"M1", "R13", "R14", "RE8", "S7", "S8", "S9", "S11", "z221", "z222", "z225"},
                new String[] {"z225"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Seregno FS",
                new String[]{"RE80", "S9", "S11", "z231", "z232", "z233", "z242"},
                new String[] {"z231", "z232", "z233", "z242"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Desio FS",
                new String[]{"RE80", "S9", "S11", "z250", "z251"},
                new String[] {"z250", "z251"},
                "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
                "Famagosta M2",
                new String[] {"M2", "NM2", "46", "59", "71", "74", "95", "98", "z501", "z509", "z510", "z515", "z516"},
                new String[]{"z501", "z509", "z510", "z515", "z516"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
           "Rho, Corso Europa",
           new String[] {"S5", "S6", "S11", "z601", "z606", "z618"},
            new String[]{"z606"},
           "train.side.front.car"
        ));
        return interchanges;
    }

    public static List<InterchangeInfo> getInterchangesTrams() {
        return Arrays.asList(
            new InterchangeInfo("Certosa FS", new String[]{"1", "12", "RE13", "S5", "S6", "S11"}, "lightrail"),
            new InterchangeInfo("Piazza Firenze", new String[]{"1", "14", "19"}, "tram.fill"),
            new InterchangeInfo("Domodossola FN", new String[]{"M5", "1", "19", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "S3", "S4"}, "lightrail"),
            new InterchangeInfo("Cadorna FN", new String[]{"M1", "M2", "1", "2", "R16", "R17", "R22", "R27", "RE1", "RE7", "S3", "S4", "MXP1"}, "lightrail"),
            new InterchangeInfo("Cairoli M1", new String[]{"M1", "1", "2", "4"}, "tram.fill.tunnel"),
            new InterchangeInfo("Cordusio M1", new String[]{"M1", "1", "12", "14", "16", "19"}, "tram.fill.tunnel"),
            new InterchangeInfo("Montenapoleone M3", new String[]{"M3", "1"}, "tram.fill.tunnel"),
            new InterchangeInfo("Turati M3", new String[]{"M3", "1"}, "tram.fill.tunnel"),
            new InterchangeInfo("Repubblica M3", new String[]{"M3", "1", "9", "33", "S1", "S2", "S5", "S6", "S12", "S13"}, "lightrail"),
            new InterchangeInfo("V.Le Tunisia", new String[]{"1", "5", "33"}, "tram.fill"),
            new InterchangeInfo("Caiazzo M2", new String[]{"M2", "1"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Genova M2", new String[]{"M2", "2", "3", "9", "10"}, "tram.fill.tunnel"),
            new InterchangeInfo("V.Le Coni Zugna Via Solari", new String[]{"2", "10", "14"}, "tram.fill"),
            new InterchangeInfo("Conciliazione M1", new String[]{"M1", "2", "10"}, "tram.fill"),
            new InterchangeInfo("Largo Quinto Alpini", new String[]{"1", "2", "10", "19"}, "tram.fill"),
            new InterchangeInfo("Lanza M2", new String[]{"M2", "2", "4", "12", "14"}, "tram.fill.tunnel"),
            new InterchangeInfo("Via Farini Via Ferrari", new String[]{"2", "4", "10", "33"}, "tram.fill"),
            new InterchangeInfo("Piazza Abbiategrasso M2", new String[]{"M2", "3", "15"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Le Cantore", new String[]{"2", "3", "9", "10", "14"}, "tram.fill"),
            new InterchangeInfo("Maciachini M3", new String[]{"M3", "4"}, "tram.fill.tunnel"),
            new InterchangeInfo("Ca' Granda M5", new String[]{"M5", "5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Bicocca M5", new String[]{"M5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Ponale M5", new String[]{"M5","7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Bignami M5", new String[]{"M5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Istria M5", new String[]{"M5", "5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Marche M5", new String[]{"M5", "5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Centrale FS", new String[]{"M2", "M3", "5", "9", "10", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "RE80", "MXP1"}, "lightrail"),
            new InterchangeInfo("Zara M3 M5", new String[]{"M3", "M5", "7", "31"}, "tram.fill.tunnel"),
            new InterchangeInfo("Precotto M1", new String[]{"M1", "7"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Romana M3", new String[]{"M3", "9"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Venezia M1", new String[]{"M1", "9", "S1", "S2", "S5", "S6", "S12", "S13"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Venezia V.Le Tunisia", new String[]{"M1", "5", "33"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Za Otto Novembre", new String[]{"5", "33", "S1", "S2", "S5", "S6", "S12", "S13"}, "lightrail"),
            new InterchangeInfo("Piazza Ascoli", new String[]{"5", "19", "33"}, "lightrail"),
            new InterchangeInfo("Garibaldi FS", new String[]{"M2", "M5", "10", "33", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "MXP1", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13"}, "lightrail"),
            new InterchangeInfo("Monumentale M5", new String[]{"M5", "10"}, "tram.fill.tunnel"),
            new InterchangeInfo("Coni Zugna M4", new String[]{"M4", "10"}, "tram.fill.tunnel"),
            new InterchangeInfo("Repetti M4", new String[]{"M4", "12", "27"}, "tram.fill.tunnel"),
            new InterchangeInfo("Stazione Forlanini FS", new String[]{"M4", "12", "27", "R38", "RE8", "RE13", "S5", "S6", "S9"}, "lightrail"),
            new InterchangeInfo("Piazza Cinque Giornate", new String[]{"9", "12", "19", "27"}, "tram.fill"),
            new InterchangeInfo("Missori M3", new String[]{"M3", "12", "15", "16", "19", "24"}, "tram.fill.tunnel"),
            new InterchangeInfo("Duomo M1 M3", new String[]{"M1", "M3", "2", "3", "12", "14", "15", "16", "19"}, "tram.fill.tunnel"),
            new InterchangeInfo("Cenisio M5", new String[]{"M5", "12", "14"}, "tram.fill.tunnel"),
            new InterchangeInfo("Santa Sofia M4", new String[]{"M4", "15"}, "tram.fill.tunnel"),
            new InterchangeInfo("S. Siro Stadio M5", new String[]{"M5", "16"}, "tram.fill.tunnel"),
            new InterchangeInfo("S. Siro Ippodromo M5", new String[]{"M5", "16"}, "tram.fill.tunnel"),
            new InterchangeInfo("De Angeli M1", new String[]{"M1", "16"}, "tram.fill.tunnel"),
            new InterchangeInfo("Sforza - Policlinico M4", new String[]{"M4", "16", "24"}, "tram.fill.tunnel"),
            new InterchangeInfo("Crocetta M3", new String[]{"M3", "16", "24"}, "tram.fill.tunnel"),
            new InterchangeInfo("Lambrate FS", new String[]{"M2", "19", "R4", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "S9"}, "lightrail"),
            new InterchangeInfo("Via Larga", new String[]{"12", "19", "24"}, "tram.fill"),
            new InterchangeInfo("P.Le Lagosta", new String[]{"7", "31", "33"}, "tram.fill"),
            new InterchangeInfo("Porta Romana M3", new String[]{"M3", "9"}, "tram.fill.tunnel"),
            new InterchangeInfo("Tricolore M4", new String[]{"M4", "9", "19"}, "tram.fill.tunnel")
        );
    }

    public static List<InterchangeInfo> getInterchangesFilobus() {
        return Arrays.asList(
            new InterchangeInfo("Lodi M3", new String[]{"M3", "NM3", "90", "91", "92"}, "tram.fill.tunnel"),
            new InterchangeInfo("V.Le Isonzo Via Ripamonti", new String[]{"24", "90", "91", "N24"}, "tram.fill"),
            new InterchangeInfo("Tibaldi", new String[]{"S9", "S19", "R31", "15", "59", "90", "91"}, "lightrail"),
            new InterchangeInfo("V.Le Tibaldi Via Meda", new String[]{"3", "59", "71", "90", "91", "N15"}, "tram.fill"),
            new InterchangeInfo("Romolo M2", new String[]{"M2", "NM2", "S9", "S19", "R31", "47", "71", "90", "91", "324", "325", "z553"}, "tram.fill.tunnel"),
            new InterchangeInfo("Ponte Guido Crepax", new String[]{"2", "90", "91", "324", "325"}, "tram.fill"),
            new InterchangeInfo("Bolivar M4", new String[]{"M4", "NM4", "58", "90", "91"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Le Brescia", new String[]{"16", "90", "91"}, "tram.fill"),
            new InterchangeInfo("Lotto M1 M5", new String[]{"M1", "NM1", "M5", "48", "49", "68", "78", "90", "91", "98"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Le Cuoco", new String[]{"16", "90", "91", "93"}, "tram.fill"),
            new InterchangeInfo("Porta Vittoria FS", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "90", "91", "93"}, "lightrail"),
            new InterchangeInfo("V.Le Campania V.Le Corsica", new String[]{"12", "27", "90", "91", "93", "973", "N27"}, "tram.fill"),
            new InterchangeInfo("Susa M4", new String[]{"M4", "NM4", "38", "54", "90", "91"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Za Ferravilla", new String[]{"5", "90", "91"}, "tram.fill"),
            new InterchangeInfo("V.Le Romagna Via Pascoli", new String[]{"19", "33", "62", "90", "91"}, "tram.fill"),
            new InterchangeInfo("V.Le Romagna Piola M2", new String[]{"M2", "NM2", "39", "62", "90", "91"}, "tram.fill.tunnel"),
            new InterchangeInfo("Loreto M1 M2", new String[]{"M1", "NM1", "M2", "NM2", "39", "55", "56", "90", "91", "174"}, "tram.fill.tunnel"),
            new InterchangeInfo("Caiazzo M2", new String[]{"M2", "NM2", "1", "90", "91", "92"}, "tram.fill.tunnel"),
            new InterchangeInfo("Stazione Centrale Via Tonale", new String[]{"5", "10", "42", "90", "91", "92"}, "tram.fill"),
            new InterchangeInfo("Sondrio M3", new String[]{"M3", "NM3", "90", "91", "92"}, "tram.fill.tunnel"),
            new InterchangeInfo("Zara M3 M5", new String[]{"M3", "NM3", "M5", "7", "31", "51", "60", "82", "90", "91", "92", "166"}, "tram.fill.tunnel"),
            new InterchangeInfo("Via Farini V.Le Stelvio", new String[]{"2", "4", "70", "82", "90", "91", "92"}, "tram.fill"),
            new InterchangeInfo("Mac Mahon / M.Te Ceneri", new String[]{"12", "90", "91"}, "tram.fill"),
            new InterchangeInfo("V.Le Certosa V.Le Serra", new String[]{"1", "14", "69", "90", "91"}, "tram.fill"),
            new InterchangeInfo("V.Le Umbria Via Comelico", new String[]{"16", "92"}, "tram.fill"),
            new InterchangeInfo("P.Za Emilia", new String[]{"12", "27", "973", "92"}, "tram.fill"),
            new InterchangeInfo("Dateo FS M4", new String[]{"M4", "NM4", "S1", "S2", "S5", "S6", "S12", "S13", "54", "61", "92"}, "tram.fill.tunnel"),
            new InterchangeInfo("P.Za Ascoli", new String[]{"5", "19", "33", "60", "61", "92"}, "tram.fill"),
            new InterchangeInfo("Lancetti FS", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "2", "92"}, "lightrail"),
            new InterchangeInfo("Via Varè (Bovisa FN)", new String[]{"R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "S1", "S2", "S3", "S4", "S12", "S13", "92"}, "lightrail"),
            new InterchangeInfo("Corvetto M3", new String[]{"M3", "NM3", "84", "93"}, "tram.fill.tunnel"),
            new InterchangeInfo("Argonne M4", new String[]{"M4", "NM4", "38", "54", "93"}, "tram.fill.tunnel"),
            new InterchangeInfo("Via B. Angelico Via Aselli", new String[]{"5", "93"}, "tram.fill"),
            new InterchangeInfo("Via Bassini Via Golgi", new String[]{"19", "33", "45", "93", "175"}, "tram.fill"),
            new InterchangeInfo("Lambrate FS M2", new String[]{"M2", "NM2", "R4", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "S9", "19", "39", "45", "54", "81", "93", "175", "924"}, "lightrail")
        );
    }

    public static List<InterchangeInfo> getInterchanges(Context context) {
        return Arrays.asList(
            new InterchangeInfo("Rho Fiera Milano", new String[]{"M1", "AV", "R21", "R23", "RE4", "RE5", "S5", "S6", "S11"}, "lightrail"),
            new InterchangeInfo("Lotto", new String[]{"M1", "M5"}, "tram.fill.tunnel"),
            new InterchangeInfo("Cadorna FN", new String[]{"M1", "M2", "R16", "R17", "R22", "R27", "RE1", "RE7", "S3", "S4", "MXP1"}, "lightrail"),
            new InterchangeInfo("Duomo", new String[]{"M1", "M3"}, "tram.fill.tunnel"),
            new InterchangeInfo("San Babila", new String[]{"M1", "M4"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Venezia", new String[]{"M1", "S1", "S2", "S5", "S6", "S12", "S13"}, "tram.fill.tunnel"),
            new InterchangeInfo("Loreto", new String[]{"M1", "M2"}, "tram.fill.tunnel"),
            new InterchangeInfo("Sesto 1° Maggio FS", new String[]{"M1", "R13", "R14", "RE8", "S7", "S8", "S9", "S11"}, "lightrail"),
            new InterchangeInfo("Romolo", new String[]{"M2", "R31", "S9", "S19"}, "lightrail"),
            new InterchangeInfo("S. Ambrogio", new String[]{"M2", "M4"}, "tram.fill.tunnel"),
            new InterchangeInfo("Porta Garibaldi", new String[]{"M2", "M5", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "MXP1", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13"}, "lightrail"),
            new InterchangeInfo("Centrale FS", new String[]{"M2", "M3", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "RE80", "MXP1"}, "lightrail"),
            new InterchangeInfo("Lambrate FS", new String[]{"M2", "R4", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "S9"}, "lightrail"),
            new InterchangeInfo("Affori FN", new String[]{"M3", "R16", "S2", "S4"}, "lightrail"),
            new InterchangeInfo("Zara", new String[]{"M3", "M5"}, "tram.fill.tunnel"),
            new InterchangeInfo("Repubblica", new String[]{"M3", "S1", "S2", "S5", "S6", "S12", "S13"}, "lightrail"),
            new InterchangeInfo("Lodi TIBB", new String[]{"M3", "R31", "S9", "S19"}, "figure.walk"),
            new InterchangeInfo("Rogoredo FS", new String[]{"M3", "AV", "R31", "R34", "R38", "RE8", "RE11", "RE13", "S1", "S2", "S9", "S12", "S13", "S19"}, "lightrail"),
            new InterchangeInfo("San Cristoforo", new String[]{"M4", "R31", "S9", "S19"}, "lightrail"),
            new InterchangeInfo("Sforza - Policlinico", new String[]{"M3", "M4"}, "figure.walk"),
            new InterchangeInfo("Dateo", new String[]{"M4", "S1", "S2", "S5", "S6", "S12", "S13"}, "tram.fill.tunnel"),
            new InterchangeInfo("Stazione Forlanini", new String[]{"M4", "R38", "RE8", "RE13", "S5", "S6", "S9"}, "lightrail"),
            new InterchangeInfo("Linate Aereoporto", new String[]{context.getString(R.string.airportKey), "M4"}, "airplane.departure"),
            new InterchangeInfo("Domodossola FN", new String[]{"M5", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "S3", "S4"}, "tram.fill.tunnel"),
            new InterchangeInfo("Como S. Giovanni", new String[]{"S10", "S11", "S40", "RE80"}, "lightrail"),
            new InterchangeInfo("Mendrisio", new String[]{"S10", "S40", "S50", "RE80"}, "lightrail"),
            new InterchangeInfo("Biasca", new String[]{"S10", "S50"}, "lightrail"),
            new InterchangeInfo("Varese", new String[]{"S5", "S40", "S50"}, "lightrail"),
            new InterchangeInfo("Gallarate", new String[]{"S30", "S50"}, "lightrail"),
            new InterchangeInfo("Busto Arsizio Nord", new String[]{"S50", "MXP1", "MXP2"}, "lightrail"),
            new InterchangeInfo("Milano Bovisa", new String[]{"S1", "S2", "S3", "S4", "S12", "S13", "MXP1", "MXP2"}, "lightrail"),
            new InterchangeInfo("Saronno", new String[]{"S1", "S3", "S9", "MXP1", "MXP2"}, "lightrail"),
            new InterchangeInfo("Monza", new String[]{"S7", "S8", "S9", "S11", "RE80"}, "lightrail")
        );
    }

    public static List<MetroStation> getAllStations(boolean isPassanteClosed) {
        if (CACHED_STATIONS == null) {
            List<MetroStation> allStations = new ArrayList<>();
            allStations.addAll(MetroStationsDB.getStationsM1());
            allStations.addAll(MetroStationsDB.getStationsM2());
            allStations.addAll(MetroStationsDB.getStationsM3());
            allStations.addAll(MetroStationsDB.getStationsM4());
            allStations.addAll(MetroStationsDB.getStationsM5());

            if (isPassanteClosed) {
                allStations.addAll(SuburbanStationsDB.getStationsS1_ClosedPassante());
                allStations.addAll(SuburbanStationsDB.getStationsS5_ClosedPassante());
                allStations.addAll(SuburbanStationsDB.getStationsS13_ClosedPassante());
            } else {
                allStations.addAll(SuburbanStationsDB.getStationsS1());
                allStations.addAll(SuburbanStationsDB.getStationsS5());
                allStations.addAll(SuburbanStationsDB.getStationsS13());
            }

            allStations.addAll(SuburbanStationsDB.getStationsS2());
            allStations.addAll(SuburbanStationsDB.getStationsS3());
            allStations.addAll(SuburbanStationsDB.getStationsS4());
            allStations.addAll(SuburbanStationsDB.getStationsS6());
            allStations.addAll(SuburbanStationsDB.getStationsS7());
            allStations.addAll(SuburbanStationsDB.getStationsS8());
            allStations.addAll(SuburbanStationsDB.getStationsS9());
            allStations.addAll(SuburbanStationsDB.getStationsS11());
            allStations.addAll(SuburbanStationsDB.getStationsS12());
            allStations.addAll(SuburbanStationsDB.getStationsS19());
            allStations.addAll(SuburbanStationsDB.getStationsS31());

            allStations.addAll(RegionalStationsDB.getStationsR1());
            allStations.addAll(RegionalStationsDB.getStationsR2());
            allStations.addAll(RegionalStationsDB.getStationsR3());
            allStations.addAll(RegionalStationsDB.getStationsR4());
            allStations.addAll(RegionalStationsDB.getStationsR5());
            allStations.addAll(RegionalStationsDB.getStationsR6());
            allStations.addAll(RegionalStationsDB.getStationsR7());
            allStations.addAll(RegionalStationsDB.getStationsR8());
            allStations.addAll(RegionalStationsDB.getStationsR9());
            allStations.addAll(RegionalStationsDB.getStationsR11());
            allStations.addAll(RegionalStationsDB.getStationsR12());
            allStations.addAll(RegionalStationsDB.getStationsR13());
            allStations.addAll(RegionalStationsDB.getStationsR14());
            allStations.addAll(RegionalStationsDB.getStationsR15());
            allStations.addAll(RegionalStationsDB.getStationsR16());
            allStations.addAll(RegionalStationsDB.getStationsR17());
            allStations.addAll(RegionalStationsDB.getStationsR18());
            allStations.addAll(RegionalStationsDB.getStationsR21());
            allStations.addAll(RegionalStationsDB.getStationsR22());
            allStations.addAll(RegionalStationsDB.getStationsR23());
            allStations.addAll(RegionalStationsDB.getStationsR24());
            allStations.addAll(RegionalStationsDB.getStationsR25());
            allStations.addAll(RegionalStationsDB.getStationsR27());
            allStations.addAll(RegionalStationsDB.getStationsR31());
            allStations.addAll(RegionalStationsDB.getStationsR32());
            allStations.addAll(RegionalStationsDB.getStationsR33());
            allStations.addAll(RegionalStationsDB.getStationsR34());
            allStations.addAll(RegionalStationsDB.getStationsR35());
            allStations.addAll(RegionalStationsDB.getStationsR36());
            allStations.addAll(RegionalStationsDB.getStationsR37());
            allStations.addAll(RegionalStationsDB.getStationsR38());
            allStations.addAll(RegionalStationsDB.getStationsR39());
            allStations.addAll(RegionalStationsDB.getStationsR40());
            allStations.addAll(RegionalStationsDB.getStationsR41());

            allStations.addAll(RegioExpStationsDB.getStationsRE1());
            allStations.addAll(RegioExpStationsDB.getStationsRE2());
            allStations.addAll(RegioExpStationsDB.getStationsRE3());
            allStations.addAll(RegioExpStationsDB.getStationsRE4());
            allStations.addAll(RegioExpStationsDB.getStationsRE5());
            allStations.addAll(RegioExpStationsDB.getStationsRE6());
            allStations.addAll(RegioExpStationsDB.getStationsRE7());
            allStations.addAll(RegioExpStationsDB.getStationsRE8());
            allStations.addAll(RegioExpStationsDB.getStationsRE11());
            allStations.addAll(RegioExpStationsDB.getStationsRE13());

            allStations.addAll(TILOStationsDB.getStationsS10());
            allStations.addAll(TILOStationsDB.getStationsS20());
            allStations.addAll(TILOStationsDB.getStationsS30());
            allStations.addAll(TILOStationsDB.getStationsS40());
            allStations.addAll(TILOStationsDB.getStationsS50());
            allStations.addAll(TILOStationsDB.getStationsS90());
            allStations.addAll(TILOStationsDB.getStationsRE80());

            allStations.addAll(MPXStationsDB.getStationsMXP1());
            allStations.addAll(MPXStationsDB.getStationsMXP2());

            allStations.addAll(TramStationsDB.getStationsTram1());
            allStations.addAll(TramStationsDB.getStationsTram2());
            allStations.addAll(TramStationsDB.getStationsTram3());
            allStations.addAll(TramStationsDB.getStationsTram4());
            allStations.addAll(TramStationsDB.getStationsTram5());
            allStations.addAll(TramStationsDB.getStationsTram7());
            allStations.addAll(TramStationsDB.getStationsTram9());
            allStations.addAll(TramStationsDB.getStationsTram10());
            allStations.addAll(TramStationsDB.getStationsTram14());
            allStations.addAll(TramStationsDB.getStationsTram15());
            allStations.addAll(TramStationsDB.getStationsTram16());
            allStations.addAll(TramStationsDB.getStationsTram19());
            allStations.addAll(TramStationsDB.getStationsTram24());
            allStations.addAll(TramStationsDB.getStationsTram27());
            allStations.addAll(TramStationsDB.getStationsTram31());
            allStations.addAll(TramStationsDB.getStationsTram33());

            allStations.addAll(BusStationsDB.getStationsZ601());
            allStations.addAll(BusStationsDB.getStationsZ602());
            allStations.addAll(BusStationsDB.getStationsZ603());
            allStations.addAll(BusStationsDB.getStationsZ606());
            allStations.addAll(BusStationsDB.getStationsZ611());
            allStations.addAll(BusStationsDB.getStationsZ612());
            allStations.addAll(BusStationsDB.getStationsZ616());
            allStations.addAll(BusStationsDB.getStationsZ617());
            allStations.addAll(BusStationsDB.getStationsZ618());
            allStations.addAll(BusStationsDB.getStationsZ619());
            allStations.addAll(BusStationsDB.getStationsZ620());
            allStations.addAll(BusStationsDB.getStationsZ621());
            allStations.addAll(BusStationsDB.getStationsZ622());
            allStations.addAll(BusStationsDB.getStationsZ625());
            allStations.addAll(BusStationsDB.getStationsZ627());
            allStations.addAll(BusStationsDB.getStationsZ636());
            allStations.addAll(BusStationsDB.getStationsZ641());
            allStations.addAll(BusStationsDB.getStationsZ642());
            allStations.addAll(BusStationsDB.getStationsZ643());
            allStations.addAll(BusStationsDB.getStationsZ644());
            allStations.addAll(BusStationsDB.getStationsZ646());
            allStations.addAll(BusStationsDB.getStationsZ647());
            allStations.addAll(BusStationsDB.getStationsZ649());

            allStations.addAll(FilobusStationsDB.getStationsFilobus90());
            allStations.addAll(FilobusStationsDB.getStationsFilobus91());
            allStations.addAll(FilobusStationsDB.getStationsFilobus92());
            allStations.addAll(FilobusStationsDB.getStationsFilobus93());

            CACHED_STATIONS = Collections.unmodifiableList(allStations);
        }
        return CACHED_STATIONS;
    }

    public static int getLineColor(Context context, String nomeLinea) {
        switch (nomeLinea) {
            //S LINES
            case "S1":
                return R.color.S1;
            case "S2":
                return R.color.S2;
            case "S3":
                return R.color.S3;
            case "S4":
                return R.color.S4;
            case "S5":
                return R.color.S5;
            case "S6":
                return R.color.S6;
            case "S7":
                return R.color.S7;
            case "S8":
                return R.color.S8;
            case "S9":
                return R.color.S9;
            case "S11":
                return R.color.S11;
            case "S12":
                return R.color.S12;
            case "S13":
                return R.color.S13;
            case "S19":
                return R.color.S19;
            case "S31":
                return R.color.S31;
            case "AV":
            case "RV":
                return R.color.AV;
            case "REG":
                return R.color.REG;

            //TILO LINES
            case "S10":
                return R.color.S10;
            case "S20":
                return R.color.S20;
            case "S30":
                return R.color.S30;
            case "S40":
                return R.color.S40;
            case "S50":
                return R.color.S50;
            case "S90":
                return R.color.S90;
            case "RE80":
                return R.color.RE80;

            //METRO LINES
            case "M1":
            case "NM1":
                return R.color.M1;
            case "M2":
            case "NM2":
                return R.color.M2;
            case "M3":
            case "NM3":
                return R.color.M3;
            case "M4":
            case "NM4":
                return R.color.M4;
            case "M5":
                return R.color.M5;

            /// SFM LINES
            case "SFM 6":
                return R.color.SFM6;

            //OTHERS
            default:
                if (nomeLinea.contains("z"))
                    return R.color.BUS;
                else if (nomeLinea.contains("N"))
                    return R.color.NIGHTLINES;
                else if (nomeLinea.equals("RE80"))
                    return R.color.RE80;
                else if (nomeLinea.contains("R") && !nomeLinea.contains("RE"))
                    return R.color.REGIONAL;
                else if (nomeLinea.contains("RE"))
                    return R.color.RE;
                else if (nomeLinea.matches("^\\d+$")) {
                    int numero = Integer.parseInt(nomeLinea);

                    if (numero >= 1 && numero <= 33)
                        return R.color.TRAM;
                    else
                        return R.color.OTHER_LINES;
                }
                else if (nomeLinea.startsWith("P"))
                    return R.color.AUTOGUIDOVIE;
                else if (nomeLinea.startsWith("MXP"))
                    return R.color.MXP;
                else if (nomeLinea.equalsIgnoreCase(context.getString(R.string.airportKey)))
                    return R.color.airport;
                else
                    return R.color.OTHER_LINES;
        }
    }
}