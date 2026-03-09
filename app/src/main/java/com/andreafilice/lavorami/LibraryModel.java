package com.andreafilice.lavorami;

import android.os.Parcel;
import android.os.Parcelable;

public class LibraryModel implements Parcelable {
    private String name;
    private String version;
    private String licenseType;
    private String copyright;
    private String licenseText;

    public LibraryModel(String name, String version, String licenseType, String copyright, String licenseText){
        this.name = name;
        this.version = version;
        this.licenseType = licenseType;
        this.copyright = copyright;
        this.licenseText = licenseText;
    }

    protected LibraryModel(Parcel in) {
        name = in.readString();
        version = in.readString();
        licenseType = in.readString();
        copyright = in.readString();
        licenseText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(version);
        dest.writeString(licenseType);
        dest.writeString(copyright);
        dest.writeString(licenseText);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<LibraryModel> CREATOR = new Creator<>() {
        @Override
        public LibraryModel createFromParcel(Parcel in) { return new LibraryModel(in); }
        @Override
        public LibraryModel[] newArray(int size) { return new LibraryModel[size]; }
    };

    public String getLicenseText() {return licenseText;}
    public void setLicenseText(String licenseText) {this.licenseText = licenseText;}
    public String getCopyright() {return copyright;}
    public void setCopyright(String copyright) {this.copyright = copyright;}
    public String getLicenseType() {return licenseType;}
    public void setLicenseType(String licenseType) {this.licenseType = licenseType;}
    public String getVersion() {return version;}
    public void setVersion(String version) {this.version = version;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}