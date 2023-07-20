package model;


// Represents a restaurant with name, rating, and review.
public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private Integer rating;
    private String review;

    // REQUIRES: rating is [1, 10]
    // EFFECTS: constructs a restaurant with given rating to be added to journal
    public Restaurant(int initialRating) {
        if ((initialRating > 0) && (initialRating < 11)) {
            rating = initialRating;
        } else {
            rating = 1;
        }
    }

    // EFFECTS: compares rating of restaurant to others;
    //          when restaurant rating > other restaurant rating, returns 1
    //          when restaurant rating < other restaurant rating, returns -1
    //          when restaurant rating = other restaurant rating, returns 0
    @Override
    public int compareTo(Restaurant o) {
        return this.rating.compareTo(o.rating);
    }

    // getters
    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    // setters
    public void addReview(String restaurantReview) {
        this.review = restaurantReview;
    }

    public void addName(String newName) {
        this.name = newName;
    }

    public void addRating(int newRating) {
        this.rating = newRating;
    }
}
