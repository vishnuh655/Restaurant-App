package com.vishnuh.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "http://ec2-18-224-24-117.us-east-2.compute.amazonaws.com/SlimServer/public/index.php/api/";
    String ZOMATO_API = "https://developers.zomato.com/api/v2.1/";

    @GET("cities")
    Call<List<RestAPI>> getCities();
//@GET("cities")
//Call<List<SpinnerRowItem>> getCities();

    @GET("rest/{city}")
    Call<List<RestAPI>> getRests(@Path("city")String code);

    @GET("restid/{restid}")
    Call<List<RestAPI>> getDetail(@Path("restid")String id);

    @Headers("user-key: 2520dc2feb6f93ef8b19fa083374705a")
    @GET("restaurant")
    Call<ZomatoApiModelClass> restaurantImage(@Query("res_id") String res_id);


}
