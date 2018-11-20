package com.vishnuh.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "http://192.168.0.3:80/slimServer/public/index.php/api/";

    @GET("cities")
    Call<List<RestAPI>> getCities();

}
