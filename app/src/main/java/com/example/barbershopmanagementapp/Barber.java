package com.example.barbershopmanagementapp;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Barber {
    String name;
    Double rating;

    public Barber(String name, Double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @NonNull
    public String toString() {
        return ("Name: " + this.name + " Rating: " + this.rating);
    }
}
