package com.andreafilice.lavorami;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequirementsDescriptor {
    /// In this class, we get from the CDN the values of the requirements.json file and parse that datas into this Class.
    /// @SerializedName are the original names from the JSON file of our CDN
    /// @ATTRIBUTE
    /// @String minimumVersionAndroid -> is the minimum version to have for using the application.
    @SerializedName("minVersionAndroid")
    public String minimumVersionAndroid;
    @SerializedName("maintenanceMode")
    public String maintenanceModeEnabled;
    @SerializedName("maintenanceModeDebug")
    public String maintenanceModeDebug;
    @SerializedName("maintenanceDeps")
    public String maintenanceDeps;
    @SerializedName("maintenanceDepsEn")
    public String maintenanceDepsEnglish;
    @SerializedName("maintenanceDepsEs")
    public String maintenanceDepsSpanish;

    public RequirementsDescriptor(String minimumVersionAndroid){this.minimumVersionAndroid = minimumVersionAndroid;}

    //*GETTER
    /// Set-up the Getter to use into MainActivity.java for get some values.
    public String getMinimumVersionAndroid(){return this.minimumVersionAndroid;}
    public boolean isMaintenanceEnabled(){return maintenanceModeEnabled.equalsIgnoreCase("true");}
    public boolean isMaintenanceDebugEnabled(){return maintenanceModeDebug.equalsIgnoreCase("false");}
    public String getMaintenanceDeps(){return this.maintenanceDeps;}
    public String getMaintenanceDepsEnglish(){return this.maintenanceDepsEnglish;}
    public String getMaintenanceDepsSpanish(){return this.maintenanceDepsSpanish;}

    public static int compareSemanticVersions(String version1, String version2) {
        //TODO: Comment better this code.
        String[] p1 = version1.split("\\.");
        String[] p2 = version2.split("\\.");

        int len = Math.max(p1.length, p2.length);

        for (int i = 0; i < len; i++) {
            int n1 = i < p1.length ? Integer.parseInt(p1[i]) : 0;
            int n2 = i < p2.length ? Integer.parseInt(p2[i]) : 0;
            if (n1 > n2) return 1;
            if (n1 < n2) return -1;
        }
        return 0;
    }
}
