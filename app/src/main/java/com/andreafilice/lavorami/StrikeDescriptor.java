package com.andreafilice.lavorami;

import com.google.gson.annotations.SerializedName;

public class StrikeDescriptor {
    /// In this class, we get from the CDN the values of the strike and parse that datas into this Class.
    /// @SerializedName are the original names from the JSON file of our CDN
    /// @ATTRIBUTES
    /// @String isStrikeEnabled -> Returns a String but the values are: true <i>or</i> false.
    /// @String strikeDate -> Is the date of the Strike, catched from the JSON File.
    /// @String strikeCompanies -> Are the companies that make this Strike and their services can suffer problems.
    /// @String strikeGuaranteed -> Returns a String but the values are: sono garantite <i>or</i> non sono garantite.

    @SerializedName("enableStrike")
    private String isStrikeEnabled;
    @SerializedName("date")
    private String strikeDate;
    @SerializedName("companies")
    private String strikeCompanies;
    @SerializedName("guaranteed")
    private String strikeGuaranteed;
    @SerializedName("linesAffectedbyDeviation")
    private String[] linesDeviation;
    @SerializedName("linesDeviationLinks")
    private String[] linesDeviationLinks;
    @SerializedName("linesSupportedGTFS")
    private String[] supportedGTFSLines;

    public StrikeDescriptor(String isStrikeEnabled, String strikeDate, String strikeCompanies, String strikeGuaranteed, String[] linesDeviation, String[] linesDeviationLinks, String[] supportedGTFSLines) {
        this.isStrikeEnabled = isStrikeEnabled;
        this.strikeDate = strikeDate;
        this.strikeCompanies = strikeCompanies;
        this.strikeGuaranteed = strikeGuaranteed;
        this.linesDeviation = linesDeviation;
        this.linesDeviationLinks = linesDeviationLinks;
        this.supportedGTFSLines = supportedGTFSLines;
    }

    //*GETTERS
    /// Set-up the Getters to use into MainActivity.java for get some values.
    public String isStrikeEnabled() {return isStrikeEnabled;}
    public String getStrikeDate() {return strikeDate;}
    public String getStrikeCompanies() {return strikeCompanies;}
    public String getStrikeGuaranteed() {return strikeGuaranteed;}
    public String[] getLinesDeviation() {return linesDeviation;}
    public String[] getLinesDeviationLinks(){return linesDeviationLinks;}
    public String[] getSupportedGTFSLines() {return supportedGTFSLines;}
}
