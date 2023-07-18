package model;

import java.util.ArrayList;
import java.util.List;

// Represents a journal with given journal name and restaurants inputted by user.
public class Journal {

    private List<Restaurant> restaurants;


    // constructor
    public Journal(String journalName) {
    }

    // adds restaurant to journal, cannot add two restaurants with same name
    public String addRestaurant(Restaurant restaurant) {
        if (restaurants.contains(restaurant)) {
            restaurants.add(restaurant);
            return ("Restaurant added!");
        } else {
            return "Cannot add restaurants with already existing names!";
        }
    }
}
