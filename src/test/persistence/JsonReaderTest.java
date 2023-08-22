package persistence;

import model.Journal;
import model.Restaurant;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// UBC (2021), Accessed July 24, 2023, JsonSerializationDemo, [source code],
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//      for saving and reading data and testing reader/writer classes

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Journal j = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyJournal.json");
        try {
            Journal j = reader.read();
            assertEquals("My restaurant journal", j.getJournalName());
            assertEquals(0, j.getRestaurants().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralJournal.json");
        try {
            Journal j = reader.read();
            assertEquals("My restaurant journal", j.getJournalName());
            List<Restaurant> restaurants = j.getRestaurants();

            assertEquals(3, restaurants.size());

            assertEquals("Crumb Cafe", j.getRestaurants().get(0).getName());
            assertEquals("The Steakhouse", j.getRestaurants().get(1).getName());
            assertEquals("CandyLand", j.getRestaurants().get(2).getName());

            assertEquals(7, j.getRestaurants().get(0).getRating());
            assertEquals(10, j.getRestaurants().get(1).getRating());
            assertEquals(4, j.getRestaurants().get(2).getRating());

            assertEquals("the coffee was good", j.getRestaurants().get(0).getReview());
            assertEquals("life-changing steaks", j.getRestaurants().get(1).getReview());
            assertEquals("too sweet for me", j.getRestaurants().get(2).getReview());

            assertEquals("Crumb Cafe", j.getRestaurantNames().get(0));
            assertEquals("The Steakhouse", j.getRestaurantNames().get(1));
            assertEquals("CandyLand", j.getRestaurantNames().get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}