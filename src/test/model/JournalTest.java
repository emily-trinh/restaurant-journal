package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JournalTest {
    private Journal testJournal;

    @BeforeEach
    void runBefore() {
        testJournal = new Journal("Journal1");
    }

    @Test
    void testJournalConstructor() {
        assertEquals(0, testJournal.getRestaurants().size());
        assertEquals(0, testJournal.getRestaurantNames().size());
        assertEquals("Journal1", testJournal.getJournalName());

    }

    @Test
    void testAddRestaurant() {
        Restaurant restaurantA = new Restaurant(5);
        Restaurant restaurantB = new Restaurant(6);

        testJournal.addRestaurant(restaurantA);
        assertEquals(1, testJournal.getRestaurants().size());
        assertEquals(1, testJournal.getRestaurantNames().size());

        testJournal.addRestaurant(restaurantB);
        assertEquals(2, testJournal.getRestaurants().size());
        assertEquals(2, testJournal.getRestaurantNames().size());

        testJournal.addRestaurant(restaurantA);
        assertEquals(2, testJournal.getRestaurants().size());
        assertEquals(2, testJournal.getRestaurantNames().size());
    }

    @Test
    void testSortByRanking() {
        Restaurant restaurantA = new Restaurant(8);
        Restaurant restaurantB = new Restaurant(6);
        Restaurant restaurantC = new Restaurant(10);

        testJournal.addRestaurant(restaurantA);
        testJournal.addRestaurant(restaurantB);
        testJournal.addRestaurant(restaurantC);

        testJournal.sortByRanking(testJournal.getRestaurants());
        assertEquals(restaurantC, testJournal.getRestaurants().get(0));
        assertEquals(restaurantA, testJournal.getRestaurants().get(1));
        assertEquals(restaurantB, testJournal.getRestaurants().get(2));

    }

}
