package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Journal {
    private List<Restaurant> restaurants;
    private List<String> restaurantNames;

    // EFFECTS: constructs a new journal with no restaurants or restaurant names
    public Journal() {
        restaurants = new ArrayList<>();
        restaurantNames = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if restaurant is not already added to journal, adds restaurant to journal
    //          and adds restaurant name to list of restaurant names
    public void addRestaurant(Restaurant newRestaurant) {
        if (!restaurants.contains(newRestaurant)) {
            restaurants.add(newRestaurant);
            restaurantNames.add(newRestaurant.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts restaurants in journal from the highest ranking to lowest
    public void sortByRanking() {
        Collections.sort(restaurants);
        Collections.reverse(restaurants);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<String> getRestaurantNames() {
        return restaurantNames;
    }
}
