package com.example.barbershopmanagementapp.Hairstyles;

import androidx.annotation.NonNull;

public class HairstyleModel {
    private String name;
    private Long count;
    private Long price;

    public HairstyleModel(Long count, String name, Long price) {
        this.count = count;
        this.name = name;
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Count: " + count + " Name: " + name + " Price: " + price;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
