package com.andreafilice.lavorami;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;
public interface APIWorks {
    @GET("LavoraMI/lavoriAttuali.json")
    Call<ArrayList<EventDescriptor>> getLavori();
}
