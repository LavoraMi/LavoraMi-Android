package com.andreafilice.lavorami;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StrikeDescriptor {
    /// In this class, we get from the CDN the values of the strike and parse that datas into this Class.
    /// @SerializedName are the original names from the JSON file of our CDN
    /// @ATTRIBUTES
    /// @String isStrikeEnabled -> Returns a String but the values are: true or false.
    /// @String strikeDate -> Is the date of the Strike, catched from the JSON File.
    /// @String strikeCompanies -> Are the companies that make this Strike and their services can suffer problems.
    /// @String strikeGuaranteed -> Returns a String that tells exactly which hours the service is guaranteed.
    /// @String strikeUpdateLive -> Returns a String that contains the live status of the strike, such as: which lines are deviated.
    /// @String enablePassanteWork -> Returns a String but the values are: true or false.
    /// @String[] linesDeviation -> Returns an array of Strings of Tram lines that have works on the track.
    /// @String[] linesDeviationLinks -> Returns an array of Strings containing the works for Tram with works on the track.
    /// @String[] supportedGTFSLines -> Returns an array of Strings for control the Bus Lines available for GTFS feature.
    /// @String[] suburbanWithInterruptions -> Returns an array of Strings containing the suburban lines with works on the track.
    /// @String[] suburbanInterruptionLinks -> Returns an array of Strings containing the works for suburban lines with works on the track.

    @SerializedName("enableStrike")
    private String isStrikeEnabled;
    @SerializedName("enableStrikeDebug")
    private String enableStrikeDebug;
    @SerializedName("date")
    private String strikeDate;
    @SerializedName("companies")
    private String strikeCompanies;
    @SerializedName("guaranteed")
    private String strikeGuaranteed;
    @SerializedName("strikeUpdateLive")
    private String strikeUpdateLive;
    @SerializedName("linesAffectedbyDeviation")
    private String[] linesDeviation;
    @SerializedName("linesDeviationLinks")
    private String[] linesDeviationLinks;
    @SerializedName("linesSupportedGTFS")
    private String[] supportedGTFSLines;
    @SerializedName("suburbanWithInterruptions")
    private String[] suburbanWithInterruptions;
    @SerializedName("suburbanInterruptionLinks")
    private String[] suburbanInterruptionLinks;
    @SerializedName("enablePassanteWork")
    private String enablePassanteWork;

    public StrikeDescriptor(String isStrikeEnabled, String enableStrikeDebug, String strikeUpdateLive, String enablePassanteWork, String strikeDate, String strikeCompanies, String strikeGuaranteed, String[] linesDeviation, String[] linesDeviationLinks, String[] supportedGTFSLines, String[] suburbanWithInterruptions, String[] suburbanInterruptionLinks) {
        this.isStrikeEnabled = isStrikeEnabled;
        this.enableStrikeDebug = enableStrikeDebug;
        this.strikeUpdateLive = strikeUpdateLive;
        this.enablePassanteWork = enablePassanteWork;
        this.strikeDate = strikeDate;
        this.strikeCompanies = strikeCompanies;
        this.strikeGuaranteed = strikeGuaranteed;
        this.linesDeviation = linesDeviation;
        this.linesDeviationLinks = linesDeviationLinks;
        this.supportedGTFSLines = supportedGTFSLines;
        this.suburbanWithInterruptions = suburbanWithInterruptions;
        this.suburbanInterruptionLinks = suburbanInterruptionLinks;
    }

    //*GETTERS
    /// Set-up the Getters to use into activities for get some values.
    public boolean isStrikeEnabled() {return isStrikeEnabled.equals("true");}
    public boolean isStrikeEnabledDebug() {return enableStrikeDebug.equals("true");}
    public boolean isPassanteWorkEnabled() {return enablePassanteWork.equalsIgnoreCase("true");}
    public String getStrikeDate() {return strikeDate;}
    public String getStrikeCompanies() {return strikeCompanies;}
    public String getStrikeGuaranteed() {return strikeGuaranteed;}
    public String getStrikeUpdateLive() {return strikeUpdateLive;}
    public String[] getLinesDeviation() {return linesDeviation;}
    public String[] getLinesDeviationLinks(){return linesDeviationLinks;}
    public String[] getSupportedGTFSLines() {return supportedGTFSLines;}
    public String[] getSuburbanWithInterruptions() {return suburbanWithInterruptions;}
    public String[] getSuburbanInterruptionLinks() {return suburbanInterruptionLinks;}

    public boolean isStrikeToday() {
        if (strikeDate == null || strikeDate.trim().isEmpty()) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
            String todayString = sdf.format(new Date());
            return todayString.equals(this.strikeDate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
