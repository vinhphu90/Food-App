package com.examplevinhphutvp.foodapp;

public class FoodData {

    private String Name;
    private String Description;
    private String price;
    private int Image;

    public FoodData(String name, String description, String price, int image) {
        Name = name;
        Description = description;
        this.price = price;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return Image;
    }
}
