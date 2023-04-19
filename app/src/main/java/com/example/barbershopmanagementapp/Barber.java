package com.example.barbershopmanagementapp;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Barber {
    String name;
    String rating;

//    public Barber(String name, String rating){
//        this.name = name;
//        this.rating = rating;
//    }

    public Barber(){

    }

    @PropertyName("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("Rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
