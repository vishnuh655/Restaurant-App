package com.vishnuh.myapp;

public class SpinnerRowItem {
    private String City;
    private String Image;

    public SpinnerRowItem(String city, String image) {
        City = city;
        Image = image;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
