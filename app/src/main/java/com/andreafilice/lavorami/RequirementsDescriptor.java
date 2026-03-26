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

    @SerializedName("maintenanceDeps")
    public String maintenanceDeps;

    public RequirementsDescriptor(String minimumVersionAndroid){this.minimumVersionAndroid = minimumVersionAndroid;}

    //*GETTER
    /// Set-up the Getter to use into MainActivity.java for get some values.
    public String getMinimumVersionAndroid(){return this.minimumVersionAndroid;}
    public boolean isMaintenanceEnabled(){return maintenanceModeEnabled.equalsIgnoreCase("true");}
    public String getMaintenanceDeps(){return this.maintenanceDeps;}

    public static int compareSemanticVersions(String version1, String version2) {
        List<Integer> version1Components = Arrays.stream(version1.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> version2Components = Arrays.stream(version2.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxLength = Math.max(version1Components.size(), version2Components.size());

        for (int i = 0; i < maxLength; i++) {
            int v1Component = i < version1Components.size() ? version1Components.get(i) : 0;
            int v2Component = i < version2Components.size() ? version2Components.get(i) : 0;

            if (v1Component > v2Component)
                return 1;
            else if (v1Component < v2Component)
                return -1;
        }

        return 0;
    }
}
