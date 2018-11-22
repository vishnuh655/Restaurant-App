package com.vishnuh.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private String RestId, Cuisine, RestName, textRating, resImage, Address;
    private double Rating;

    TextView restnameText, ratingTextView, addressTextView, cuisineTextView;
    ImageView resImageView;
    RatingBar rateBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        //Get incoming data from intent
        getIncomingIntent();

        //Retrofit object for zomatoApi
        Retrofit retrofitZomato = new Retrofit.Builder()
                .baseUrl(API.ZOMATO_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API apiZomato = retrofitZomato.create(API.class);
        Call<ZomatoApiModelClass> callDetailImage = apiZomato.restaurantImage(RestId);

        //HTTP Call for fetching image from ZomatoApi
        getResImage(callDetailImage);


        //Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Api interface object
        API api = retrofit.create(API.class);

        Call<List<RestAPI>> callDetail = api.getDetail(RestId);

        //HTTP Call for fetching details from API
        callGetDetail(callDetail);

    }


    private void getResImage(Call<ZomatoApiModelClass> call){
        call.enqueue(new Callback<ZomatoApiModelClass>() {
            @Override
            public void onResponse(Call<ZomatoApiModelClass> call, Response<ZomatoApiModelClass> response) {
                ZomatoApiModelClass restDetail = response.body();

                if (restDetail.getFeatured_image() != null)
                    resImage = restDetail.getFeatured_image();

//                resImage = restDetail.getFeatured_image();
                Log.d(TAG, "onResponse: " + resImage);

                resImageView = findViewById(R.id.detail_imageView);
                if(resImage != null){
                    Glide.with(getApplicationContext()).asBitmap().load(resImage).into(resImageView);
                }
            }

            @Override
            public void onFailure(Call<ZomatoApiModelClass> call, Throwable t) {

            }
        });
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("RestId")){
            RestId = getIntent().getStringExtra("RestId");
//            Toast.makeText(this, "" + RestId, Toast.LENGTH_SHORT).show();
        }
    }

    private void callGetDetail(Call<List<RestAPI>> call){
        call.enqueue(new Callback<List<RestAPI>>() {
            @Override
            public void onResponse(Call<List<RestAPI>> call, Response<List<RestAPI>> response) {
                List<RestAPI> restDetail = response.body();

                for(RestAPI c: restDetail){
                    Cuisine = c.getCuisines();
                    Rating = c.getRating();
                    textRating = c.getRatingText();
                    RestName = c.getRestaurant_name();
                    Address = c.getAddress();
                }
                Log.d(TAG, "onResponse: " + RestName);
                restnameText = findViewById(R.id.detail_restText);
                restnameText.setText(RestName);

                rateBar = findViewById(R.id.ratingBar);
                rateBar.setRating((float) Rating);

                ratingTextView = findViewById(R.id.ratingTextView);
                ratingTextView.setText(textRating);

                addressTextView = findViewById(R.id.addressText);
                addressTextView.setText(Address);

                cuisineTextView = findViewById(R.id.textCusinies);
                cuisineTextView.setText(Cuisine);

            }

            @Override
            public void onFailure(Call<List<RestAPI>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
