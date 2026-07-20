package com.andreafilice.lavorami;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class InterchangesDB {

    public static List<InterchangeInfo> getMetroInterchanges(Context context) {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* METRO LINES
        /// Metro M1
        interchanges.add(new InterchangeInfo("Sesto 1° Maggio FS", new String[]{"M1", "NM1", "S7", "S8", "S9", "S11", "R13", "R14", "RE8", "700", "702", "712", "727", "729"}, "tram.fill.tunnel", "Main", 19));
        interchanges.add(new InterchangeInfo("Sesto Rondò", new String[]{"M1", "NM1", "700", "701", "708", "713"}, "tram.fill.tunnel", "Main", 18));
        interchanges.add(new InterchangeInfo("Sesto Marelli", new String[]{"M1", "NM1", "51", "81", "87"}, "tram.fill.tunnel", "Main", 17));
        interchanges.add(new InterchangeInfo("Villa S. Giovanni", new String[]{"M1", "NM1", "51", "81", "87"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Precotto", new String[]{"M1", "NM1", "7", "51", "86", "174"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Gorla", new String[]{"M1", "NM1", "44", "174"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Turro", new String[]{"M1", "NM1", "44", "174"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Rovereto", new String[]{"M1", "NM1", "174"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Pasteur", new String[]{"M1", "NM1"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Loreto M1 M2", new String[]{"M1", "NM1", "M2", "NM2", "39", "55", "56", "90", "91"}, "tram.fill.tunnel", "Main", 10));
        interchanges.add(new InterchangeInfo("Lima", new String[]{"M1", "NM1", "60", "81", "N25", "N26"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Porta Venezia FS", new String[]{"M1", "NM1", "S1", "S2", "S5", "S6", "S12", "S13", "5", "9", "33"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Palestro", new String[]{"M1", "NM1"}, "tram.fill.tunnel", "Main", 7));
        interchanges.add(new InterchangeInfo("San Babila M1 M4", new String[]{"M1", "NM1", "M4", "NM4", "NM3", "61", "84"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Duomo M1 M3", new String[]{"M1", "NM1", "M3", "NM3", "12", "15", "16", "19", "60", "61", "N15", "N24", "N27"}, "building.columns.fill", "Main", 5));
        interchanges.add(new InterchangeInfo("Cordusio", new String[]{"M1", "NM1", "1", "12", "16", "19"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Cairoli", new String[]{"M1", "NM1", "NM2", "1", "2", "4", "50", "96", "97"}, "building.columns.fill", "Main", 3));
        interchanges.add(new InterchangeInfo("Cadorna FN M1 M2", new String[]{"M1", "NM1", "M2", "NM2", "S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Conciliazione", new String[]{"M1", "NM1", "2", "10", "67", "68", "N26"}, "tram.fill", "Main", 1));
        interchanges.add(new InterchangeInfo("Pagano", new String[]{"M1", "NM1", "67", "85"}, "tram.fill.tunnel", "Main", 0));

        interchanges.add(new InterchangeInfo("Buonarroti", new String[]{"M1", "NM1"}, "tram.fill.tunnel", "Rho Fiera-Milano", 9));
        interchanges.add(new InterchangeInfo("Amendola", new String[]{"M1", "NM1", "68"}, "tram.fill.tunnel", "Rho Fiera-Milano", 8));
        interchanges.add(new InterchangeInfo("Lotto M1 M5", new String[]{"M1", "NM1", "M5", "48", "49", "68", "78", "90", "91", "98"}, "tram.fill.tunnel", "Rho Fiera-Milano", 7));
        interchanges.add(new InterchangeInfo("QT8", new String[]{"M1", "NM1", "68", "560"}, "tram.fill.tunnel", "Rho Fiera-Milano", 6));
        interchanges.add(new InterchangeInfo("Lampugnano", new String[]{"M1", "NM1", "68"}, "tram.fill.tunnel", "Rho Fiera-Milano", 5));
        interchanges.add(new InterchangeInfo("Uruguay", new String[]{"M1", "40", "68", "69"}, "tram.fill.tunnel", "Rho Fiera-Milano", 4));
        interchanges.add(new InterchangeInfo("Bonola", new String[]{"M1", "40", "64", "68", "69"}, "tram.fill.tunnel", "Rho Fiera-Milano", 3));
        interchanges.add(new InterchangeInfo("S. Leonardo", new String[]{"M1", "NM1"}, "tram.fill.tunnel", "Rho Fiera-Milano", 2));
        interchanges.add(new InterchangeInfo("Molino Dorino", new String[]{"M1", "NM1", "35", "69", "80", "424", "528", "z601", "z617", "z620", "z621", "z649"}, "bus.fill", "Rho Fiera-Milano", 1));
        interchanges.add(new InterchangeInfo("Rho Fiera-Milano", new String[]{"M1", "S5", "S6", "S11", "R21", "R23", "RE4", "RE5", "RE80", "542", "561"}, "lightrail", "Rho Fiera-Milano", 0));

        interchanges.add(new InterchangeInfo("Wagner", new String[]{"M1", "NM1", "67"}, "tram.fill.tunnel", "Bisceglie", 6));
        interchanges.add(new InterchangeInfo("De Angeli", new String[]{"M1", "NM1", "16", "63", "80"}, "tram.fill.tunnel", "Bisceglie", 5));
        interchanges.add(new InterchangeInfo("Gambara", new String[]{"M1", "NM1"}, "tram.fill.tunnel", "Bisceglie", 4));
        interchanges.add(new InterchangeInfo("Bande Nere", new String[]{"M1", "NM1", "67", "98"}, "tram.fill.tunnel", "Bisceglie", 3));
        interchanges.add(new InterchangeInfo("Primaticcio", new String[]{"M1", "NM1", "63", "64"}, "tram.fill.tunnel", "Bisceglie", 2));
        interchanges.add(new InterchangeInfo("Inganni", new String[]{"M1", "NM1", "49", "58", "63", "67"}, "tram.fill.tunnel", "Bisceglie", 1));
        interchanges.add(new InterchangeInfo("Bisceglie", new String[]{"M1", "NM1", "47", "58", "63", "76", "78", "321", "322", "323", "327", "433", "z551"}, "bus.fill", "Bisceglie", 0));

        /// Metro M2
        interchanges.add(new InterchangeInfo("Gessate", new String[]{"M2", "z404", "z405"}, "tram.fill.tunnel", "Gessate", 0));
        interchanges.add(new InterchangeInfo("Cascina Antonietta", new String[]{"M2"}, "tram.fill.tunnel", "Gessate", 1));
        interchanges.add(new InterchangeInfo("Gorgonzola", new String[]{"M2", "z403", "z407", "z411", "z419"}, "tram.fill.tunnel", "Gessate", 2));
        interchanges.add(new InterchangeInfo("Villa Pompea", new String[]{"M2"}, "tram.fill.tunnel", "Gessate", 3));
        interchanges.add(new InterchangeInfo("Bussero", new String[]{"M2"}, "tram.fill.tunnel", "Gessate", 4));
        interchanges.add(new InterchangeInfo("Cassina de Pecchi", new String[]{"M2"}, "tram.fill.tunnel", "Gessate", 5));
        interchanges.add(new InterchangeInfo("Villa Fiorita", new String[]{"M2", "z401"}, "tram.fill.tunnel", "Gessate", 6));
        interchanges.add(new InterchangeInfo("Cernusco Sul Naviglio", new String[]{"M2", "z402"}, "tram.fill.tunnel", "Gessate", 7));
        interchanges.add(new InterchangeInfo("Cascina Burrona", new String[]{"M2", "924"}, "tram.fill.tunnel", "Gessate", 8));
        interchanges.add(new InterchangeInfo("Vimodrone", new String[]{"M2"}, "tram.fill.tunnel", "Gessate", 9));

        interchanges.add(new InterchangeInfo("Cologno Nord", new String[]{"M2", "701", "702", "707"}, "tram.fill.tunnel", "Cologno Nord", 0));
        interchanges.add(new InterchangeInfo("Cologno Centro", new String[]{"M2", "702", "707"}, "tram.fill.tunnel", "Cologno Nord", 1));
        interchanges.add(new InterchangeInfo("Cologno Sud", new String[]{"M2", "701", "707", "709"}, "tram.fill.tunnel", "Cologno Nord", 2));

        interchanges.add(new InterchangeInfo("Cascina Gobba", new String[]{"M2", "NM2", "44", "54", "86", "925"}, "bus.fill", "Main", 0));
        interchanges.add(new InterchangeInfo("Cimiano", new String[]{"M2", "NM2", "53", "54"}, "tram.fill.tunnel", "Main", 1));
        interchanges.add(new InterchangeInfo("Udine", new String[]{"M2", "NM2", "53", "54", "55", "175", "925"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Lambrate FS", new String[]{"M2", "NM2", "S9", "R4", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "19", "39", "45", "54", "81", "93", "175", "924"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Piola", new String[]{"M2", "NM2", "39", "62", "90", "91"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Loreto M1 M2", new String[]{"M2", "NM2", "M1", "NM1", "39", "55", "56", "90", "91"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Caiazzo M2", new String[]{"M2", "NM2", "1", "90", "91", "92"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Centrale FS M2 M3", new String[]{"M2", "NM2", "M3", "NM3", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "MXP1", "RE80", "5", "9", "10", "42", "60", "81", "87", "90", "91", "92", "728", "N25", "N26"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Gioia", new String[]{"M2", "NM2", "43", "N26"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Garibaldi FS M2 M5", new String[]{"M2", "NM2", "M5", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "10", "33", "N25", "N26"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Moscova", new String[]{"M2", "43", "84"}, "tram.fill.tunnel", "Main", 10));
        interchanges.add(new InterchangeInfo("Lanza", new String[]{"M2", "NM2", "2", "4", "12", "14", "57", "97"}, "tram.fill", "Main", 11));
        interchanges.add(new InterchangeInfo("Cadorna FN M1 M2", new String[]{"M2", "NM2", "M1", "NM1", "S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("S. Ambrogio M2 M4", new String[]{"M2", "NM2", "M4", "NM4", "50", "96", "97"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("S. Agostino", new String[]{"M2", "NM2", "NM4"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Porta Genova", new String[]{"M2", "NM2", "2", "3", "9", "10", "74", "N25", "N26"}, "building.columns.fill", "Main", 15));
        interchanges.add(new InterchangeInfo("Romolo FS", new String[]{"M2", "NM2", "S9", "S19", "R31", "47", "90", "91", "71", "324", "325", "z553"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Famagosta", new String[]{"M2", "NM2", "46", "59", "71", "74", "95", "98", "z501", "z509", "z510", "z515", "z516"}, "bus.fill", "Main", 17));

        interchanges.add(new InterchangeInfo("P.Za Abbiategrasso", new String[]{"M2", "NM2", "3", "15", "65", "79", "230", "N15"}, "tram.fill.tunnel", "P.Za Abbiategrasso", 0));

        interchanges.add(new InterchangeInfo("Assago Milanofiori Forum", new String[]{"M2", "321", "328", "352", "z501", "z510", "z515"}, "tram.fill.tunnel", "Assago Milanofiori Forum", 0));
        interchanges.add(new InterchangeInfo("Assago Milanofiori Nord", new String[]{"M2"}, "tram.fill.tunnel", "Assago Milanofiori Forum", 1));

        /// Metro M3
        interchanges.add(new InterchangeInfo("Comasina", new String[]{"M3", "NM3", "35", "41", "52", "83", "89", "165", "705", "729"}, "bus.fill", "Main", 0));
        interchanges.add(new InterchangeInfo("Affori FN", new String[]{"M3", "NM3", "S2", "S4", "R16", "35", "40", "41", "52", "70"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Affori Centro", new String[]{"M3", "NM3", "70"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Dergano", new String[]{"M3", "NM3", "70"}, "tram.fill.tunnel", "Main", 3));
        interchanges.add(new InterchangeInfo("Maciachini", new String[]{"M3", "NM3", "2", "4", "70"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Zara", new String[]{"M3", "NM3", "M5", "7", "31", "51", "60", "82", "90", "91", "92", "166"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Sondrio", new String[]{"M3", "NM3", "43", "81", "90", "91", "92"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Centrale FS M2 M3", new String[]{"M3", "NM3", "M2", "NM2", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "MXP1", "RE80", "5", "9", "10", "42", "60", "81", "87", "90", "91", "92", "728", "N25", "N26"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Repubblica FS", new String[]{"M3", "NM3", "S1", "S2", "S5", "S6", "S12", "S13", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Turati", new String[]{"M3", "NM3", "1", "43", "84"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Montenapoleone", new String[]{"M3", "1", "96"}, "building.columns.fill", "Main", 10));
        interchanges.add(new InterchangeInfo("Duomo M1 M3", new String[]{"M3", "NM3", "M1", "NM1", "12", "15", "16", "19", "60", "61", "N15", "N24", "N27"}, "building.columns.fill", "Main", 11));
        interchanges.add(new InterchangeInfo("Missori", new String[]{"M3", "NM3", "M4", "12", "15", "16", "19", "24", "N15", "N24"}, "building.columns.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Crocetta", new String[]{"M3", "NM3", "16", "24", "65", "N24"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Porta Romana", new String[]{"M3", "NM3", "9", "62", "65", "N26"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Lodi TIBB", new String[]{"M3", "NM3", "S9", "S19", "R31", "65", "90", "91", "92"}, "figure.walk", "Main", 15));
        interchanges.add(new InterchangeInfo("Brenta", new String[]{"M3", "NM3", "34", "65"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Corvetto", new String[]{"M3", "NM3", "84", "93", "95"}, "tram.fill.tunnel", "Main", 17));
        interchanges.add(new InterchangeInfo("Porto di Mare", new String[]{"M3", "NM3", "77", "95"}, "tram.fill.tunnel", "Main", 18));
        interchanges.add(new InterchangeInfo("Rogoredo FS", new String[]{"M3", "NM3", "S1", "S2", "S9", "S12", "S13", "S19", "R31", "R34", "R38", "RE8", "RE11", "RE13", "77", "88", "95", "140"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("San Donato", new String[]{"M3", "NM3", "45", "77", "121", "130", "132", "901", "902", "903", "z410", "z411", "z412", "z413", "z415", "z420"}, "bus.fill", "Main", 20));

        /// Metro M4
        interchanges.add(new InterchangeInfo("Linate Aereoporto", new String[]{"M4", context.getString(R.string.airportKey), "NM4", "183", "901", "903", "923", "973", "z409"}, "airplane.departure", "Main", 0));
        interchanges.add(new InterchangeInfo("Repetti", new String[]{"M4", "NM4", "12", "27", "45", "175", "N27"}, "tram.fill.tunnel", "Main", 1));
        interchanges.add(new InterchangeInfo("Stazione Forlanini FS", new String[]{"M4", "NM4", "S5", "S6", "S9", "R38", "RE8", "RE13", "12", "27", "45", "175", "973", "N27"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Argonne", new String[]{"M4", "NM4", "38", "45", "54", "93", "175"}, "tram.fill.tunnel", "Main", 3));
        interchanges.add(new InterchangeInfo("Susa", new String[]{"M4", "NM4", "38", "54", "90", "91"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Dateo FS", new String[]{"M4", "NM4", "S1", "S2", "S5", "S6", "S12", "S13", "54", "61", "92"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Tricolore", new String[]{"M4", "NM4", "9", "19", "61"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("San Babila M1 M4", new String[]{"M4", "NM4", "M1", "NM1", "NM3", "61", "84"}, "tram.fill.tunnel", "Main", 7));
        interchanges.add(new InterchangeInfo("Sforza - Policlinico", new String[]{"M4", "NM4", "M3", "NM3", "16", "24", "65", "96", "97", "N24"}, "figure.walk", "Main", 8));
        interchanges.add(new InterchangeInfo("Santa Sofia", new String[]{"M4", "NM4", "15", "96", "97", "N15"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Vetra", new String[]{"M4", "NM4", "3", "96", "97"}, "tram.fill.tunnel", "Main", 10));
        interchanges.add(new InterchangeInfo("De Amicis", new String[]{"M4", "NM4", "NM2", "2", "14", "96", "97"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("S. Ambrogio M2 M4", new String[]{"M4", "NM4", "M2", "NM2", "50", "96", "97"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Coni Zugna", new String[]{"M4", "NM4", "2", "10", "58"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("California", new String[]{"M4", "NM4", "58", "68"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Bolivar", new String[]{"M4", "NM4", "58", "85", "90", "91"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Tolstoj", new String[]{"M4", "NM4", "58"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Frattini", new String[]{"M4", "NM4", "58", "98"}, "tram.fill.tunnel", "Main", 17));
        interchanges.add(new InterchangeInfo("Gelsomini", new String[]{"M4", "NM4", "50", "58", "64"}, "tram.fill.tunnel", "Main", 18));
        interchanges.add(new InterchangeInfo("Segneri", new String[]{"M4", "NM4", "50", "64"}, "tram.fill.tunnel", "Main", 19));
        interchanges.add(new InterchangeInfo("San Cristoforo FS", new String[]{"M4", "NM4", "S9", "S19", "R31", "47", "49", "95", "324", "325", "326", "351", "z553"}, "lightrail", "Main", 20));

        /// Metro M5
        interchanges.add(new InterchangeInfo("Bignami", new String[]{"M5", "4", "31", "713", "728"}, "tram.fill.tunnel", "Main", 0));
        interchanges.add(new InterchangeInfo("Ponale", new String[]{"M5", "4", "31", "51", "172"}, "tram.fill.tunnel", "Main", 1));
        interchanges.add(new InterchangeInfo("Bicocca", new String[]{"M5", "4", "7", "31", "52", "172", "783"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Ca' Granda", new String[]{"M5", "4", "5", "7", "31", "86", "172"}, "tram.fill.tunnel", "Main", 3));
        interchanges.add(new InterchangeInfo("Istria", new String[]{"M5", "5", "7", "31", "42"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Marche", new String[]{"M5", "5", "7", "31"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Isola", new String[]{"M5"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Garibaldi FS M2 M5", new String[]{"M5", "M2", "NM2", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "10", "33", "N25", "N26"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Monumentale", new String[]{"M5", "10", "12", "14", "70"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Cenisio", new String[]{"M5", "12", "14"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Gerusalemme", new String[]{"M5"}, "tram.fill.tunnel", "Main", 10));
        interchanges.add(new InterchangeInfo("Domodossola FN", new String[]{"M5", "S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "1", "19"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Tre Torri", new String[]{"M5"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Lotto M1 M5", new String[]{"M5", "M1", "NM1", "48", "49", "68", "78", "90", "91", "98"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Portello", new String[]{"M5", "48", "78"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("San Siro Ippodromo", new String[]{"M5", "16"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("San Siro Stadio", new String[]{"M5", context.getString(R.string.stadiumKey), "16", "49"}, "stadium.fill", "Main", 16));

        return interchanges;
    }

    public static List<InterchangeInfo> getSuburbanInterchanges() {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* SUBURBAN LINES
        /// Suburbano S1
        interchanges.add(new InterchangeInfo("Saronno", new String[]{"S1", "S3", "S9", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Saronno Sud", new String[]{"S1", "S3", "S9"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Caronno Pertusella", new String[]{"S1", "S3"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Cesate", new String[]{"S1", "S3"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Garbagnate Milanese", new String[]{"S1", "S3", "S13", "RE13"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Garbagnate Parco Delle Groane", new String[]{"S1", "S3"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Bollate Nord", new String[]{"S1", "S3"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Bollate Centro", new String[]{"S1", "S3", "S13", "RE13"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Novate Milanese", new String[]{"S1", "S3"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Quarto Oggiaro", new String[]{"S1", "S3", "40"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S1", "S2", "S3", "S4", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "2", "92"}, "tram.fill", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S1", "S2", "S5", "S6", "S12", "S13", "91", "93"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Milano Rogoredo", new String[]{"S1", "S2", "S9", "S12", "S13", "S19", "R31", "R34", "R38", "RE8", "RE11", "RE13", "M3", "NM3", "77", "88", "95", "140"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("San Donato Milanese", new String[]{"S1", "S12"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Borgolombardo", new String[]{"S1", "S12", "132"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("San Giuliano Milanese", new String[]{"S1", "S12", "121"}, "lightrail", "Main", 20));
        interchanges.add(new InterchangeInfo("Melegnano", new String[]{"S1", "S12"}, "lightrail", "Main", 21));
        interchanges.add(new InterchangeInfo("San Zenone Al Lambro", new String[]{"S1"}, "lightrail", "Main", 22));
        interchanges.add(new InterchangeInfo("Tavazzano", new String[]{"S1"}, "lightrail", "Main", 23));
        interchanges.add(new InterchangeInfo("Lodi", new String[]{"S1", "R38", "RE11", "RV"}, "lightrail", "Main", 24));

        /// Suburbano S2
        interchanges.add(new InterchangeInfo("Mariano Comense", new String[]{"S2", "R16", "z221"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Cabiate", new String[]{"S2", "R16"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Meda", new String[]{"S2", "R16"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Seveso", new String[]{"S2", "S4", "R16"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Cesano Maderno", new String[]{"S2", "S4", "S9", "R16", "z250"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Boviso Masciago", new String[]{"S2", "S4", "R16"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Varedo", new String[]{"S2", "S4", "R16"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Palazzolo Milanese", new String[]{"S2", "S4", "R16"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Paderno Dugnano", new String[]{"S2", "S4", "R16", "566"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Cormano Cusano Milanino", new String[]{"S2", "S4", "R16", "727"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Bruzzano Parco Nord", new String[]{"S2", "S4", "R16"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Affori", new String[]{"S2", "S4", "R16", "M3", "NM3", "35", "40", "41", "52", "70"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S2", "S1", "S3", "S4", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S2", "S1", "S5", "S6", "S12", "S13", "2", "92"}, "tram.fill", "Main", 13));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S2", "S1", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S2", "S1", "S5", "S6", "S12", "S13", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S2", "S1", "S5", "S6", "S12", "S13", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S2", "S1", "S5", "S6", "S12", "S13", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 17));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S2", "S1", "S5", "S6", "S12", "S13", "91", "93"}, "tram.fill.tunnel", "Main", 18));
        interchanges.add(new InterchangeInfo("Milano Rogoredo", new String[]{"S2", "S1", "S9", "S12", "S13", "S19", "R31", "R34", "R38", "RE8", "RE11", "RE13", "M3", "NM3", "77", "88", "95", "140"}, "lightrail", "Main", 19));

        /// Suburbano S3
        interchanges.add(new InterchangeInfo("Saronno", new String[]{"S3", "S1", "S9", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Saronno Sud", new String[]{"S3", "S1", "S9"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Caronno Pertusella", new String[]{"S3", "S1"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Cesate", new String[]{"S3", "S1"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Garbagnate Milanese", new String[]{"S3", "S1", "S13", "RE13"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Garbagnate Parco Delle Groane", new String[]{"S3", "S1"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Bollate Nord", new String[]{"S3", "S1"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Bollate Centro", new String[]{"S3", "S1", "S13", "RE13"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Novate Milanese", new String[]{"S3", "S1"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Quarto Oggiaro", new String[]{"S3", "S1", "40"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S3", "S1", "S2", "S4", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Domodossola", new String[]{"S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "M5", "1", "19"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Cadorna", new String[]{"S3", "S4", "R16", "RE17", "R22", "R27", "RE1", "RE7", "MXP2", "M1", "NM1", "M2", "NM2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "tram.fill.tunnel", "Main", 12));

        /// Suburbano S4
        interchanges.add(new InterchangeInfo("Camnago - Lentate", new String[]{"S4", "S11"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Seveso", new String[]{"S4", "S2", "R16"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Cesano Maderno", new String[]{"S4", "S2", "S9", "R16", "z250"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Boviso Masciago", new String[]{"S4", "S2", "R16"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Varedo", new String[]{"S4", "S2", "R16"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Palazzolo Milanese", new String[]{"S4", "S2", "R16"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Paderno Dugnano", new String[]{"S4", "S2", "R16", "566"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Cormano Cusano Milanino", new String[]{"S4", "S2", "R16", "727"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Bruzzano Parco Nord", new String[]{"S4", "S2", "R16"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Affori", new String[]{"S4", "S2", "R16", "M3", "NM3", "35", "40", "41", "52", "70"}, "tram.fill.tunnel", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S4", "S1", "S2", "S3", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Domodossola", new String[]{"S4", "S3", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "M5", "1", "19"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Cadorna", new String[]{"S4", "S3", "R16", "RE17", "R22", "R27", "RE1", "RE7", "MXP2", "M1", "NM1", "M2", "NM2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "tram.fill.tunnel", "Main", 13));

        /// Suburbano S5
        interchanges.add(new InterchangeInfo("Varese", new String[]{"S5", "S40", "S50", "RE5"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Gazzada Schianno Morazzone", new String[]{"S5", "RE5"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Castronno", new String[]{"S5", "RE5"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Albizzate Solbiate Arno", new String[]{"S5", "RE5"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Cavaria", new String[]{"S5", "RE5"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Gallarate", new String[]{"S5", "S30", "S50", "R21", "R23", "RV", "RE4", "RE5", "MXP1"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Busto Arsizio", new String[]{"S5", "S50", "R21", "R23", "RV", "RE4", "RE5"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Legnano", new String[]{"S5", "R21", "RV", "RE5", "z601", "z602", "z611", "z627", "z636", "z642"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Canegrate", new String[]{"S5", "R21", "z611"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Parabiago", new String[]{"S5", "R21", "R23", "RE5", "z644"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Vanzago Pogliano", new String[]{"S5", "RE5"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Rho", new String[]{"S5", "S6", "S11", "RV", "431", "433", "z616"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Rho Fiera Milano", new String[]{"S5", "S6", "S11", "R21", "R23", "RE4", "RE5", "AV", "M1", "542", "561"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Certosa", new String[]{"S5", "S6", "S11", "1", "12", "35", "40", "57"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Milano Villapizzone", new String[]{"S5", "S6", "S11", "R6", "RE2"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S5", "S1", "S2", "S6", "S12", "S13", "2", "92"}, "tram.fill", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S5", "S1", "S2", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S5", "S1", "S2", "S6", "S12", "S13", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 17));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S5", "S1", "S2", "S6", "S12", "S13", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 18));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S5", "S1", "S2", "S6", "S12", "S13", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 19));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S5", "S1", "S2", "S6", "S12", "S13", "91", "93"}, "tram.fill.tunnel", "Main", 20));
        interchanges.add(new InterchangeInfo("Milano Forlanini", new String[]{"S5", "S6", "S9", "R38", "RE8", "RE13", "M4", "NM4", "12", "27", "45", "175", "973", "N27"}, "tram.fill.tunnel", "Main", 21));
        interchanges.add(new InterchangeInfo("Segrate", new String[]{"S5", "S6"}, "lightrail", "Main", 22));
        interchanges.add(new InterchangeInfo("Pioltello Limito", new String[]{"S5", "S6", "R4", "RE2", "RE6", "z402"}, "lightrail", "Main", 23));
        interchanges.add(new InterchangeInfo("Vignate", new String[]{"S5", "S6", "R4", "z401"}, "lightrail", "Main", 24));
        interchanges.add(new InterchangeInfo("Melzo", new String[]{"S5", "S6", "R4", "z401", "z404", "z407", "z411"}, "bus.fill", "Main", 25));
        interchanges.add(new InterchangeInfo("Pozzuolo Martesana", new String[]{"S5", "S6", "R4"}, "lightrail", "Main", 26));
        interchanges.add(new InterchangeInfo("Trecella", new String[]{"S5", "S6", "R4"}, "lightrail", "Main", 27));
        interchanges.add(new InterchangeInfo("Cassano D'Adda", new String[]{"S5", "S6", "R4", "z405", "z407"}, "lightrail", "Main", 28));
        interchanges.add(new InterchangeInfo("Treviglio", new String[]{"S5", "S6", "R2", "R4", "R6", "R7", "RE6"}, "lightrail", "Main", 29));

        /// Suburbano S6
        interchanges.add(new InterchangeInfo("Novara", new String[]{"S6", "R25", "RV"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Trecate", new String[]{"S6", "RV"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Magenta", new String[]{"S6", "RV", "z559", "z620", "z641", "z642", "z646"}, "bus.fill", "Main", 2));
        interchanges.add(new InterchangeInfo("Corbetta - S. Stefano Ticino", new String[]{"S6", "RV", "z552", "z642"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Vittuone - Arluno", new String[]{"S6", "RV", "z622", "z643"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Pregnana Milanese", new String[]{"S6", "z616", "z649"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Rho", new String[]{"S6", "S5", "S11", "RV", "431", "433", "z616"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Rho Fiera Milano", new String[]{"S6", "S5", "S11", "R21", "R23", "RE4", "RE5", "AV", "M1", "542", "561"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Milano Certosa", new String[]{"S6", "S5", "S11", "1", "12", "35", "40", "57"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Villapizzone", new String[]{"S6", "S5", "S11", "R6", "RE2"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S6", "S1", "S2", "S5", "S12", "S13", "2", "92"}, "tram.fill", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S6", "S1", "S2", "S5", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S6", "S1", "S2", "S5", "S12", "S13", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S6", "S1", "S2", "S5", "S12", "S13", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S6", "S1", "S2", "S5", "S12", "S13", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S6", "S1", "S2", "S5", "S12", "S13", "91", "93"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano Forlanini", new String[]{"S6", "S5", "S9", "R38", "RE8", "RE13", "M4", "NM4", "12", "27", "45", "175", "973", "N27"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Segrate", new String[]{"S6", "S5"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Pioltello Limito", new String[]{"S6", "S5", "R4", "RE2", "RE6", "z402"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Vignate", new String[]{"S6", "S5", "R4", "z401"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("Melzo", new String[]{"S6", "S5", "R4", "z401", "z404", "z407", "z411"}, "bus.fill", "Main", 20));
        interchanges.add(new InterchangeInfo("Pozzuolo Martesana", new String[]{"S6", "S5", "R4"}, "lightrail", "Main", 21));
        interchanges.add(new InterchangeInfo("Trecella", new String[]{"S6", "S5", "R4"}, "lightrail", "Main", 22));
        interchanges.add(new InterchangeInfo("Cassano D'Adda", new String[]{"S6", "S5", "R4", "z405", "z407"}, "lightrail", "Main", 23));
        interchanges.add(new InterchangeInfo("Treviglio", new String[]{"S6", "S5", "R2", "R4", "R6", "R7", "RE6"}, "lightrail", "Main", 24));

        /// Suburbano S7
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S7", "S1", "S2", "S5", "S6", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 0));
        interchanges.add(new InterchangeInfo("Milano Greco Pirelli", new String[]{"S7", "S8", "S9", "S11", "R4", "R13", "R14", "R38", "RE13", "RE80", "7", "52", "81", "86", "174"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Sesto S. Giovanni", new String[]{"S7", "S8", "S9", "S11", "R13", "R14", "RE8", "RE80", "M1", "NM1", "700", "702", "712", "727", "729"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Monza", new String[]{"S7", "S8", "S9", "S11", "R7", "R13", "R14", "RE8", "RE13", "RE80", "z201", "z203", "z205", "z206", "z208", "z211", "z213", "z221", "z222", "z228"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Monza Sobborghi", new String[]{"S7"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Villasanta", new String[]{"S7"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Buttafava", new String[]{"S7"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Biassono - Lesmo Parco", new String[]{"S7"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Macherio - Canonica", new String[]{"S7"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Triuggio - Ponte Albiate", new String[]{"S7", "z233"}, "bus.fill", "Main", 9));
        interchanges.add(new InterchangeInfo("Carate Calò", new String[]{"S7"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Villa Raverio", new String[]{"S7"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Besana", new String[]{"S7", "z323", "z242"}, "bus.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Renate - Veduggio", new String[]{"S7", "z242"}, "bus.fill", "Main", 13));
        interchanges.add(new InterchangeInfo("Cassago Nibionno Bulciago", new String[]{"S7"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Costa Masnaga", new String[]{"S7"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("Molteno", new String[]{"S7", "R18"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Oggiono", new String[]{"S7", "R18"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Sala Al Barro - Galbiate", new String[]{"S7", "R18"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Civate", new String[]{"S7", "R18"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("Valmadrera", new String[]{"S7", "R18"}, "lightrail", "Main", 20));
        interchanges.add(new InterchangeInfo("Lecco", new String[]{"S7", "S8", "R7", "R13", "R18", "RE8"}, "lightrail", "Main", 21));

        /// Suburbano S8
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S8", "S1", "S2", "S5", "S6", "S7", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 0));
        interchanges.add(new InterchangeInfo("Milano Greco Pirelli", new String[]{"S8", "S7", "S9", "S11", "R4", "R13", "R14", "R38", "RE13", "RE80", "7", "52", "81", "86", "174"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Sesto S. Giovanni", new String[]{"S8", "S7", "S9", "S11", "R13", "R14", "RE8", "RE80", "M1", "NM1", "700", "702", "712", "727", "729"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Monza", new String[]{"S8", "S7", "S9", "S11", "R7", "R13", "R14", "RE8", "RE13", "RE80", "z201", "z203", "z205", "z206", "z208", "z211", "z213", "z221", "z222", "z228"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Arcore", new String[]{"S8", "R13", "R14", "z208"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Carnate Usmate", new String[]{"S8", "R13", "R14", "RE8"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Osnago", new String[]{"S8"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Cernusco - Merate", new String[]{"S8", "R13", "RE8"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Olgiate - Calco - Brivio", new String[]{"S8"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Airuno", new String[]{"S8"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Calolziocorte Olginate", new String[]{"S8", "R7", "RE8"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Lecco Maggianico", new String[]{"S8", "R7"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Lecco", new String[]{"S8", "S7", "R7", "R13", "R18", "RE8"}, "lightrail", "Main", 12));

        /// Suburbano S9
        interchanges.add(new InterchangeInfo("Saronno", new String[]{"S9", "S1", "S3", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Saronno Sud", new String[]{"S9", "S1", "S3"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Ceriano Laghetto - Solaro", new String[]{"S9"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Cesano Maderno", new String[]{"S9", "S2", "S4", "R16"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Seveso Baruccana", new String[]{"S9"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Seregno", new String[]{"S9", "S11", "RE13", "RE80", "z233"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Desio", new String[]{"S9", "S11", "RE80", "z250", "z251"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Lissone - Muggiò", new String[]{"S9", "S11", "z227", "z238"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Monza", new String[]{"S9", "S7", "S8", "S11", "R7", "R13", "R14", "RE8", "RE13", "RE80", "z201", "z203", "z205", "z206", "z208", "z211", "z213", "z221", "z222", "z228"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Sesto S. Giovanni", new String[]{"S9", "S7", "S8", "S11", "R13", "R14", "RE8", "RE80", "M1", "NM1", "700", "702", "712", "727", "729"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Greco Pirelli", new String[]{"S9", "S7", "S8", "S11", "R4", "R13", "R14", "R38", "RE13", "RE80", "7", "52", "81", "86", "174"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Milano Lambrate", new String[]{"S9", "R4", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "RV", "M2", "NM2", "19", "39", "45", "54", "81", "93", "175", "924"}, "tram.fill.tunnel", "Main", 11));
        interchanges.add(new InterchangeInfo("Milano Forlanini", new String[]{"S9", "S5", "S6", "R38", "RE8", "RE13", "M4", "NM4", "12", "27", "45", "175", "973", "N27"}, "tram.fill.tunnel", "Main", 12));
        interchanges.add(new InterchangeInfo("Milano Scalo Romana", new String[]{"S9", "S19", "R31", "M3", "NM3", "65", "90", "91", "92"}, "tram.fill.tunnel", "Main", 13));
        interchanges.add(new InterchangeInfo("Milano Tibaldi", new String[]{"S9", "S19", "R31", "15", "59", "90", "91"}, "tram.fill", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Romolo", new String[]{"S9", "S19", "R31", "M2", "NM2", "47", "90", "91", "71", "324", "325", "z553"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano San Cristoforo", new String[]{"S9", "S19", "R31", "M4", "NM4", "47", "49", "95", "324", "325", "326", "351", "z553"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Corsico", new String[]{"S9", "S19", "R31", "326"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Cesano Boscone", new String[]{"S9", "S19", "R31"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Trezzano Sul Naviglio", new String[]{"S9", "S19", "R31"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("Gaggiano", new String[]{"S9", "S19", "R31", "z557"}, "lightrail", "Main", 20));
        interchanges.add(new InterchangeInfo("Albairate Vermezzo", new String[]{"S9", "S19", "R31", "z554"}, "lightrail", "Main", 21));

        /// Suburbano S11
        interchanges.add(new InterchangeInfo("Rho", new String[]{"S11", "S5", "S6", "RV", "431", "433", "z616"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Rho Fiera Milano", new String[]{"S11", "S5", "S6", "R21", "R23", "RE4", "RE5", "AV", "M1", "542", "561"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Milano Certosa", new String[]{"S11", "S5", "S6", "1", "12", "35", "40", "57"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Milano Villapizzone", new String[]{"S11", "S5", "S6", "R6", "RE2"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S11", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Milano Greco Pirelli", new String[]{"S11", "S7", "S8", "S9", "R4", "R13", "R14", "R38", "RE13", "RE80", "7", "52", "81", "86", "174"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Sesto S. Giovanni", new String[]{"S11", "S7", "S8", "S9", "R13", "R14", "RE8", "RE80", "M1", "NM1", "700", "702", "712", "727", "729"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Monza", new String[]{"S11", "S7", "S8", "S9", "R7", "R13", "R14", "RE8", "RE13", "RE80", "z201", "z203", "z205", "z206", "z208", "z211", "z213", "z221", "z222", "z228"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Lissone - Muggiò", new String[]{"S11", "S9", "z227", "z238"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Desio", new String[]{"S11", "S9", "RE80", "z250", "z251"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Seregno", new String[]{"S11", "S9", "RE13", "RE80", "z233"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Camnago - Lentate", new String[]{"S11", "S4"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Carimate", new String[]{"S11"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Cantù - Cermenate", new String[]{"S11", "RE80"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Cucciago", new String[]{"S11"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Como Camerlata", new String[]{"S11", "S10", "S40", "R17", "R18", "RE7", "RE13", "RE80"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("Como San Giovanni", new String[]{"S11", "S10", "S40", "R18", "RE13", "RE80"}, "lightrail", "Main", 16));

        /// Suburbano S12
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S12", "S1", "S2", "S3", "S4", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S12", "S1", "S2", "S5", "S6", "S13", "2", "92"}, "tram.fill", "Main", 1));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S12", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S12", "S1", "S2", "S5", "S6", "S13", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 3));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S12", "S1", "S2", "S5", "S6", "S13", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S12", "S1", "S2", "S5", "S6", "S13", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S12", "S1", "S2", "S5", "S6", "S13", "91", "93"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Milano Rogoredo", new String[]{"S12", "S1", "S2", "S9", "S13", "S19", "R31", "R34", "R38", "RE8", "RE11", "RE13", "M3", "NM3", "77", "88", "95", "140"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("San Donato Milanese", new String[]{"S12", "S1"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Borgolombardo", new String[]{"S12", "S1", "132"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("San Giuliano Milanese", new String[]{"S12", "S1", "121"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Melegnano", new String[]{"S12", "S1"}, "lightrail", "Main", 11));

        /// Suburbano S13
        interchanges.add(new InterchangeInfo("Garbagnate Milanese", new String[]{"S13", "S1", "S3", "RE13"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Bollate Centro", new String[]{"S13", "S1", "S3", "RE13"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"S13", "S1", "S2", "S3", "S4", "S12", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "MXP2", "82", "92"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Milano Lancetti", new String[]{"S13", "S1", "S2", "S5", "S6", "S12", "2", "92"}, "tram.fill", "Main", 3));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"S13", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "MXP1", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Milano Repubblica", new String[]{"S13", "S1", "S2", "S5", "S6", "S12", "M3", "NM3", "1", "9", "33", "43"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Milano Porta Venezia", new String[]{"S13", "S1", "S2", "S5", "S6", "S12", "M1", "NM1", "5", "9", "33"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Milano Dateo", new String[]{"S13", "S1", "S2", "S5", "S6", "S12", "M4", "NM4", "54", "61", "92"}, "tram.fill.tunnel", "Main", 7));
        interchanges.add(new InterchangeInfo("Milano Porta Vittoria", new String[]{"S13", "S1", "S2", "S5", "S6", "S12", "91", "93"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Rogoredo", new String[]{"S13", "S1", "S2", "S9", "S12", "S19", "R31", "R34", "R38", "RE8", "RE11", "RE13", "M3", "NM3", "77", "88", "95", "140"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Locate Triulzi", new String[]{"S13", "220"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Pieve Emanuele", new String[]{"S13", "220", "222", "328"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Villamaggiore", new String[]{"S13"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Certosa di Pavia", new String[]{"S13"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Pavia", new String[]{"S13", "R34", "R35", "R36", "R37", "RE13", "RV"}, "lightrail", "Main", 14));

        /// Suburbano S19
        interchanges.add(new InterchangeInfo("Milano Rogoredo", new String[]{"S19", "S1", "S2", "S9", "S12", "S13", "R31", "R34", "R38", "RE8", "RE11", "RE13", "M3", "NM3", "77", "88", "95", "140"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Milano Scalo Romana", new String[]{"S19", "S9", "R31", "M3", "NM3", "65", "90", "91", "92"}, "tram.fill.tunnel", "Main", 1));
        interchanges.add(new InterchangeInfo("Milano Tibaldi", new String[]{"S19", "S9", "R31", "15", "59", "90", "91"}, "tram.fill", "Main", 2));
        interchanges.add(new InterchangeInfo("Milano Romolo", new String[]{"S19", "S9", "R31", "M2", "NM2", "47", "90", "91", "71", "324", "325", "z553"}, "tram.fill.tunnel", "Main", 3));
        interchanges.add(new InterchangeInfo("Milano San Cristoforo", new String[]{"S19", "S9", "R31", "M4", "NM4", "47", "49", "95", "324", "325", "326", "351", "z553"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Corsico", new String[]{"S19", "S9", "R31", "326"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Cesano Boscone", new String[]{"S19", "S9", "R31"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Trezzano Sul Naviglio", new String[]{"S19", "S9", "R31"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Gaggiano", new String[]{"S19", "S9", "R31", "z557"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Albairate Vermezzo", new String[]{"S19", "S9", "R31", "z554"}, "lightrail", "Main", 9));

        /// Suburbano S31
        interchanges.add(new InterchangeInfo("Brescia", new String[]{"S31", "R1", "R3", "R4", "R5", "R7", "R8", "RE3", "RE6", "AV"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Brescia Borgo San Giovanni", new String[]{"S31", "R3"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Brescia Violino", new String[]{"S31", "R3"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Castegnato", new String[]{"S31", "R3"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Paderno Franciacorta", new String[]{"S31", "R3"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Passirano", new String[]{"S31", "R3"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Bornato Calino", new String[]{"S31", "R3", "R9"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Borgonato Adro", new String[]{"S31", "R3"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Provaglio Timoline", new String[]{"S31", "R3"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Iseo", new String[]{"S31", "R3", "R9", "RE3"}, "lightrail", "Main", 9));

        return interchanges;
    }

    public static List<InterchangeInfo> getMalpensaExpressInterchanges(Context context) {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* MALPENSA EXPRESS LINES
        /// Malpensa Express 1
        interchanges.add(new InterchangeInfo("Gallarate", new String[]{"MXP1", "S5", "S30", "S50", "R21", "R23", "RV", "RE4", "RE5"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T2", new String[]{"MXP1", context.getString(R.string.airportKey), "S50", "MXP2"}, "airplane.departure", "Main", 1));
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T1", new String[]{"MXP1", context.getString(R.string.airportKey), "S50", "MXP2"}, "airplane.departure", "Main", 2));
        interchanges.add(new InterchangeInfo("Ferno - Lonate Pozzolo", new String[]{"MXP1", "S50"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Busto Arsizio Nord", new String[]{"MXP1", "S50", "R27", "MXP2"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Castellanza", new String[]{"MXP1", "R27"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Rescaldina", new String[]{"MXP1", "R27"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Saronno", new String[]{"MXP1", "S1", "S3", "S9", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP2"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"MXP1", "S1", "S2", "S3", "S4", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP2", "82", "92"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Milano Porta Garibaldi", new String[]{"MXP1", "S1", "S2", "S5", "S6", "S7", "S8", "S9", "S11", "S12", "S13", "AV", "R6", "R13", "R14", "R21", "R23", "R34", "RE2", "RE5", "RE6", "RE13", "M2", "NM2", "M5", "10", "33", "N25", "N26"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Milano Centrale", new String[]{"MXP1", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "RE80", "M3", "NM3", "M2", "NM2", "5", "9", "10", "42", "60", "81", "87", "90", "91", "92", "728", "N25", "N26"}, "tram.fill.tunnel", "Main", 10));

        /// Malpensa Express 2
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T2", new String[]{"MXP2", context.getString(R.string.airportKey), "S50", "MXP1"}, "airplane.departure", "Main", 0));
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T1", new String[]{"MXP2", context.getString(R.string.airportKey), "S50", "MXP1"}, "airplane.departure", "Main", 1));
        interchanges.add(new InterchangeInfo("Busto Arsizio Nord", new String[]{"MXP2", "S50", "R27", "MXP1"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Saronno", new String[]{"MXP2", "S1", "S3", "S9", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Milano Bovisa", new String[]{"MXP2", "S1", "S2", "S3", "S4", "S12", "S13", "R16", "R17", "R22", "R27", "RE1", "RE7", "RE13", "MXP1", "82", "92"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Milano Domodossola", new String[]{"MXP2", "S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "M5", "1", "19"}, "tram.fill.tunnel", "Main", 5));
        interchanges.add(new InterchangeInfo("Milano Cadorna", new String[]{"MXP2", "S3", "S4", "R16", "RE17", "R22", "R27", "RE1", "RE7", "M1", "NM1", "M2", "NM2", "1", "2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "tram.fill.tunnel", "Main", 6));

        return interchanges;
    }

    public static List<InterchangeInfo> getTILOInterchanges(Context context) {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* TILO LINES
        /// TILO S10
        interchanges.add(new InterchangeInfo("Como Camerlata", new String[]{"S10", "S11", "S40", "R17", "R18", "RE7", "RE13", "RE80"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Como San Giovanni", new String[]{"S10", "S11", "S40", "R18", "RE13", "RE80"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Chiasso", new String[]{"S10", "S40", "RE80"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Balerna", new String[]{"S10", "S40", "RE80"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Mendrisio", new String[]{"S10", "S40", "S50", "S90", "RE80"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Mendrisio San Martino", new String[]{"S10", "S50", "S90", "RE80"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Capolago - Riva San Vitale", new String[]{"S10", "S50", "S90", "RE80"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Maroggia - Melano", new String[]{"S10", "S50", "S90", "RE80"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Melide", new String[]{"S10", "S50", "S90", "RE80"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Lugano Paradiso", new String[]{"S10", "S40", "S50", "S90", "RE80"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Lugano", new String[]{"S10", "S40", "S50", "S90", "RE80"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Giubiasco", new String[]{"S10", "S20", "S30", "S90"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Bellinzona", new String[]{"S10", "S20", "S30", "S50", "S90"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Arbedo - Castione", new String[]{"S10", "S20", "S50", "S90"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Biasca", new String[]{"S10", "S50"}, "lightrail", "Main", 14));

        /// TILO S20
        interchanges.add(new InterchangeInfo("Arbedo - Castione", new String[]{"S20", "S10", "S50", "S90"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Bellinzona", new String[]{"S20", "S10", "S30", "S50", "S90"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Giubiasco", new String[]{"S20", "S10", "S30", "S90"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("San Antonino", new String[]{"S20", "S30", "RE80"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Cadenazzo", new String[]{"S20", "S30", "RE80"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Riazzino", new String[]{"S20", "RE80"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Gordola", new String[]{"S20", "RE80"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Tenero", new String[]{"S20", "RE80"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Minusio", new String[]{"S20", "RE80"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Locarno", new String[]{"S20", "RE80"}, "lightrail", "Main", 9));

        /// TILO S30
        interchanges.add(new InterchangeInfo("Gallarate", new String[]{"S30", "S5", "S50", "R21", "R23", "RV", "RE4", "RE5", "MXP1"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Besnate", new String[]{"S30", "R21"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Mornago Cimbro", new String[]{"S30", "R21"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Ternate Varano Borghi", new String[]{"S30", "R21"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Travedona Biandronno", new String[]{"S30", "R21"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Besozzo", new String[]{"S30", "R21"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Sangiano", new String[]{"S30", "R21"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Laveno Mombello", new String[]{"S30", "R21"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Calde'", new String[]{"S30", "R21"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Porto Valtravaglia", new String[]{"S30", "R21"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Luino", new String[]{"S30", "R21"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Colmegna", new String[]{"S30"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Maccagno", new String[]{"S30"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Pino - Tronzano", new String[]{"S30"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Ranzo - San Abbondio", new String[]{"S30"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Gerra", new String[]{"S30"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("San Nazzaro", new String[]{"S30"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Magradino - Vira", new String[]{"S30"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Quartino", new String[]{"S30"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Cadenazzo", new String[]{"S30", "S20", "RE80"}, "lightrail", "Main", 19));

        /// TILO S40
        interchanges.add(new InterchangeInfo("Varese", new String[]{"S40", "S5", "S50", "RE5"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Induno Olona", new String[]{"S40", "S50", "RE5"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Arcisate", new String[]{"S40", "S50", "RE5"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Cantello Gaggiolo", new String[]{"S40", "S50"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Stabio", new String[]{"S40", "S50", "RE80"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Mendrisio", new String[]{"S40", "S10", "S50", "S90", "RE80"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Balerna", new String[]{"S40", "S10", "RE80"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Chiasso", new String[]{"S40", "S10", "RE80"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Como San Giovanni", new String[]{"S40", "S10", "S11", "R18", "RE13", "RE80"}, "lightrail", "Main", 8));

        /// TILO S50
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T2", new String[]{"S50", context.getString(R.string.airportKey), "MXP1", "MXP2"}, "airplane.departure", "Main", 0));
        interchanges.add(new InterchangeInfo("Malpensa Aereoporto T1", new String[]{"S50", context.getString(R.string.airportKey), "MXP1", "MXP2"}, "airplane.departure", "Main", 1));
        interchanges.add(new InterchangeInfo("Ferno - Lonate Pozzolo", new String[]{"S50", "MXP1"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Busto Arsizio Nord", new String[]{"S50", "R27", "MXP1", "MXP2"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Busto Arsizio", new String[]{"S50", "S5", "R21", "R23", "RV", "RE4", "RE5"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Gallarate", new String[]{"S50", "S5", "S30", "R21", "R23", "RV", "RE4", "RE5", "MXP1"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Varese", new String[]{"S50", "S5", "S40", "RE5"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Induno Olona", new String[]{"S50", "S40", "RE5"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Arcisate", new String[]{"S50", "S40", "RE5"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Cantello Gaggiolo", new String[]{"S50", "S40"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Stabio", new String[]{"S50", "S40", "RE80"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Mendrisio", new String[]{"S50", "S10", "S40", "S90", "RE80"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Capolago - Riva San Vitale", new String[]{"S50", "S10", "S90", "RE80"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Maroggia - Melano", new String[]{"S50", "S10", "S90", "RE80"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Melide", new String[]{"S50", "S10", "S90", "RE80"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Lugano Paradiso", new String[]{"S50", "S10", "S40", "S90", "RE80"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("Lugano", new String[]{"S50", "S10", "S40", "S90", "RE80"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Bellinzona", new String[]{"S50", "S10", "S20", "S30", "S90"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Arbedo - Castione", new String[]{"S50", "S10", "S20", "S90"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Biasca", new String[]{"S50", "S10"}, "lightrail", "Main", 19));

        /// TILO S90
        interchanges.add(new InterchangeInfo("Bellinzona", new String[]{"S90", "S10", "S20", "S30", "S50"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Giubiasco", new String[]{"S90", "S10", "S20", "S30"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Rivera - Bironico", new String[]{"S90", "S10", "S50"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Mezzovico", new String[]{"S90", "S10", "S40", "S50"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Taverne - Torricella", new String[]{"S90", "S10", "S40", "S50"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Lamone - Cadempino", new String[]{"S90", "S10", "S40", "S50"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Lugano", new String[]{"S90", "S10", "S40", "S50", "RE80"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Lugano Paradiso", new String[]{"S90", "S10", "S40", "S50", "RE80"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Melide", new String[]{"S90", "S10", "S50", "RE80"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Maroggia - Melano", new String[]{"S90", "S10", "S50", "RE80"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Capolago - Riva San Vitale", new String[]{"S90", "S10", "S50", "RE80"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Mendrisio San Martino", new String[]{"S90", "S10", "S50", "RE80"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Mendrisio", new String[]{"S90", "S10", "S40", "S50", "RE80"}, "lightrail", "Main", 12));

        /// TILO RE80
        interchanges.add(new InterchangeInfo("Milano Centrale", new String[]{"RE80", "AV", "R4", "RE2", "RE4", "RE6", "RE8", "RE11", "RE13", "MXP1", "M2", "NM2", "M3", "NM3", "5", "9", "10", "42", "60", "81", "87", "90", "91", "92", "728", "N25", "N26"}, "tram.fill.tunnel", "Main", 0));
        interchanges.add(new InterchangeInfo("Monza", new String[]{"RE80", "S7", "S8", "S9", "S11", "R7", "R13", "R14", "RE8", "RE13", "z201", "z203", "z205", "z206", "z208", "z211", "z213", "z221", "z222", "z228"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Seregno", new String[]{"RE80", "S9", "S11", "RE13", "z233"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Como San Giovanni", new String[]{"RE80", "S10", "S11", "S40", "R18", "RE13"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Chiasso", new String[]{"RE80", "S10", "S40"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Balerna", new String[]{"RE80", "S10", "S40"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Mendrisio", new String[]{"RE80", "S10", "S40", "S50", "S90"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Mendrisio San Martino", new String[]{"RE80", "S10", "S50", "S90"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Capolago - Riva San Vitale", new String[]{"RE80", "S10", "S50", "S90"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Maroggia - Melano", new String[]{"RE80", "S10", "S50", "S90"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Melide", new String[]{"RE80", "S10", "S50", "S90"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Lugano Paradiso", new String[]{"RE80", "S10", "S40", "S50", "S90"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Lugano", new String[]{"RE80", "S10", "S40", "S50", "S90"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("San Antonino", new String[]{"RE80", "S20", "S30"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Cadenazzo", new String[]{"RE80", "S20", "S30"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Riazzino", new String[]{"RE80", "S20"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("Gordola", new String[]{"RE80", "S20"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Tenero", new String[]{"RE80", "S20"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Minusio", new String[]{"RE80", "S20"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Locarno", new String[]{"RE80", "S20"}, "lightrail", "Main", 19));

        return interchanges;
    }

    public static List<InterchangeInfo> getRegionalInterchanges() {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* TRAM LINES
        /// Regionale R1
        interchanges.add(new InterchangeInfo("Brescia", new String[]{"R1", "S31", "AV", "R3", "R4", "R5", "R7", "R8", "RE3", "RE6"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Ospitaletto Travagliato", new String[]{"R1", "R4", "R5"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Rovato", new String[]{"R1", "R4", "R5", "R7", "R9", "RE6"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Coccaglio", new String[]{"R1", "R5"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Cologne", new String[]{"R1", "R5"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Palazzolo Sull'Oglio", new String[]{"R1", "R5"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Grumello Del Monte", new String[]{"R1", "R5"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Chiuduno", new String[]{"R1", "R5"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Montello Gorlago", new String[]{"R1", "R5"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Albano Sant'Alessandro", new String[]{"R1", "R5"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Seriate", new String[]{"R1", "R5"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Bergamo", new String[]{"R1", "R2", "R5", "RE2", "RV"}, "lightrail", "Main", 11));

        /// Regionale R2
        interchanges.add(new InterchangeInfo("Treviglio", new String[]{"R2", "S5", "S6", "R4", "R6", "R7", "RE6"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Treviglio Ovest", new String[]{"R2", "RE2", "RV", "z405"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Verdello Dalmine", new String[]{"R2", "RE2", "RV"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Levate", new String[]{"R2"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Stezzano", new String[]{"R2"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Stezzano", new String[]{"R2"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Bergamo", new String[]{"R2", "R1", "R5", "R7", "RE2", "RV"}, "lightrail", "Main", 6));

        /// Regionale R3
        interchanges.add(new InterchangeInfo("Brescia", new String[]{"R3", "S31", "R1", "R4", "R5", "R7", "R8", "RE3", "RE6", "AV"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Brescia Borgo San Giovanni", new String[]{"R3", "S31"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Brescia Violino", new String[]{"R3", "S31"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Castegnato", new String[]{"R3", "S31"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Paderno Franciacorta", new String[]{"R3", "S31"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Passirano", new String[]{"R3", "S31"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Bornato Calino", new String[]{"R3", "S31", "R9"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Borgonato Adro", new String[]{"R3", "S31"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Provaglio Timoline", new String[]{"R3", "S31"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Iseo", new String[]{"R3", "S31", "R9", "RE3"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Pilzone", new String[]{"R3"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Sulzano", new String[]{"R3", "R9", "RE3"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Sale Marasino", new String[]{"R3", "RE3"}, "lightrail", "Main", 12));
        interchanges.add(new InterchangeInfo("Marone Zone", new String[]{"R3", "R9", "RE3"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Vello", new String[]{"R3"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Toline", new String[]{"R3"}, "lightrail", "Main", 15));
        interchanges.add(new InterchangeInfo("Pisogne", new String[]{"R3", "R9", "RE3"}, "lightrail", "Main", 16));
        interchanges.add(new InterchangeInfo("Pian Camuno-Gratacasolo", new String[]{"R3"}, "lightrail", "Main", 17));
        interchanges.add(new InterchangeInfo("Darfo - Corna", new String[]{"R3", "RE3"}, "lightrail", "Main", 18));
        interchanges.add(new InterchangeInfo("Boario Terme", new String[]{"R3", "RE3"}, "lightrail", "Main", 19));
        interchanges.add(new InterchangeInfo("Pian Di Borno", new String[]{"R3"}, "lightrail", "Main", 20));
        interchanges.add(new InterchangeInfo("Cogno - Esine", new String[]{"R3"}, "lightrail", "Main", 21));
        interchanges.add(new InterchangeInfo("Cividate - Malegno", new String[]{"R3"}, "lightrail", "Main", 22));
        interchanges.add(new InterchangeInfo("Breno", new String[]{"R3", "RE3"}, "lightrail", "Main", 23));
        interchanges.add(new InterchangeInfo("Ceto - Cerveno", new String[]{"R3"}, "lightrail", "Main", 24));
        interchanges.add(new InterchangeInfo("Capo Di Ponte", new String[]{"R3", "RE3"}, "lightrail", "Main", 25));
        interchanges.add(new InterchangeInfo("Cedegolo", new String[]{"R3", "RE3"}, "lightrail", "Main", 26));
        interchanges.add(new InterchangeInfo("Malonno", new String[]{"R3", "RE3"}, "lightrail", "Main", 27));
        interchanges.add(new InterchangeInfo("Sonico", new String[]{"R3"}, "lightrail", "Main", 28));
        interchanges.add(new InterchangeInfo("Edolo", new String[]{"R3", "RE3"}, "lightrail", "Main", 29));

        /// Regionale R4
        interchanges.add(new InterchangeInfo("Brescia", new String[]{"R4", "S31", "AV", "R1", "R3", "R5", "R7", "R8", "RE3", "RE6"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Ospitaletto Travagliato", new String[]{"R4", "R1", "R5"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Rovato", new String[]{"R4", "R1", "R5", "R7", "R9", "RE6"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Chiari", new String[]{"R4", "RE6"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Calcio", new String[]{"R4"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Romano", new String[]{"R4", "RE6"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Morengo Bariano", new String[]{"R4"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Vidalengo", new String[]{"R4"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Treviglio", new String[]{"R4", "S5", "S6", "R2", "R6", "R7", "RE6"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Cassano D'Adda", new String[]{"R4", "S5", "S6", "z405", "z407"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Trecella", new String[]{"R4", "S5", "S6"}, "lightrail", "Main", 10));
        interchanges.add(new InterchangeInfo("Pozzuolo Martesana", new String[]{"R4", "S5", "S6"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Melzo", new String[]{"R4", "S5", "S6", "z401", "z404", "z407", "z411"}, "bus.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Vignate", new String[]{"R4", "S5", "S6", "z401"}, "lightrail", "Main", 13));
        interchanges.add(new InterchangeInfo("Pioltello Limito", new String[]{"R4", "S5", "S6", "RE2", "RE6", "z402"}, "lightrail", "Main", 14));
        interchanges.add(new InterchangeInfo("Milano Lambrate", new String[]{"R4", "S9", "R6", "R7", "R34", "R38", "RE2", "RE6", "RE8", "RE11", "RE13", "RV", "M2", "NM2", "19", "39", "45", "54", "81", "93", "175", "924"}, "tram.fill.tunnel", "Main", 15));
        interchanges.add(new InterchangeInfo("Milano Greco Pirelli", new String[]{"R4", "S7", "S8", "S9", "S11", "R13", "R14", "R38", "RE13", "RE80", "7", "52", "81", "86", "174"}, "lightrail", "Main", 16));

        /// Regionale R5
        interchanges.add(new InterchangeInfo("Brescia", new String[]{"R5", "S31", "AV", "R1", "R3", "R4", "R7", "R8", "RE3", "RE6"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Folzano", new String[]{"R5", "R8"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Bagnolo Mella", new String[]{"R5"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Manerbio", new String[]{"R5"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Verolanuova", new String[]{"R5"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Robecco Pontevico", new String[]{"R5"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Olmeneta", new String[]{"R5", "R6"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Cremona", new String[]{"R5", "R6", "R37", "R39", "R40", "RE11"}, "lightrail", "Main", 7));

        /// Regionale R6
        interchanges.add(new InterchangeInfo("Treviglio", new String[]{"R6", "S5", "S6", "R2", "R4", "R7", "RE6"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Caravaggio", new String[]{"R6"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Capralba", new String[]{"R6"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Casaletto Vaprio", new String[]{"R6"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Crema", new String[]{"R6"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Madignano", new String[]{"R6"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Castelleone", new String[]{"R6"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Soresina", new String[]{"R6"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Casalbuttano", new String[]{"R6"}, "lightrail", "Main", 8));
        interchanges.add(new InterchangeInfo("Olmeneta", new String[]{"R6", "R5"}, "lightrail", "Main", 9));
        interchanges.add(new InterchangeInfo("Cremona", new String[]{"R6", "R5", "R37", "R39", "R40", "RE11"}, "lightrail", "Main", 10));

        /// Regionale R7
        interchanges.add(new InterchangeInfo("Lecco", new String[]{"R7", "S7", "S8", "R13", "R18", "RE8"}, "lightrail", "Main", 0));
        interchanges.add(new InterchangeInfo("Lecco Maggianico", new String[]{"R7", "S8"}, "lightrail", "Main", 1));
        interchanges.add(new InterchangeInfo("Vercurago San Girolamo", new String[]{"R7"}, "lightrail", "Main", 2));
        interchanges.add(new InterchangeInfo("Calolziocorte Olginate", new String[]{"R7", "S8", "RE8"}, "lightrail", "Main", 3));
        interchanges.add(new InterchangeInfo("Cisano Caprino Bergamasco", new String[]{"R7"}, "lightrail", "Main", 4));
        interchanges.add(new InterchangeInfo("Pontida", new String[]{"R7"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Ambivere Mapello", new String[]{"R7"}, "lightrail", "Main", 6));
        interchanges.add(new InterchangeInfo("Ponte San Pietro", new String[]{"R7", "R14"}, "lightrail", "Main", 7));
        interchanges.add(new InterchangeInfo("Bergamo", new String[]{"R7", "R1", "R2", "R5", "RE2", "RV"}, "lightrail", "Main", 8));

        return interchanges;
    }

    public static List<InterchangeInfo> getTramInterchanges(Context context) {
        List<InterchangeInfo> interchanges = new ArrayList<>();

        //* TRAM LINES
        /// Tram 1
        interchanges.add(new InterchangeInfo("Roserio (Ospedale Sacco)", new String[]{"1", "12", "560", "566"}, "tram.fill", "Main", 0));
        interchanges.add(new InterchangeInfo("Via Grassi", new String[]{"1", "12"}, "tram.fill", "Main", 1));
        interchanges.add(new InterchangeInfo("Largo Boccioni", new String[]{"1", "12", "40", "560"}, "tram.fill", "Main", 2));
        interchanges.add(new InterchangeInfo("Via Mambretti", new String[]{"1", "12", "40"}, "tram.fill", "Main", 3));
        interchanges.add(new InterchangeInfo("Via Palizzi Via Mambretti", new String[]{"1", "12", "40"}, "tram.fill", "Main", 4));
        interchanges.add(new InterchangeInfo("Certosa FS", new String[]{"1", "12", "S6", "S5", "S11", "35", "40", "57"}, "lightrail", "Main", 5));
        interchanges.add(new InterchangeInfo("Viale Espinasse Via Palizzi", new String[]{"1", "12", "40"}, "tram.fill", "Main", 6));
        interchanges.add(new InterchangeInfo("Piazzale Santorre di Santarosa", new String[]{"1", "12"}, "tram.fill", "Main", 7));
        interchanges.add(new InterchangeInfo("Viale Espinasse Via Nuvolone", new String[]{"1"}, "tram.fill", "Main", 8));
        interchanges.add(new InterchangeInfo("Viale Espinasse Via Casella", new String[]{"1"}, "tram.fill", "Main", 9));
        interchanges.add(new InterchangeInfo("Viale Espinasse Piazzale Accursio", new String[]{"1", "14", "69", "z602", "z603", "z6C3"}, "bus.fill", "Main", 10));
        interchanges.add(new InterchangeInfo("Viale Certosa Via Grosotto", new String[]{"1", "14", "48", "69"}, "tram.fill", "Main", 11));
        interchanges.add(new InterchangeInfo("Viale Certosa Viale Serra", new String[]{"1", "14", "48", "90", "91", "z602", "z603", "z6C3"}, "bus.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Piazza Firenze", new String[]{"1", "14", "19", "43", "48", "57", "69"}, "bus.fill", "Main", 13));
        interchanges.add(new InterchangeInfo("Corso Sempione Via E. Filiberto", new String[]{"1", "19", "43", "48", "57", "78", "z602", "z603", "z6C3"}, "bus.fill", "Main", 14));
        interchanges.add(new InterchangeInfo("Corso Sempione Via Arona", new String[]{"1", "19", "43", "57"}, "tram.fill", "Main", 15));
        interchanges.add(new InterchangeInfo("Domodossola FN M5", new String[]{"1", "M5", "19", "43", "57"}, "tram.fill.tunnel", "Main", 16));
        interchanges.add(new InterchangeInfo("Corso Sempione Via Procaccini", new String[]{"1", "10", "43", "57", "z602", "z603", "z6C3"}, "bus.fill", "Main", 17));
        interchanges.add(new InterchangeInfo("Corso Sempione Via Villasanta", new String[]{"1", "10", "43", "57", "z602", "z603", "z6C3"}, "bus.fill", "Main", 18));
        interchanges.add(new InterchangeInfo("Corso Sempione Via Canova", new String[]{"1", "10", "N25", "N26"}, "tram.fill", "Main", 19));
        interchanges.add(new InterchangeInfo("Arco Della Pace", new String[]{"1", "10"}, "tram.fill", "Main", 20));
        interchanges.add(new InterchangeInfo("Via Pagano Via Canova", new String[]{"1", "10", "85"}, "tram.fill", "Main", 21));
        interchanges.add(new InterchangeInfo("Largo Quinto Alpini", new String[]{"1", "2", "10", "19", "85"}, "tram.fill", "Main", 22));
        interchanges.add(new InterchangeInfo("Via Venti Settembre", new String[]{"1", "2", "19"}, "tram.fill", "Main", 23));
        interchanges.add(new InterchangeInfo("Piazza Virgilio", new String[]{"1", "2", "19"}, "tram.fill", "Main", 24));
        interchanges.add(new InterchangeInfo("Cadorna FN M1 M2", new String[]{"1", "M2", "NM2", "M1", "NM1", "2", "S3", "S4", "R16", "R17", "R22", "R27", "RE1", "RE7", "MXP2", "50", "96", "97", "z602", "z603", "z6C3", "N25", "N26"}, "lightrail", "Main", 25));
        interchanges.add(new InterchangeInfo("Foro Buonaparte Via Ricasoli", new String[]{"1", "2"}, "building.columns.fill", "Main", 26));
        interchanges.add(new InterchangeInfo("Cairoli M1", new String[]{"1", "M1", "NM1", "NM2", "2", "4", "50", "96", "97"}, "tram.fill.tunnel", "Main", 27));
        interchanges.add(new InterchangeInfo("Via Cusani", new String[]{"1", "14", "96"}, "tram.fill", "Main", 28));
        interchanges.add(new InterchangeInfo("Cordusio M1", new String[]{"1", "M1", "NM1", "12", "16", "19"}, "tram.fill.tunnel", "Main", 29));
        interchanges.add(new InterchangeInfo("Via Grossi", new String[]{"1"}, "tram.fill.tunnel", "Main", 30));
        interchanges.add(new InterchangeInfo("Teatro Alla Scala", new String[]{"1", context.getString(R.string.monumentKey)}, "building.columns.fill", "Main", 31));
        interchanges.add(new InterchangeInfo("Montenapoleone M3", new String[]{"1", "M3", "96"}, "tram.fill.tunnel", "Main", 32));
        interchanges.add(new InterchangeInfo("Via Manzoni Via Pisoni", new String[]{"1"}, "tram.fill", "Main", 33));
        interchanges.add(new InterchangeInfo("Piazza Cavour", new String[]{"1", "NM3", "84", "96", "97"}, "tram.fill", "Main", 34));
        interchanges.add(new InterchangeInfo("Turati M3", new String[]{"1", "M3", "NM3", "43", "84"}, "tram.fill.tunnel", "Main", 35));
        interchanges.add(new InterchangeInfo("Repubblica M3", new String[]{"1", "M3", "NM3", "9", "33", "S13", "S1", "S2", "S5", "S6", "S12", "43"}, "tram.fill.tunnel", "Main", 36));
        interchanges.add(new InterchangeInfo("Viale Vittorio Veneto", new String[]{"1", "9", "33"}, "tram.fill", "Main", 37));
        interchanges.add(new InterchangeInfo("Viale Tunisia", new String[]{"1", "5", "33"}, "tram.fill", "Main", 38));
        interchanges.add(new InterchangeInfo("Piazza Cincinnato", new String[]{"1", "5"}, "tram.fill", "Main", 39));
        interchanges.add(new InterchangeInfo("Via Vitruvio", new String[]{"1", "5", "60", "81", "N26"}, "tram.fill", "Main", 40));
        interchanges.add(new InterchangeInfo("Via Settembrini", new String[]{"1"}, "tram.fill", "Main", 41));
        interchanges.add(new InterchangeInfo("Caiazzo M2", new String[]{"1", "M2", "NM2", "90", "91", "92"}, "tram.fill.tunnel", "Main", 42));
        interchanges.add(new InterchangeInfo("Viale Brianza", new String[]{"1"}, "tram.fill", "Main", 43));
        interchanges.add(new InterchangeInfo("Via Venini Via Battaglia", new String[]{"1"}, "tram.fill", "Main", 44));
        interchanges.add(new InterchangeInfo("Via Venini Via Sauli", new String[]{"1"}, "tram.fill", "Main", 45));
        interchanges.add(new InterchangeInfo("Piazza Morbegno", new String[]{"1"}, "tram.fill", "Main", 46));
        interchanges.add(new InterchangeInfo("Greco Rovereto", new String[]{"1", "87", "174"}, "tram.fill", "Main", 47));

        /// Tram 3
        /* MODIFICHE TRAM: Questa linea non è completa per via dei lavori in Via Torino e Concorrenti.
        interchanges.add(new InterchangeInfo("Gratosoglio", new String[]{"3", "15", "79", "167", "N15"}, "tram.fill", "Main", 0));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia", new String[]{"3", "15", "230", "N15"}, "tram.fill", "Main", 1));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia Via Saponaro", new String[]{"3", "15", "230", "N15"}, "tram.fill", "Main", 2));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia Via Feraboli", new String[]{"3", "15", "N15"}, "tram.fill", "Main", 3));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia Via De Ruggiero", new String[]{"3", "15", "N15"}, "tram.fill", "Main", 4));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia (Isola Anita)", new String[]{"3", "15", "N15"}, "tram.fill", "Main", 5));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia Via S. Abbondio", new String[]{"3", "15"}, "tram.fill", "Main", 6));
        interchanges.add(new InterchangeInfo("Via Dei Missaglia Via Boifava", new String[]{"3", "15", "N15"}, "tram.fill", "Main", 7));
        interchanges.add(new InterchangeInfo("Piazza Abbiategrasso M2", new String[]{"3", "M2", "NM2", "15", "65", "79", "230", "N15"}, "tram.fill.tunnel", "Main", 8));
        interchanges.add(new InterchangeInfo("Via Montegani Via Neera", new String[]{"3", "N15"}, "tram.fill", "Main", 9));
        interchanges.add(new InterchangeInfo("Via Montegani Via Palmieri", new String[]{"3", "N15"}, "tram.fill", "Main", 10));
        interchanges.add(new InterchangeInfo("Via Montegani V.Le Da Cermenate", new String[]{"3", "95", "N15"}, "tram.fill", "Main", 11));
        interchanges.add(new InterchangeInfo("Via Meda Via Spaventa", new String[]{"3", "N15"}, "tram.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Viale Tibaldi Via Meda", new String[]{"3", "59", "71", "91", "N15"}, "tram.fill", "Main", 13));
        interchanges.add(new InterchangeInfo("Largo Mahler", new String[]{"3", "59", "71", "N15"}, "tram.fill", "Main", 14));
        interchanges.add(new InterchangeInfo("Corso San Gottardo Via Lagrange", new String[]{"3", "71", "N15"}, "tram.fill", "Main", 15));
        interchanges.add(new InterchangeInfo("Piazza XXIV Maggio", new String[]{"3", "9", "10", "71", "N15", "N25"}, "tram.fill", "Main", 16));*/

        /// Tram 7
        interchanges.add(new InterchangeInfo("Piazzale Lagosta", new String[]{"7", "31", "33", "60"}, "tram.fill", "Main", 0));
        interchanges.add(new InterchangeInfo("Zara M3 M5", new String[]{"7", "M3", "NM3", "M5", "31", "51", "60", "82", "90", "91", "92", "166"}, "tram.fill.tunnel", "Main", 1));
        interchanges.add(new InterchangeInfo("Marche M5", new String[]{"7", "M5", "5", "31"}, "tram.fill.tunnel", "Main", 2));
        interchanges.add(new InterchangeInfo("Viale Zara Viale Laurana", new String[]{"7", "5", "31"}, "tram.fill", "Main", 3));
        interchanges.add(new InterchangeInfo("Istria M5", new String[]{"7", "M5", "5", "31", "42"}, "tram.fill.tunnel", "Main", 4));
        interchanges.add(new InterchangeInfo("Viale Fulvio Testi Via Dolcebuono", new String[]{"7", "5", "31", "42"}, "tram.fill", "Main", 5));
        interchanges.add(new InterchangeInfo("Ca' Granda M5", new String[]{"7", "M5", "4", "5", "31", "86", "172"}, "tram.fill.tunnel", "Main", 6));
        interchanges.add(new InterchangeInfo("Viale Fulvio Testi Via Pianell", new String[]{"7", "31"}, "tram.fill", "Main", 7));
        interchanges.add(new InterchangeInfo("Viale Fulvio Testi Via S. Marcellina", new String[]{"7", "31"}, "tram.fill", "Main", 8));
        interchanges.add(new InterchangeInfo("Via Pulci (Bicocca M5)", new String[]{"7", "M5", "4", "31", "52", "172", "783"}, "tram.fill.tunnel", "Main", 9));
        interchanges.add(new InterchangeInfo("Università Bicocca Scienza", new String[]{"7", "52", "87"}, "building.columns.fill", "Main", 10));
        interchanges.add(new InterchangeInfo("Greco Pirelli FS", new String[]{"7", "S11", "S7", "S8", "S9", "R4", "R13", "R14", "R38", "RE13", "RE80", "52", "81", "86", "174"}, "lightrail", "Main", 11));
        interchanges.add(new InterchangeInfo("Arcimboldi Ateneo Nuovo", new String[]{"7", "87"}, "tram.fill", "Main", 12));
        interchanges.add(new InterchangeInfo("Largo Mattei", new String[]{"7", "86", "174"}, "tram.fill", "Main", 13));
        interchanges.add(new InterchangeInfo("Precotto M1", new String[]{"7", "M1", "NM1", "51", "86", "174"}, "tram.fill.tunnel", "Main", 14));
        interchanges.add(new InterchangeInfo("Via Tremelloni", new String[]{"7"}, "tram.fill", "Main", 15));
        interchanges.add(new InterchangeInfo("Via Tremelloni Via Anassagora", new String[]{"7"}, "tram.fill", "Main", 16));
        interchanges.add(new InterchangeInfo("Via Tremelloni Via Gassman", new String[]{"7", "86"}, "tram.fill", "Main", 17));
        interchanges.add(new InterchangeInfo("Via Tremelloni Via Tognazzi", new String[]{"7", "86"}, "tram.fill", "Main", 18));
        interchanges.add(new InterchangeInfo("Via Adriano Via Lussu", new String[]{"7", "53", "56", "86"}, "tram.fill", "Main", 19));
        interchanges.add(new InterchangeInfo("Via Adriano Via Vipiteno", new String[]{"7", "86"}, "tram.fill", "Main", 20));

        return interchanges;
    }
}
