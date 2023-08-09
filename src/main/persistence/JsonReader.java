package persistence;

import model.Event;
import model.EventLog;
import model.Journal;
import model.Restaurant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// CPSC 210 UBC (2021), Accessed July 24, 2023, JsonSerializationDemo, [source code],
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//      for saving and reading data

// Represents a reader that reads journal from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Journal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses journal from JSON object and returns it
    private Journal parseJournal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Journal j = new Journal(name);
        addRestaurants(j, jsonObject);
        return j;
    }

    // MODIFIES: j
    // EFFECTS: parses restaurants from JSON object and adds them to journal
    private void addRestaurants(Journal j, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(j, nextRestaurant);
        }
    }

    // MODIFIES: j
    // EFFECTS: parses restaurant from JSON object and adds it to journal
    private void addRestaurant(Journal j, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");

        Restaurant restaurant = new Restaurant(rating);
        restaurant.addName(name);
        restaurant.addReview(review);

        j.addRestaurant(restaurant);

    }
}
