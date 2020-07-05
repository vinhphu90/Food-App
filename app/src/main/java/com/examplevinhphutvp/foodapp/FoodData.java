package com.examplevinhphutvp.foodapp;

public class FoodData {

    private String Name;
    private String Description;
    private String price;
    private String Image;
    private String Address;
    private String Phone;
    private String key;

    public FoodData(String name, String description, String price, String image, String address, String phone) {
        Name = name;
        Description = description;
        this.price = price;
        Image = image;
        Address = address;
        Phone = phone;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
