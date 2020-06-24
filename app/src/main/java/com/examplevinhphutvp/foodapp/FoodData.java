package com.examplevinhphutvp.foodapp;

public class FoodData {

    private String Name;
    private String Description;
    private String price;
    private String Image;

    public FoodData(String name, String description, String price, String image) {
        Name = name;
        Description = description;
        this.price = price;
        Image = image;
    }

    public FoodData() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
