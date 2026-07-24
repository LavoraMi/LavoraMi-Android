package com.andreafilice.lavorami;

import com.google.gson.annotations.SerializedName;

public class MetroStatusDescriptor {
    @SerializedName("metroStatus")
    private String[] metroStatus;
    @SerializedName("orariChiusura")
    private String[] orariChiusura;
    @SerializedName("orariApertura")
    private String[] orariApertura;
    @SerializedName("orariAperturaFestivi")
    private String[] orariAperturaFestivi;

    public MetroStatusDescriptor(String[] metroStatus, String[] orariChiusura, String[] orariApertura, String[] orariAperturaFestivi){
        this.metroStatus = metroStatus;
        this.orariChiusura = orariChiusura;
        this.orariApertura = orariApertura;
        this.orariAperturaFestivi = orariAperturaFestivi;
    }

    public String[] getMetroStatus(){return metroStatus;}
    public String[] getOrariChiusura() {return orariChiusura;}
    public String[] getOrariApertura() {return orariApertura;}
    public String[] getOrariAperturaFestivi() {return orariAperturaFestivi;}
}
