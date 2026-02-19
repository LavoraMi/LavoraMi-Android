package com.andreafilice.lavorami;

import android.util.Log;

public class InterchangeInfo {
    private String key;
    private String[] lines;
    private String[] linesToShow;
    private String typeOfInterchange;

    public InterchangeInfo(String key, String[] lines, String[] linesToShow, String typeOfInterchange){
        this.key = key;
        this.lines = lines;
        this.linesToShow = linesToShow;
        this.typeOfInterchange = typeOfInterchange;
    }

    public InterchangeInfo(String key, String[] lines, String typeOfInterchange){
        this(key, lines, null, typeOfInterchange);
    }

    public String getKey() {return key;}
    public String[] getLines() {return lines;}
    public String[] getLinesToShow() {return linesToShow;}
    public String getTypeOfInterchange() {return typeOfInterchange;}

    public int getCardImageID() {return getTitleIconID(this.typeOfInterchange);}

    public int getTitleIconID(String iconName){
        Log.d("ID", iconName);
        switch(iconName) {
            case "lightrail":
            case "train.side.front.car":
                return R.drawable.ic_train;
            case "tram.fill.tunnel":
                return R.drawable.ic_metro;
            case "figure.walk":
                return R.drawable.ic_walking;
            case "airplane.departure":
                return R.drawable.ic_plane;
            default:
                return R.drawable.empty_image;
        }
    }
}
