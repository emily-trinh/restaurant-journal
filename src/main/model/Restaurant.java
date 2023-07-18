package model;


import static jdk.nashorn.internal.objects.ArrayBufferView.length;

// Represents a restaurant with given name, rating, and optional quick review.
public class Restaurant {

    private String name;
    private Integer rating;
    private String review;
    private static Integer MAX_REVIEW_LENGTH;

    // constructs a restaurant to be added to journal
    public Restaurant(String restaurantName, Integer restaurantRating) {
        name = restaurantName;
        rating = restaurantRating;
        MAX_REVIEW_LENGTH = 50;
    }

    public String addReview(String restaurantReview) {
        if (length(restaurantReview) <= MAX_REVIEW_LENGTH) {
            return "Review added!";
        } else {
            return "Review too long!";
        }
    }
}
