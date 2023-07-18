package model;


import static jdk.nashorn.internal.objects.ArrayBufferView.length;

// Represents a restaurant with given name, rating, and optional quick review.
public class Restaurant {

    private final String name;
    private Integer rating;
    private String review;
    private static Integer MAX_REVIEW_LENGTH;

    // REQUIRES: length of restaurant name > 0 and restaurant rating must be from [1, 10]
    // EFFECTS: constructs a restaurant to be added to journal
    public Restaurant(String restaurantName, Integer restaurantRating) {
        name = restaurantName;
        rating = restaurantRating;
        MAX_REVIEW_LENGTH = 50;
    }


    // REQUIRES: review length > 0
    // MODIFIES: this
    // EFFECTS: adds review to restaurant and returns a success or failure message
    public String addReview(String restaurantReview) {
        if (length(restaurantReview) <= MAX_REVIEW_LENGTH) {
            return "Review added!";
        } else {
            return "Review too long!";
        }
    }

    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

}
