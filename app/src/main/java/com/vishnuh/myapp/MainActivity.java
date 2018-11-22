package com.vishnuh.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mRestId = new ArrayList<>();



    private ArrayList<SpinnerRowItem> mSpinnerRow = new ArrayList<>();

    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spinner);

        //Creating Retrofit Object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creating Object for API interface
        final API api = retrofit.create(API.class);

        Call<List<RestAPI>> callCities = api.getCities();

        //HTTP Call method for Spinner data
        callGetCities(callCities);

        //Spinner Click Listener
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SpinnerRowItem clickedItem = (SpinnerRowItem) parent.getItemAtPosition(position);
                String clickedItemName = clickedItem.getCity();
                Call<List<RestAPI>> callRests = api.getRests(clickedItemName);

                //HTTP call method for RecyclerView Data
                loadRests(callRests);

//                Toast.makeText(MainActivity.this,clickedItemName +  " Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Getting City data for spinner
    private void callGetCities(Call<List<RestAPI>> call) {
        call.enqueue(new Callback<List<RestAPI>>() {
            @Override
            public void onResponse(Call<List<RestAPI>> call, Response<List<RestAPI>> response) {

                List<RestAPI> cities = response.body();
                mSpinnerRow.add(new SpinnerRowItem("Select City", ""));

                //Adding data to mSpinnerRow arraylist
                for(RestAPI c: cities){
//                    Log.d(TAG, "City " + c.getCity() + " " + c.getImage());
                    mSpinnerRow.add(new SpinnerRowItem(c.getCity(), c.getImage()));
                }

                CustomSpinnerAdpater mAdapter = new CustomSpinnerAdpater(getApplicationContext(), mSpinnerRow);
                spin.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<RestAPI>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void loadRests(Call<List<RestAPI>> call){
        call.enqueue(new Callback<List<RestAPI>>() {
            @Override
            public void onResponse(Call<List<RestAPI>> call, Response<List<RestAPI>> response) {
                List<RestAPI> rests = response.body();

                mNames.clear();
                mAddress.clear();
                mImageUrls.clear();
                mRestId.clear();

                for(RestAPI c: rests){
                    Log.d(TAG, "Restaurant Name " + c.getRestaurant_name() + " " + c.getLocality());
                    mNames.add(c.getRestaurant_name());
                    mAddress.add(c.getLocality());

                    //Adding place holder image
                    mImageUrls.add("https://placeimg.com/640/480/any");
                    mRestId.add(c.getRestaurant_id());
                    Log.d(TAG, "onResponse: " + mImageUrls);
                }

                initRecyclerView();

            }
            @Override
            public void onFailure(Call<List<RestAPI>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage() + " ERROR grabbing data", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing Bitmamps");

//        mImageUrls.add("https://placeimg.com/640/480/any");
//        mNames.add("Test 123");


        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mAddress, mRestId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
