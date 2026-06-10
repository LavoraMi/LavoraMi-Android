package com.andreafilice.lavorami;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StationDB {
    private static List<MetroStation> CACHED_STATIONS = null;

    public static List<MetroStation> getStationsM1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Sesto 1° Maggio FS", 45.54156, 9.23835, "Main", "M1"));
        stations.add(new MetroStation("Sesto Rondò", 45.5342, 9.23128, "Main", "M1"));
        stations.add(new MetroStation("Sesto Marelli", 45.52356, 9.22796, "Main", "M1"));
        stations.add(new MetroStation("Villa S. Giovanni", 45.51745, 9.22613, "Main", "M1"));
        stations.add(new MetroStation("Precotto", 45.51215, 9.22449, "Main", "M1"));
        stations.add(new MetroStation("Gorla", 45.50662, 9.22287, "Main", "M1"));
        stations.add(new MetroStation("Turro", 45.50091, 9.22114, "Main", "M1"));
        stations.add(new MetroStation("Rovereto", 45.49578, 9.21957, "Main", "M1"));
        stations.add(new MetroStation("Pasteur", 45.49091, 9.21816, "Main", "M1"));
        stations.add(new MetroStation("Loreto", 45.48584, 9.21638, "Main", "M1"));
        stations.add(new MetroStation("Lima", 45.48027, 9.21085, "Main", "M1"));
        stations.add(new MetroStation("Porta Venezia", 45.47471, 9.20568, "Main", "M1"));
        stations.add(new MetroStation("Palestro", 45.47142, 9.20194, "Main", "M1"));
        stations.add(new MetroStation("San Babila", 45.46642, 9.19757, "Main", "M1"));
        stations.add(new MetroStation("Duomo", 45.46443, 9.18927, "Main", "M1"));
        stations.add(new MetroStation("Cordusio", 45.46539, 9.18635, "Main", "M1"));
        stations.add(new MetroStation("Cairoli", 45.4682, 9.18225, "Main", "M1"));
        stations.add(new MetroStation("Cadorna FN", 45.4682, 9.17588, "Main", "M1"));
        stations.add(new MetroStation("Conciliazione", 45.46749, 9.1663, "Main", "M1"));
        stations.add(new MetroStation("Pagano", 45.46828, 9.16077, "Main", "M1"));
        stations.add(new MetroStation("Buonarroti", 45.47054, 9.1552, "Rho", "M1"));
        stations.add(new MetroStation("Amendola", 45.47356, 9.15132, "Rho", "M1"));
        stations.add(new MetroStation("Lotto", 45.47909, 9.14454, "Rho", "M1"));
        stations.add(new MetroStation("QT8", 45.48625, 9.13743, "Rho", "M1"));
        stations.add(new MetroStation("Lampugnano", 45.4894, 9.12731, "Rho", "M1"));
        stations.add(new MetroStation("Uruguay", 45.49344, 9.12045, "Rho", "M1"));
        stations.add(new MetroStation("Bonola", 45.49703, 9.11009, "Rho", "M1"));
        stations.add(new MetroStation("San Leonardo", 45.50116, 9.10149, "Rho", "M1"));
        stations.add(new MetroStation("Molino Dorino", 45.50516, 9.09323, "Rho", "M1"));
        stations.add(new MetroStation("Pero", 45.50869, 9.08581, "Rho", "M1"));
        stations.add(new MetroStation("Rho Fiera", 45.51797, 9.08564, "Rho", "M1"));
        stations.add(new MetroStation("Wagner", 45.46784, 9.15529, "Bisceglie", "M1"));
        stations.add(new MetroStation("De Angeli", 45.46656, 9.14987, "Bisceglie", "M1"));
        stations.add(new MetroStation("Gambara", 45.46499, 9.14295, "Bisceglie", "M1"));
        stations.add(new MetroStation("Bande Nere", 45.46133, 9.13695, "Bisceglie", "M1"));
        stations.add(new MetroStation("Primaticcio", 45.45952, 9.12961, "Bisceglie", "M1"));
        stations.add(new MetroStation("Inganni", 45.45756, 9.12225, "Bisceglie", "M1"));
        stations.add(new MetroStation("Bisceglie", 45.45531, 9.11335, "Bisceglie", "M1"));
        stations.add(new MetroStation("Parri", 45.45188, 9.09925, "Bisceglie - New", "M1"));
        stations.add(new MetroStation("Baggio", 45.4553, 9.08892, "Bisceglie - New", "M1"));
        stations.add(new MetroStation("Olmi", 45.45642, 9.08226, "Bisceglie - New", "M1"));
        return stations;
    }

    public static List<MetroStation> getStationsM2() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Assago Forum", 45.40183, 9.14562, "Assago", "M2"));
        stations.add(new MetroStation("Assago Milanofiori Nord", 45.40945, 9.15004, "Assago", "M2"));
        stations.add(new MetroStation("P.Za Abbiategrasso", 45.42984, 9.17838, "Abbiategrasso", "M2"));
        stations.add(new MetroStation("Famagosta", 45.43719, 9.16795, "Main", "M2"));
        stations.add(new MetroStation("Romolo", 45.44373, 9.16767, "Main", "M2"));
        stations.add(new MetroStation("Porta Genova FS", 45.45273, 9.16972, "Main", "M2"));
        stations.add(new MetroStation("S. Agostino", 45.45834, 9.16977, "Main", "M2"));
        stations.add(new MetroStation("S. Ambrogio", 45.46185, 9.17325, "Main", "M2"));
        stations.add(new MetroStation("Cadorna FN", 45.4682, 9.17588, "Main", "M2"));
        stations.add(new MetroStation("Lanza", 45.47196, 9.18273, "Main", "M2"));
        stations.add(new MetroStation("Moscova", 45.4775, 9.18471, "Main", "M2"));
        stations.add(new MetroStation("Garibaldi FS", 45.48351, 9.18671, "Main", "M2"));
        stations.add(new MetroStation("Gioia", 45.48462, 9.19523, "Main", "M2"));
        stations.add(new MetroStation("Centrale FS", 45.48469, 9.20274, "Main", "M2"));
        stations.add(new MetroStation("Caiazzo", 45.48525, 9.2091, "Main", "M2"));
        stations.add(new MetroStation("Loreto", 45.48584, 9.21638, "Main", "M2"));
        stations.add(new MetroStation("Piola", 45.48081, 9.22509, "Main", "M2"));
        stations.add(new MetroStation("Lambrate FS", 45.48423, 9.235, "Main", "M2"));
        stations.add(new MetroStation("Udine", 45.49145, 9.23688, "Main", "M2"));
        stations.add(new MetroStation("Cimiano", 45.50004, 9.24142, "Main", "M2"));
        stations.add(new MetroStation("Crescenzago", 45.50521, 9.24822, "Main", "M2"));
        stations.add(new MetroStation("Cascina Gobba", 45.51114, 9.26052, "Main", "M2"));
        stations.add(new MetroStation("Cologno Sud", 45.52021, 9.27492, "Cologno", "M2"));
        stations.add(new MetroStation("Cologno Centro", 45.52747, 9.28296, "Cologno", "M2"));
        stations.add(new MetroStation("Cologno Nord", 45.53426, 9.29111, "Cologno", "M2"));
        stations.add(new MetroStation("Vimodrone", 45.51574, 9.28564, "Gessate", "M2"));
        stations.add(new MetroStation("Cascina Burrona", 45.51736, 9.29783, "Gessate", "M2"));
        stations.add(new MetroStation("Cernusco Sul Naviglio", 45.52097, 9.33083, "Gessate", "M2"));
        stations.add(new MetroStation("Villa Fiorita", 45.5205, 9.34609, "Gessate", "M2"));
        stations.add(new MetroStation("Cassina De Pecchi", 45.52163, 9.36213, "Gessate", "M2"));
        stations.add(new MetroStation("Bussero", 45.52541, 9.3758, "Gessate", "M2"));
        stations.add(new MetroStation("Villa Pompea", 45.52778, 9.38505, "Gessate", "M2"));
        stations.add(new MetroStation("Gorgonzola", 45.53649, 9.4036, "Gessate", "M2"));
        stations.add(new MetroStation("Cascina Antonietta", 45.5421, 9.42364, "Gessate", "M2"));
        stations.add(new MetroStation("Gessate", 45.54524, 9.43656, "Gessate", "M2"));
        return stations;
    }

    public static List<MetroStation> getStationsM3() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("San Donato", 45.42897, 9.25653, "Main", "M3"));
        stations.add(new MetroStation("Rogoredo FS", 45.43366, 9.23849, "Main", "M3"));
        stations.add(new MetroStation("Porto Di Mare", 45.43717, 9.23041, "Main", "M3"));
        stations.add(new MetroStation("Corvetto", 45.4403, 9.22385, "Main", "M3"));
        stations.add(new MetroStation("Brenta", 45.44301, 9.21801, "Main", "M3"));
        stations.add(new MetroStation("Lodi TIBB", 45.44698, 9.20975, "Main", "M3"));
        stations.add(new MetroStation("Porta Romana", 45.45152, 9.203, "Main", "M3"));
        stations.add(new MetroStation("Crocetta", 45.45597, 9.19569, "Main", "M3"));
        stations.add(new MetroStation("Missori", 45.46057, 9.18818, "Main", "M3"));
        stations.add(new MetroStation("Duomo", 45.46443, 9.18927, "Main", "M3"));
        stations.add(new MetroStation("Montenapoleone", 45.47027, 9.19249, "Main", "M3"));
        stations.add(new MetroStation("Turati", 45.47455, 9.19481, "Main", "M3"));
        stations.add(new MetroStation("Repubblica", 45.48038, 9.19878, "Main", "M3"));
        stations.add(new MetroStation("Centrale FS", 45.48469, 9.20274, "Main", "M3"));
        stations.add(new MetroStation("Sondrio", 45.4896, 9.20109, "Main", "M3"));
        stations.add(new MetroStation("Zara", 45.49225, 9.19246, "Main", "M3"));
        stations.add(new MetroStation("Maciachini", 45.49788, 9.18478, "Main", "M3"));
        stations.add(new MetroStation("Dergano", 45.50564, 9.17978, "Main", "M3"));
        stations.add(new MetroStation("Affori Centro", 45.51358, 9.17417, "Main", "M3"));
        stations.add(new MetroStation("Affori FN", 45.52087, 9.16901, "Main", "M3"));
        stations.add(new MetroStation("Comasina", 45.52835, 9.16387, "Main", "M3"));
        return stations;
    }

    public static List<MetroStation> getStationsM4() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Linate Aereoporto", 45.46284, 9.27791, "Main", "M4"));
        stations.add(new MetroStation("Repetti", 45.46201, 9.2404, "Main", "M4"));
        stations.add(new MetroStation("Stazione Forlanini", 45.46465, 9.2363, "Main", "M4"));
        stations.add(new MetroStation("Argonne", 45.46815, 9.23119, "Main", "M4"));
        stations.add(new MetroStation("Susa", 45.4682, 9.22499, "Main", "M4"));
        stations.add(new MetroStation("Dateo", 45.46799, 9.21845, "Main", "M4"));
        stations.add(new MetroStation("Tricolore", 45.46793, 9.20868, "Main", "M4"));
        stations.add(new MetroStation("San Babila", 45.46642, 9.19757, "Main", "M4"));
        stations.add(new MetroStation("Sforza-Policlinico", 45.45874, 9.19433, "Main", "M4"));
        stations.add(new MetroStation("Santa Sofia", 45.45633, 9.18863, "Main", "M4"));
        stations.add(new MetroStation("Vetra", 45.4571, 9.18262, "Main", "M4"));
        stations.add(new MetroStation("De Amicis", 45.45909, 9.17721, "Main", "M4"));
        stations.add(new MetroStation("S. Ambrogio", 45.46182, 9.17321, "Main", "M4"));
        stations.add(new MetroStation("Coni Zugna", 45.45909, 9.1649, "Main", "M4"));
        stations.add(new MetroStation("California", 45.45755, 9.16005, "Main", "M4"));
        stations.add(new MetroStation("Bolivar", 45.45538, 9.15316, "Main", "M4"));
        stations.add(new MetroStation("Tolstoj", 45.45359, 9.14801, "Main", "M4"));
        stations.add(new MetroStation("Frattini", 45.45182, 9.14245, "Main", "M4"));
        stations.add(new MetroStation("Gelsomini", 45.4496, 9.13542, "Main", "M4"));
        stations.add(new MetroStation("Segneri", 45.44695, 9.13086, "Main", "M4"));
        stations.add(new MetroStation("San Cristoforo", 45.44256, 9.13008, "Main", "M4"));
        return stations;
    }

    public static List<MetroStation> getStationsM5() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Bignami", 45.52653, 9.21227, "Main", "M5"));
        stations.add(new MetroStation("Ponale", 45.52186, 9.20939, "Main", "M5"));
        stations.add(new MetroStation("Bicocca", 45.51429, 9.20549, "Main", "M5"));
        stations.add(new MetroStation("Ca' Granda", 45.50733, 9.20121, "Main", "M5"));
        stations.add(new MetroStation("Istria", 45.50163, 9.19792, "Main", "M5"));
        stations.add(new MetroStation("Marche", 45.49639, 9.19499, "Main", "M5"));
        stations.add(new MetroStation("Zara", 45.49225, 9.19246, "Main", "M5"));
        stations.add(new MetroStation("Isola", 45.48759, 9.19131, "Main", "M5"));
        stations.add(new MetroStation("Garibaldi FS", 45.48351, 9.18671, "Main", "M5"));
        stations.add(new MetroStation("Monumentale", 45.48532, 9.18098, "Main", "M5"));
        stations.add(new MetroStation("Cenisio", 45.48799, 9.17207, "Main", "M5"));
        stations.add(new MetroStation("Gerusalemme", 45.48469, 9.16696, "Main", "M5"));
        stations.add(new MetroStation("Domodossola FN", 45.48135, 9.1621, "Main", "M5"));
        stations.add(new MetroStation("Tre Torri", 45.47807, 9.15664, "Main", "M5"));
        stations.add(new MetroStation("Portello", 45.4814, 9.15045, "Main", "M5"));
        stations.add(new MetroStation("Lotto", 45.47909, 9.14454, "Main", "M5"));
        stations.add(new MetroStation("Segesta", 45.47909, 9.13734, "Main", "M5"));
        stations.add(new MetroStation("S. Siro Ippodromo", 45.47909, 9.12858, "Main", "M5"));
        stations.add(new MetroStation("S. Siro Stadio", 45.47909, 9.11857, "Main", "M5"));
        return stations;
    }

    public static List<MetroStation> getStationsS1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "S1"));
        stations.add(new MetroStation("Saronno Sud", 45.61235, 9.04557, "Main", "S1"));
        stations.add(new MetroStation("Caronno Pertusella", 45.5981, 9.05794, "Main", "S1"));
        stations.add(new MetroStation("Cesate", 45.5908, 9.06631, "Main", "S1"));
        stations.add(new MetroStation("Garbagnate Milanese", 45.58014, 9.08042, "Main", "S1"));
        stations.add(new MetroStation("Garbagnate Parco Delle Groane", 45.57108, 9.09097, "Main", "S1"));
        stations.add(new MetroStation("Bollate Nord", 45.55262, 9.11209, "Main", "S1"));
        stations.add(new MetroStation("Bollate Centro", 45.54289, 9.12283, "Main", "S1"));
        stations.add(new MetroStation("Novate Milanese", 45.533, 9.13238, "Main", "S1"));
        stations.add(new MetroStation("Milano Quarto Oggiaro", 45.51918, 9.14562, "Main", "S1"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S1"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S1"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S1"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S1"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S1"));
        stations.add(new MetroStation("Milano Dateo", 45.46799, 9.21845, "Main", "S1"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S1"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "S1"));
        stations.add(new MetroStation("S. Donato Milanese", 45.41879, 9.25291, "Main", "S1"));
        stations.add(new MetroStation("Borgolombardo", 45.4045, 9.27046, "Main", "S1"));
        stations.add(new MetroStation("S. Giuliano Milanese", 45.39134, 9.28652, "Main", "S1"));
        stations.add(new MetroStation("Melegnano", 45.35647, 9.31936, "Main", "S1"));
        stations.add(new MetroStation("S. Zenone Al Lambro", 45.33783, 9.36165, "Main", "S1"));
        stations.add(new MetroStation("Tavazzano", 45.32641, 9.40282, "Main", "S1"));
        stations.add(new MetroStation("Lodi", 45.3092, 9.49776, "Main", "S1"));
        return stations;
    }

    public static List<MetroStation> getStationsS2() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Mariano Comense", 45.69358, 9.18141, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.67783, 9.17024, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.67703, 9.16952, "Main", "S2"));
        stations.add(new MetroStation("Cabiate", 45.67592, 9.16843, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.67453, 9.16765, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.66457, 9.16155, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.66423, 9.16136, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.66377, 9.16097, "Main", "S2"));
        stations.add(new MetroStation("Meda", 45.66237, 9.15897, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.6615, 9.15763, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.66062, 9.15583, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65735, 9.14869, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65638, 9.14725, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65224, 9.14125, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65177, 9.14074, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65083, 9.14018, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.65019, 9.14008, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.64966, 9.14011, "Main", "S2"));
        stations.add(new MetroStation("Seveso", 45.6483, 9.14018, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.64507, 9.14113, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.64208, 9.14166, "Main", "S2"));
        stations.add(new MetroStation("Cesano Maderno", 45.6307, 9.14177, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.61244, 9.14142, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.61244, 9.14142, "Main", "S2"));
        stations.add(new MetroStation("Boviso Masciago", 45.61199, 9.14128, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.61129, 9.14151, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.61027, 9.14185, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.60926, 9.14246, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.60795, 9.1435, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.59736, 9.15218, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.5963, 9.1528, "Main", "S2"));
        stations.add(new MetroStation("Varedo", 45.59555, 9.15341, "Main", "S2"));
        stations.add(new MetroStation("Palazzolo Milanese", 45.58107, 9.15667, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.57158, 9.15773, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.57054, 9.15782, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.56981, 9.15808, "Main", "S2"));
        stations.add(new MetroStation("Paderno Dugnano", 45.56499, 9.16132, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.5626, 9.16269, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.55181, 9.17153, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.55073, 9.17259, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.54979, 9.17317, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.54909, 9.17339, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.54733, 9.17379, "Main", "S2"));
        stations.add(new MetroStation("Cormano Cusano Milanino", 45.5457, 9.17379, "Main", "S2"));
        stations.add(new MetroStation("Milano Bruzzano Parco Nord", 45.53374, 9.1728, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.53116, 9.1725, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.52997, 9.17209, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.52846, 9.17132, "Main", "S2"));
        stations.add(new MetroStation("Milano Affori", 45.52131, 9.16743, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.51012, 9.15873, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.50909, 9.15834, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.50826, 9.15818, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.50724, 9.15801, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.50651, 9.15802, "Main", "S2"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49897, 9.16027, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49846, 9.16051, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49793, 9.16098, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49742, 9.16166, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49576, 9.16522, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49503, 9.16905, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.4947, 9.17309, "Main", "S2"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.4935, 9.17755, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.49167, 9.17813, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.4899, 9.1785, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.48802, 9.18088, "Main", "S2"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S2"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S2"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.47192, 9.21691, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.47157, 9.21723, "Main", "S2"));
        stations.add(new MetroStation("Milano Dateo", 45.46579, 9.21725, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.46059, 9.21751, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45996, 9.21821, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45985, 9.21946, "Main", "S2"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45949, 9.23408, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45929, 9.23498, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45887, 9.23581, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45835, 9.2364, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45793, 9.23668, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45732, 9.23679, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45417, 9.23675, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.45046, 9.23638, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.44969, 9.23593, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.44843, 9.23507, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.44736, 9.23445, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.44638, 9.23428, "Main", "S2"));
        stations.add(new MetroStation("NO_DRAW", 45.44505, 9.23446, "Main", "S2"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "S2"));
        return stations;
    }

    public static List<MetroStation> getStationsS3() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "S3"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "S3"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S3"));
        stations.add(new MetroStation("Milano Quarto Oggiaro", 45.51918, 9.14562, "Main", "S3"));
        stations.add(new MetroStation("Novate Milanese", 45.533, 9.13238, "Main", "S3"));
        stations.add(new MetroStation("Bollate Centro", 45.54289, 9.12283, "Main", "S3"));
        stations.add(new MetroStation("Bollate Nord", 45.55262, 9.11209, "Main", "S3"));
        stations.add(new MetroStation("Garbagnate Parco Delle Groane", 45.57108, 9.09097, "Main", "S3"));
        stations.add(new MetroStation("Garbagnate Milanese", 45.58014, 9.08042, "Main", "S3"));
        stations.add(new MetroStation("Cesate", 45.5908, 9.06631, "Main", "S3"));
        stations.add(new MetroStation("Caronno Pertusella", 45.5981, 9.05794, "Main", "S3"));
        stations.add(new MetroStation("Saronno Sud", 45.61235, 9.04557, "Main", "S3"));
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "S3"));
        return stations;
    }

    public static List<MetroStation> getStationsS4() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Camnago - Lentate", 45.66837, 9.13328, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.66643, 9.13467, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.66412, 9.13586, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.66259, 9.13691, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.66083, 9.13779, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.65788, 9.13916, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.65668, 9.13972, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.65555, 9.13989, "Main", "S4"));
        stations.add(new MetroStation("Seveso", 45.6483, 9.14018, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.64507, 9.14113, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.64208, 9.14166, "Main", "S4"));
        stations.add(new MetroStation("Cesano Maderno", 45.6307, 9.14177, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.61244, 9.14142, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.61244, 9.14142, "Main", "S4"));
        stations.add(new MetroStation("Boviso Masciago", 45.61199, 9.14128, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.61129, 9.14151, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.61027, 9.14185, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.60926, 9.14246, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.60795, 9.1435, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.59736, 9.15218, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.5963, 9.1528, "Main", "S4"));
        stations.add(new MetroStation("Varedo", 45.59555, 9.15341, "Main", "S4"));
        stations.add(new MetroStation("Palazzolo Milanese", 45.58107, 9.15667, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.57158, 9.15773, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.57054, 9.15782, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.56981, 9.15808, "Main", "S4"));
        stations.add(new MetroStation("Paderno Dugnano", 45.56499, 9.16132, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.5626, 9.16269, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.55181, 9.17153, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.55073, 9.17259, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.54979, 9.17317, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.54909, 9.17339, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.54733, 9.17379, "Main", "S4"));
        stations.add(new MetroStation("Cormano Cusano Milanino", 45.5457, 9.17379, "Main", "S4"));
        stations.add(new MetroStation("Milano Bruzzano Parco Nord", 45.53374, 9.1728, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.53116, 9.1725, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.52997, 9.17209, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.52846, 9.17132, "Main", "S4"));
        stations.add(new MetroStation("Milano Affori", 45.52131, 9.16743, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.51012, 9.15873, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.50909, 9.15834, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.50826, 9.15818, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.50724, 9.15801, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.50651, 9.15802, "Main", "S4"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.50046, 9.15957, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.49641, 9.1601, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.49394, 9.16044, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.49002, 9.161, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.49002, 9.161, "Main", "S4"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "S4"));
        stations.add(new MetroStation("NO_DRAW", 45.47308, 9.16977, "Main", "S4"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "S4"));
        return stations;
    }

    public static List<MetroStation> getStationsS5() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Varese", 45.81625, 8.83295, "Main", "S5"));
        stations.add(new MetroStation("Gazzada Schianno Morazzone", 45.77868, 8.82473, "Main", "S5"));
        stations.add(new MetroStation("Castronno", 45.74525, 8.81051, "Main", "S5"));
        stations.add(new MetroStation("Albizzate - Solbiate Arno", 45.7233, 8.80628, "Main", "S5"));
        stations.add(new MetroStation("Cavaria", 45.69814, 8.80371, "Main", "S5"));
        stations.add(new MetroStation("Gallarate", 45.65974, 8.79853, "Main", "S5"));
        stations.add(new MetroStation("Busto Arsizio", 45.61593, 8.86589, "Main", "S5"));
        stations.add(new MetroStation("Legnano", 45.5937, 8.91087, "Main", "S5"));
        stations.add(new MetroStation("Canegrate", 45.56934, 8.92673, "Main", "S5"));
        stations.add(new MetroStation("Parabiago", 45.55261, 8.94651, "Main", "S5"));
        stations.add(new MetroStation("Vanzago - Pogliano", 45.52536, 8.99573, "Main", "S5"));
        stations.add(new MetroStation("Rho", 45.52411, 9.04355, "Main", "S5"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "S5"));
        stations.add(new MetroStation("Milano Certosa", 45.50683, 9.13593, "Main", "S5"));
        stations.add(new MetroStation("Milano Villapizzone", 45.50202, 9.15092, "Main", "S5"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S5"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S5"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S5"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S5"));
        stations.add(new MetroStation("Milano Dateo", 45.46799, 9.21845, "Main", "S5"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S5"));
        stations.add(new MetroStation("Milano Forlanini", 45.46438, 9.23691, "Main", "S5"));
        stations.add(new MetroStation("Segrate", 45.48075, 9.29859, "Main", "S5"));
        stations.add(new MetroStation("Pioltello Limito", 45.48611, 9.32949, "Main", "S5"));
        stations.add(new MetroStation("Vigate", 45.49441, 9.37649, "Main", "S5"));
        stations.add(new MetroStation("Melzo", 45.50208, 9.4192, "Main", "S5"));
        stations.add(new MetroStation("Pozzuolo Martesana", 45.50852, 9.45631, "Main", "S5"));
        stations.add(new MetroStation("Trecella", 45.51283, 9.48068, "Main", "S5"));
        stations.add(new MetroStation("Cassano d'Adda", 45.51446, 9.51278, "Main", "S5"));
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "S5"));
        return stations;
    }

    public static List<MetroStation> getStationsS6() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Novara", 45.45111, 8.62451, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44646, 8.63063, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44493, 8.63249, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44425, 8.6337, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44352, 8.63542, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44304, 8.63701, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4426, 8.63928, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.42846, 8.73317, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.42835, 8.73435, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.42829, 8.73555, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.42832, 8.73684, "Main", "S6"));
        stations.add(new MetroStation("Trecate", 45.42866, 8.73938, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.42894, 8.74138, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.43048, 8.74621, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.43333, 8.75409, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.44801, 8.7959, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4487, 8.7982, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45065, 8.80617, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45108, 8.80824, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45149, 8.81101, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45711, 8.84784, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45763, 8.84983, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45843, 8.85211, "Main", "S6"));
        stations.add(new MetroStation("Magenta", 45.4681, 8.88073, "Main", "S6"));
        stations.add(new MetroStation("Corbetta - S. Stefano", 45.48107, 8.91821, "Main", "S6"));
        stations.add(new MetroStation("Vittuone - Arluno", 45.49093, 8.94733, "Main", "S6"));
        stations.add(new MetroStation("Pregana Milanese", 45.51011, 9.00279, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51851, 9.02764, "Main", "S6"));
        stations.add(new MetroStation("Rho", 45.52411, 9.04355, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52714, 9.05292, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52802, 9.05644, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52859, 9.05897, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52893, 9.06112, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52869, 9.06408, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52819, 9.06588, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.5272, 9.06826, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.52649, 9.07062, "Main", "S6"));
        stations.add(new MetroStation("Rho Fiera Milano", 45.52113, 9.0885, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.50815, 9.12884, "Main", "S6"));
        stations.add(new MetroStation("Milano Certosa", 45.50643, 9.13582, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.50517, 9.14224, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.50456, 9.14479, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.50342, 9.14777, "Main", "S6"));
        stations.add(new MetroStation("Milano Villapizzone", 45.50202, 9.15092, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.49858, 9.15781, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.49576, 9.16522, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.49503, 9.16905, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4947, 9.17309, "Main", "S6"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4935, 9.17755, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.49167, 9.17813, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4899, 9.1785, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.48802, 9.18088, "Main", "S6"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S6"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S6"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.47192, 9.21691, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.47157, 9.21723, "Main", "S6"));
        stations.add(new MetroStation("Milano Dateo", 45.46579, 9.21725, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46059, 9.21751, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45996, 9.21821, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45985, 9.21946, "Main", "S6"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45954, 9.2313, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.45966, 9.23397, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46016, 9.23566, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46099, 9.23689, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4625, 9.23737, "Main", "S6"));
        stations.add(new MetroStation("Milano Forlanini", 45.46438, 9.23691, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.4658, 9.23658, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46747, 9.23713, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46864, 9.23818, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.46971, 9.23997, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.47048, 9.24296, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.47115, 9.24578, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.47221, 9.2511, "Main", "S6"));
        stations.add(new MetroStation("Segrate", 45.48075, 9.29859, "Main", "S6"));
        stations.add(new MetroStation("Pioltello Limito", 45.48611, 9.32949, "Main", "S6"));
        stations.add(new MetroStation("Vigate", 45.49441, 9.37649, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.49995, 9.40689, "Main", "S6"));
        stations.add(new MetroStation("Melzo", 45.50208, 9.4192, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.50741, 9.45002, "Main", "S6"));
        stations.add(new MetroStation("Pozzuolo Martesana", 45.50852, 9.45631, "Main", "S6"));
        stations.add(new MetroStation("Trecella", 45.51283, 9.48068, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51549, 9.49648, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51588, 9.50006, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51588, 9.50354, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51543, 9.50798, "Main", "S6"));
        stations.add(new MetroStation("Cassano d'Adda", 45.51446, 9.51278, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51286, 9.51766, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51194, 9.52206, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51176, 9.52646, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51275, 9.5445, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51391, 9.57049, "Main", "S6"));
        stations.add(new MetroStation("NO_DRAW", 45.51436, 9.57557, "Main", "S6"));
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "S6"));
        return stations;
    }

    public static List<MetroStation> getStationsS7() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "S7"));
        stations.add(new MetroStation("Valmadrera", 45.84641, 9.36801, "Main", "S7"));
        stations.add(new MetroStation("Civate", 45.83133, 9.35275, "Main", "S7"));
        stations.add(new MetroStation("Sala Al Barro - Galbiate", 45.82118, 9.35937, "Main", "S7"));
        stations.add(new MetroStation("Oggiono", 45.78891, 9.33758, "Main", "S7"));
        stations.add(new MetroStation("Molteno", 45.78085, 9.30171, "Main", "S7"));
        stations.add(new MetroStation("Costa Masnaga", 45.76347, 9.2849, "Main", "S7"));
        stations.add(new MetroStation("Cassano Nibionno Bulciago", 45.74621, 9.27996, "Main", "S7"));
        stations.add(new MetroStation("Renate - Veduggio", 45.7284, 9.27997, "Main", "S7"));
        stations.add(new MetroStation("Besana", 45.70274, 9.28303, "Main", "S7"));
        stations.add(new MetroStation("Villa Raverio", 45.69038, 9.26172, "Main", "S7"));
        stations.add(new MetroStation("Carate Calo'", 45.67642, 9.25213, "Main", "S7"));
        stations.add(new MetroStation("Triuggio - Ponte Albiate", 45.65892, 9.26682, "Main", "S7"));
        stations.add(new MetroStation("Macherio - Canonica", 45.64556, 9.2869, "Main", "S7"));
        stations.add(new MetroStation("Biassono - Lesmo Parco", 45.63221, 9.29829, "Main", "S7"));
        stations.add(new MetroStation("Buttafava", 45.62142, 9.3013, "Main", "S7"));
        stations.add(new MetroStation("Villasanta", 45.60336, 9.30516, "Main", "S7"));
        stations.add(new MetroStation("Monza Sobborghi", 45.5819, 9.28449, "Main", "S7"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "S7"));
        stations.add(new MetroStation("Sesto S. Giovanni", 45.54126, 9.23903, "Main", "S7"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "S7"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S7"));
        return stations;
    }

    public static List<MetroStation> getStationsS8() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "S8"));
        stations.add(new MetroStation("Lecco Maggianico", 45.83102, 9.41205, "Main", "S8"));
        stations.add(new MetroStation("Calolziocorte Olginate", 45.80175, 9.42714, "Main", "S8"));
        stations.add(new MetroStation("Airuno", 45.75549, 9.42232, "Main", "S8"));
        stations.add(new MetroStation("Olgiate-Calco-Brivio", 45.72919, 9.40366, "Main", "S8"));
        stations.add(new MetroStation("Cernusco-Merate", 45.69503, 9.39692, "Main", "S8"));
        stations.add(new MetroStation("Osnago", 45.67873, 9.38711, "Main", "S8"));
        stations.add(new MetroStation("Carnate Usmate", 45.65318, 9.37493, "Main", "S8"));
        stations.add(new MetroStation("Arcore", 45.62377, 9.32292, "Main", "S8"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "S8"));
        stations.add(new MetroStation("Sesto S. Giovanni", 45.54126, 9.23903, "Main", "S8"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "S8"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S8"));
        return stations;
    }

    public static List<MetroStation> getStationsS9() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "S9"));
        stations.add(new MetroStation("Saronno Sud", 45.61235, 9.04557, "Main", "S9"));
        stations.add(new MetroStation("Ceriano Laghetto - Solaro", 45.6249, 9.07936, "Main", "S9"));
        stations.add(new MetroStation("Ceriano Laghetto Groane", 45.62718, 9.1002, "Main", "S9"));
        stations.add(new MetroStation("Cesano Maderno Laghetto Groane", 45.63033, 9.12652, "Main", "S9"));
        stations.add(new MetroStation("Cesano Maderno", 45.63073, 9.14209, "Main", "S9"));
        stations.add(new MetroStation("Seveso Baruccana", 45.63807, 9.16292, "Main", "S9"));
        stations.add(new MetroStation("Seregno", 45.64609, 9.20302, "Main", "S9"));
        stations.add(new MetroStation("Desio", 45.62011, 9.21829, "Main", "S9"));
        stations.add(new MetroStation("Lissone - Muggiò", 45.60618, 9.23526, "Main", "S9"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "S9"));
        stations.add(new MetroStation("Sesto S. Giovanni", 45.54126, 9.23903, "Main", "S9"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "S9"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "S9"));
        stations.add(new MetroStation("Milano Forlanini", 45.46438, 9.23691, "Main", "S9"));
        stations.add(new MetroStation("Milano Scalo Romana", 45.44585, 9.21303, "Main", "S9"));
        stations.add(new MetroStation("Milano Tibaldi - Bocconi", 45.44394, 9.18506, "Main", "S9"));
        stations.add(new MetroStation("Milano Romolo", 45.44335, 9.1675, "Main", "S9"));
        stations.add(new MetroStation("Milano S. Cristoforo", 45.44256, 9.13008, "Main", "S9"));
        stations.add(new MetroStation("Corsico", 45.43605, 9.10899, "Main", "S9"));
        stations.add(new MetroStation("Cesano Boscone", 45.43044, 9.09158, "Main", "S9"));
        stations.add(new MetroStation("Trezzano sul Naviglio", 45.42025, 9.0669, "Main", "S9"));
        stations.add(new MetroStation("Gaggiano", 45.40874, 9.03118, "Main", "S9"));
        stations.add(new MetroStation("Albairate - Vermezzo", 45.40435, 8.95822, "Main", "S9"));
        return stations;
    }

    public static List<MetroStation> getStationsS11() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Como S. Giovanni", 45.80901, 9.07279, "Main", "S11"));
        stations.add(new MetroStation("Como Camerlata", 45.78457, 9.0794, "Main", "S11"));
        stations.add(new MetroStation("Cucciago", 45.74081, 9.08206, "Main", "S11"));
        stations.add(new MetroStation("Cantù - Cermenate", 45.71623, 9.09948, "Main", "S11"));
        stations.add(new MetroStation("Carimate", 45.69718, 9.10737, "Main", "S11"));
        stations.add(new MetroStation("Camnago - Lentate", 45.66837, 9.13328, "Main", "S11"));
        stations.add(new MetroStation("Seregno", 45.64609, 9.20302, "Main", "S11"));
        stations.add(new MetroStation("Desio", 45.62011, 9.21829, "Main", "S11"));
        stations.add(new MetroStation("Lissone - Muggiò", 45.60618, 9.23526, "Main", "S11"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "S11"));
        stations.add(new MetroStation("Sesto S. Giovanni", 45.54126, 9.23903, "Main", "S11"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "S11"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S11"));
        stations.add(new MetroStation("Milano Villapizzone", 45.50202, 9.15092, "Main", "S11"));
        stations.add(new MetroStation("Milano Certosa", 45.50683, 9.13593, "Main", "S11"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "S11"));
        stations.add(new MetroStation("Rho", 45.52411, 9.04355, "Main", "S11"));
        return stations;
    }

    public static List<MetroStation> getStationsS12() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Cormano Cusano Milanino", 45.54571, 9.17409, "Main", "S12"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S12"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S12"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S12"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S12"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S12"));
        stations.add(new MetroStation("Milano Dateo", 45.46799, 9.21845, "Main", "S12"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S12"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "S12"));
        stations.add(new MetroStation("S. Donato Milanese", 45.41879, 9.25291, "Main", "S12"));
        stations.add(new MetroStation("Borgolombardo", 45.4045, 9.27046, "Main", "S12"));
        stations.add(new MetroStation("S. Giuliano Milanese", 45.39134, 9.28652, "Main", "S12"));
        stations.add(new MetroStation("Melegnano", 45.35647, 9.31936, "Main", "S12"));
        return stations;
    }

    public static List<MetroStation> getStationsS13() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "S13"));
        stations.add(new MetroStation("Certosa di Pavia", 45.25656, 9.1542, "Main", "S13"));
        stations.add(new MetroStation("Villamaggiore", 45.32047, 9.19051, "Main", "S13"));
        stations.add(new MetroStation("Pieve Emanuele", 45.33966, 9.20351, "Main", "S13"));
        stations.add(new MetroStation("Locate Triulzi", 45.35974, 9.22152, "Main", "S13"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "S13"));
        stations.add(new MetroStation("Milano Pta Vittoria", 45.45989, 9.22355, "Main", "S13"));
        stations.add(new MetroStation("Milano Dateo", 45.46799, 9.21845, "Main", "S13"));
        stations.add(new MetroStation("Milano Pta Venezia", 45.47633, 9.20709, "Main", "S13"));
        stations.add(new MetroStation("Milano Repubblica", 45.48034, 9.19888, "Main", "S13"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "S13"));
        stations.add(new MetroStation("Milano Lancetti", 45.4949, 9.17637, "Main", "S13"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "S13"));
        return stations;
    }

    public static List<MetroStation> getStationsS19() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "S19"));
        stations.add(new MetroStation("Milano Scalo Romana", 45.44585, 9.21303, "Main", "S19"));
        stations.add(new MetroStation("Milano Tibaldi - Bocconi", 45.44394, 9.18506, "Main", "S19"));
        stations.add(new MetroStation("Milano Romolo", 45.44335, 9.1675, "Main", "S19"));
        stations.add(new MetroStation("Milano S. Cristoforo", 45.44256, 9.13008, "Main", "S19"));
        stations.add(new MetroStation("Corsico", 45.43605, 9.10899, "Main", "S19"));
        stations.add(new MetroStation("Cesano Boscone", 45.43044, 9.09158, "Main", "S19"));
        stations.add(new MetroStation("Trezzano sul Naviglio", 45.42025, 9.0669, "Main", "S19"));
        stations.add(new MetroStation("Gaggiano", 45.40874, 9.03118, "Main", "S19"));
        stations.add(new MetroStation("Albairate - Vermezzo", 45.40435, 8.95822, "Main", "S19"));
        return stations;
    }

    public static List<MetroStation> getStationsS31() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "S31"));
        stations.add(new MetroStation("Brescia Borgo S. Giovanni", 45.54333, 10.19076, "Main", "S31"));
        stations.add(new MetroStation("Brescia Violino", 45.54511, 10.16873, "Main", "S31"));
        stations.add(new MetroStation("Castegnato", 45.56608, 10.11827, "Main", "S31"));
        stations.add(new MetroStation("Paderno Franciacorta", 45.58691, 10.08725, "Main", "S31"));
        stations.add(new MetroStation("Passirano", 45.59021, 10.06359, "Main", "S31"));
        stations.add(new MetroStation("Bornato - Calino", 45.59035, 10.03298, "Main", "S31"));
        stations.add(new MetroStation("Borgonato - Adro", 45.62225, 10.02005, "Main", "S31"));
        stations.add(new MetroStation("Provaglio Timoline", 45.63504, 10.03538, "Main", "S31"));
        stations.add(new MetroStation("Iseo", 45.65657, 10.05003, "Main", "S31"));
        return stations;
    }

    public static List<MetroStation> getStationsR1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Bergamo", 45.69043, 9.67504, "Main", "R1"));
        stations.add(new MetroStation("Seriate", 45.68326, 9.72462, "Main", "R1"));
        stations.add(new MetroStation("Albano S. Alessandro", 45.683, 9.76759, "Main", "R1"));
        stations.add(new MetroStation("Montello Gorlago", 45.67028, 9.80867, "Main", "R1"));
        stations.add(new MetroStation("Chiuduno", 45.65056, 9.84695, "Main", "R1"));
        stations.add(new MetroStation("Grumello Del Monte", 45.63416, 9.86293, "Main", "R1"));
        stations.add(new MetroStation("Palazzolo Sull'Oglio", 45.60238, 9.89489, "Main", "R1"));
        stations.add(new MetroStation("Cologne", 45.57669, 9.93554, "Main", "R1"));
        stations.add(new MetroStation("Coccaglio", 45.56109, 9.97769, "Main", "R1"));
        stations.add(new MetroStation("Coccaglio", 45.56109, 9.97769, "Main", "R1"));
        stations.add(new MetroStation("Rovato", 45.5523, 10.00101, "Main", "R1"));
        stations.add(new MetroStation("Ospitaletto Travagliato", 45.54615, 10.07437, "Main", "R1"));
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "R1"));
        return stations;
    }

    public static List<MetroStation> getStationsR2() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Bergamo", 45.69043, 9.67504, "Main", "R2"));
        stations.add(new MetroStation("Stezzano", 45.65868, 9.64492, "Main", "R2"));
        stations.add(new MetroStation("Levate", 45.62515, 9.62877, "Main", "R2"));
        stations.add(new MetroStation("Verdello Dalmine", 45.60571, 9.6198, "Main", "R2"));
        stations.add(new MetroStation("Arcene", 45.57445, 9.60543, "Main", "R2"));
        stations.add(new MetroStation("Treviglio Ovest", 45.52171, 9.58044, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51921, 9.57915, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51831, 9.57909, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51765, 9.57925, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51682, 9.57971, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51643, 9.58014, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51589, 9.58076, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51514, 9.58235, "Main", "R2"));
        stations.add(new MetroStation("NO_DRAW", 45.51494, 9.58409, "Main", "R2"));
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "R2"));
        return stations;
    }

    public static List<MetroStation> getStationsR3() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "R3"));
        stations.add(new MetroStation("Brescia Borgo San Giovanni", 45.54333, 10.19076, "Main", "R3"));
        stations.add(new MetroStation("Brescia Violino", 45.54511, 10.16873, "Main", "R3"));
        stations.add(new MetroStation("Castegnato", 45.56608, 10.11827, "Main", "R3"));
        stations.add(new MetroStation("Paderno Franciacorta", 45.58691, 10.08725, "Main", "R3"));
        stations.add(new MetroStation("Passirano", 45.59021, 10.06359, "Main", "R3"));
        stations.add(new MetroStation("Bornato - Calino", 45.59035, 10.03298, "Main", "R3"));
        stations.add(new MetroStation("Borgonato - Adro", 45.62225, 10.02005, "Main", "R3"));
        stations.add(new MetroStation("Provaglio Timoline", 45.63504, 10.03538, "Main", "R3"));
        stations.add(new MetroStation("Iseo", 45.65657, 10.05003, "Main", "R3"));
        stations.add(new MetroStation("Pilzone", 45.67434, 10.07929, "Main", "R3"));
        stations.add(new MetroStation("Sulzano", 45.68955, 10.10057, "Main", "R3"));
        stations.add(new MetroStation("Sale Marasino", 45.71288, 10.11159, "Main", "R3"));
        stations.add(new MetroStation("Marone Zone", 45.73838, 10.09324, "Main", "R3"));
        stations.add(new MetroStation("Vello", 45.75338, 10.08231, "Main", "R3"));
        stations.add(new MetroStation("Toline", 45.78738, 10.08822, "Main", "R3"));
        stations.add(new MetroStation("Pisogne", 45.80659, 10.10575, "Main", "R3"));
        stations.add(new MetroStation("Pian Camuno - Gratacasolo", 45.84004, 10.1388, "Main", "R3"));
        stations.add(new MetroStation("Darfo - Corna", 45.88429, 10.18106, "Main", "R3"));
        stations.add(new MetroStation("Boario Terme", 45.89214, 10.19083, "Main", "R3"));
        stations.add(new MetroStation("Pian di Borno", 45.91594, 10.22149, "Main", "R3"));
        stations.add(new MetroStation("Cogno - Esine", 45.9295, 10.24106, "Main", "R3"));
        stations.add(new MetroStation("Cividate - Malegno", 45.94339, 10.26871, "Main", "R3"));
        stations.add(new MetroStation("Breno", 45.95908, 10.30571, "Main", "R3"));
        stations.add(new MetroStation("Ceto - Cerveno", 45.99689, 10.33894, "Main", "R3"));
        stations.add(new MetroStation("Capo Di Ponte", 46.03108, 10.34631, "Main", "R3"));
        stations.add(new MetroStation("Cedegolo", 46.07252, 10.35009, "Main", "R3"));
        stations.add(new MetroStation("NO_DRAW", 46.08773, 10.31501, "Main", "R3"));
        stations.add(new MetroStation("NO_DRAW", 46.10273, 10.3108, "Main", "R3"));
        stations.add(new MetroStation("Malonno", 46.11875, 10.31667, "Main", "R3"));
        stations.add(new MetroStation("Sonico", 46.16322, 10.35137, "Main", "R3"));
        stations.add(new MetroStation("NO_DRAW", 46.16771, 10.34999, "Main", "R3"));
        stations.add(new MetroStation("NO_DRAW", 46.16965, 10.34917, "Main", "R3"));
        stations.add(new MetroStation("NO_DRAW", 46.17078, 10.34739, "Main", "R3"));
        stations.add(new MetroStation("Edolo", 46.17701, 10.32948, "Main", "R3"));
        return stations;
    }

    public static List<MetroStation> getStationsR4() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.50886, 9.21184, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.50579, 9.21207, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.49825, 9.22059, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.49663, 9.22502, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.49492, 9.2282, "Main", "R4"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.47459, 9.23925, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.47173, 9.2444, "Main", "R4"));
        stations.add(new MetroStation("NO_DRAW", 45.472, 9.25028, "Main", "R4"));
        stations.add(new MetroStation("Pioltello Limito", 45.48611, 9.32949, "Main", "R4"));
        stations.add(new MetroStation("Vigate", 45.49441, 9.37649, "Main", "R4"));
        stations.add(new MetroStation("Melzo", 45.50208, 9.4192, "Main", "R4"));
        stations.add(new MetroStation("Pozzuolo Martesana", 45.50852, 9.45631, "Main", "R4"));
        stations.add(new MetroStation("Trecella", 45.51283, 9.48068, "Main", "R4"));
        stations.add(new MetroStation("Cassano d'Adda", 45.51446, 9.51278, "Main", "R4"));
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "R4"));
        stations.add(new MetroStation("Vidalengo", 45.51788, 9.64023, "Main", "R4"));
        stations.add(new MetroStation("Morengo Bariano", 45.52172, 9.70313, "Main", "R4"));
        stations.add(new MetroStation("Romano", 45.52366, 9.75348, "Main", "R4"));
        stations.add(new MetroStation("Cividate - Calcio", 45.53273, 9.84137, "Main", "R4"));
        stations.add(new MetroStation("Chiari", 45.53994, 9.92658, "Main", "R4"));
        stations.add(new MetroStation("Rovato", 45.5523, 10.00101, "Main", "R4"));
        stations.add(new MetroStation("Ospitaletto Travagliato", 45.54615, 10.07437, "Main", "R4"));
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "R4"));
        return stations;
    }

    public static List<MetroStation> getStationsR5() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52981, 10.22417, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52943, 10.22569, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52872, 10.22711, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52768, 10.22808, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52674, 10.2284, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52529, 10.22828, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52389, 10.22716, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52225, 10.22434, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.52061, 10.22176, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.51898, 10.22079, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.51898, 10.22079, "Main", "R5"));
        stations.add(new MetroStation("Folzano", 45.49157, 10.21186, "Main", "R5"));
        stations.add(new MetroStation("Bagnolo Mella", 45.42839, 10.18375, "Main", "R5"));
        stations.add(new MetroStation("Manerbio", 45.35709, 10.13251, "Main", "R5"));
        stations.add(new MetroStation("Verolanuova", 45.325, 10.08371, "Main", "R5"));
        stations.add(new MetroStation("Robecco Pontevico", 45.26614, 10.07881, "Main", "R5"));
        stations.add(new MetroStation("Olmeneta", 45.23143, 10.03184, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.14857, 10.03518, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.14625, 10.03397, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.1446, 10.03179, "Main", "R5"));
        stations.add(new MetroStation("NO_DRAW", 45.14343, 10.02829, "Main", "R5"));
        stations.add(new MetroStation("Cremona", 45.14322, 10.01796, "Main", "R5"));
        return stations;
    }

    public static List<MetroStation> getStationsR6() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "R6"));
        stations.add(new MetroStation("Caravaggio", 45.4913, 9.6378, "Main", "R6"));
        stations.add(new MetroStation("Capralba", 45.44352, 9.64001, "Main", "R6"));
        stations.add(new MetroStation("Casaletto Vaprio", 45.40864, 9.63164, "Main", "R6"));
        stations.add(new MetroStation("Crema", 45.36669, 9.69408, "Main", "R6"));
        stations.add(new MetroStation("Madignano", 45.34367, 9.73026, "Main", "R6"));
        stations.add(new MetroStation("Castelleone", 45.30173, 9.7677, "Main", "R6"));
        stations.add(new MetroStation("Soresina", 45.28319, 9.85666, "Main", "R6"));
        stations.add(new MetroStation("Casalbuttano", 45.25543, 9.96243, "Main", "R6"));
        stations.add(new MetroStation("Olmeneta", 45.23143, 10.03184, "Main", "R6"));
        stations.add(new MetroStation("NO_DRAW", 45.14857, 10.03518, "Main", "R6"));
        stations.add(new MetroStation("NO_DRAW", 45.14625, 10.03397, "Main", "R6"));
        stations.add(new MetroStation("NO_DRAW", 45.1446, 10.03179, "Main", "R6"));
        stations.add(new MetroStation("NO_DRAW", 45.14343, 10.02829, "Main", "R6"));
        stations.add(new MetroStation("Cremona", 45.14322, 10.01796, "Main", "R6"));
        return stations;
    }

    public static List<MetroStation> getStationsR7() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "R7"));
        stations.add(new MetroStation("Lecco Maggianico", 45.83102, 9.41205, "Main", "R7"));
        stations.add(new MetroStation("Vercurago S. Girolamo", 45.81281, 9.42259, "Main", "R7"));
        stations.add(new MetroStation("Calolziocorte Olginate", 45.80175, 9.42714, "Main", "R7"));
        stations.add(new MetroStation("Cisano Caprino Bergamasco", 45.73972, 9.47441, "Main", "R7"));
        stations.add(new MetroStation("Pontida", 45.73017, 9.51172, "Main", "R7"));
        stations.add(new MetroStation("Ambivere Mapello", 45.72215, 9.55022, "Main", "R7"));
        stations.add(new MetroStation("Ponte S. Pietro", 45.69904, 9.58446, "Main", "R7"));
        stations.add(new MetroStation("Bergamo", 45.69043, 9.67504, "Main", "R7"));
        return stations;
    }

    public static List<MetroStation> getStationsR8() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52981, 10.22417, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52943, 10.22569, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52872, 10.22711, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52768, 10.22808, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52674, 10.2284, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52529, 10.22828, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52389, 10.22716, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52225, 10.22434, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.52061, 10.22176, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.51898, 10.22079, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.51898, 10.22079, "Main", "R8"));
        stations.add(new MetroStation("Folzano", 45.49157, 10.21186, "Main", "R8"));
        stations.add(new MetroStation("Montirone", 45.45129, 10.23934, "Main", "R8"));
        stations.add(new MetroStation("Ghedi", 45.40929, 10.28039, "Main", "R8"));
        stations.add(new MetroStation("Viadana Bresciana", 45.37372, 10.332, "Main", "R8"));
        stations.add(new MetroStation("Calvisano", 45.34656, 10.34921, "Main", "R8"));
        stations.add(new MetroStation("Visano", 45.31532, 10.36334, "Main", "R8"));
        stations.add(new MetroStation("Remedello Sopra", 45.27846, 10.379, "Main", "R8"));
        stations.add(new MetroStation("Remedello Sotto", 45.25744, 10.38827, "Main", "R8"));
        stations.add(new MetroStation("Asola", 45.22049, 10.40249, "Main", "R8"));
        stations.add(new MetroStation("Canneto Sull'Oglio", 45.15018, 10.3717, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.1321, 10.36136, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.13079, 10.3617, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.12947, 10.36325, "Main", "R8"));
        stations.add(new MetroStation("Piadena", 45.12776, 10.37008, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.12619, 10.37532, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.12478, 10.37768, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 45.12287, 10.37875, "Main", "R8"));
        stations.add(new MetroStation("San Giovanni In Croce", 45.07577, 10.37735, "Main", "R8"));
        stations.add(new MetroStation("Casalmaggiore", 44.99303, 10.42486, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.98963, 10.42714, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.98777, 10.42744, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.98631, 10.42723, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.9847, 10.42645, "Main", "R8"));
        stations.add(new MetroStation("Mezzano Rondani", 44.96691, 10.39907, "Main", "R8"));
        stations.add(new MetroStation("Colorno", 44.92796, 10.38279, "Main", "R8"));
        stations.add(new MetroStation("Torrile San Polo", 44.88572, 10.3627, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.80993, 10.34638, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.80841, 10.3449, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.80763, 10.34312, "Main", "R8"));
        stations.add(new MetroStation("NO_DRAW", 44.80739, 10.33926, "Main", "R8"));
        stations.add(new MetroStation("Parma", 44.81027, 10.32858, "Main", "R8"));
        return stations;
    }

    public static List<MetroStation> getStationsR9() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Iseo", 45.65657, 10.05003, "Main", "R9"));
        stations.add(new MetroStation("Provaglio Timoline", 45.63504, 10.03538, "Main", "R9"));
        stations.add(new MetroStation("Borgonato - Adro", 45.62225, 10.02005, "Main", "R9"));
        stations.add(new MetroStation("Bornato - Calino", 45.59035, 10.03298, "Main", "R9"));
        stations.add(new MetroStation("Cazzago S. Martino", 45.57642, 10.02756, "Main", "R9"));
        stations.add(new MetroStation("Rovato Città", 45.56581, 10.00597, "Main", "R9"));
        stations.add(new MetroStation("Rovato FN", 45.55256, 10.00183, "Main", "R9"));
        return stations;
    }

    public static List<MetroStation> getStationsR11() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Colico", 46.13789, 9.37528, "Main", "R11"));
        stations.add(new MetroStation("Dubino", 46.17267, 9.43217, "Main", "R11"));
        stations.add(new MetroStation("Verceia", 46.20273, 9.45862, "Main", "R11"));
        stations.add(new MetroStation("Novate Mezzola", 46.21663, 9.44674, "Main", "R11"));
        stations.add(new MetroStation("Samolaco", 46.24645, 9.42372, "Main", "R11"));
        stations.add(new MetroStation("S.Cassiano Valchiavenna", 46.27419, 9.39772, "Main", "R11"));
        stations.add(new MetroStation("Prata Camportaccio", 46.30778, 9.39772, "Main", "R11"));
        stations.add(new MetroStation("Chiavenna", 46.31961, 9.40493, "Main", "R11"));
        return stations;
    }

    public static List<MetroStation> getStationsR12() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Tirano", 46.21552, 10.16697, "Main", "R12"));
        stations.add(new MetroStation("Villa Di Tirano", 46.19625, 10.13053, "Main", "R12"));
        stations.add(new MetroStation("Bianzone", 46.17946, 10.11245, "Main", "R12"));
        stations.add(new MetroStation("Tresenda Aprica Teglio", 46.16214, 10.08366, "Main", "R12"));
        stations.add(new MetroStation("S. Giacomo di Teglio", 46.15985, 10.02754, "Main", "R12"));
        stations.add(new MetroStation("Chiuro", 46.16459, 9.99241, "Main", "R12"));
        stations.add(new MetroStation("Ponte in Valtellina", 46.16275, 9.97594, "Main", "R12"));
        stations.add(new MetroStation("Poggiridenti Trevisio Piateda", 46.16696, 9.93396, "Main", "R12"));
        stations.add(new MetroStation("Sondrio", 46.1672, 9.87288, "Main", "R12"));
        return stations;
    }

    public static List<MetroStation> getStationsR13() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "R13"));
        stations.add(new MetroStation("Abbadia Lariana", 45.89578, 9.33506, "Main", "R13"));
        stations.add(new MetroStation("Mandello del Lario", 45.91511, 9.32193, "Main", "R13"));
        stations.add(new MetroStation("Olcio", 45.93804, 9.31401, "Main", "R13"));
        stations.add(new MetroStation("Lierna", 45.96087, 9.30283, "Main", "R13"));
        stations.add(new MetroStation("Fiumelatte", 45.99904, 9.29271, "Main", "R13"));
        stations.add(new MetroStation("Varenna Esino", 46.01491, 9.2862, "Main", "R13"));
        stations.add(new MetroStation("Bellano Tartavalle Terme", 46.03987, 9.30277, "Main", "R13"));
        stations.add(new MetroStation("Dervio", 46.07764, 9.30622, "Main", "R13"));
        stations.add(new MetroStation("Dorio", 46.10264, 9.31921, "Main", "R13"));
        stations.add(new MetroStation("Piona", 46.12475, 9.35057, "Main", "R13"));
        stations.add(new MetroStation("Colico", 46.13789, 9.37528, "Main", "R13"));
        stations.add(new MetroStation("Delebio", 46.13853, 9.46118, "Main", "R13"));
        stations.add(new MetroStation("Cosio Traona", 46.13548, 9.52552, "Main", "R13"));
        stations.add(new MetroStation("Morbegno", 46.13658, 9.56877, "Main", "R13"));
        stations.add(new MetroStation("Talamona", 46.13982, 9.60042, "Main", "R13"));
        stations.add(new MetroStation("Ardenno Masino", 46.16159, 9.65066, "Main", "R13"));
        stations.add(new MetroStation("S. Pietro Berbenno", 46.1612, 9.74049, "Main", "R13"));
        stations.add(new MetroStation("Castione Andeveno", 46.16153, 9.80806, "Main", "R13"));
        stations.add(new MetroStation("Sondrio", 46.1672, 9.87288, "Main", "R13"));
        return stations;
    }

    public static List<MetroStation> getStationsR14() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.48358, 9.1891, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.48346, 9.19056, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.48403, 9.19168, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.48569, 9.19374, "Main", "R14"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "R14"));
        stations.add(new MetroStation("Sesto S. Giovanni", 45.54126, 9.23903, "Main", "R14"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.5819, 9.28449, "Main", "R14"));
        stations.add(new MetroStation("NO_DRAW", 45.60336, 9.30516, "Main", "R14"));
        stations.add(new MetroStation("Arcore", 45.62377, 9.32292, "Main", "R14"));
        stations.add(new MetroStation("Carnate Usmate", 45.65318, 9.37493, "Main", "R14"));
        stations.add(new MetroStation("Paderno Robbiate", 45.67839, 9.43921, "Main", "R14"));
        stations.add(new MetroStation("Calusco", 45.68428, 9.47082, "Main", "R14"));
        stations.add(new MetroStation("Terno D'Isola", 45.68852, 9.53619, "Main", "R14"));
        stations.add(new MetroStation("Ponte S. Pietro", 45.69904, 9.58446, "Main", "R14"));
        stations.add(new MetroStation("Bergamo Ospedale", 45.68821, 9.63868, "Main", "R14"));
        stations.add(new MetroStation("Bergamo", 45.69043, 9.67504, "Main", "R14"));
        return stations;
    }

    public static List<MetroStation> getStationsR15() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Carnate Usmate", 45.65318, 9.37493, "Main", "R15"));
        stations.add(new MetroStation("Lesmo", 45.64466, 9.3093, "Main", "R15"));
        stations.add(new MetroStation("Macherio Sovico", 45.64239, 9.26689, "Main", "R15"));
        stations.add(new MetroStation("Seregno",  45.64609,  9.20302,  "Main", "R15"));
        return stations;
    }

    public static List<MetroStation> getStationsR16() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "R16"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "R16"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "R16"));
        stations.add(new MetroStation("Milano Affori", 45.52131, 9.16743, "Main", "R16"));
        stations.add(new MetroStation("NO_DRAW", 45.54571, 9.17409, "Main", "R16"));
        stations.add(new MetroStation("NO_DRAW", 45.56499, 9.16132, "Main", "R16"));
        stations.add(new MetroStation("NO_DRAW", 45.58107, 9.15667, "Main", "R16"));
        stations.add(new MetroStation("NO_DRAW", 45.59555, 9.15341, "Main", "R16"));
        stations.add(new MetroStation("NO_DRAW", 45.61199, 9.14128, "Main", "R16"));
        stations.add(new MetroStation("Cesano Maderno", 45.63073, 9.14209, "Main", "R16"));
        stations.add(new MetroStation("Seveso", 45.6483, 9.14018, "Main", "R16"));
        stations.add(new MetroStation("Meda", 45.66242, 9.15886, "Main", "R16"));
        stations.add(new MetroStation("Cabiate", 45.67592, 9.16843, "Main", "R16"));
        stations.add(new MetroStation("Mariano Comense", 45.69358, 9.18141, "Main", "R16"));
        stations.add(new MetroStation("Carugo - Giussano", 45.70581, 9.19883, "Main", "R16"));
        stations.add(new MetroStation("Arosio", 45.71831, 9.21013, "Main", "R16"));
        stations.add(new MetroStation("Inverigo", 45.74041, 9.22436, "Main", "R16"));
        stations.add(new MetroStation("Lambrugo - Lurago D'Erba", 45.76163, 9.23861, "Main", "R16"));
        stations.add(new MetroStation("Merone", 45.78032, 9.24342, "Main", "R16"));
        stations.add(new MetroStation("Erba", 45.81309, 9.23006, "Main", "R16"));
        stations.add(new MetroStation("Pontelambro Castelmarte", 45.82922, 9.22559, "Main", "R16"));
        stations.add(new MetroStation("Caslino D'Erba", 45.83518, 9.23219, "Main", "R16"));
        stations.add(new MetroStation("Canzo", 45.84903, 9.27034, "Main", "R16"));
        stations.add(new MetroStation("Asso", 45.855, 9.2693, "Main", "R16"));
        return stations;
    }

    public static List<MetroStation> getStationsR17() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Como Lago", 45.81416, 9.08419, "Main", "R17"));
        stations.add(new MetroStation("Como Borghi", 45.8049, 9.09082, "Main", "R17"));
        stations.add(new MetroStation("Como Camerlata", 45.78457, 9.0794, "Main", "R17"));
        stations.add(new MetroStation("Grandate - Breccia", 45.78145, 9.05574, "Main", "R17"));
        stations.add(new MetroStation("Portichetto - Luisago", 45.75928, 9.05047, "Main", "R17"));
        stations.add(new MetroStation("Fino Mornasco", 45.74647, 9.04717, "Main", "R17"));
        stations.add(new MetroStation("Cadorago", 45.7299, 9.04101, "Main", "R17"));
        stations.add(new MetroStation("Calsino Al Piano", 45.71114, 9.0401, "Main", "R17"));
        stations.add(new MetroStation("Lomazzo", 45.69867, 9.0363, "Main", "R17"));
        stations.add(new MetroStation("Rovellasca - Manera", 45.66574, 9.03827, "Main", "R17"));
        stations.add(new MetroStation("Rovello Porro", 45.65159, 9.03634, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.64006, 9.03486, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.63827, 9.03173, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.63644, 9.02744, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.6341, 9.02405, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.63272, 9.02347, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.63047, 9.02392, "Main", "R17"));
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.61235, 9.04557, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.5981, 9.05794, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.5908, 9.06631, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.58014, 9.08042, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.57108, 9.09097, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.55262, 9.11209, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.54289, 9.12283, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.533, 9.13238, "Main", "R17"));
        stations.add(new MetroStation("NO_DRAW", 45.51918, 9.14562, "Main", "R17"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "R17"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "R17"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "R17"));
        return stations;
    }

    public static List<MetroStation> getStationsR18() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Como S. Giovanni", 45.80901, 9.07279, "Main", "R18"));
        stations.add(new MetroStation("Como Camerlata", 45.78457, 9.0794, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.78139, 9.07736, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.77799, 9.0738, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.77587, 9.07324, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.77335, 9.0741, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.772, 9.07779, "Main", "R18"));
        stations.add(new MetroStation("NO_DRAW", 45.77623, 9.08744, "Main", "R18"));
        stations.add(new MetroStation("Albate - Trecallo", 45.77753, 9.09582, "Main", "R18"));
        stations.add(new MetroStation("Cantù", 45.75239, 9.13008, "Main", "R18"));
        stations.add(new MetroStation("Brenna - Alzate", 45.75416, 9.18327, "Main", "R18"));
        stations.add(new MetroStation("Anzano del Parco", 45.76505, 9.20267, "Main", "R18"));
        stations.add(new MetroStation("Merone", 45.78032, 9.24342, "Main", "R18"));
        stations.add(new MetroStation("Moiana", 45.7872, 9.25578, "Main", "R18"));
        stations.add(new MetroStation("Casletto - Rogeno", 45.78618, 9.27539, "Main", "R18"));
        stations.add(new MetroStation("Molteno", 45.78085, 9.30171, "Main", "R18"));
        stations.add(new MetroStation("Oggiono", 45.78891, 9.33758, "Main", "R18"));
        stations.add(new MetroStation("Sala Al Barro - Galbiate", 45.82118, 9.35937, "Main", "R18"));
        stations.add(new MetroStation("Civate", 45.83133, 9.35275, "Main", "R18"));
        stations.add(new MetroStation("Valmadrera", 45.84641, 9.36801, "Main", "R18"));
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "R18"));
        return stations;
    }

    public static List<MetroStation> getStationsR21() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Luino",  45.9969,  8.73738,  "Main", "R21"));
        stations.add(new MetroStation("Porto Valtraglia",  45.95836,  8.67402,  "Main","R21"));
        stations.add(new MetroStation("Calde'",  45.9457,  8.66324,  "Main","R21"));
        stations.add(new MetroStation("Laveno Mombello",  45.90326,  8.6244,  "Main","R21"));
        stations.add(new MetroStation("Sangiano",  45.87449,  8.63087,  "Main","R21"));
        stations.add(new MetroStation("Besozzo",  45.84233,  8.66322,  "Main","R21"));
        stations.add(new MetroStation("Travedona Biandronno",  45.80976,  8.69615,  "Main","R21"));
        stations.add(new MetroStation("Ternate Varano Borghi",  45.78183,  8.70069,  "Main","R21"));
        stations.add(new MetroStation("Mornago Cimbro",  45.72985,  8.73441,  "Main","R21"));
        stations.add(new MetroStation("Besnate",  45.69549,  8.76125,  "Main","R21"));
        stations.add(new MetroStation("Gallarate",  45.65974,  8.79853,  "Main","R21"));
        stations.add(new MetroStation("Busto Arsizio", 45.61593, 8.86589, "Main", "R21"));
        stations.add(new MetroStation("Legnano", 45.5937, 8.91087, "Main", "R21"));
        stations.add(new MetroStation("Canegrate", 45.56934, 8.92673, "Main", "R21"));
        stations.add(new MetroStation("Parabiago", 45.55261, 8.94651, "Main", "R21"));
        stations.add(new MetroStation("NO_DRAW", 45.52536, 8.99573, "Main", "R21"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "R21"));
        stations.add(new MetroStation("NO_DRAW", 45.50683, 9.13593, "Main", "R21"));
        stations.add(new MetroStation("NO_DRAW", 45.50202, 9.15092, "Main", "R21"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "R21"));
        return stations;
    }

    public static List<MetroStation> getStationsR22() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Laveno Mombello Lago", 45.90845, 8.61866, "Main", "R22"));
        stations.add(new MetroStation("Cittiglio", 45.89232, 8.66753, "Main", "R22"));
        stations.add(new MetroStation("Gemonio", 45.87782, 8.6739, "Main", "R22"));
        stations.add(new MetroStation("Cocquio Trevisago", 45.86029, 8.68993, "Main", "R22"));
        stations.add(new MetroStation("Gavirate", 45.84566, 8.71452, "Main", "R22"));
        stations.add(new MetroStation("Barasso Comerio", 45.83534, 8.75106, "Main", "R22"));
        stations.add(new MetroStation("Morosolo Casciago", 45.82657, 8.7771, "Main", "R22"));
        stations.add(new MetroStation("Varese Casbeno", 45.81506, 8.8114, "Main", "R22"));
        stations.add(new MetroStation("Varese Nord", 45.81837, 8.8335, "Main", "R22"));
        stations.add(new MetroStation("Malnate", 45.79546, 8.87826, "Main", "R22"));
        stations.add(new MetroStation("Vedano Olona", 45.77736, 8.88697, "Main", "R22"));
        stations.add(new MetroStation("Venegono Superiore - Castiglione", 45.75819, 8.8904, "Main", "R22"));
        stations.add(new MetroStation("Venegono Inferiore", 45.73672, 8.89524, "Main", "R22"));
        stations.add(new MetroStation("Tradate", 45.71044, 8.90694, "Main", "R22"));
        stations.add(new MetroStation("Abbiate Guazzone", 45.702, 8.9187, "Main", "R22"));
        stations.add(new MetroStation("Locate Varesino - Carbonate", 45.68991, 8.93596, "Main", "R22"));
        stations.add(new MetroStation("Mozzate", 45.67781, 8.95505, "Main", "R22"));
        stations.add(new MetroStation("Cislago", 45.66098, 8.97749, "Main", "R22"));
        stations.add(new MetroStation("Gerenzano Turate", 45.64559, 9.00144, "Main", "R22"));
        stations.add(new MetroStation("Saronno",  45.62534,  9.03075,  "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.61235, 9.04557, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.5981, 9.05794, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.5908, 9.06631, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.58014, 9.08042, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.57108, 9.09097, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.55262, 9.11209, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.54289, 9.12283, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.533, 9.13238, "Main", "R22"));
        stations.add(new MetroStation("NO_DRAW", 45.51918, 9.14562, "Main", "R22"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "R22"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "R22"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "R22"));
        return stations;
    }

    public static List<MetroStation> getStationsR23() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.50202, 9.15092, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.50683, 9.13593, "Main", "R23"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.52411, 9.04355, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.52536, 8.99573, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.55261, 8.94651, "Main", "R23"));
        stations.add(new MetroStation("NO_DRAW", 45.56934, 8.92673, "Main", "R23"));
        stations.add(new MetroStation("Legnano", 45.5937, 8.91087, "Main", "R23"));
        stations.add(new MetroStation("Busto Arsizio", 45.61593, 8.86589, "Main", "R23"));
        stations.add(new MetroStation("Gallarate", 45.65974, 8.79853, "Main", "R23"));
        stations.add(new MetroStation("Casorate Sempione", 45.67502, 8.74387, "Main", "R23"));
        stations.add(new MetroStation("Somma Lombardo", 45.68639, 8.71296, "Main", "R23"));
        stations.add(new MetroStation("Vergiate", 45.71723, 8.69349, "Main", "R23"));
        stations.add(new MetroStation("Sesto Calende", 45.72624, 8.62813, "Main", "R23"));
        stations.add(new MetroStation("Dormelletto", 45.72338, 8.58226, "Main", "R23"));
        stations.add(new MetroStation("Arona", 45.75539, 8.55908, "Main", "R23"));
        stations.add(new MetroStation("Meina", 45.7944, 8.53596, "Main", "R23"));
        stations.add(new MetroStation("Lesa", 45.82571, 8.55869, "Main", "R23"));
        stations.add(new MetroStation("Belgirate", 45.84334, 8.5711, "Main", "R23"));
        stations.add(new MetroStation("Stresa", 45.88449, 8.53146, "Main", "R23"));
        stations.add(new MetroStation("Baveno", 45.90915, 8.50207, "Main", "R23"));
        stations.add(new MetroStation("Verbania - Pallanza", 45.94378, 8.47271, "Main", "R23"));
        stations.add(new MetroStation("Mergozzo", 45.9613, 8.44088, "Main", "R23"));
        stations.add(new MetroStation("Cuzzago", 45.99382, 8.37275, "Main", "R23"));
        stations.add(new MetroStation("Premosello Chiovenda", 46.00245, 8.32718, "Main", "R23"));
        stations.add(new MetroStation("Vogogna Ossola", 46.00588, 8.29466, "Main", "R23"));
        stations.add(new MetroStation("Domodossola", 46.11548, 8.2965, "Main", "R23"));
        return stations;
    }

    public static List<MetroStation> getStationsR24() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Laveno Mombello", 45.90326, 8.6244, "Main", "R24"));
        stations.add(new MetroStation("Leggiuno Monvalle", 45.86739, 8.62976, "Main", "R24"));
        stations.add(new MetroStation("Ispra", 45.81168, 8.62103, "Main", "R24"));
        stations.add(new MetroStation("Taino Angera", 45.77236, 8.60576, "Main", "R24"));
        stations.add(new MetroStation("Sesto Calende", 45.72624, 8.62813, "Main", "R24"));
        return stations;
    }

    public static List<MetroStation> getStationsR25() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Albonese", 45.29779, 8.7146, "Main", "R25"));
        stations.add(new MetroStation("Borgo Lavezzaro", 45.31876, 8.70319, "Main", "R25"));
        stations.add(new MetroStation("Vespolate", 45.3515, 8.67188, "Main", "R25"));
        stations.add(new MetroStation("Garbagna", 45.38559, 8.66642, "Main", "R25"));
        stations.add(new MetroStation("NO_DRAW", 45.40708, 8.66339, "Main", "R25"));
        stations.add(new MetroStation("NO_DRAW", 45.41255, 8.6618, "Main", "R25"));
        stations.add(new MetroStation("NO_DRAW", 45.41795, 8.65779, "Main", "R25"));
        stations.add(new MetroStation("NO_DRAW", 45.41795, 8.65779, "Main", "R25"));
        stations.add(new MetroStation("Novara", 45.45111, 8.62451, "Main", "R25"));
        return stations;
    }

    public static List<MetroStation> getStationsR27() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Novara Nord", 45.45236, 8.62669, "Main", "R27"));
        stations.add(new MetroStation("Galliate", 45.48106, 8.69135, "Main", "R27"));
        stations.add(new MetroStation("Galliate Parco del Ticino", 45.50629, 8.71782, "Main", "R27"));
        stations.add(new MetroStation("Turbigo", 45.52819, 8.73883, "Main", "R27"));
        stations.add(new MetroStation("Castano Primo", 45.556, 8.77282, "Main", "R27"));
        stations.add(new MetroStation("Vanzaghello Magnago", 45.57758, 8.78765, "Main", "R27"));
        stations.add(new MetroStation("Busto Arsizio Nord",  45.60617,  8.85139,  "Main","R27"));
        stations.add(new MetroStation("Castellanza",  45.61056,  8.87547,  "Main", "R27"));
        stations.add(new MetroStation("Rescaldina",  45.62229,  8.94666,  "Main", "R27"));
        stations.add(new MetroStation("Saronno",  45.62534,  9.03075,  "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.61235, 9.04557, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.5981, 9.05794, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.5908, 9.06631, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.58014, 9.08042, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.57108, 9.09097, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.55262, 9.11209, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.54289, 9.12283, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.533, 9.13238, "Main", "R27"));
        stations.add(new MetroStation("NO_DRAW", 45.51918, 9.14562, "Main", "R27"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "R27"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "R27"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "R27"));
        return stations;
    }

    public static List<MetroStation> getStationsR31() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Mortara", 45.25217, 8.73165, "Main", "R31"));
        stations.add(new MetroStation("Parona Lomellina", 45.28022, 8.78027, "Main", "R31"));
        stations.add(new MetroStation("Vigevano", 45.31284, 8.86189, "Main", "R31"));
        stations.add(new MetroStation("Abbiategrasso", 45.39758, 8.92044, "Main", "R31"));
        stations.add(new MetroStation("Albairate - Vermezzo", 45.40435, 8.95822, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.40874, 9.03118, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.42025, 9.0669, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.43044, 9.09158, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.43605, 9.10899, "Main", "R31"));
        stations.add(new MetroStation("Milano S. Cristoforo", 45.44256, 9.13008, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44803, 9.15253, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44798, 9.15556, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44724, 9.15768, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44568, 9.15947, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44392, 9.16207, "Main", "R31"));
        stations.add(new MetroStation("Milano Romolo", 45.44335, 9.1675, "Main", "R31"));
        stations.add(new MetroStation("Milano Tibaldi - Bocconi", 45.44394, 9.18506, "Main", "R31"));
        stations.add(new MetroStation("Milano Scalo Romana", 45.44585, 9.21303, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44652, 9.22732, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44637, 9.23, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.4461, 9.23245, "Main", "R31"));
        stations.add(new MetroStation("NO_DRAW", 45.44472, 9.23457, "Main", "R31"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "R31"));
        return stations;
    }

    public static List<MetroStation> getStationsR32() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Mortara", 45.25217, 8.73165, "Main", "R32"));
        stations.add(new MetroStation("Olevano", 45.2121, 8.71197, "Main", "R32"));
        stations.add(new MetroStation("Valle Lomellina", 45.1506, 8.68577, "Main", "R32"));
        stations.add(new MetroStation("Sartirana", 45.11173, 8.67, "Main", "R32"));
        stations.add(new MetroStation("Torreberetti", 45.06041, 8.66069, "Main", "R32"));
        stations.add(new MetroStation("Valenza", 45.0235, 8.62605, "Main", "R32"));
        stations.add(new MetroStation("Valmadonna", 44.97261, 8.61085, "Main", "R32"));
        stations.add(new MetroStation("Alessandria", 44.9088, 8.60757, "Main", "R32"));
        return stations;
    }

    public static List<MetroStation> getStationsR33() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "R33"));
        stations.add(new MetroStation("S. Martino Cava", 45.14492, 9.11521, "Main", "R33"));
        stations.add(new MetroStation("Bressana Bottarone", 45.08628, 9.11075, "Main", "R33"));
        stations.add(new MetroStation("Lungavilla", 45.04979, 9.06906, "Main", "R33"));
        stations.add(new MetroStation("NO_DRAW", 45.00154, 9.02083, "Main", "R33"));
        stations.add(new MetroStation("NO_DRAW", 44.99957, 9.01779, "Main", "R33"));
        stations.add(new MetroStation("Voghera", 44.99789, 9.00861, "Main", "R33"));
        return stations;
    }

    public static List<MetroStation> getStationsR34() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.48358, 9.1891, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.48346, 9.19056, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.48403, 9.19168, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.48569, 9.19374, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.4989, 9.20421, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.50056, 9.20715, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.50117, 9.21019, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.50112, 9.21172, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.49605, 9.22629, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.49466, 9.22843, "Main", "R34"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "R34"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.35974, 9.22152, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.33966, 9.20351, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.32047, 9.19051, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.25656, 9.1542, "Main", "R34"));
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "R34"));
        stations.add(new MetroStation("S. Martino Cava", 45.14492, 9.11521, "Main", "R34"));
        stations.add(new MetroStation("Bressana Bottarone", 45.08628, 9.11075, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.08167, 9.1077, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.07408, 9.11792, "Main", "R34"));
        stations.add(new MetroStation("Bressana Argine", 45.07284, 9.13702, "Main", "R34"));
        stations.add(new MetroStation("Pinarolo Po", 45.07333, 9.17029, "Main", "R34"));
        stations.add(new MetroStation("Barbinello", 45.0723, 9.20552, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.06224, 9.23796, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.06198, 9.2443, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.06327, 9.24911, "Main", "R34"));
        stations.add(new MetroStation("Broni", 45.06623, 9.25893, "Main", "R34"));
        stations.add(new MetroStation("Stradella", 45.07947, 9.30086, "Main", "R34"));
        stations.add(new MetroStation("Arena Po", 45.08217, 9.36031, "Main", "R34"));
        stations.add(new MetroStation("Castel S. Giovanni", 45.06313, 9.43678, "Main", "R34"));
        stations.add(new MetroStation("Sarmato", 45.06291, 9.49108, "Main", "R34"));
        stations.add(new MetroStation("Rottofreno", 45.06043, 9.54914, "Main", "R34"));
        stations.add(new MetroStation("S. Nicolò", 45.05868, 9.60648, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05573, 9.63675, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05973, 9.68695, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05994, 9.69216, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05971, 9.69646, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05874, 9.70049, "Main", "R34"));
        stations.add(new MetroStation("NO_DRAW", 45.05705, 9.70329, "Main", "R34"));
        stations.add(new MetroStation("Piacenza", 45.05196, 9.70609, "Main", "R34"));
        return stations;
    }

    public static List<MetroStation> getStationsR35() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "R35"));
        stations.add(new MetroStation("NO_DRAW", 45.147, 9.07146, "Main", "R35"));
        stations.add(new MetroStation("Sairano", 45.13881, 9.04694, "Main", "R35"));
        stations.add(new MetroStation("Sairano Zinasco", 45.13373, 9.03019, "Main", "R35"));
        stations.add(new MetroStation("Zinasco Nuovo", 45.12452, 8.99978, "Main", "R35"));
        stations.add(new MetroStation("Pieve Albignola", 45.11621, 8.95975, "Main", "R35"));
        stations.add(new MetroStation("Sannazzaro", 45.10546, 8.90514, "Main", "R35"));
        stations.add(new MetroStation("Ferrera Lomellina", 45.11192, 8.86284, "Main", "R35"));
        stations.add(new MetroStation("NO_DRAW", 45.12236, 8.82717, "Main", "R35"));
        stations.add(new MetroStation("NO_DRAW", 45.12297, 8.82275, "Main", "R35"));
        stations.add(new MetroStation("NO_DRAW", 45.12267, 8.8185, "Main", "R35"));
        stations.add(new MetroStation("Lomello", 45.11737, 8.79909, "Main", "R35"));
        stations.add(new MetroStation("Mede", 45.09506, 8.74248, "Main", "R35"));
        stations.add(new MetroStation("Torreberetti", 45.06041, 8.66069, "Main", "R35"));
        stations.add(new MetroStation("Valenza", 45.0235, 8.62605, "Main", "R35"));
        stations.add(new MetroStation("Valmadonna", 44.97261, 8.61085, "Main", "R35"));
        stations.add(new MetroStation("Alessandria", 44.9088, 8.60757, "Main", "R35"));
        return stations;
    }

    public static List<MetroStation> getStationsR36() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "R36"));
        stations.add(new MetroStation("Cava Carbonara", 45.147, 9.07146, "Main", "R36"));
        stations.add(new MetroStation("Vilanova D'Ardenghi", 45.15703, 9.03725, "Main", "R36"));
        stations.add(new MetroStation("Gropello Cairoli", 45.1732, 8.99173, "Main", "R36"));
        stations.add(new MetroStation("Garlasco", 45.19886, 8.92681, "Main", "R36"));
        stations.add(new MetroStation("Tromello", 45.2119, 8.87214, "Main", "R36"));
        stations.add(new MetroStation("Gambolò Remondò", 45.23529, 8.79975, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.24627, 8.7335, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.24736, 8.73207, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.2488, 8.73114, "Main", "R36"));
        stations.add(new MetroStation("Mortara", 45.25217, 8.73165, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.25702, 8.73222, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.25809, 8.73177, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.25933, 8.73032, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.26035, 8.72724, "Main", "R36"));
        stations.add(new MetroStation("Nicorvo", 45.27558, 8.67129, "Main", "R36"));
        stations.add(new MetroStation("Robbio", 45.29342, 8.59701, "Main", "R36"));
        stations.add(new MetroStation("Palestro", 45.30714, 8.53437, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.3385, 8.44845, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.3389, 8.44724, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.33915, 8.44599, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.3392, 8.44488, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.33903, 8.44361, "Main", "R36"));
        stations.add(new MetroStation("NO_DRAW", 45.3386, 8.44179, "Main", "R36"));
        stations.add(new MetroStation("Vercelli", 45.32975, 8.41639, "Main", "R36"));
        return stations;
    }

    public static List<MetroStation> getStationsR37() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.19312, 9.15027, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.19347, 9.15176, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.19352, 9.15346, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.19258, 9.15616, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.19026, 9.16128, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.18864, 9.16317, "Main", "R37"));
        stations.add(new MetroStation("Pavia Pta Garibaldi", 45.18421, 9.1667, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.18346, 9.16904, "Main", "R37"));
        stations.add(new MetroStation("Motta S. Damiano", 45.17821, 9.21992, "Main", "R37"));
        stations.add(new MetroStation("Albuzzano", 45.17238, 9.26174, "Main", "R37"));
        stations.add(new MetroStation("Belgioioso", 45.16441, 9.31687, "Main", "R37"));
        stations.add(new MetroStation("Corteolona", 45.15958, 9.37548, "Main", "R37"));
        stations.add(new MetroStation("S. Cristina e Bissone", 45.1604, 9.40104, "Main", "R37"));
        stations.add(new MetroStation("Miradolo Terme", 45.1619, 9.44985, "Main", "R37"));
        stations.add(new MetroStation("Chignolo Po", 45.16081, 9.49418, "Main", "R37"));
        stations.add(new MetroStation("Lambrinia", 45.16426, 9.52742, "Main", "R37"));
        stations.add(new MetroStation("Orio Litta", 45.16667, 9.5506, "Main", "R37"));
        stations.add(new MetroStation("Ospedaletto Lodigiano", 45.16919, 9.57523, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.18669, 9.63925, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.18681, 9.64441, "Main", "R37"));
        stations.add(new MetroStation("NO_DRAW", 45.18555, 9.64825, "Main", "R37"));
        stations.add(new MetroStation("Casalpusterlengo", 45.18235, 9.65366, "Main", "R37"));
        stations.add(new MetroStation("Codogno", 45.15496, 9.70135, "Main", "R37"));
        return stations;
    }

    public static List<MetroStation> getStationsR38() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Piacenza", 45.05196, 9.70609, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.05681, 9.70405, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.05883, 9.70432, "Main", "R38"));
        stations.add(new MetroStation("S. Stefano Lodigiano", 45.12207, 9.7238, "Main", "R38"));
        stations.add(new MetroStation("Codogno", 45.15496, 9.70135, "Main", "R38"));
        stations.add(new MetroStation("Casalpusterlengo", 45.18235, 9.65366, "Main", "R38"));
        stations.add(new MetroStation("Secugnago", 45.22335, 9.58696, "Main", "R38"));
        stations.add(new MetroStation("Lodi", 45.3092, 9.49776, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.32641, 9.40282, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.33783, 9.36165, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.35647, 9.31936, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.39134, 9.28652, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.4045, 9.27046, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.41879, 9.25291, "Main", "R38"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "R38"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.49492, 9.2282, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.49663, 9.22502, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.49825, 9.22059, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.50579, 9.21207, "Main", "R38"));
        stations.add(new MetroStation("NO_DRAW", 45.50886, 9.21184, "Main", "R38"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "R38"));
        return stations;
    }

    public static List<MetroStation> getStationsR39() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Codogno", 45.15496, 9.70135, "Main", "R39"));
        stations.add(new MetroStation("NO_DRAW", 45.1497, 9.70613, "Main", "R39"));
        stations.add(new MetroStation("NO_DRAW", 45.14846, 9.70914, "Main", "R39"));
        stations.add(new MetroStation("NO_DRAW", 45.14801, 9.71223, "Main", "R39"));
        stations.add(new MetroStation("NO_DRAW", 45.14843, 9.71592, "Main", "R39"));
        stations.add(new MetroStation("Maleo", 45.17007, 9.75533, "Main", "R39"));
        stations.add(new MetroStation("Pizzighettone", 45.18189, 9.77711, "Main", "R39"));
        stations.add(new MetroStation("Ponte D'Adda", 45.18441, 9.79092, "Main", "R39"));
        stations.add(new MetroStation("Acquanegra Cremonese", 45.17194, 9.8886, "Main", "R39"));
        stations.add(new MetroStation("Cava Tigozzi", 45.15671, 9.95572, "Main", "R39"));
        stations.add(new MetroStation("Cremona", 45.14322, 10.01796, "Main", "R39"));
        return stations;
    }

    public static List<MetroStation> getStationsR40() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Cremona", 45.14322, 10.01796, "Main", "R40"));
        stations.add(new MetroStation("Villetta Malagnino", 45.13686, 10.11201, "Main", "R40"));
        stations.add(new MetroStation("Gazzo Pieve S. Giacomo", 45.13702, 10.19003, "Main", "R40"));
        stations.add(new MetroStation("Torre De'Picenardi", 45.13733, 10.28694, "Main", "R40"));
        stations.add(new MetroStation("Torre De'Picenardi", 45.13733, 10.28694, "Main", "R40"));
        stations.add(new MetroStation("Piadena", 45.12776, 10.37008, "Main", "R40"));
        stations.add(new MetroStation("Bozzolo", 45.10718, 10.47275, "Main", "R40"));
        stations.add(new MetroStation("Marcaria", 45.12437, 10.53369, "Main", "R40"));
        stations.add(new MetroStation("S. Michele In Bosco", 45.12244, 10.57345, "Main", "R40"));
        stations.add(new MetroStation("Ospitaletto Mantovano", 45.12984, 10.60466, "Main", "R40"));
        stations.add(new MetroStation("Castellucchio", 45.14439, 10.64745, "Main", "R40"));
        stations.add(new MetroStation("Mantova", 45.15863, 10.78329, "Main", "R40"));
        return stations;
    }

    public static List<MetroStation> getStationsR41() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Voghera", 44.99789, 9.00861, "Main", "R41"));
        stations.add(new MetroStation("Casteggio", 45.01724, 9.12474, "Main", "R41"));
        stations.add(new MetroStation("S. Giuletta", 45.03563, 9.17935, "Main", "R41"));
        stations.add(new MetroStation("Broni", 45.06623, 9.25893, "Main", "R41"));
        stations.add(new MetroStation("Stradella", 45.07947, 9.30086, "Main", "R41"));
        stations.add(new MetroStation("Arena Po", 45.08217, 9.36031, "Main", "R41"));
        stations.add(new MetroStation("Castel S. Giovanni", 45.06313, 9.43678, "Main", "R41"));
        stations.add(new MetroStation("Sarmato", 45.06291, 9.49108, "Main", "R41"));
        stations.add(new MetroStation("Rottofreno", 45.06043, 9.54914, "Main", "R41"));
        stations.add(new MetroStation("S. Nicolò", 45.05868, 9.60648, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05573, 9.63675, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05973, 9.68695, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05994, 9.69216, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05971, 9.69646, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05874, 9.70049, "Main", "R41"));
        stations.add(new MetroStation("NO_DRAW", 45.05705, 9.70329, "Main", "R41"));
        stations.add(new MetroStation("Piacenza", 45.05196, 9.70609, "Main", "R41"));
        return stations;
    }

    public static List<MetroStation> getStationsRE1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Laveno Mombello Lago", 45.90845, 8.61866, "Main", "RE1"));
        stations.add(new MetroStation("Cittiglio", 45.89232, 8.66753, "Main", "RE1"));
        stations.add(new MetroStation("Gemonio", 45.87782, 8.6739, "Main", "RE1"));
        stations.add(new MetroStation("Cocquio Trevisago", 45.86029, 8.68993, "Main", "RE1"));
        stations.add(new MetroStation("Gavirate", 45.84566, 8.71452, "Main", "RE1"));
        stations.add(new MetroStation("Barasso Comerio", 45.83534, 8.75106, "Main", "RE1"));
        stations.add(new MetroStation("Morosolo Casciago", 45.82657, 8.7771, "Main", "RE1"));
        stations.add(new MetroStation("Varese Casbeno", 45.81506, 8.8114, "Main", "RE1"));
        stations.add(new MetroStation("Varese Nord", 45.81837, 8.8335, "Main", "RE1"));
        stations.add(new MetroStation("Malnate", 45.79546, 8.87826, "Main", "RE1"));
        stations.add(new MetroStation("Tradate", 45.71044, 8.90694, "Main", "RE1"));
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.61235, 9.04557, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.5981, 9.05794, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.5908, 9.06631, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.58014, 9.08042, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.57108, 9.09097, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.55262, 9.11209, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.54289, 9.12283, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.533, 9.13238, "Main", "RE1"));
        stations.add(new MetroStation("NO_DRAW", 45.51918, 9.14562, "Main", "RE1"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "RE1"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "RE1"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "RE1"));
        return stations;
    }

    public static List<MetroStation> getStationsRE2() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Garibaldi", "RE2"));
        stations.add(new MetroStation("Milano Villapizzone", 45.50202, 9.15092, "Garibaldi", "RE2"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Greco", "RE2"));
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Centrale", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49443, 9.21352, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49659, 9.21633, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49707, 9.21866, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49704, 9.22278, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49605, 9.22629, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.49466, 9.22843, "Main", "RE2"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.47459, 9.23925, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.47173, 9.2444, "Main", "RE2"));
        stations.add(new MetroStation("NO_DRAW", 45.472, 9.25028, "Main", "RE2"));
        stations.add(new MetroStation("Pioltello Limito", 45.48611, 9.32949, "Main", "RE2"));
        stations.add(new MetroStation("Treviglio Ovest", 45.52171, 9.58044, "Main", "RE2"));
        stations.add(new MetroStation("Verdello Dalmine", 45.60571, 9.6198, "Main", "RE2"));
        stations.add(new MetroStation("Bergamo", 45.69043, 9.67504, "Main", "RE2"));
        return stations;
    }

    public static List<MetroStation> getStationsRE3() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "RE3"));
        stations.add(new MetroStation("Bornato - Calino", 45.59035, 10.03298, "Main", "RE3"));
        stations.add(new MetroStation("Iseo", 45.65657, 10.05003, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66224, 10.05384, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66488, 10.06002, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66539, 10.06448, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66646, 10.06706, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66806, 10.06976, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.66965, 10.07418, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.67325, 10.07804, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.67854, 10.08743, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.68051, 10.09102, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.68363, 10.09394, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.68583, 10.09804, "Main", "RE3"));
        stations.add(new MetroStation("Sulzano", 45.68955, 10.10057, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.69264, 10.10422, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.69966, 10.10813, "Main", "RE3"));
        stations.add(new MetroStation("Sale Marasino", 45.71288, 10.11159, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.72073, 10.11019, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.72525, 10.10525, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 45.7313, 10.09426, "Main", "RE3"));
        stations.add(new MetroStation("Marone Zone", 45.73838, 10.09324, "Main", "RE3"));
        stations.add(new MetroStation("Pisogne", 45.80659, 10.10575, "Main", "RE3"));
        stations.add(new MetroStation("Darfo - Corna", 45.88429, 10.18106, "Main", "RE3"));
        stations.add(new MetroStation("Boario Terme", 45.89214, 10.19083, "Main", "RE3"));
        stations.add(new MetroStation("Breno", 45.95908, 10.30571, "Main", "RE3"));
        stations.add(new MetroStation("Capo Di Ponte", 46.03108, 10.34631, "Main", "RE3"));
        stations.add(new MetroStation("Cedegolo", 46.07252, 10.35009, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.08773, 10.31501, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.10273, 10.3108, "Main", "RE3"));
        stations.add(new MetroStation("Malonno", 46.11875, 10.31667, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.15891, 10.349, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.16771, 10.34999, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.16965, 10.34917, "Main", "RE3"));
        stations.add(new MetroStation("NO_DRAW", 46.17078, 10.34739, "Main", "RE3"));
        stations.add(new MetroStation("Edolo", 46.17701, 10.32948, "Main", "RE3"));
        return stations;
    }

    public static List<MetroStation> getStationsRE4() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Domodossola", 46.11548, 8.2965, "Main", "RE4"));
        stations.add(new MetroStation("Verbania - Pallanza", 45.94378, 8.47271, "Main", "RE4"));
        stations.add(new MetroStation("Stresa", 45.88449, 8.53146, "Main", "RE4"));
        stations.add(new MetroStation("Arona", 45.75539, 8.55908, "Main", "RE4"));
        stations.add(new MetroStation("Sesto Calende", 45.72624, 8.62813, "Main", "RE4"));
        stations.add(new MetroStation("Gallarate", 45.65974, 8.79853, "Main", "RE4"));
        stations.add(new MetroStation("Busto Arsizio", 45.61593, 8.86589, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.5937, 8.91087, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.56934, 8.92673, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.55261, 8.94651, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.52536, 8.99573, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.52411, 9.04355, "Main", "RE4"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50683, 9.13593, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50202, 9.15092, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.48449, 9.18737, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.48358, 9.1891, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.48346, 9.19056, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.48403, 9.19168, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.48569, 9.19374, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.4989, 9.20421, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50056, 9.20715, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50117, 9.21019, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50112, 9.21172, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.50067, 9.21408, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.49986, 9.21498, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.49832, 9.21502, "Main", "RE4"));
        stations.add(new MetroStation("NO_DRAW", 45.49543, 9.2131, "Main", "RE4"));
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Main", "RE4"));
        return stations;
    }

    public static List<MetroStation> getStationsRE5() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Porto Ceresio", 45.90371, 8.9005, "Main", "RE5"));
        stations.add(new MetroStation("Bisuschio - Viggiù", 45.87071, 8.88779, "Main", "RE5"));
        stations.add(new MetroStation("Arcisate", 45.85857, 8.86291, "Main", "RE5"));
        stations.add(new MetroStation("Induno Olona", 45.84587, 8.83802, "Main", "RE5"));
        stations.add(new MetroStation("Varese", 45.81625, 8.83295, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.77868, 8.82473, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.74525, 8.81051, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.7233, 8.80628, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.69814, 8.80371, "Main", "RE5"));
        stations.add(new MetroStation("Gallarate", 45.65974, 8.79853, "Main", "RE5"));
        stations.add(new MetroStation("Busto Arsizio", 45.61593, 8.86589, "Main", "RE5"));
        stations.add(new MetroStation("Legnano", 45.5937, 8.91087, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.56934, 8.92673, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.55261, 8.94651, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.52536, 8.99573, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.52411, 9.04355, "Main", "RE5"));
        stations.add(new MetroStation("Rho FieraMilano", 45.52113, 9.0885, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.50683, 9.13593, "Main", "RE5"));
        stations.add(new MetroStation("NO_DRAW", 45.50202, 9.15092, "Main", "RE5"));
        stations.add(new MetroStation("Milano Pta Garibaldi", 45.48449, 9.18737, "Main", "RE5"));
        return stations;
    }

    public static List<MetroStation> getStationsRE6() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49443, 9.21352, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49659, 9.21633, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49707, 9.21866, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49704, 9.22278, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49605, 9.22629, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49466, 9.22843, "Main", "RE6"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.47459, 9.23925, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.47173, 9.2444, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.472, 9.25028, "Main", "RE6"));
        stations.add(new MetroStation("Pioltello Limito", 45.48611, 9.32949, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.49441, 9.37649, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.50208, 9.4192, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.50852, 9.45631, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.51283, 9.48068, "Main", "RE6"));
        stations.add(new MetroStation("NO_DRAW", 45.51446, 9.51278, "Main", "RE6"));
        stations.add(new MetroStation("Treviglio", 45.51531, 9.58864, "Main", "RE6"));
        stations.add(new MetroStation("Romano", 45.52366, 9.75348, "Main", "RE6"));
        stations.add(new MetroStation("Chiari", 45.53994, 9.92658, "Main", "RE6"));
        stations.add(new MetroStation("Rovato", 45.5523, 10.00101, "Main", "RE6"));
        stations.add(new MetroStation("Brescia", 45.53252, 10.21297, "Main", "RE6"));
        stations.add(new MetroStation("Desenzano del Garda", 45.46274, 10.53642, "Main", "RE6"));
        stations.add(new MetroStation("Peschiera del Garda", 45.43856, 10.70242, "Main", "RE6"));
        stations.add(new MetroStation("Verona P.N", 45.42902, 10.98249, "Main", "RE6"));
        return stations;
    }

    public static List<MetroStation> getStationsRE7() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Como Lago", 45.81416, 9.08419, "Main", "RE7"));
        stations.add(new MetroStation("Como Borghi", 45.8049, 9.09082, "Main", "RE7"));
        stations.add(new MetroStation("Como Camerlata", 45.78457, 9.0794, "Main", "RE7"));
        stations.add(new MetroStation("Grandate - Breccia", 45.78145, 9.05574, "Main", "RE7"));
        stations.add(new MetroStation("Lomazzo", 45.69867, 9.0363, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.64006, 9.03486, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.63827, 9.03173, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.63644, 9.02744, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.6341, 9.02405, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.63272, 9.02347, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.63047, 9.02392, "Main", "RE7"));
        stations.add(new MetroStation("Saronno", 45.62534, 9.03075, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.61235, 9.04557, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.5981, 9.05794, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.5908, 9.06631, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.58014, 9.08042, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.57108, 9.09097, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.55262, 9.11209, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.54289, 9.12283, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.533, 9.13238, "Main", "RE7"));
        stations.add(new MetroStation("NO_DRAW", 45.51918, 9.14562, "Main", "RE7"));
        stations.add(new MetroStation("Milano Bovisa", 45.50257, 9.15925, "Main", "RE7"));
        stations.add(new MetroStation("Milano Domodossola", 45.48089, 9.16224, "Main", "RE7"));
        stations.add(new MetroStation("Milano Cadorna", 45.46843, 9.17553, "Main", "RE7"));
        return stations;
    }

    public static List<MetroStation> getStationsRE8() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Main", "RE8"));
        stations.add(new MetroStation("Monza", 45.57797, 9.27289, "Main", "RE8"));
        stations.add(new MetroStation("Lecco", 45.85637, 9.3934, "Main", "RE8"));
        stations.add(new MetroStation("Mandello del Lario", 45.91511, 9.32193, "Main", "RE8"));
        stations.add(new MetroStation("Varenna Esino", 46.01491, 9.2862, "Main", "RE8"));
        stations.add(new MetroStation("Bellano Tartavalle Terme", 46.03987, 9.30277, "Main", "RE8"));
        stations.add(new MetroStation("Colico", 46.13789, 9.37528, "Main", "RE8"));
        stations.add(new MetroStation("Morbegno", 46.13658, 9.56877, "Main", "RE8"));
        stations.add(new MetroStation("Sondrio", 46.1672, 9.87288, "Main", "RE8"));
        stations.add(new MetroStation("Tresenda Aprica Teglio", 46.16214, 10.08366, "Main", "RE8"));
        stations.add(new MetroStation("Tirano", 46.21552, 10.16697, "Main", "RE8"));
        return stations;
    }

    public static List<MetroStation> getStationsRE11() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Mantova", 45.15863, 10.78329, "Main", "RE11"));
        stations.add(new MetroStation("Castellucchio", 45.14439, 10.64745, "Main", "RE11"));
        stations.add(new MetroStation("Marcaria", 45.12437, 10.53369, "Main", "RE11"));
        stations.add(new MetroStation("Bozzolo", 45.10718, 10.47275, "Main", "RE11"));
        stations.add(new MetroStation("Piadena", 45.12776, 10.37008, "Main", "RE11"));
        stations.add(new MetroStation("Cremona", 45.14322, 10.01796, "Main", "RE11"));
        stations.add(new MetroStation("Ponte D'Adda", 45.18441, 9.79092, "Main", "RE11"));
        stations.add(new MetroStation("Codogno", 45.15496, 9.70135, "Main", "RE11"));
        stations.add(new MetroStation("Lodi", 45.3092, 9.49776, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.32641, 9.40282, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.33783, 9.36165, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.35647, 9.31936, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.39134, 9.28652, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.4045, 9.27046, "Main", "RE11"));
        stations.add(new MetroStation("NO_DRAW", 45.41879, 9.25291, "Main", "RE11"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "RE11"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "RE11"));
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Main", "RE11"));
        return stations;
    }

    public static List<MetroStation> getStationsRE13() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Milano Centrale", 45.48713, 9.20482, "Main", "RE13"));
        stations.add(new MetroStation("Milano Lambrate", 45.48475, 9.23678, "Main", "RE13"));
        stations.add(new MetroStation("Milano Rogoredo", 45.43386, 9.23911, "Main", "RE13"));
        stations.add(new MetroStation("Pavia", 45.18878, 9.14488, "Main", "RE13"));
        stations.add(new MetroStation("Pizzale Lungavilla", 45.04979, 9.06906, "Main", "RE13"));
        stations.add(new MetroStation("Voghera", 44.99789, 9.00861, "Main", "RE13"));
        stations.add(new MetroStation("Tortona", 44.89995, 8.86206, "Main", "RE13"));
        stations.add(new MetroStation("Alessandria", 44.9088, 8.60757, "Asti", "RE13"));
        stations.add(new MetroStation("Asti", 44.8951, 8.20801, "Asti", "RE13"));
        stations.add(new MetroStation("Pozzolo Formigaro", 44.79729, 8.78265, "Novi Ligure", "RE13"));
        stations.add(new MetroStation("Novi Ligure", 44.76251, 8.78694, "Novi Ligure", "RE13"));
        stations.add(new MetroStation("Arquata", 44.69223, 8.885, "Novi Ligure", "RE13"));
        return stations;
    }

    public static List<MetroStation> getStationsS10() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Biasca",  46.35198,  8.97416,  "Main","S10"));
        stations.add(new MetroStation("Castione",  46.22363,  9.0415,  "Main","S10"));
        stations.add(new MetroStation("Bellinzona",  46.19543,  9.02951,  "Main","S10"));
        stations.add(new MetroStation("Giubiasco",  46.17381,  9.00359,  "Main","S10"));
        stations.add(new MetroStation("Lugano",  46.00501,  8.94695,  "Main","S10"));
        stations.add(new MetroStation("Lugano Paradiso",  45.98917,  8.94656,  "Main","S10"));
        stations.add(new MetroStation("Melide",  45.95576,  8.94823,  "Main","S10"));
        stations.add(new MetroStation("Maroggia - Melano",  45.93241,  8.97383,  "Main","S10"));
        stations.add(new MetroStation("Capolago - Riva S. Vitale",  45.90285,  8.97889,  "Main","S10"));
        stations.add(new MetroStation("Mendrisio S. Martino",  45.87721,  8.98308,  "Main","S10"));
        stations.add(new MetroStation("Mendrisio",  45.8691,  8.97878,  "Main","S10"));
        stations.add(new MetroStation("Balerna",  45.84681,  9.0051,  "Main","S10"));
        stations.add(new MetroStation("Chiasso",  45.83217,  9.03175,  "Main","S10"));
        stations.add(new MetroStation("Como S. Giovanni",  45.80901,  9.07279,  "Main","S10"));
        stations.add(new MetroStation("Como Camerlata",  45.78457,  9.0794,  "Main","S10"));
        return stations;
    }

    public static List<MetroStation> getStationsS50() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Biasca",  46.35198,  8.97416,  "Main","S50"));
        stations.add(new MetroStation("Castione",  46.22363,  9.0415,  "Main","S50"));
        stations.add(new MetroStation("Bellinzona",  46.19543,  9.02951,  "Main","S50"));
        stations.add(new MetroStation("Giubiasco",  46.17381,  9.00359,  "Main","S50"));
        stations.add(new MetroStation("Lugano",  46.00501,  8.94695,  "Main","S50"));
        stations.add(new MetroStation("Lugano Paradiso",  45.98917,  8.94656,  "Main","S50"));
        stations.add(new MetroStation("Melide",  45.95576,  8.94823,  "Main","S50"));
        stations.add(new MetroStation("Maroggia - Melano",  45.93241,  8.97383,  "Main","S50"));
        stations.add(new MetroStation("Capolago - Riva S. Vitale",  45.90285,  8.97889,  "Main","S50"));
        stations.add(new MetroStation("Mendrisio S. Martino",  45.87721,  8.98308,  "Main","S50"));
        stations.add(new MetroStation("Mendrisio",  45.8691,  8.97878,  "Main","S50"));
        stations.add(new MetroStation("Stabio",  45.84971,  8.94394,  "Main","S50"));
        stations.add(new MetroStation("Cantello Gaggiolo",  45.83781,  8.90739,  "Main","S50"));
        stations.add(new MetroStation("Arcisate",  45.85857,  8.86291,  "Main","S50"));
        stations.add(new MetroStation("Induno Olona",  45.84587,  8.83802,  "Main","S50"));
        stations.add(new MetroStation("Varese",  45.81625,  8.83295,  "Main","S50"));
        stations.add(new MetroStation("Gallarate",  45.65974,  8.79853,  "Main","S50"));
        stations.add(new MetroStation("Busto Arsizio",  45.61593,  8.86589,  "Main","S50"));
        stations.add(new MetroStation("Busto Arsizio Nord",  45.60617,  8.85139,  "Main","S50"));
        stations.add(new MetroStation("Ferno - Lonate Pozzolo",  45.60854,  8.75525,  "Main","S50"));
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 1",  45.62714,  8.71129,  "Main","S50"));
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 2",  45.65013,  8.72133,  "Main","S50"));
        return stations;
    }

    public static List<MetroStation> getStationsS30() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Cadenazzo",  46.15262,  8.94168,  "Main","S30"));
        stations.add(new MetroStation("Quartino",  46.15129,  8.88732,  "Main","S30"));
        stations.add(new MetroStation("Magadino - Vira",  46.14531,  8.85019,  "Main","S30"));
        stations.add(new MetroStation("S. Nazzaro",  46.13519,  8.80704,  "Main","S30"));
        stations.add(new MetroStation("Gerra",  46.12207,  8.78494,  "Main","S30"));
        stations.add(new MetroStation("Ranzo - S. Abbondio",  46.11697,  8.7766,  "Main","S30"));
        stations.add(new MetroStation("Pino - Tronzano",  46.09866,  8.73706,  "Main","S30"));
        stations.add(new MetroStation("Maccagno",  46.04324,  8.73763,  "Main","S30"));
        stations.add(new MetroStation("Colmegna",  46.02475,  8.75189,  "Main","S30"));
        stations.add(new MetroStation("Luino",  45.9969,  8.73738,  "Main","S30"));
        stations.add(new MetroStation("Porto Valtraglia",  45.95836,  8.67402,  "Main","S30"));
        stations.add(new MetroStation("Calde'",  45.9457,  8.66324,  "Main","S30"));
        stations.add(new MetroStation("Laveno Mombello",  45.90326,  8.6244,  "Main","S30"));
        stations.add(new MetroStation("Sangiano",  45.87449,  8.63087,  "Main","S30"));
        stations.add(new MetroStation("Besozzo",  45.84233,  8.66322,  "Main","S30"));
        stations.add(new MetroStation("Travedona Biandronno",  45.80976,  8.69615,  "Main","S30"));
        stations.add(new MetroStation("Ternate Varano Borghi",  45.78183,  8.70069,  "Main","S30"));
        stations.add(new MetroStation("Mornago Cimbro",  45.72985,  8.73441,  "Main","S30"));
        stations.add(new MetroStation("Besnate",  45.69549,  8.76125,  "Main","S30"));
        stations.add(new MetroStation("Gallarate",  45.65974,  8.79853,  "Main","S30"));
        return stations;
    }

    public static List<MetroStation> getStationsS40() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Como S. Giovanni",  45.80901,  9.07279,  "Main","S40"));
        stations.add(new MetroStation("Chiasso",  45.83217,  9.03175,  "Main","S40"));
        stations.add(new MetroStation("Balerna",  45.84681,  9.0051,  "Main","S40"));
        stations.add(new MetroStation("Mendrisio",  45.8691,  8.97878,  "Main","S40"));
        stations.add(new MetroStation("Stabio",  45.84971,  8.94394,  "Main","S40"));
        stations.add(new MetroStation("Cantello Gaggiolo",  45.83781,  8.90739,  "Main","S40"));
        stations.add(new MetroStation("Arcisate",  45.85857,  8.86291,  "Main","S40"));
        stations.add(new MetroStation("Induno Olona",  45.84587,  8.83802,  "Main","S40"));
        stations.add(new MetroStation("Varese",  45.81625,  8.83295,  "Main","S40"));
        return stations;
    }

    public static List<MetroStation> getStationsRE80() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Locarno",  46.17264,  8.80166,  "Main","RE80"));
        stations.add(new MetroStation("Minusio",  46.174,  8.81956,  "Main","RE80"));
        stations.add(new MetroStation("Tenero",  46.1774,  8.85186,  "Main","RE80"));
        stations.add(new MetroStation("Gordola",  46.17915,  8.86585,  "Main","RE80"));
        stations.add(new MetroStation("Riazzino",  46.17532,  8.88625,  "Main","RE80"));
        stations.add(new MetroStation("Cadenazzo",  46.15262,  8.94168,  "Main","RE80"));
        stations.add(new MetroStation("S. Antonino",  46.16041,  8.97392,  "Main","RE80"));
        stations.add(new MetroStation("Lugano",  46.00501,  8.94695,  "Main","RE80"));
        stations.add(new MetroStation("Lugano Paradiso",  45.98917,  8.94656,  "Main","RE80"));
        stations.add(new MetroStation("Melide",  45.95576,  8.94823,  "Main","RE80"));
        stations.add(new MetroStation("Maroggia - Melano",  45.93241,  8.97383,  "Main","RE80"));
        stations.add(new MetroStation("Capolago - Riva S. Vitale",  45.90285,  8.97889,  "Main","RE80"));
        stations.add(new MetroStation("Mendrisio S. Martino",  45.87721,  8.98308,  "Main","RE80"));
        stations.add(new MetroStation("Mendrisio",  45.8691,  8.97878,  "Main","RE80"));
        stations.add(new MetroStation("Balerna",  45.84681,  9.0051,  "Main","RE80"));
        stations.add(new MetroStation("Chiasso",  45.83217,  9.03175,  "Main","RE80"));
        stations.add(new MetroStation("Como S. Giovanni",  45.80901,  9.07279,  "Main","RE80"));
        stations.add(new MetroStation("Seregno",  45.64609,  9.20302,  "Main","RE80"));
        stations.add(new MetroStation("Monza",  45.57797,  9.27289,  "Main","RE80"));
        stations.add(new MetroStation("Milano Centrale",  45.48713,  9.20482,  "Main","RE80"));
        return stations;
    }

    public static List<MetroStation> getStationsMXP1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Gallarate",  45.65974,  8.79853,  "Main","MXP1"));
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 2",  45.65013,  8.72133,  "Main","MXP1"));
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 1",  45.62714,  8.71129,  "Main","MXP1"));
        stations.add(new MetroStation("Ferno - Lonate Pozzolo",  45.60854,  8.75525,  "Main","MXP1"));
        stations.add(new MetroStation("Busto Arsizio Nord",  45.60617,  8.85139,  "Main","MXP1"));
        stations.add(new MetroStation("Castellanza",  45.61056,  8.87547,  "Main","MXP1"));
        stations.add(new MetroStation("Rescaldina",  45.62229,  8.94666,  "Main","MXP1"));
        stations.add(new MetroStation("Saronno",  45.62534,  9.03075,  "Main","MXP1"));
        stations.add(new MetroStation("Milano Bovisa",  45.50257,  9.15925,  "Main","MXP1"));
        stations.add(new MetroStation("Milano Pta Garibaldi",  45.48449,  9.18737,  "Main","MXP1"));
        stations.add(new MetroStation("Milano Centrale",  45.48713,  9.20482,  "Main","MXP1"));
        return stations;
    }

    public static List<MetroStation> getStationsMXP2() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 2",  45.65013,  8.72133,  "Main","MXP2"));
        stations.add(new MetroStation("Malpensa Aereoporto Terminal 1",  45.62714,  8.71129,  "Main","MXP2"));
        stations.add(new MetroStation("Busto Arsizio Nord",  45.60617,  8.85139,  "Main","MXP2"));
        stations.add(new MetroStation("Saronno",  45.62534,  9.03075,  "Main","MXP2"));
        stations.add(new MetroStation("Milano Bovisa",  45.50257,  9.15925,  "Main","MXP2"));
        stations.add(new MetroStation("Milano Domodossola",  45.48089,  9.16224,  "Main","MXP2"));
        stations.add(new MetroStation("Milano Cadorna",  45.46843,  9.17553,  "Main","MXP2"));
        return stations;
    }

    public static List<MetroStation> getStationsTram1() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Roserio (Ospedale Sacco)", 45.51736, 9.11962, "Main", "1"));
        stations.add(new MetroStation("Roserio", 45.51762, 9.11984, "Main", "1"));
        stations.add(new MetroStation("Via Grassi", 45.51379, 9.12571, "Main", "1"));
        stations.add(new MetroStation("Largo Boccioni", 45.51168, 9.12797, "Main", "1"));
        stations.add(new MetroStation("Via Mambretti", 45.50948, 9.13137, "Main", "1"));
        stations.add(new MetroStation("Certosa FS", 45.50778, 9.13729, "Main", "1"));
        stations.add(new MetroStation("V.Le Espinasse Via Palizzi", 45.50281, 9.1387, "Main", "1"));
        stations.add(new MetroStation("P.Le Santorre Di Santarosa", 45.50038, 9.14012, "Main", "1"));
        stations.add(new MetroStation("V.Le Espinasse Via Nuvolone", 45.49788, 9.14188, "Main", "1"));
        stations.add(new MetroStation("V.Le Espinasse Via Casella", 45.4956, 9.14347, "Main", "1"));
        stations.add(new MetroStation("V.Le Espinasse P.Le Accursio", 45.49413, 9.14448, "Main", "1"));
        stations.add(new MetroStation("P.Le Accursio", 45.49282, 9.14537, "Main", "1"));
        stations.add(new MetroStation("V.Le Certosa Via Grosotto", 45.4916, 9.14805, "Main", "1"));
        stations.add(new MetroStation("V.Le Certosa V.Le Serra", 45.49016, 9.15104, "Main", "1"));
        stations.add(new MetroStation("Piazza Firenze", 45.48781, 9.15631, "Main", "1"));
        stations.add(new MetroStation("C.So Sempione Via E. Filiberto", 45.48616, 9.15844, "Main", "1"));
        stations.add(new MetroStation("C.So Sempione Via Arona", 45.48396, 9.16105, "Main", "1"));
        stations.add(new MetroStation("Domodossola FN M5", 45.48209, 9.16358, "Main", "1"));
        stations.add(new MetroStation("C.So Sempione Via Procaccini", 45.48016, 9.16612, "Main", "1"));
        stations.add(new MetroStation("C.So Sempione Via Villasanta", 45.47916, 9.16743, "Main", "1"));
        stations.add(new MetroStation("C.So Sempione Via Canova", 45.47827, 9.1686, "Main", "1"));
        stations.add(new MetroStation("Arco Della Pace", 45.47639, 9.1714, "Main", "1"));
        stations.add(new MetroStation("Via Pagano Via Canova", 45.47422, 9.17002, "Main", "1"));
        stations.add(new MetroStation("L.Go Quinto Alpini", 45.47264, 9.16761, "Main", "1"));
        stations.add(new MetroStation("Via XX Settembre", 45.4703, 9.16988, "Main", "1"));
        stations.add(new MetroStation("Piazza Virgilio", 45.46851, 9.17237, "Main", "1"));
        stations.add(new MetroStation("Cadorna FN M1 M2", 45.46796, 9.17648, "Main", "1"));
        stations.add(new MetroStation("Foro Buonaparte Via Ricasoli", 45.46747, 9.17914, "Main", "1"));
        stations.add(new MetroStation("Cairoli M1", 45.46831, 9.18257, "Main", "1"));
        stations.add(new MetroStation("Via Cusani", 45.46884, 9.18483, "Main", "1"));
        stations.add(new MetroStation("Cordusio M1", 45.46566, 9.18691, "Main", "1"));
        stations.add(new MetroStation("Teatro Alla Scala", 45.46757, 9.19004, "Main", "1"));
        stations.add(new MetroStation("Montenapoleone M3", 45.4693, 9.192, "Main", "1"));
        stations.add(new MetroStation("Piazza Cavour", 45.47274, 9.19523, "Main", "1"));
        stations.add(new MetroStation("Turati M3", 45.47557, 9.19474, "Main", "1"));
        stations.add(new MetroStation("Repubblica M3", 45.47788, 9.19775, "Main", "1"));
        stations.add(new MetroStation("V.Le Vittorio Veneto", 45.47691, 9.20113, "Main", "1"));
        stations.add(new MetroStation("V.Le Tunisia", 45.47885, 9.20292, "Main", "1"));
        stations.add(new MetroStation("Via Vitruvio", 45.48217, 9.20646, "Main", "1"));
        stations.add(new MetroStation("Via Settembrini", 45.48376, 9.20803, "Main", "1"));
        stations.add(new MetroStation("Caiazzo M2", 45.48509, 9.20943, "Main", "1"));
        stations.add(new MetroStation("V.Le Brianza", 45.48764, 9.21222, "Main", "1"));
        stations.add(new MetroStation("Via Venini Via Battaglia", 45.48975, 9.21431, "Main", "1"));
        stations.add(new MetroStation("Piazza Morbegno", 45.49259, 9.21644, "Main", "1"));
        stations.add(new MetroStation("Greco Rovereto", 45.49495, 9.21625, "Main", "1"));
        return stations;
    }

   /*public static List<MetroStation> getStationsTram2() {
       List<MetroStation> stations = new ArrayList<>();
       stations.add(new MetroStation("P.le Negrelli", 45.44360, 9.13933, "Main", "2"));
       stations.add(new MetroStation("Via L. Il Moro Via Guintellino", 45.44494, 9.143068, "Main", "2"));
       stations.add(new MetroStation("Via L. Il Moro Cavalcavia Don Milani", 45.44559, 9.14584, "Main", "2"));
       stations.add(new MetroStation("Via L. Il Moro, 25", 45.44635, 9.14973, "Main", "2"));
       stations.add(new MetroStation("Via L. Il Moro Via Pestalozzi", 45.44729, 9.15419, "Main", "2"));
       stations.add(new MetroStation("Ponte Guido Crepax", 45.44812, 9.15800, "Main", "2"));
       stations.add(new MetroStation("Ripa Di P.ta Ticinese D'Adda", 45.44869, 9.16068, "Main", "2"));
       stations.add(new MetroStation("Ripa Di P.ta Ticinese Via Lombardini", 45.44918, 9.16318, "Main", "2"));
       stations.add(new MetroStation("Via Valenza Alzaia Nav. Grande", 45.45086, 9.16857, "Main", "2"));
       stations.add(new MetroStation("P.ta Genova M2", 45.45294, 9.17017, "Main", "2"));
       stations.add(new MetroStation("C.so Colombo P.le Cantore", 45.45495, 9.17220, "Main", "2"));
       stations.add(new MetroStation("V.le Coni Zugna Via Solari", 45.45733, 9.16909, "Main", "2"));
       stations.add(new MetroStation("Coni Zugna M4", 45.45943, 9.16590, "Main", "2"));
       stations.add(new MetroStation("P.le Aquileia", 45.46124, 9.16318, "Main", "2"));
       stations.add(new MetroStation("V.le S. Michele Del Carso P.za De Meis", 45.46390, 9.16419, "Main", "2"));
       stations.add(new MetroStation("P.le Baracca", 45.46561, 9.16469, "Main", "2"));
       stations.add(new MetroStation("Conciliazione M1", 45.46736, 9.16524, "Main", "2"));
       stations.add(new MetroStation("Via Ariosto", 0, 0, "Main", "2"));
       stations.add(new MetroStation("L.go Quinto Alpini", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Venti Settembre", 0, 0, "Main", "2"));
       stations.add(new MetroStation("P.za Virgilio", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Cadorna M1 M2", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Foro Buonaparte Via Ricasoli", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Cairoli M1", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Lanza M2", 0, 0, "Main", "2"));
       stations.add(new MetroStation("P.za Lega Lombarda", 0, 0, "Main", "2"));
       stations.add(new MetroStation("V.le Montello", 0, 0, "Main", "2"));
       stations.add(new MetroStation("P.le Baiamonti", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Farini Via Ferrari", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Farini Via Valtellina", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Farini Via Alserio", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Farini V.le Stelvio", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Bernina", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Lancetti", 0, 0, "Main", "2"));
       stations.add(new MetroStation("P.le Nigra", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Via Imbriani Via Scalvini", 0, 0, "Main", "2"));
       stations.add(new MetroStation("Bausan", 0, 0, "Main", "2"));
       return stations;
    }*/
    public static List<MetroStation> getStationsTram3() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Gratosoglio", 45.40365, 9.17444, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia", 45.40652, 9.17481, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia Via Saponaro", 45.41018, 9.17447, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia Via De Ruggiero", 45.4182, 9.17546, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia (Isola Anita)", 45.42281, 9.17698, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia Via S. Abbondio", 45.42443, 9.17711, "Main", "3"));
        stations.add(new MetroStation("Via Dei Missaglia Via Boifava", 45.42655, 9.17728, "Main", "3"));
        stations.add(new MetroStation("Piazza Abbiategrasso M2", 45.42924, 9.17747, "Main", "3"));
        stations.add(new MetroStation("Via Montegani Via Neera", 45.43284, 9.17777, "Main", "3"));
        stations.add(new MetroStation("Via Montegani Via Palmieri", 45.43463, 9.17799, "Main", "3"));
        stations.add(new MetroStation("Via Montegani V.Le Da Cermenate", 45.43718, 9.1783, "Main", "3"));
        stations.add(new MetroStation("Via Meda Via Spaventa", 45.44006, 9.17863, "Main", "3"));
        stations.add(new MetroStation("V.Le Tibaldi Via Meda", 45.44315, 9.17902, "Main", "3"));
        stations.add(new MetroStation("Largo Mahler", 45.44579, 9.17926, "Main", "3"));
        stations.add(new MetroStation("C.So S. Gottardo Via Lagrange", 45.44861, 9.17974, "Main", "3"));
        stations.add(new MetroStation("Piazza XXIV Maggio", 45.45177, 9.18015, "Main", "3"));
        stations.add(new MetroStation("Piazza XXIV Maggio", 45.4524, 9.17969, "Main", "3"));
        stations.add(new MetroStation("Via Vigevano V.Le Gorizia", 45.45272, 9.1763, "Main", "3"));
        stations.add(new MetroStation("Via Vigevano Via Corsico", 45.45308, 9.17344, "Main", "3"));
        stations.add(new MetroStation("Porta Genova M2", 45.45322, 9.17065, "Main", "3"));
        stations.add(new MetroStation("P.Le Cantore", 45.45491, 9.17225, "Main", "3"));
        return stations;
    }

    public static List<MetroStation> getStationsTram5() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Niguarda Ospedale", 45.50855, 9.18961, "Main", "5"));
        stations.add(new MetroStation("V.Le Ca' Granda Via Cherasco", 45.50839, 9.19179, "Main", "5"));
        stations.add(new MetroStation("V.Le Ca' Granda Via Val Furva", 45.50768, 9.19473, "Main", "5"));
        stations.add(new MetroStation("V.Le Ca' Granda V.Le Suzzani", 45.50695, 9.1978, "Main", "5"));
        stations.add(new MetroStation("Ca' Granda M5", 45.50643, 9.19989, "Main", "5"));
        stations.add(new MetroStation("V.Le Testi Via Dolcebuono", 45.50384, 9.19893, "Main", "5"));
        stations.add(new MetroStation("Istria M5", 45.50161, 9.19763, "Main", "5"));
        stations.add(new MetroStation("V.Le Zara Via Laurana", 45.49944, 9.19637, "Main", "5"));
        stations.add(new MetroStation("Marche M5", 45.4959, 9.19439, "Main", "5"));
        stations.add(new MetroStation("P.Za Carbonari", 45.49471, 9.19749, "Main", "5"));
        stations.add(new MetroStation("V.Le Lunigiana Via Gioia", 45.49223, 9.2037, "Main", "5"));
        stations.add(new MetroStation("V.Le Lunigiana", 45.49107, 9.20599, "Main", "5"));
        stations.add(new MetroStation("Stazione Centrale Via Tonale", 45.48866, 9.20385, "Main", "5"));
        stations.add(new MetroStation("Stazione Centrale M2 M3", 45.48685, 9.20192, "Main", "5"));
        stations.add(new MetroStation("Stazione Centrale P.Za Duca D'Aosta M2 M3", 45.48427, 9.20312, "Main", "5"));
        stations.add(new MetroStation("Via Vitruvio", 45.48254, 9.20632, "Main", "5"));
        stations.add(new MetroStation("P.Za Cincinnato", 45.4799, 9.20393, "Main", "5"));
        stations.add(new MetroStation("V.Le Tunisia", 45.47885, 9.20292, "Main", "5"));
        stations.add(new MetroStation("P.Ta Venezia V.Le Tunisia", 45.47668, 9.20672, "Main", "5"));
        stations.add(new MetroStation("P.Za Otto Novembre", 45.47475, 9.21084, "Main", "5"));
        stations.add(new MetroStation("P.Za Adelaide di Savoia", 45.4732, 9.21434, "Main", "5"));
        stations.add(new MetroStation("P.Za Ascoli", 45.47322, 9.21658, "Main", "5"));
        stations.add(new MetroStation("P.Za Aspari", 45.47195, 9.22088, "Main", "5"));
        stations.add(new MetroStation("P.Za Ferravilla", 45.47105, 9.22336, "Main", "5"));
        stations.add(new MetroStation("Via B. Angelico Via Colombo", 45.47087, 9.22649, "Main", "5"));
        stations.add(new MetroStation("Via B. Angelico Via Aselli", 45.47083, 9.22989, "Main", "5"));
        stations.add(new MetroStation("Via B. Angelico Via Paladini", 45.47086, 9.23322, "Main", "5"));
        stations.add(new MetroStation("Via Amadeo Via S. Benigno", 45.47091, 9.23624, "Main", "5"));
        stations.add(new MetroStation("Ortica", 45.47154, 9.23777, "Main", "5"));
        return stations;
    }

    public static List<MetroStation> getStationsTram7() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("P.Le Lagosta", 45.48975, 9.19152, "Main", "7"));
        stations.add(new MetroStation("Zara M3 M5", 45.49231, 9.19296, "Main", "7"));
        stations.add(new MetroStation("Marche M5", 45.49617, 9.19514, "Main", "7"));
        stations.add(new MetroStation("V.Le Zara Via Laurana", 45.49907, 9.19678, "Main", "7"));
        stations.add(new MetroStation("Istria M5", 45.50183, 9.19836, "Main", "7"));
        stations.add(new MetroStation("V.Le Testi Via Dolcebuono", 45.5039, 9.19949, "Main", "7"));
        stations.add(new MetroStation("Ca' Granda M5", 45.50687, 9.20119, "Main", "7"));
        stations.add(new MetroStation("V.Le Testi Via Pianell", 45.50814, 9.20191, "Main", "7"));
        stations.add(new MetroStation("V.Le Testi Via S. Marcellina", 45.51167, 9.20394, "Main", "7"));
        stations.add(new MetroStation("Via Pulci V.le Sarca", 45.51431, 9.20744, "Main", "7"));
        stations.add(new MetroStation("Università Bicocca Scienza", 45.51372, 9.21127, "Main", "7"));
        stations.add(new MetroStation("Milano Greco Pirelli", 45.51288, 9.21416, "Main", "7"));
        stations.add(new MetroStation("Arcimboldi Ateneo Nuovo", 45.51598, 9.21386, "Main", "7"));
        stations.add(new MetroStation("L.Go Mattei", 45.51395, 9.22123, "Main", "7"));
        stations.add(new MetroStation("Precotto M1", 45.51364, 9.22439, "Main", "7"));
        stations.add(new MetroStation("Tremelloni", 45.51395, 9.2276, "Main", "7"));
        stations.add(new MetroStation("Via Tremelloni Via Anassagora", 45.51429, 9.23108, "Main", "7"));
        stations.add(new MetroStation("Precotto Interno Deposito", 45.51786, 9.2329, "Main", "7"));
        return stations;
    }

    public static List<MetroStation> getStationsTram9() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Stazione Centrale M2 M3", 45.48699, 9.20277, "Main", "9"));
        stations.add(new MetroStation("Via Filzi Via Pirelli", 45.48431, 9.20018, "Main", "9"));
        stations.add(new MetroStation("Via Filzi Via Adda", 45.48316, 9.19939, "Main", "9"));
        stations.add(new MetroStation("P.Za S. Gioachimo", 45.48146, 9.19805, "Main", "9"));
        stations.add(new MetroStation("V.Le Monte Santo", 45.47978, 9.19489, "Main", "9"));
        stations.add(new MetroStation("Repubblica M3", 45.47788, 9.19775, "Main", "9"));
        stations.add(new MetroStation("V.Le Vittorio Veneto", 45.47678, 9.20024, "Main", "9"));
        stations.add(new MetroStation("Porta Venezia M1", 45.47418, 9.20594, "Main", "9"));
        stations.add(new MetroStation("V.Le Piave", 45.47225, 9.20654, "Main", "9"));
        stations.add(new MetroStation("Tricolore M4", 45.46831, 9.2069, "Main", "9"));
        stations.add(new MetroStation("V.Le Premuda", 45.46545, 9.20717, "Main", "9"));
        stations.add(new MetroStation("P.Za 5 Giornate", 45.46279, 9.20746, "Main", "9"));
        stations.add(new MetroStation("V.Le Monte Nero Via Spartaco", 45.46, 9.20727, "Main", "9"));
        stations.add(new MetroStation("V.Le Monte Nero Via Bergamo", 45.45741, 9.20542, "Main", "9"));
        stations.add(new MetroStation("V.Le Monte Nero Via Pier Lombardo", 45.45535, 9.2043, "Main", "9"));
        stations.add(new MetroStation("Porta Romana M3", 45.45139, 9.20241, "Main", "9"));
        stations.add(new MetroStation("V.Le Sabotino", 45.45098, 9.20052, "Main", "9"));
        stations.add(new MetroStation("Via Ripamonti V.Le Sabotino", 45.45102, 9.19707, "Main", "9"));
        stations.add(new MetroStation("V.Le Bigny", 45.45098, 9.1944, "Main", "9"));
        stations.add(new MetroStation("Via Bocconi", 45.45097, 9.19069, "Main", "9"));
        stations.add(new MetroStation("P.Ta Lodovica", 45.45178, 9.18711, "Main", "9"));
        stations.add(new MetroStation("V.Le Col Di Lana", 45.45213, 9.18346, "Main", "9"));
        stations.add(new MetroStation("Piazza XXIV Maggio", 45.4524, 9.17969, "Main", "9"));
        stations.add(new MetroStation("Via Vigevano V.Le Gorizia", 45.45272, 9.1763, "Main", "9"));
        stations.add(new MetroStation("P.Le Cantore", 45.45491, 9.17225, "Main", "9"));
        stations.add(new MetroStation("Porta Genova M2", 45.45341, 9.17002, "Main", "9"));
        return stations;
    }

    public static List<MetroStation> getStationsTram10() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("V.le Lunigiana", 45.49136, 9.20558, "Main", "10"));
        stations.add(new MetroStation("Via Schiaparelli Via P.te Seveso", 45.4897, 9.20476, "Main", "10"));
        stations.add(new MetroStation("Stazione Centrale Via Tonale", 45.48866, 9.20385, "Main", "10"));
        stations.add(new MetroStation("Stazione Centrale M2 M3", 45.48687, 9.20192, "Main", "10"));
        stations.add(new MetroStation("Via Filzi Via Pirelli", 45.48431, 9.20018, "Main", "10"));
        stations.add(new MetroStation("Via Filzi Via Adda", 45.48316, 9.19939, "Main", "10"));
        stations.add(new MetroStation("P.za S. Gioachimo", 45.48146, 9.19805, "Main", "10"));
        stations.add(new MetroStation("V.le Monte Santo", 45.47978, 9.19489, "Main", "10"));
        stations.add(new MetroStation("P.le Principessa Clotilde (Osp. Fatebenefratelli)", 45.48034, 9.19256, "Main", "10"));
        stations.add(new MetroStation("V.le Monte Grappa Via Gioia", 45.48082, 9.18907, "Main", "10"));
        stations.add(new MetroStation("Stazione Garibaldi M2 M5", 45.48374, 9.1867, "Main", "10"));
        stations.add(new MetroStation("Via Farini Via Ferrari", 45.48573, 9.18262, "Main", "10"));
        stations.add(new MetroStation("Monumentale M5", 45.48459, 9.18042, "Main", "10"));
        stations.add(new MetroStation("P.le Cim. Monumentale Via Bramante", 45.48415, 9.17845, "Main", "10"));
        stations.add(new MetroStation("Via Procaccini Via Nono", 45.48403, 9.17567, "Main", "10"));
        stations.add(new MetroStation("Via Procaccini Via Lomazzo", 45.48374, 9.17108, "Main", "10"));
        stations.add(new MetroStation("P.za Gramsci", 45.48202, 9.16815, "Main", "10"));
        stations.add(new MetroStation("C.so Sempione Via Procaccini", 45.48098, 9.16637, "Main", "10"));
        stations.add(new MetroStation("C.so Sempione Via Villasanta", 45.47917, 9.16743, "Main", "10"));
        stations.add(new MetroStation("C.so Sempione Via Canova", 45.47826, 9.16858, "Main", "10"));
        stations.add(new MetroStation("Arco Della Pace", 45.47639, 9.1714, "Main", "10"));
        stations.add(new MetroStation("Via Pagano Via Canova", 45.47422, 9.17002, "Main", "10"));
        stations.add(new MetroStation("L.go Quinto Alpini", 45.47264, 9.16761, "Main", "10"));
        stations.add(new MetroStation("Via Ariosto", 45.46995, 9.16591, "Main", "10"));
        stations.add(new MetroStation("Conciliazione M1", 45.46787, 9.16528, "Main", "10"));
        stations.add(new MetroStation("P.le Baracca", 45.46653, 9.16486, "Main", "10"));
        stations.add(new MetroStation("V.le S. Michele Del Carso P.za De Meis", 45.46392, 9.1641, "Main", "10"));
        stations.add(new MetroStation("P.le Aquileia", 45.46092, 9.16353, "Main", "10"));
        stations.add(new MetroStation("Coni Zugna M4", 45.45965, 9.16546, "Main", "10"));
        stations.add(new MetroStation("V.le Coni Zugna Via Solari", 45.4573, 9.16907, "Main", "10"));
        stations.add(new MetroStation("P.le Cantore", 45.45538, 9.17199, "Main", "10"));
        stations.add(new MetroStation("P.ta Genova M2", 45.45341, 9.17002, "Main", "10"));
        stations.add(new MetroStation("Via Vigevano Via Corsico", 45.45296, 9.17294, "Main", "10"));
        stations.add(new MetroStation("Via Vigevano V.le Gorizia", 45.45275, 9.17559, "Main", "10"));
        stations.add(new MetroStation("P.za Ventiquattro Maggio", 45.45199, 9.17941, "Main", "10"));
        return stations;
    }

    public static List<MetroStation> getStationsTram15() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Rozzano Via G. Rossa", 45.37693, 9.15001, "Main", "15"));
        stations.add(new MetroStation("Rozzano Togliatti", 45.37861, 9.14921, "Main", "15"));
        stations.add(new MetroStation("Rozzano Municipio", 45.38061, 9.15516, "Main", "15"));
        stations.add(new MetroStation("Rozzano Lombardia", 45.38199, 9.15952, "Main", "15"));
        stations.add(new MetroStation("Rozzano Romagna", 45.38283, 9.16199, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.38354, 9.16424, "Main", "15"));
        stations.add(new MetroStation("V.Le Romagna Rozzano", 45.38344, 9.16727, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.38319, 9.16995, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.38337, 9.17044, "Main", "15"));
        stations.add(new MetroStation("Via Grandi Via Buozzi Rozzano", 45.38393, 9.17061, "Main", "15"));
        stations.add(new MetroStation("Via Curiel Viale Isonzo Rozzano", 45.38905, 9.17205, "Main", "15"));
        stations.add(new MetroStation("Via Curiel Via Lambro Rozzano", 45.39426, 9.17302, "Main", "15"));
        stations.add(new MetroStation("Via Curiel Via Maggi Rozzano", 45.39822, 9.17345, "Main", "15"));
        stations.add(new MetroStation("Via dei Missaglia Via Baroni", 45.40383, 9.17463, "Main", "15"));
        stations.add(new MetroStation("Via dei Missaglia Scuola Santarosa", 45.40652, 9.17481, "Main", "15"));
        stations.add(new MetroStation("Via Dei Missaglia Via Saponaro", 45.41018, 9.17447, "Main", "15"));
        stations.add(new MetroStation("Via Dei Missaglia Via De Ruggiero", 45.4182, 9.17546, "Main", "15"));
        stations.add(new MetroStation("Via Dei Missaglia (Isola Anita)", 45.42281, 9.17698, "Main", "15"));
        stations.add(new MetroStation("Via Dei Missaglia Via S. Abbondio", 45.42443, 9.17711, "Main", "15"));
        stations.add(new MetroStation("Via Dei Missaglia Via Boifava", 45.42655, 9.17728, "Main", "15"));
        stations.add(new MetroStation("Piazza Abbiategrasso M2", 45.42924, 9.17747, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.42992, 9.17769, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.43025, 9.1781, "Main", "15"));
        stations.add(new MetroStation("Medeghino", 45.43142, 9.18049, "Main", "15"));
        stations.add(new MetroStation("Agrippa", 45.43278, 9.18307, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.43326, 9.18388, "Main", "15"));
        stations.add(new MetroStation("Volvino", 45.43462, 9.18371, "Main", "15"));
        stations.add(new MetroStation("G. Da Cermenate", 45.43769, 9.18348, "Main", "15"));
        stations.add(new MetroStation("Pezzotti", 45.44129, 9.18319, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.44409, 9.18274, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.44424, 9.18282, "Main", "15"));
        stations.add(new MetroStation("Tibaldi", 45.44441, 9.18342, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.44469, 9.18428, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.44484, 9.18441, "Main", "15"));
        stations.add(new MetroStation("Giambologna", 45.44567, 9.18466, "Main", "15"));
        stations.add(new MetroStation("Castelbarco", 45.4488, 9.18564, "Main", "15"));
        stations.add(new MetroStation("P.Ta Lodovica", 45.45152, 9.18658, "Main", "15"));
        stations.add(new MetroStation("C.So Italia Via Lusardi", 45.45363, 9.18701, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.45562, 9.18732, "Main", "15"));
        stations.add(new MetroStation("S. Sofia M4", 45.45591, 9.18745, "Main", "15"));
        stations.add(new MetroStation("Missori M3", 45.46014, 9.18825, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.46158, 9.18854, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.46278, 9.18856, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.46323, 9.18918, "Main", "15"));
        stations.add(new MetroStation("NO_DRAW", 45.46329, 9.18935, "Main", "15"));
        stations.add(new MetroStation("Duomo M1 M3", 45.46326, 9.18971, "Main", "15"));
        return stations;
    }

    public static List<MetroStation> getStationsTram16() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Via Monte Velino", 45.4531, 9.22888, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45309, 9.22901, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45306, 9.22905, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45299, 9.22909, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45245, 9.22911, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45235, 9.22907, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45227, 9.22892, "Main", "16"));
        stations.add(new MetroStation("Via Del Turchino Via Maspero", 45.45193, 9.22732, "Main", "16"));
        stations.add(new MetroStation("P.Le Cuoco", 45.4512, 9.22464, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45064, 9.22353, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45026, 9.22112, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.4502, 9.2207, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45023, 9.22045, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45033, 9.22021, "Main", "16"));
        stations.add(new MetroStation("P.Za Salgari", 45.45047, 9.21995, "Main", "16"));
        stations.add(new MetroStation("Via Tito Livio", 45.45189, 9.21644, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45257, 9.21472, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45261, 9.21466, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45267, 9.21462, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45275, 9.21459, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45286, 9.21462, "Main", "16"));
        stations.add(new MetroStation("V.Le Umbria Via Comelico", 45.45365, 9.21529, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45376, 9.21533, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45384, 9.21534, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45392, 9.2153, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.454, 9.2152, "Main", "16"));
        stations.add(new MetroStation("Via Comelico", 45.45457, 9.21386, "Main", "16"));
        stations.add(new MetroStation("Via Cadore Via Bergamo", 45.45553, 9.21154, "Main", "16"));
        stations.add(new MetroStation("Via Bergamo", 45.45644, 9.2093, "Main", "16"));
        stations.add(new MetroStation("V.Le Monte Nero Via Bergamo", 45.45772, 9.20602, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45785, 9.20567, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45799, 9.20514, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45797, 9.20497, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45789, 9.20483, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45777, 9.20474, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45762, 9.20465, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.4575, 9.20454, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45745, 9.20442, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45742, 9.20425, "Main", "16"));
        stations.add(new MetroStation("Via Lamarmora", 45.45726, 9.20297, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.4571, 9.20242, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45698, 9.20169, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45698, 9.20152, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45694, 9.20121, "Main", "16"));
        stations.add(new MetroStation("Via Lamarmora Via Commenda", 45.45669, 9.19934, "Main", "16"));
        stations.add(new MetroStation("Crocetta M3", 45.45618, 9.19619, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45612, 9.1959, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45616, 9.19575, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.45626, 9.19557, "Main", "16"));
        stations.add(new MetroStation("Sforza Policlinico M4", 45.4582, 9.19246, "Main", "16"));
        stations.add(new MetroStation("Missori M3", 45.4604, 9.18866, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46045, 9.18853, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46059, 9.18839, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46067, 9.18838, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46086, 9.18841, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46115, 9.18849, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46135, 9.18851, "Main", "16"));
        stations.add(new MetroStation("Duomo M1 M3", 45.46324, 9.18853, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46359, 9.18853, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46368, 9.1885, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46375, 9.18842, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46385, 9.18826, "Main", "16"));
        stations.add(new MetroStation("Via Orefici P.Za Cordusio", 45.46456, 9.18722, "Main", "16"));
        stations.add(new MetroStation("Cordusio M1", 45.46571, 9.18574, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46598, 9.18525, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.466, 9.18514, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.466, 9.18507, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46575, 9.18225, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46575, 9.18208, "Main", "16"));
        stations.add(new MetroStation("Via Meravigli", 45.46572, 9.18208, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46566, 9.18065, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46565, 9.18042, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46573, 9.1793, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46575, 9.17868, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.4658, 9.17817, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46596, 9.17751, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46602, 9.1773, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46606, 9.177, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46603, 9.17661, "Main", "16"));
        stations.add(new MetroStation("L.Go D'Ancona", 45.46605, 9.17604, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46591, 9.17545, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46574, 9.17411, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46573, 9.17393, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46575, 9.17296, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46572, 9.17202, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46571, 9.17141, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46573, 9.17108, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46576, 9.1707, "Main", "16"));
        stations.add(new MetroStation("S. Maria Delle Grazie Cenacolo Vinciano", 45.46576, 9.16991, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46568, 9.16885, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46575, 9.16815, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46588, 9.16725, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46608, 9.16588, "Main", "16"));
        stations.add(new MetroStation("P.Le Baracca", 45.46626, 9.16532, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46621, 9.16447, "Main", "16"));
        stations.add(new MetroStation("C.So Vercelli L.Go Settimio Severo", 45.46642, 9.16268, "Main", "16"));
        stations.add(new MetroStation("C.So Vercelli Via Cherubini", 45.46675, 9.15812, "Main", "16"));
        stations.add(new MetroStation("P.Za Piemonte", 45.46709, 9.15356, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46713, 9.15314, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46723, 9.15287, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46746, 9.15252, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46783, 9.15204, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46793, 9.15186, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46794, 9.15172, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46789, 9.15157, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46767, 9.15127, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46701, 9.15024, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46696, 9.15013, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.46696, 9.15002, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.467, 9.14984, "Main", "16"));
        stations.add(new MetroStation("De Angeli M1", 45.4671, 9.14972, "Main", "16"));
        stations.add(new MetroStation("Via Faruffini Via Coreggio", 45.46819, 9.14831, "Main", "16"));
        stations.add(new MetroStation("P.Le Brescia Osp. San Luca", 45.47008, 9.14593, "Main", "16"));
        stations.add(new MetroStation("Via Dolci Via Ricciarelli", 45.47225, 9.14296, "Main", "16"));
        stations.add(new MetroStation("P.Za M.Te Falterona", 45.47367, 9.14108, "Main", "16"));
        stations.add(new MetroStation("Segesta M5", 45.47556, 9.13871, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47578, 9.13828, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.4759, 9.13804, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47597, 9.13779, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47599, 9.13748, "Main", "16"));
        stations.add(new MetroStation("P.Za Esquilino", 45.47609, 9.13241, "Main", "16"));
        stations.add(new MetroStation("San Siro Ippodromo M5", 45.47612, 9.12897, "Main", "16"));
        stations.add(new MetroStation("P.Za Axum (Stadio Meazza)", 45.47617, 9.1248, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47615, 9.12441, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47616, 9.12427, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47621, 9.12407, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47627, 9.12392, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47632, 9.12365, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47637, 9.12325, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47642, 9.12293, "Main", "16"));
        stations.add(new MetroStation("NO_DRAW", 45.47647, 9.12277, "Main", "16"));
        stations.add(new MetroStation("San Siro Stadio M5", 45.4767, 9.12207, "Main", "16"));
        return stations;
    }

    public static List<MetroStation> getStationsTram19() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("P.Za Castelli", 45.4991, 9.15074, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49893, 9.1509, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49888, 9.15135, "Main", "19"));
        stations.add(new MetroStation("Via Mac Mahon Via Bramantino", 45.49775, 9.15281, "Main", "19"));
        stations.add(new MetroStation("Via Mac Mahon Via Artieri", 45.49635, 9.15464, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.496, 9.15515, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49589, 9.15518, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49577, 9.15514, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49566, 9.15503, "Main", "19"));
        stations.add(new MetroStation("P.Za Prealpi", 45.49469, 9.15343, "Main", "19"));
        stations.add(new MetroStation("Via Da Panicale", 45.49327, 9.15122, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49299, 9.15103, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.49286, 9.15106, "Main", "19"));
        stations.add(new MetroStation("Via Bartolini V.Le M.Te Ceneri", 45.49141, 9.15304, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48886, 9.15633, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48865, 9.15642, "Main", "19"));
        stations.add(new MetroStation("P.Za Firenze", 45.48846, 9.15636, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4877, 9.15653, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48755, 9.15664, "Main", "19"));
        stations.add(new MetroStation("C.So Sempione Via E. Filiberto", 45.48616, 9.15844, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48606, 9.15855, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48581, 9.15867, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48567, 9.15875, "Main", "19"));
        stations.add(new MetroStation("C.So Sempione Via Arona", 45.48418, 9.16065, "Main", "19"));
        stations.add(new MetroStation("Domodossola M5", 45.48244, 9.16299, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48241, 9.16304, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48234, 9.16308, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48227, 9.1631, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4822, 9.16308, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48215, 9.16304, "Main", "19"));
        stations.add(new MetroStation("Domodossola M5", 45.48135, 9.16174, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4804, 9.16034, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48022, 9.16025, "Main", "19"));
        stations.add(new MetroStation("V.Le Boezio", 45.47971, 9.16017, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47721, 9.16009, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47711, 9.16017, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47705, 9.1603, "Main", "19"));
        stations.add(new MetroStation("P.Za Sei Febbraio", 45.47702, 9.1607, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47702, 9.16102, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47698, 9.16118, "Main", "19"));
        stations.add(new MetroStation("Via Monti Via Savoia Cavalleria", 45.47472, 9.1641, "Main", "19"));
        stations.add(new MetroStation("L.Go Quinto Alpini", 45.47256, 9.16692, "Main", "19"));
        stations.add(new MetroStation("Via Venti Settembre", 45.4703, 9.16988, "Main", "19"));
        stations.add(new MetroStation("P.za Virgilio", 45.46851, 9.17237, "Main", "19"));
        stations.add(new MetroStation("L.Go D'Ancona", 45.46623, 9.17527, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46599, 9.17573, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46595, 9.17593, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46603, 9.17691, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46603, 9.17708, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46599, 9.17729, "Main", "19"));
        stations.add(new MetroStation("C.So Magenta Via Nirone", 45.4658, 9.17801, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46572, 9.17882, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4657, 9.17941, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46561, 9.18058, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46565, 9.18101, "Main", "19"));
        stations.add(new MetroStation("Via Meravigli", 45.46569, 9.18172, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46597, 9.18511, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46594, 9.18527, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46554, 9.18581, "Main", "19"));
        stations.add(new MetroStation("Cordusio M1", 45.46542, 9.18593, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46371, 9.18842, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46362, 9.18848, "Main", "19"));
        stations.add(new MetroStation("Duomo M1 M3", 45.46289, 9.18845, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46154, 9.18848, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46137, 9.18854, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46106, 9.1892, "Main", "19"));
        stations.add(new MetroStation("Missori M3", 45.46077, 9.19039, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46073, 9.19082, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46074, 9.19103, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46091, 9.19139, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46204, 9.19305, "Main", "19"));
        stations.add(new MetroStation("Via Larga", 45.46237, 9.19372, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46287, 9.19481, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46302, 9.19604, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46302, 9.19698, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46293, 9.19724, "Main", "19"));
        stations.add(new MetroStation("L.Go Augusto", 45.46261, 9.19775, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46234, 9.19834, "Main", "19"));
        stations.add(new MetroStation("Palazzo di Giustizia", 45.4623, 9.20062, "Main", "19"));
        stations.add(new MetroStation("C.So P.ta Vittoria (Camera del Lavoro)", 45.46227, 9.20382, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46226, 9.20639, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46223, 9.20653, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46216, 9.20661, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46199, 9.20666, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46186, 9.20675, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46182, 9.20689, "Main", "19"));
        stations.add(new MetroStation("P.Za 5 Giornate", 45.46182, 9.20734, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46184, 9.20752, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4619, 9.20762, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.46196, 9.20765, "Main", "19"));
        stations.add(new MetroStation("V.Le Premuda", 45.46523, 9.20735, "Main", "19"));
        stations.add(new MetroStation("Tricolore M4", 45.46746, 9.20713, "Main", "19"));
        stations.add(new MetroStation("V.Le Piave", 45.47164, 9.20673, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47188, 9.20671, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47194, 9.20676, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47197, 9.20686, "Main", "19"));
        stations.add(new MetroStation("Via Bixio", 45.47237, 9.21096, "Main", "19"));
        stations.add(new MetroStation("P.Za Adelaide Di Savoia", 45.47279, 9.21418, "Main", "19"));
        stations.add(new MetroStation("P.Za Ascoli", 45.47322, 9.21658, "Main", "19"));
        stations.add(new MetroStation("P.Za Carlo Erba", 45.47489, 9.22085, "Main", "19"));
        stations.add(new MetroStation("V.Le Romagna Via Pascoli", 45.4759, 9.22331, "Main", "19"));
        stations.add(new MetroStation("Via Pascoli P.Za L. Da Vinci", 45.47677, 9.22551, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47686, 9.22566, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47709, 9.22574, "Main", "19"));
        stations.add(new MetroStation("P.Za Leonardo Da Vinci (Politecnico)", 45.47906, 9.22576, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47918, 9.22576, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47926, 9.22587, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47928, 9.22622, "Main", "19"));
        stations.add(new MetroStation("Via Bassini Via Ponzio", 45.47928, 9.22962, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47931, 9.23157, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.47935, 9.23173, "Main", "19"));
        stations.add(new MetroStation("Via Bassini Via Golgi", 45.4802, 9.23393, "Main", "19"));
        stations.add(new MetroStation("Via Valvassori Peroni", 45.48119, 9.23634, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48165, 9.23742, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48174, 9.23755, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4822, 9.23781, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48232, 9.23785, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4828, 9.23776, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48423, 9.2371, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48438, 9.23691, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48458, 9.23671, "Main", "19"));
        stations.add(new MetroStation("Lambrate FS M2", 45.48485, 9.23659, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48507, 9.23631, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.4851, 9.2362, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48509, 9.23609, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48501, 9.23591, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48489, 9.23583, "Main", "19"));
        stations.add(new MetroStation("NO_DRAW", 45.48475, 9.23584, "Main", "19"));
        stations.add(new MetroStation("Lambrate M2", 45.48459, 9.23596, "Main", "19"));
        return stations;
    }

    public static List<MetroStation> getStationsTram24() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Piazza Fontana", 45.46337, 9.19405, "Main", "24"));
        stations.add(new MetroStation("Via Larga", 45.46237, 9.19372, "Main", "24"));
        stations.add(new MetroStation("Piazza Missori", 45.46092, 9.18993, "Main", "24"));
        stations.add(new MetroStation("Missori M3", 45.4604, 9.18866, "Main", "24"));
        stations.add(new MetroStation("Sforza - Policlinico M4", 45.4582, 9.19246, "Main", "24"));
        stations.add(new MetroStation("Crocetta M3", 45.45537, 9.19561, "Main", "24"));
        stations.add(new MetroStation("C.So Porta Vigentina", 45.45364, 9.19607, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti V.Le Sabotino", 45.45076, 9.19695, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Bellezza", 45.44833, 9.1976, "Main", "24"));
        stations.add(new MetroStation("V.Le Isonzo Via Ripamonti", 45.44677, 9.19801, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Lorenzini", 45.44309, 9.19892, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Rutilia", 45.43976, 9.19983, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Quaranta", 45.43555, 9.20065, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Dell'Assunta", 45.43338, 9.20105, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Noto", 45.43143, 9.20178, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Val Di Sole", 45.42863, 9.20246, "Main", "24"));
        stations.add(new MetroStation("Via Ripamonti Via Chopin", 45.42577, 9.20336, "Main", "24"));
        stations.add(new MetroStation("Vigentino", 45.42196, 9.20354, "Main", "24"));
        stations.add(new MetroStation("IEO", 45.41376, 9.20629, "Main - New", "24"));
        return stations;
    }

    public static List<MetroStation> getStationsTram31() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Bicocca M5", 45.51498, 9.2058, "Main", "31"));
        stations.add(new MetroStation("V.Le Testi Via La Farina", 45.51612, 9.20643, "Main", "31"));
        stations.add(new MetroStation("V.Le Testi Via S. Glicerio", 45.5189, 9.20802, "Main", "31"));
        stations.add(new MetroStation("Ponale M5", 45.52224, 9.20991, "Main", "31"));
        stations.add(new MetroStation("Bignami M5", 45.5258, 9.21192, "Main", "31"));
        stations.add(new MetroStation("Parco Nord (Torretta)", 45.5258, 9.21192, "Main", "31"));
        stations.add(new MetroStation("Parco Nord Clerici (Sesto S.G.)", 45.53379, 9.21564, "Main", "31"));
        stations.add(new MetroStation("Parco Nord Ist. Tecnico (Cinisello B.)", 45.53763, 9.2178, "Main", "31"));
        stations.add(new MetroStation("Parco Nord Osp. Bassini (Cinisello B.)", 45.54571, 9.21812, "Main", "31"));
        stations.add(new MetroStation("Gorky Monfalcone (Cinisello B.)", 45.55119, 9.21771, "Main", "31"));
        stations.add(new MetroStation("Libertà (Cinisello B.)", 45.55336, 9.21663, "Main", "31"));
        stations.add(new MetroStation("Gramsci (Cinisello B.)", 45.55579, 9.21562, "Main", "31"));
        stations.add(new MetroStation("Villa Ghirlanda (Cinisello B.)", 45.55802, 9.21467, "Main", "31"));
        stations.add(new MetroStation("Monte Ortigara (Cinisello B.)", 45.5594, 9.21405, "Main", "31"));
        stations.add(new MetroStation("Cinisello (Primo Maggio)", 45.56002, 9.21108, "Main", "31"));
        return stations;
    }

    public static List<MetroStation> getStationsTram33() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Rimembranze Di Lambrate", 45.48271, 9.24159, "Main", "33"));
        stations.add(new MetroStation("Via Valvassori Peroni", 45.48146, 9.23682, "Main", "33"));
        stations.add(new MetroStation("Via Bassini Via Golgi", 45.48057, 9.23466, "Main", "33"));
        stations.add(new MetroStation("Via Bassini Via Ponzio", 45.47935, 9.23044, "Main", "33"));
        stations.add(new MetroStation("P.Za Leonardo Da Vinci (Politecnico)", 45.47934, 9.22674, "Main", "33"));
        stations.add(new MetroStation("Via Pascoli P.Za L. Da Vinci", 45.47716, 9.22562, "Main", "33"));
        stations.add(new MetroStation("P.Za Carlo Erba", 45.47531, 9.22164, "Main", "33"));
        stations.add(new MetroStation("P.Za Ascoli", 45.47383, 9.21798, "Main", "33"));
        stations.add(new MetroStation("P.Za Adelaide Di Savoia", 45.47303, 9.21531, "Main", "33"));
        stations.add(new MetroStation("P.Za Otto Novembre", 45.47463, 9.2114, "Main", "33"));
        stations.add(new MetroStation("P.Ta Venezia V.Le Tunisia", 45.47646, 9.20739, "Main", "33"));
        stations.add(new MetroStation("V.Le Tunisia", 45.47833, 9.20333, "Main", "33"));
        stations.add(new MetroStation("V.Le Vittorio Veneto", 45.47691, 9.20113, "Main", "33"));
        stations.add(new MetroStation("Repubblica M3", 45.47812, 9.19744, "Main", "33"));
        stations.add(new MetroStation("V.Le Monte Santo", 45.47928, 9.195, "Main", "33"));
        stations.add(new MetroStation("P.Le Principessa Clotilde (Osp. FateBeneFratelli)", 45.48034, 9.19256, "Main", "33"));
        stations.add(new MetroStation("V.Le Monte Grappa Via Gioia", 45.48083, 9.18907, "Main", "33"));
        stations.add(new MetroStation("Garibaldi FS M2 M5", 45.48374, 9.1867, "Main", "33"));
        stations.add(new MetroStation("Via Farini Via Ferrari", 45.48573, 9.18262, "Main", "33"));
        stations.add(new MetroStation("NO_DRAW", 45.48576, 9.18211, "Main", "33"));
        stations.add(new MetroStation("NO_DRAW", 45.48838, 9.18258, "Main", "33"));
        stations.add(new MetroStation("Via Ugo Bassi", 45.48897, 9.18468, "Main", "33"));
        stations.add(new MetroStation("Via Porro Lambertenghi", 45.48909, 9.18702, "Main", "33"));
        stations.add(new MetroStation("NO_DRAW", 45.48903, 9.19041, "Main", "33"));
        stations.add(new MetroStation("P.Le Lagosta", 45.48964, 9.19153, "Main", "33"));
        return stations;
    }

    public static List<MetroStation> getStationsFilobus90() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lotto M1 M5", 45.47993, 9.14325, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47751, 9.14301, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47692, 9.1431, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47639, 9.14328, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47624, 9.14346, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47605, 9.14348, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47574, 9.14345, "Main", "90"));
        stations.add(new MetroStation("P.Le Zavattari", 45.47531, 9.14352, "Main", "90"));
        stations.add(new MetroStation("V.Le Murillo Via Ricciarelli", 45.47307, 9.14416, "Main", "90"));
        stations.add(new MetroStation("P.Le Brescia Osp. San Luca", 45.47197, 9.14456, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47166, 9.14478, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47154, 9.14515, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47135, 9.14535, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47048, 9.14563, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47029, 9.14556, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.4701, 9.14535, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.47006, 9.14524, "Main", "90"));
        stations.add(new MetroStation("P.Le Brescia", 45.46972, 9.14526, "Main", "90"));
        stations.add(new MetroStation("P.Le Ghirlandaio", 45.46635, 9.14625, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.46571, 9.14655, "Main", "90"));
        stations.add(new MetroStation("V.Le Bezzi Via Marostica", 45.46337, 9.14789, "Main", "90"));
        stations.add(new MetroStation("P.Le Tripoli", 45.46081, 9.14945, "Main", "90"));
        stations.add(new MetroStation("V.Le Misurata", 45.45748, 9.15133, "Main", "90"));
        stations.add(new MetroStation("Bolivar M4", 45.45565, 9.1524, "Main", "90"));
        stations.add(new MetroStation("P.Za Napoli", 45.45284, 9.15404, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.45036, 9.15548, "Main", "90"));
        stations.add(new MetroStation("P.Le Delle Milizie V.Le Troya", 45.45013, 9.15583, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44948, 9.15689, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44914, 9.15731, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44876, 9.15756, "Main", "90"));
        stations.add(new MetroStation("Ponte Guido Crepax", 45.44815, 9.15784, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44721, 9.15834, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44682, 9.15867, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44645, 9.15928, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44563, 9.16094, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44483, 9.16309, "Main", "90"));
        stations.add(new MetroStation("V.Le Cassala", 45.44472, 9.16364, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44446, 9.16485, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.4442, 9.1666, "Main", "90"));
        stations.add(new MetroStation("Romolo M2", 45.44415, 9.16739, "Main", "90"));
        stations.add(new MetroStation("P.Za Belfanti", 45.44393, 9.17168, "Main", "90"));
        stations.add(new MetroStation("V.Le Liguria", 45.44377, 9.17518, "Main", "90"));
        stations.add(new MetroStation("V.Le Tibaldi Via Meda", 45.44361, 9.17822, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44355, 9.17961, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44357, 9.18004, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44368, 9.18066, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44399, 9.18192, "Main", "90"));
        stations.add(new MetroStation("Tibaldi", 45.44445, 9.18323, "Main", "90"));
        stations.add(new MetroStation("V.Le Toscana Stazione Tibaldi", 45.44588, 9.18786, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44607, 9.1888, "Main", "90"));
        stations.add(new MetroStation("NO_DRAW", 45.44617, 9.18985, "Main", "90"));
        stations.add(new MetroStation("V.Le Toscana Via Vittadini", 45.44638, 9.19472, "Main", "90"));
        stations.add(new MetroStation("V.Le Isonzo Via Ripamonti", 45.44656, 9.19848, "Main", "90"));
        stations.add(new MetroStation("P.Za Trento", 45.4468, 9.20386, "Main", "90"));
        stations.add(new MetroStation("Lodi M3", 45.44698, 9.20817, "Main", "90"));
        return stations;
    }

    public static List<MetroStation> getStationsFilobus91() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lodi M3", 45.44681, 9.20926, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44698, 9.21025, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44706, 9.21057, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44725, 9.21072, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44747, 9.2107, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44762, 9.21058, "Main", "91"));
        stations.add(new MetroStation("V.Le Umbria Lodi M3", 45.44817, 9.21081, "Main", "91"));
        stations.add(new MetroStation("V.Le Umbria Via Muratori", 45.45036, 9.21263, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45061, 9.21283, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45063, 9.21291, "Main", "91"));
        stations.add(new MetroStation("Via Tertulliano Via Lattanzio", 45.45036, 9.21394, "Main", "91"));
        stations.add(new MetroStation("Via Tertulliano Via Decembrio", 45.44956, 9.2167, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44935, 9.21753, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44931, 9.21765, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44923, 9.21773, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44923, 9.21783, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.4493, 9.21793, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44939, 9.21794, "Main", "91"));
        stations.add(new MetroStation("P.Za Salgari", 45.44992, 9.21993, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45005, 9.22033, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44994, 9.22046, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.4499, 9.22063, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.44992, 9.22081, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45002, 9.22097, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45011, 9.22101, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45023, 9.22101, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45065, 9.22364, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45057, 9.22381, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45052, 9.22402, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45057, 9.22421, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45066, 9.22438, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45074, 9.22449, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45093, 9.22454, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45134, 9.22448, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45145, 9.22438, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45152, 9.22431, "Main", "91"));
        stations.add(new MetroStation("P.Le Cuoco", 45.45196, 9.22431, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45211, 9.22428, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.45254, 9.22414, "Main", "91"));
        stations.add(new MetroStation("V.Le Molise Via Cadibona", 45.45319, 9.22411, "Main", "91"));
        stations.add(new MetroStation("V.Le Molise", 45.45673, 9.22409, "Main", "91"));
        stations.add(new MetroStation("Porta Vittoria", 45.45956, 9.22403, "Main", "91"));
        stations.add(new MetroStation("V.Le Campania V.Le Corsica", 45.46301, 9.22401, "Main", "91"));
        stations.add(new MetroStation("V.Le Campania Via Archimede", 45.46596, 9.22396, "Main", "91"));
        stations.add(new MetroStation("Susa M4", 45.46971, 9.22391, "Main", "91"));
        stations.add(new MetroStation("P.Za Ferravilla", 45.47175, 9.22387, "Main", "91"));
        stations.add(new MetroStation("L.Go Rio De Janeiro", 45.47384, 9.22386, "Main", "91"));
        stations.add(new MetroStation("V.Le Romagna Via Pascoli", 45.47578, 9.2238, "Main", "91"));
        stations.add(new MetroStation("V.Le Romagna Piola M2", 45.47921, 9.22378, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.47978, 9.22379, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.47985, 9.22408, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.47996, 9.22427, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48017, 9.22438, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48041, 9.22439, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48061, 9.22426, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48076, 9.22401, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48082, 9.22377, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48083, 9.2235, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48078, 9.22327, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48073, 9.22315, "Main", "91"));
        stations.add(new MetroStation("V.Le Gran Sasso Piola M2", 45.4809, 9.22279, "Main", "91"));
        stations.add(new MetroStation("V.Le Gran Sasso Via Garofalo", 45.48283, 9.21899, "Main", "91"));
        stations.add(new MetroStation("Loreto M1 M2", 45.48425, 9.21598, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48566, 9.21303, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48577, 9.21281, "Main", "91"));
        stations.add(new MetroStation("V.Le Andrea Doria", 45.48572, 9.21208, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48543, 9.21013, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.4854, 9.2099, "Main", "91"));
        stations.add(new MetroStation("Caiazzo M2", 45.48556, 9.20943, "Main", "91"));
        stations.add(new MetroStation("P.Za Luigi Di Savoia", 45.48664, 9.2073, "Main", "91"));
        stations.add(new MetroStation("Stazione Centrale Via Tonale", 45.48825, 9.20404, "Main", "91"));
        stations.add(new MetroStation("Sondrio M3", 45.48996, 9.20064, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49103, 9.1985, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49115, 9.19823, "Main", "91"));
        stations.add(new MetroStation("V.Le Sauro Via Oldofredi", 45.49132, 9.19776, "Main", "91"));
        stations.add(new MetroStation("Zara M3 M5", 45.49247, 9.19342, "Main", "91"));
        stations.add(new MetroStation("V.Le Stelvio Via Lario", 45.49312, 9.19096, "Main", "91"));
        stations.add(new MetroStation("Via Farini V.Le Stelvio", 45.49453, 9.18559, "Main", "91"));
        stations.add(new MetroStation("Via Bernina", 45.49561, 9.1816, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49569, 9.18126, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49711, 9.18187, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.4973, 9.18181, "Main", "91"));
        stations.add(new MetroStation("V.Le Jenner", 45.49695, 9.17681, "Main", "91"));
        stations.add(new MetroStation("P.Le Nigra", 45.49663, 9.1715, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.4966, 9.17087, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49662, 9.17058, "Main", "91"));
        stations.add(new MetroStation("P.Le Lugano", 45.49814, 9.16697, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49837, 9.16644, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49851, 9.1659, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49853, 9.16543, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49849, 9.16502, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49838, 9.16467, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.49785, 9.16349, "Main", "91"));
        stations.add(new MetroStation("Via Mac Mahon V.Le M.Te Ceneri", 45.49373, 9.15677, "Main", "91"));
        stations.add(new MetroStation("Via Bartolini V.Le M.Te Ceneri", 45.49171, 9.15364, "Main", "91"));
        stations.add(new MetroStation("V.Le Certosa V.Le Serra", 45.49057, 9.15193, "Main", "91"));
        stations.add(new MetroStation("V.Le Serra Via Traiano", 45.48899, 9.14948, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48571, 9.14458, "Main", "91"));
        stations.add(new MetroStation("V.Le Serra V.Le Scarampo", 45.48504, 9.14371, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48421, 9.14323, "Main", "91"));
        stations.add(new MetroStation("NO_DRAW", 45.48377, 9.14311, "Main", "91"));
        stations.add(new MetroStation("P.Za Stuparich", 45.48255, 9.14288, "Main", "91"));
        stations.add(new MetroStation("Lotto M1 M5", 45.47988, 9.14266, "Main", "91"));
        return stations;
    }

    public static List<MetroStation> getStationsFilobus92() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lodi M3", 45.44681, 9.20926, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.44698, 9.21025, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.44706, 9.21057, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.44725, 9.21072, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.44747, 9.2107, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.44762, 9.21058, "Main", "92"));
        stations.add(new MetroStation("V.Le Umbria Lodi M3", 45.44817, 9.21081, "Main", "92"));
        stations.add(new MetroStation("V.Le Umbria Via Muratori", 45.45036, 9.21263, "Main", "92"));
        stations.add(new MetroStation("V.Le Umbria Via Comelico", 45.45365, 9.21529, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.45456, 9.21609, "Main", "92"));
        stations.add(new MetroStation("Via Maestri Campionesi", 45.45527, 9.21662, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.45658, 9.21713, "Main", "92"));
        stations.add(new MetroStation("Via Arconati", 45.45759, 9.21745, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.45874, 9.21761, "Main", "92"));
        stations.add(new MetroStation("P.Za Emilia", 45.46172, 9.21747, "Main", "92"));
        stations.add(new MetroStation("V.Le Piceno", 45.46597, 9.21741, "Main", "92"));
        stations.add(new MetroStation("Dateo FS M4", 45.46858, 9.21733, "Main", "92"));
        stations.add(new MetroStation("V.Le Dei Mille P.Za Novelli", 45.47066, 9.2173, "Main", "92"));
        stations.add(new MetroStation("P.Za Ascoli", 45.47398, 9.21725, "Main", "92"));
        stations.add(new MetroStation("V.Le Abruzzi Via Plinio", 45.47733, 9.2172, "Main", "92"));
        stations.add(new MetroStation("P.Le Bacone", 45.48054, 9.21715, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.4817, 9.21711, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.48181, 9.2169, "Main", "92"));
        stations.add(new MetroStation("Via Piccini V.Le Abruzzi", 45.48206, 9.21647, "Main", "92"));
        stations.add(new MetroStation("P.Le Loreto Via Pergolesi", 45.48361, 9.2134, "Main", "92"));
        stations.add(new MetroStation("Caiazzo M2", 45.48556, 9.20943, "Main", "92"));
        stations.add(new MetroStation("P.Za Luigi Di Savoia", 45.48664, 9.2073, "Main", "92"));
        stations.add(new MetroStation("Stazione Centrale Via Tonale", 45.48825, 9.20404, "Main", "92"));
        stations.add(new MetroStation("Sondrio M3", 45.48996, 9.20064, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49103, 9.1985, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49115, 9.19823, "Main", "92"));
        stations.add(new MetroStation("V.Le Sauro Via Oldofredi", 45.49132, 9.19776, "Main", "92"));
        stations.add(new MetroStation("Zara M3 M5", 45.49247, 9.19342, "Main", "92"));
        stations.add(new MetroStation("V.Le Stelvio Via Lario", 45.49312, 9.19096, "Main", "92"));
        stations.add(new MetroStation("Via Farini V.Le Stelvio", 45.49453, 9.18559, "Main", "92"));
        stations.add(new MetroStation("Via Bernina", 45.49561, 9.1816, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49565, 9.18092, "Main", "92"));
        stations.add(new MetroStation("Lancetti FS", 45.49545, 9.1769, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49518, 9.17425, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49511, 9.17349, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49513, 9.17308, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49523, 9.17241, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49526, 9.17226, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49535, 9.17215, "Main", "92"));
        stations.add(new MetroStation("P.Le Nigra", 45.4958, 9.1719, "Main", "92"));
        stations.add(new MetroStation("Via Imbriani Via Scalvini", 45.49923, 9.16933, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.49955, 9.16866, "Main", "92"));
        stations.add(new MetroStation("Bausan", 45.50157, 9.16629, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50212, 9.16555, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50222, 9.16563, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50233, 9.16565, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50243, 9.16558, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50251, 9.16543, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50251, 9.16525, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50246, 9.16513, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50251, 9.16504, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50289, 9.16461, "Main", "92"));
        stations.add(new MetroStation("Via Broglio Via Varè", 45.5025, 9.1627, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50222, 9.16193, "Main", "92"));
        stations.add(new MetroStation("NO_DRAW", 45.50201, 9.16202, "Main", "92"));
        stations.add(new MetroStation("Via Varè (Bovisa FN)", 45.50177, 9.16208, "Main", "92"));
        return stations;
    }

    public static List<MetroStation> getStationsFilobus93() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("V.Le Omero", 45.43247, 9.22468, "Main", "93"));
        stations.add(new MetroStation("V.Le Omero Via Barabino", 45.435, 9.22457, "Main", "93"));
        stations.add(new MetroStation("P.Le Gabriele Rosa", 45.43673, 9.22456, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43693, 9.22455, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43698, 9.22481, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43705, 9.22502, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43713, 9.22513, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43728, 9.22528, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43748, 9.22533, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43769, 9.22528, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43789, 9.22501, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43801, 9.22461, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43804, 9.2243, "Main", "93"));
        stations.add(new MetroStation("V.Le Martini", 45.4388, 9.22433, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43957, 9.22429, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43972, 9.2245, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43981, 9.22469, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.43996, 9.22476, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44016, 9.22472, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44026, 9.2246, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44033, 9.22447, "Main", "93"));
        stations.add(new MetroStation("Corvetto M3", 45.4407, 9.22443, "Main", "93"));
        stations.add(new MetroStation("P.Le Bologna", 45.4435, 9.22442, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44409, 9.22441, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44422, 9.22452, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44432, 9.22454, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44445, 9.22447, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44456, 9.22432, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44473, 9.22419, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.44518, 9.22413, "Main", "93"));
        stations.add(new MetroStation("V.Le Puglie", 45.45004, 9.22411, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45066, 9.22438, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45074, 9.22449, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45093, 9.22454, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45134, 9.22448, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45145, 9.22438, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45152, 9.22431, "Main", "93"));
        stations.add(new MetroStation("P.Le Cuoco", 45.45196, 9.22431, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45211, 9.22428, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.45254, 9.22414, "Main", "93"));
        stations.add(new MetroStation("V.Le Molise Via Cadibona", 45.45319, 9.22411, "Main", "93"));
        stations.add(new MetroStation("V.Le Molise", 45.45673, 9.22409, "Main", "93"));
        stations.add(new MetroStation("Porta Vittoria", 45.45956, 9.22403, "Main", "93"));
        stations.add(new MetroStation("V.Le Campania V.Le Corsica", 45.46301, 9.22401, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46555, 9.22409, "Main", "93"));
        stations.add(new MetroStation("Via Sismondi V.Le Campania", 45.4655, 9.22527, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46557, 9.22699, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46553, 9.22703, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.4655, 9.22711, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46549, 9.22721, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46552, 9.22726, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46556, 9.22737, "Main", "93"));
        stations.add(new MetroStation("Via Lomellina Via Sismondi", 45.46554, 9.22938, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.46558, 9.23018, "Main", "93"));
        stations.add(new MetroStation("Argonne M4", 45.46822, 9.23025, "Main", "93"));
        stations.add(new MetroStation("Via B. Angelico Via Aselli", 45.46974, 9.23019, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47238, 9.23012, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47251, 9.2302, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47258, 9.23035, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47265, 9.2304, "Main", "93"));
        stations.add(new MetroStation("P.Le Gorini Istituto Dei Tumori", 45.47292, 9.23042, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.4733, 9.23034, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47345, 9.23042, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.4736, 9.23037, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47391, 9.23034, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47429, 9.23036, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47445, 9.23018, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47447, 9.23006, "Main", "93"));
        stations.add(new MetroStation("Via Celora Istituto Besta", 45.47649, 9.23003, "Main", "93"));
        stations.add(new MetroStation("Via Bassini Via Ponzio", 45.47888, 9.23007, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47925, 9.23002, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47926, 9.23161, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.47936, 9.23197, "Main", "93"));
        stations.add(new MetroStation("Via Bassini Via Golgi", 45.48, 9.23368, "Main", "93"));
        stations.add(new MetroStation("Via Valvassori Via Peroni", 45.48096, 9.23604, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.48129, 9.2366, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.48408, 9.23655, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.48432, 9.2366, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.48444, 9.23667, "Main", "93"));
        stations.add(new MetroStation("NO_DRAW", 45.48456, 9.23663, "Main", "93"));
        stations.add(new MetroStation("Lambrate FS M2", 45.48493, 9.23635, "Main", "93"));
        return stations;
    }

    public static List<MetroStation> getStationsZ620(){
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Magenta, Tobagi", 45.45574, 8.89153, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45589, 8.89149, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45576, 8.88789, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45624, 8.88509, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45377, 8.88516, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45118, 8.88457, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.44934, 8.88429, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.44795, 8.88466, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.44772, 8.88446, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.44781, 8.88363, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45283, 8.87501, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.45624, 8.87145, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46083, 8.86814, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46115, 8.86823, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46151, 8.86841, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Leopardi/Italia",45.46179, 8.86928, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46223, 8.87055, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46232, 8.87077, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46254, 8.87077, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46402, 8.87042, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46492, 8.87636, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46502, 8.87678, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46515, 8.87683, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46603, 8.87665, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46614, 8.87666, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46622, 8.87673, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46632, 8.87685, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Novara/Pellico,Carabinieri", 45.46741, 8.87983, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46798, 8.88145, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46797, 8.88168, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46805, 8.88183, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46811, 8.88178, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46824, 8.88252, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46632, 8.87685, "Main", "z620"));
        stations.add(new MetroStation("NO_DRAW", 45.46632, 8.87685, "Main", "z620"));
        //dio santo le fermate sono sbagliate guarda su https://citymapper.com/milano/autobus/movibus-z620?lang=it
        stations.add(new MetroStation("Magenta, Novara 15", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Brocca 41,Stazione FS", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Cavallari 28", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Tragella", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Magenta, Milano 101", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Corbetta, Simone Da Corbetta", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Corbetta, Simone Da Corbetta/S. Ambrogio", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Corbetta, Beretta/Volta", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Corbetta, Milano,La Pobbia", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Vittuone, Milano 22", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Vittuone, Milano 7", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Sedriano, Magenta 11", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Sedriano, Fagnani/Europa", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Bareggio, Novara/Roma", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Bareggio, Milano/Leoncavallo", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Cornaredo, Dubini,S.Pietro all'Olmo", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Cornaredo, Milano/S.Siro", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Cornaredo, Milano/Garibaldi", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Cornaredo, Milano/Cascina Torretta", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Settimo Milanese, Gramsci 32", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Settimo Milanese, Gramsci/Foscolo", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Settimo Milanese, Gramsci", 0, 0, "Main", "z620"));
        stations.add(new MetroStation("Milano, Molino Dorino M1", 0, 0, "Main", "z620"));
        return stations;
    }

    public static List<InterchangeInfo> getBusInterchanges() {
        List<InterchangeInfo> interchanges = new ArrayList<>();
        interchanges.add(new InterchangeInfo(
                "Molino Dorino MM",
                new String[]{"M1", "z601", "z606", "z617", "z620", "z621", "z648", "z649"},
                new String[] {"z601", "z606", "z617", "z620", "z621", "z648", "z649"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Milano Cadorna FN",
                new String[]{"M1", "M2", "MXP", "R16", "R17", "R22", "R27", "RE1", "RE7", "S3", "S4", "z602", "z603", "z6C3"},
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
                new String[]{"z625", "z627", "z644", "z648", "z649"},
                new String[]{"z625"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Legnano",
                new String[]{"z601", "z602", "z611", "z612", "z627", "z642"},
                new String[]{"z611", "z612", "z642", "z627", "z636"},
                "bus.fill"
        ));

        interchanges.add(new InterchangeInfo(
                "Bisceglie MM",
                new String[]{"M1", "z551", "z560"},
                new String[]{"z551", "z560"},
                "tram.fill.tunnel"
        ));

        interchanges.add(new InterchangeInfo(
                "Romolo FS",
                new String[]{"M2", "S9", "S19", "R31"},
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
                new String[]{"M4", "z409"},
                new String[] {"z409"},
                "airplane.departure"
        ));

        interchanges.add(new InterchangeInfo(
                "San Donato M3",
                new String[]{"M3", "z410", "z411", "z412", "z413", "z415", "z420"},
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
                new String[] {"M2", "z501", "z509", "z510", "z515", "z516"},
                new String[]{"z501", "z509", "z510", "z515", "z516"},
                "tram.fill.tunnel"
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
                new InterchangeInfo("Ca' Granda M5", new String[]{"M5", "4", "5", "7", "31"}, "tram.fill.tunnel"),
                new InterchangeInfo("Bicocca M5", new String[]{"M5", "4", "7", "31"}, "tram.fill.tunnel"),
                new InterchangeInfo("Ponale M5", new String[]{"M5", "4", "7", "31"}, "tram.fill.tunnel"),
                new InterchangeInfo("Bignami M5", new String[]{"M5", "4", "7", "31"}, "tram.fill.tunnel"),
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
                new InterchangeInfo("Rho Fiera-Milano", new String[]{"M1", "AV", "R21", "R23", "RE4", "RE5", "S5", "S6", "S11"}, "lightrail"),
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

    public static List<MetroStation> getAllStations() {
        if(CACHED_STATIONS == null) {
            List<MetroStation> allStations = new ArrayList<>();
            allStations.addAll(getStationsM1());
            allStations.addAll(getStationsM2());
            allStations.addAll(getStationsM3());
            allStations.addAll(getStationsM4());
            allStations.addAll(getStationsM5());

            allStations.addAll(getStationsS1());
            allStations.addAll(getStationsS2());
            allStations.addAll(getStationsS3());
            allStations.addAll(getStationsS4());
            allStations.addAll(getStationsS5());
            allStations.addAll(getStationsS6());
            allStations.addAll(getStationsS7());
            allStations.addAll(getStationsS8());
            allStations.addAll(getStationsS9());
            allStations.addAll(getStationsS11());
            allStations.addAll(getStationsS12());
            allStations.addAll(getStationsS13());
            allStations.addAll(getStationsS19());
            allStations.addAll(getStationsS31());

            allStations.addAll(getStationsR1());
            allStations.addAll(getStationsR2());
            allStations.addAll(getStationsR3());
            allStations.addAll(getStationsR4());
            allStations.addAll(getStationsR5());
            allStations.addAll(getStationsR6());
            allStations.addAll(getStationsR7());
            allStations.addAll(getStationsR8());
            allStations.addAll(getStationsR9());
            allStations.addAll(getStationsR11());
            allStations.addAll(getStationsR12());
            allStations.addAll(getStationsR13());
            allStations.addAll(getStationsR14());
            allStations.addAll(getStationsR15());
            allStations.addAll(getStationsR16());
            allStations.addAll(getStationsR17());
            allStations.addAll(getStationsR18());
            allStations.addAll(getStationsR21());
            allStations.addAll(getStationsR22());
            allStations.addAll(getStationsR23());
            allStations.addAll(getStationsR24());
            allStations.addAll(getStationsR25());
            allStations.addAll(getStationsR27());
            allStations.addAll(getStationsR31());
            allStations.addAll(getStationsR32());
            allStations.addAll(getStationsR33());
            allStations.addAll(getStationsR34());
            allStations.addAll(getStationsR35());
            allStations.addAll(getStationsR36());
            allStations.addAll(getStationsR37());
            allStations.addAll(getStationsR38());
            allStations.addAll(getStationsR39());
            allStations.addAll(getStationsR40());
            allStations.addAll(getStationsR41());

            allStations.addAll(getStationsRE1());
            allStations.addAll(getStationsRE2());
            allStations.addAll(getStationsRE3());
            allStations.addAll(getStationsRE4());
            allStations.addAll(getStationsRE5());
            allStations.addAll(getStationsRE6());
            allStations.addAll(getStationsRE7());
            allStations.addAll(getStationsRE8());
            allStations.addAll(getStationsRE11());
            allStations.addAll(getStationsRE13());

            allStations.addAll(getStationsS10());
            allStations.addAll(getStationsS30());
            allStations.addAll(getStationsS40());
            allStations.addAll(getStationsS50());
            allStations.addAll(getStationsRE80());

            allStations.addAll(getStationsMXP1());
            allStations.addAll(getStationsMXP2());

            allStations.addAll(getStationsTram1());
            allStations.addAll(getStationsTram3());
            allStations.addAll(getStationsTram5());
            allStations.addAll(getStationsTram7());
            allStations.addAll(getStationsTram9());
            allStations.addAll(getStationsTram10());
            allStations.addAll(getStationsTram15());
            allStations.addAll(getStationsTram16());
            allStations.addAll(getStationsTram19());
            allStations.addAll(getStationsTram24());
            allStations.addAll(getStationsTram31());
            allStations.addAll(getStationsTram33());

            allStations.addAll(getStationsZ620());

            allStations.addAll(getStationsFilobus90());
            allStations.addAll(getStationsFilobus91());
            allStations.addAll(getStationsFilobus92());
            allStations.addAll(getStationsFilobus93());

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
                return R.color.AV;

            //TILO LINES
            case "S10":
                return R.color.S10;
            case "S30":
                return R.color.S30;
            case "S40":
                return R.color.S40;
            case "S50":
                return R.color.S50;
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

            //OTHERS
            default:
                if (nomeLinea.contains("z"))
                    return R.color.BUS;
                else if (nomeLinea.contains("Filobus"))
                    return R.color.FILOBUS;
                else if(nomeLinea.contains("N"))
                    return R.color.NIGHTLINES;
                else if(nomeLinea.equals("RE80"))
                    return R.color.RE80;
                else if (nomeLinea.contains("R") && !nomeLinea.contains("RE"))
                    return R.color.REGIONAL;
                else if (nomeLinea.contains("RE"))
                    return R.color.RE;
                else if(nomeLinea.matches("9[0-3]"))
                    return R.color.FILOBUS;
                else if (nomeLinea.matches("^\\d+$")) {
                    int numero = Integer.parseInt(nomeLinea);

                    if (numero >= 1 && numero <= 33)
                        return R.color.TRAM;
                    else
                        return R.color.OTHER_LINES;
                }
                else if (nomeLinea.startsWith("P"))
                    return R.color.AUTOGUIDOVIE;
                else if(nomeLinea.startsWith("MXP"))
                    return R.color.MXP;
                else if(nomeLinea.equalsIgnoreCase(context.getString(R.string.airportKey)))
                    return R.color.airport;
                else
                    return R.color.OTHER_LINES;
        }
    }
}