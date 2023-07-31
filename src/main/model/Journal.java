package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// CPSC 210 UBC (2021), Accessed July 24, 2023, JsonSerializationDemo, [source code],
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//      for saving and reading data

// Represents a Journal with a name
public class Journal implements Writable {
    private List<Restaurant> restaurants;
    private List<String> restaurantNames;
    private String name;

    // EFFECTS: constructs a new journal with given name and no restaurants or restaurant names
    public Journal(String name) {
        restaurants = new ArrayList<>();
        restaurantNames = new ArrayList<>();
        this.name = name;
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
    
    public String getJournalName() {
        return name;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("restaurants", restaurantsToJson());
        json.put("restaurant names", restaurantNamesToJson());
        return json;
    }

    // EFFECTS: returns restaurants in this journal as a JSON array
    private JSONArray restaurantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurants) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns names of restaurants in this journal as a JSON array
    private JSONArray restaurantNamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurants) {
            jsonArray.put(r.getName());
        }

        return jsonArray;
    }
}
