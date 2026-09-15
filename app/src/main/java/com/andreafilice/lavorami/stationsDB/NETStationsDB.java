package com.andreafilice.lavorami.stationsDB;

import com.andreafilice.lavorami.MetroStation;

import java.util.ArrayList;
import java.util.List;

public class NETStationsDB {
    public static List<MetroStation> getStationsZ301() {
        List<MetroStation> stations = new ArrayList<>();
        stations.add(new MetroStation("Lampugnano M1", 45.48981, 9.12795, "Main", "z301"));
        stations.add(new MetroStation("Milano, Via Ghisallo P.Le Ai Laghi", 45.50122, 9.13073, "Main", "z301"));
        stations.add(new MetroStation("Novate Milanese A4", 45.52997, 9.15043, "Main", "z301"));
        stations.add(new MetroStation("Cormano A4", 45.54161, 9.17686, "Main", "z301"));
        stations.add(new MetroStation("Agrate Brianza A4", 45.56976, 9.36232, "Main", "z301"));
        stations.add(new MetroStation("Cavenago Brianza A4", 45.57873, 9.41581, "Main", "z301"));
        stations.add(new MetroStation("Trezzo Sull'Adda A4", 45.6016, 9.51948, "Main", "z301"));
        stations.add(new MetroStation("Capriate (Brembate) A4", 45.60923, 9.54209, "Main", "z301"));
        stations.add(new MetroStation("Dalmine A4", 45.64327, 9.61971, "Main", "z301"));
        stations.add(new MetroStation("Via Carnovali Via Autostrada (Bergamo)", 45.68661, 9.66995, "Main", "z301"));
        stations.add(new MetroStation("Bergamo (Autostazione)", 45.69251, 9.67635, "Main", "z301"));
        stations.add(new MetroStation("Bergamo (Autostazione)", 45.69188, 9.67549, "Ritorno", "z301"));
        stations.add(new MetroStation("Via Carnovali Via Autostrada (Bergamo)", 45.68666, 9.66986, "Ritorno", "z301"));
        stations.add(new MetroStation("Dalmine A4", 45.64414, 9.6201, "Ritorno", "z301"));
        stations.add(new MetroStation("Capriate (Brembate) A4", 45.61007, 9.54291, "Ritorno", "z301"));
        stations.add(new MetroStation("Trezzo Sull'Adda A4", 45.60198, 9.5191, "Ritorno", "z301"));
        stations.add(new MetroStation("Cavenago Brianza A4", 45.5793, 9.41633, "Ritorno", "z301"));
        stations.add(new MetroStation("Agrate Brianza A4", 45.57017, 9.36197, "Ritorno", "z301"));
        stations.add(new MetroStation("Cormano A4", 45.54192, 9.17671, "Ritorno", "z301"));
        stations.add(new MetroStation("Novate Milanese A4", 45.52983, 9.14849, "Ritorno", "z301"));
        stations.add(new MetroStation("Milano, Via Ghisallo P.Le Ai Laghi", 45.50121, 9.13015, "Ritorno", "z301"));
        stations.add(new MetroStation("Lampugnano M1", 45.49014, 9.12765, "Ritorno", "z301"));

        return stations;
    }
}
