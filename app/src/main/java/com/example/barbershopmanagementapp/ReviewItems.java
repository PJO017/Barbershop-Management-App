package com.example.barbershopmanagementapp;

import androidx.annotation.NonNull;

public class ReviewItems {
    String review, barber, customer;
    double rating;

    public ReviewItems(String review, double rating, String customer) {
        this.review = review;
        this.rating = rating;
        this.customer = customer;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @NonNull
    public String toString() {
        return ("Customer" + this.customer + "Review" + this.review + "Rating" + this.rating);
    }
}
