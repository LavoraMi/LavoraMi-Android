package com.andreafilice.lavorami;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;
public interface APIWorks {
    @GET("lavoriAttuali.json")
    Call<ArrayList<EventDescriptor>> getLavori();

    @GET("_vars.json")
    Call<StrikeDescriptor> getStrike();
}
