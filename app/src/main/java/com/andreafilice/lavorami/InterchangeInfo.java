package com.andreafilice.lavorami;


public class InterchangeInfo {
    private String key;
    private String[] lines;
    private String[] linesToShow;
    private String typeOfInterchange;
    private String branch;
    private int lineOrder;

    public InterchangeInfo(String key, String[] lines, String[] linesToShow, String typeOfInterchange, String branch, int lineOrder){
        this.key = key;
        this.lines = lines;
        this.linesToShow = linesToShow;
        this.typeOfInterchange = typeOfInterchange;
        this.branch = branch;
        this.lineOrder = lineOrder;
    }

    public InterchangeInfo(String key, String[] lines, String typeOfInterchange){
        this(key, lines, null, typeOfInterchange, "Main", 0);
    }

    public InterchangeInfo(String key, String[] lines, String typeOfInterchange, String branch, int lineOrder){
        this(key, lines, null, typeOfInterchange, branch, lineOrder);
    }

    public InterchangeInfo(String key, String[] lines, String[] linesToShow, String typeOfInterchange){
        this(key, lines, linesToShow, typeOfInterchange, "Main", 0);
    }

    public String getKey() {return key;}
    public String[] getLines() {return lines;}
    public String[] getLinesToShow() {return linesToShow;}
    public String getTypeOfInterchange() {return typeOfInterchange;}
    public String getBranch() {return branch;}
    public int getLineOrder() {return lineOrder;}
    public int getCardImageID() {return getTitleIconID(this.typeOfInterchange);}

    public int getTitleIconID(String iconName){
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
            case "bus.fill":
                return R.drawable.ic_bus;
            case "tram.fill":
                return R.drawable.ic_tram;
            case "stadium.fill":
                return R.drawable.ic_stadium;
            case "building.columns.fill":
                return R.drawable.ic_culture_point;
            default:
                return R.drawable.empty_image;
        }
    }
}
