package com.example.projetandroid1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StarWarsApi {

    @GET("api/vehicles")
    Call<RestVehicleResponse> getListVehicle();
}
