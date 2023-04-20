package com.example.barbershopmanagementapp;

public class ReviewItems {
    String review;
    long rating;



    String user;

    public ReviewItems(String user, String review, long rating) {
        this.review = review;
        this.rating = rating;
        this.user = user;
    }

    public String toString (String user, String review, long rating){
        return ("User" + user + "Review" + review + "Rating" + rating);
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
}
