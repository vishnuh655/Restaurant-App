package com.vishnuh.myapp;


import com.google.gson.annotations.SerializedName;

public class RestAPI {
    @SerializedName("City")
    private String city;

    @SerializedName("Address")
    private String address;

    @SerializedName("Restaurent Name")
    private String restaurent_name;

    @SerializedName("Restaurant ID")
    private String restaurent_id;

    @SerializedName("Locality Verbose")
    private String locality;

    @SerializedName("Rating text")
    private String rating;

    public RestAPI(String city) {
        city = city;
    }

    public RestAPI(String city, String restaurent_name, String address, String rating) {
        city = city;
        restaurent_name = restaurent_name;
        address = address;
        rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurent_name() {
        return restaurent_name;
    }

    public void setRestaurent_name(String restaurent_name) {
        this.restaurent_name = restaurent_name;
    }

    public String getRestaurent_id() {
        return restaurent_id;
    }

    public void setRestaurent_id(String restaurent_id) {
        this.restaurent_id = restaurent_id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    //    "Restaurant ID":"130008",
// "Restaurant Name":"Fisherman's Cove",
// "Address":"Main Candolim Road,
// Titos Vaddo, Candolim, Goa",
// "Locality Verbose":"Candolim, Goa",
// "Rating text":"Good"}
}
