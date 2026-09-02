package com.andreafilice.lavorami;

import android.content.Context;

import com.andreafilice.lavorami.stationsDB.MetroStationsDB;
import com.andreafilice.lavorami.stationsDB.SuburbanStationsDB;
import com.andreafilice.lavorami.stationsDB.RegionalStationsDB;
import com.andreafilice.lavorami.stationsDB.RegioExpStationsDB;
import com.andreafilice.lavorami.stationsDB.TILOStationsDB;
import com.andreafilice.lavorami.stationsDB.MPXStationsDB;
import com.andreafilice.lavorami.stationsDB.TramStationsDB;
import com.andreafilice.lavorami.stationsDB.MovibusStationsDB;
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
            "Milano Molino Dorino M1",
            new String[]{"M1", "NM1", "35", "69", "80", "424", "528", "z601", "z617", "z620", "z621", "z649"},
            new String[]{"z601", "z617", "z620", "z621", "z649"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Milano Cadorna FN",
            new String[]{"M1", "NM1", "M2", "NM2", "S3", "S4", "R22", "R27", "RE1", "RE7", "MXP2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"},
            new String[]{"z602", "z603", "z6C3"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Parabiago FS",
            new String[]{"S5", "R21", "R23", "RE5", "z644"},
            new String[]{"z644"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Vittuone FS",
            new String[]{"S6", "RV", "z622", "z643"},
            new String[]{"z643"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Rho, Corso Europa",
            new String[]{"S5", "S6", "S11", "z601", "z606", "z618"},
            new String[]{"z606", "z618"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Rho FS",
            new String[]{"S5", "S6", "S11", "z616"},
            new String[]{"z616"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Cuggiono, Piazza della Vittoria",
            new String[]{"z621", "z622", "z627", "z641", "z646"},
            new String[]{"z622"},
            "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
            "Busto Garolfo",
            new String[]{"z625", "z627", "z644", "z647", "z649"},
            new String[]{"z625"},
            "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
            "Arese, Il Centro",
            new String[]{"561", "z612"},
            new String[]{"z612"},
            "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
            "Legnano FS",
            new String[]{"S5", "R21", "RE5", "REG", "RV", "z601", "z602", "z611", "z627", "z636", "z642"},
            new String[]{"z611", "z612", "z627", "z636", "z642"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Milano Bisceglie M1",
            new String[]{"M1", "NM1", "47", "58", "63", "76", "78", "321", "322", "323", "327", "433", "z551", "z560"},
            new String[]{"z551", "z560"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Milano Romolo FS M2",
            new String[]{"M2", "NM2", "S9", "S19", "R31", "47", "Filobus 90", "Filobus 91", "71", "324", "325", "z553"},
            new String[]{"z553"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Santo Stefano Ticino FS",
            new String[]{"S6", "RV", "z552"},
            new String[]{"z552"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Albairate - Vermezzo FS",
            new String[]{"S9", "S19", "R31", "z554"},
            new String[]{"z554"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Gaggiano FS",
            new String[]{"S9", "S19", "R31", "z557"},
            new String[]{"z557"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Magenta FS",
            new String[]{"S6", "RV", "z559", "z620", "z641", "z642", "z646"},
            new String[]{"z641", "z646", "z559"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Abbiategrasso Vittorio Veneto",
            new String[]{"z551", "z552", "z553", "z555", "z559"},
            new String[]{"z555", "z556"},
            "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
            "Melzo FS",
            new String[]{"R4", "S5", "S6", "z401", "z404", "z407", "z411"},
            new String[]{"z401", "z411"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Pioltello Limito FS",
            new String[]{"R4", "RE2", "RE6", "S5", "S6", "z402", "z409"},
            new String[]{"z402"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Gorgonzola M2",
            new String[]{"M2", "z310", "z315", "z403", "z407", "z419"},
            new String[]{"z315", "z403", "z407", "z419"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Gessate M2",
            new String[]{"M2", "z310", "z311", "z312", "z313", "z314", "z404", "z405", "z406"},
            new String[]{"z310", "z311", "z312", "z313", "z404", "z405", "z406"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Linate Aereoporto M4",
            new String[]{"M4", "NM4", "183", "901", "903", "923", "973", "z409"},
            new String[]{"z409"},
            "airplane.departure"
        ));

        interchanges.add(new InterchangeInfo(
            "San Donato M3",
            new String[]{"M3", "NM3", "45", "77", "121", "130", "132", "901", "902", "903", "z410", "z411", "z412", "z413", "z415", "z420"},
            new String[]{"z410", "z412", "z413", "z415", "z420"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Melegnano FS",
            new String[]{"S1", "S12", "REG", "z431"},
            new String[]{"z431", "z432"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "San Zenone Al Lambro FS",
            new String[]{"S1", "z415", "z418", "z420"},
            new String[]{"z418"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Monza FS",
            new String[]{"S7", "S8", "S9", "S11", "R7", "R13", "R14", "RE8", "RE80", "z203", "z205", "z219", "z221", "z222", "z228", "z314", "z321"},
            new String[]{"z203", "z205", "z219", "z221", "z222", "z228", "z321"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Cesano FN",
            new String[]{"S2", "S4", "S9", "R16", "z209", "z250", "z251"},
            new String[]{"z209", "z250", "z251"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Sesto San Giovanni FS M1",
            new String[]{"M1", "NM1", "S7", "S8", "S9", "S11", "R13", "R14", "RE8", "700", "702", "712", "727", "729", "z221", "z222", "z225", "z227", "z301"},
            new String[]{"z225", "z227", "z301"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Seregno FS",
            new String[]{"S9", "S11", "R15", "RE80", "z228", "z231", "z232", "z233", "z242"},
            new String[]{"z231", "z232", "z233"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Milano Famagosta M2",
            new String[]{"M2", "NM2", "46", "59", "71", "74", "95", "98", "z501", "z509", "z510", "z515", "z516"},
            new String[]{"z501", "z509", "z510", "z515", "z516"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Lissone - Muggiò FS",
            new String[]{"S9", "S11", "z227", "z234", "z250"},
            new String[]{"z227", "z234"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Cologno Nord M2",
            new String[]{"M2", "701", "702", "707", "z203", "z304", "z305", "z307", "z322", "z323"},
            new String[]{"z304", "z305", "z307", "z322", "z323"},
            "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
            "Cassano D'Adda FS",
            new String[]{"S5", "S6", "R4", "z309"},
            new String[]{"z309"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Arcore FS",
            new String[]{"S8", "R13", "R14", "RE8", "z208", "z317", "z319"},
            new String[]{"z317", "z319"},
            "train.side.front.car"
        ));

        interchanges.add(new InterchangeInfo(
            "Carnate Usmate FS",
            new String[]{"S8", "R13", "R14", "R15", "RE8", "z318", "z319"},
            new String[]{"z318"},
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

            allStations.addAll(MovibusStationsDB.getStationsZ601());
            allStations.addAll(MovibusStationsDB.getStationsZ602());
            allStations.addAll(MovibusStationsDB.getStationsZ603());
            allStations.addAll(MovibusStationsDB.getStationsZ606());
            allStations.addAll(MovibusStationsDB.getStationsZ611());
            allStations.addAll(MovibusStationsDB.getStationsZ612());
            allStations.addAll(MovibusStationsDB.getStationsZ616());
            allStations.addAll(MovibusStationsDB.getStationsZ617());
            allStations.addAll(MovibusStationsDB.getStationsZ618());
            allStations.addAll(MovibusStationsDB.getStationsZ619());
            allStations.addAll(MovibusStationsDB.getStationsZ620());
            allStations.addAll(MovibusStationsDB.getStationsZ621());
            allStations.addAll(MovibusStationsDB.getStationsZ622());
            allStations.addAll(MovibusStationsDB.getStationsZ625());
            allStations.addAll(MovibusStationsDB.getStationsZ627());
            allStations.addAll(MovibusStationsDB.getStationsZ636());
            allStations.addAll(MovibusStationsDB.getStationsZ641());
            allStations.addAll(MovibusStationsDB.getStationsZ642());
            allStations.addAll(MovibusStationsDB.getStationsZ643());
            allStations.addAll(MovibusStationsDB.getStationsZ644());
            allStations.addAll(MovibusStationsDB.getStationsZ646());
            allStations.addAll(MovibusStationsDB.getStationsZ647());
            allStations.addAll(MovibusStationsDB.getStationsZ649());
            allStations.addAll(MovibusStationsDB.getStationsZ6C3());

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