package com.vishnuh.myapp;


import com.google.gson.annotations.SerializedName;

public class RestAPI {

    private String featured_image;

    @SerializedName("City")
    private String city;

    private String Image;

    @SerializedName("Address")
    private String address;

    @SerializedName("Restaurant Name")
    private String restaurant_name;

    @SerializedName("Restaurant ID")
    private String restaurant_id;

    @SerializedName("Locality Verbose")
    private String locality;

    @SerializedName("Aggregate rating")
    private double rating;

    @SerializedName("Rating text")
    private String ratingText;

    private String Cuisines;

    @SerializedName("Average Cost for two")
    private int AvgCost;


    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public String getCuisines() {
        return Cuisines;
    }

    public void setCuisines(String cuisines) {
        Cuisines = cuisines;
    }

    public int getAvgCost() {
        return AvgCost;
    }

    public void setAvgCost(int avgCost) {
        AvgCost = avgCost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    //    "Restaurant ID":"130008",
// "Restaurant Name":"Fisherman's Cove",
// "Address":"Main Candolim Road,
// Titos Vaddo, Candolim, Goa",
// "Locality Verbose":"Candolim, Goa",
// "Rating text":"Good"}
}
