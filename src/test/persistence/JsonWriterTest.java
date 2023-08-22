package persistence;

import model.Restaurant;
import model.Journal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// UBC (2021), Accessed July 24, 2023, JsonSerializationDemo, [source code],
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//      for saving and reading data and testing reader/writer classes

class JsonWriterTest {


    @Test
    void testWriterInvalidFile() {
        try {
            Journal j = new Journal("My restaurant journal");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyJournal() {
        try {
            Journal j = new Journal("My restaurant journal");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJournal.json");
            j = reader.read();
            assertEquals("My restaurant journal", j.getJournalName());
            assertEquals(0, j.getRestaurants().size());
            assertEquals(0, j.getRestaurantNames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralJournal() {
        try {
            Journal j = new Journal("My restaurant journal");
            Restaurant restaurantA = new Restaurant(7);
            Restaurant restaurantB = new Restaurant(10);

            restaurantA.addName("Crumb Cafe");
            restaurantB.addName("The Steakhouse");

            restaurantA.addReview("the coffee was good");
            restaurantB.addReview("life-changing steaks");

            j.addRestaurant(restaurantA);
            j.addRestaurant(restaurantB);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJournal.json");
            j = reader.read();
            assertEquals("My restaurant journal", j.getJournalName());
            List<Restaurant> restaurants = j.getRestaurants();
            List<String> restaurantNames = j.getRestaurantNames();
            assertEquals(2, restaurants.size());
            assertEquals(2, restaurantNames.size());

            assertEquals("Crumb Cafe", j.getRestaurants().get(0).getName());
            assertEquals("The Steakhouse", j.getRestaurants().get(1).getName());

            assertEquals(7, j.getRestaurants().get(0).getRating());
            assertEquals(10, j.getRestaurants().get(1).getRating());

            assertEquals("the coffee was good", j.getRestaurants().get(0).getReview());
            assertEquals("life-changing steaks", j.getRestaurants().get(1).getReview());

            assertEquals("Crumb Cafe", j.getRestaurantNames().get(0));
            assertEquals("The Steakhouse", j.getRestaurantNames().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}