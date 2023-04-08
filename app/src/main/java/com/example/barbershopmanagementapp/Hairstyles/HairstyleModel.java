package com.example.barbershopmanagementapp.Hairstyles;

public class HairstyleModel {
    private String hairstyle_name;
    private int imgId;
    private int price;

    public HairstyleModel(String hairstyle_name, int imgId, int price) {
        this.hairstyle_name = hairstyle_name;
        this.imgId = imgId;
        this.price = price;
    }
    public String getHairstyle_name() {
        return hairstyle_name;
    }

    public void setHairstyle_name(String hairstyle_name) {
        this.hairstyle_name = hairstyle_name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
