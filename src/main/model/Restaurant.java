package model;


// Represents a restaurant with given name, rating, and optional quick review.
public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private Integer rating;
    private String review;

    // REQUIRES: rating is [1, 10]
    // EFFECTS: constructs a restaurant with given rating to be added to journal
    public Restaurant(int initialRating) {
        if ((initialRating > 0) && (initialRating < 10)) {
            rating = initialRating;
        } else {
            rating = 0;
        }
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
