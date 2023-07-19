package model;

import java.util.List;

// Represents a journal with given journal name and restaurants inputted by user.
public class Journal {

    private List<Restaurant> restaurants;


    // constructor
    public Journal(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    // adds restaurant to journal, cannot add two restaurants with same name
    public List<Restaurant> addRestaurant(Restaurant restaurant) {
        if (!restaurants.contains(restaurant)) {
            restaurants.add(restaurant);
        }
        return restaurants;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
