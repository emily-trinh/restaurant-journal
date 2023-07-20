package model;

import java.util.Collections;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

// Represents a restaurant with given name, rating, and optional quick review.
public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private Integer rating;
    private String review;
    private static Integer MAX_REVIEW_LENGTH;

    // REQUIRES: length of restaurant name > 0 and restaurant rating must be from [1, 10]
    // EFFECTS: constructs a restaurant to be added to journal
    public Restaurant() {
        MAX_REVIEW_LENGTH = 50;
    }


    // REQUIRES: review length > 0
    // MODIFIES: this
    // EFFECTS: adds review to restaurant and returns a success or failure message
    public void addReview(String restaurantReview) {
        this.review = restaurantReview;
    }

    public void addName(String newName) {
        this.name = newName;
    }

    public void addRating(int newRating) {
        this.rating = newRating;
    }

    @Override
    public int compareTo(Restaurant o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }


    public String getReview() {
        return review;
    }

}
