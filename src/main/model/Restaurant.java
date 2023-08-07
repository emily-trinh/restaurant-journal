package model;


// Citation: Rafael del Nero (2019), Sorting with Comparable and Comparator in Java, [source code]
// https://www.infoworld.com/article/3323403/java-challengers-5-sorting-with-comparable-and-comparator-in-java.html


import org.json.JSONObject;
import persistence.Writable;

// Represents a restaurant with name, rating, and review.
public class Restaurant implements Comparable<Restaurant>, Writable {

    private String name;
    private Integer rating;
    private String review;

    // EFFECTS: constructs a restaurant with given rating
    public Restaurant(int initialRating) {
        if ((initialRating > 0) && (initialRating < 11)) {
            rating = initialRating;
        } else {
            rating = 1;
        }
    }

    // EFFECTS: compares rating of restaurant to others;
    //          when restaurant rating > other restaurant rating, returns >= 1
    //          when restaurant rating < other restaurant rating, returns <= -1
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("rating", rating);
        json.put("review", review);
        return json;
    }

    @Override
    public String toString() {
        return (name + " - Rating: " + rating);
    }
}
