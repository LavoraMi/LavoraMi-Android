package com.andreafilice.lavorami;

public class MetroStation {
    private String name;
    private double latitude;
    private double longitude;
    private String branch;
    private String line;

    public MetroStation(String name, double latitude, double longitude, String branch, String line) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.branch=branch;
        this.line=line;
    }

    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getBranch() {
        return branch;
    }
    public String getLine() {
        return line;
    }
}
