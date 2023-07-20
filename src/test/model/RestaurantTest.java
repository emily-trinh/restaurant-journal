package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant testRestaurant;


    @BeforeEach
    void runBefore() {
        testRestaurant = new Restaurant(0);
    }
    @Test
    void testRestaurantConstructor() {
        assertNull(testRestaurant.getName());
        assertEquals(0, testRestaurant.getRating());
        assertNull(testRestaurant.getReview());
    }

    @Test
    void testConstructorInvalidRatings() {
        Restaurant testRestaurantA = new Restaurant(15);

        assertNull(testRestaurantA.getName());
        assertEquals(0, testRestaurantA.getRating());
        assertNull(testRestaurantA.getReview());

        Restaurant testRestaurantB = new Restaurant(-9);

        assertNull(testRestaurantB.getName());
        assertEquals(0, testRestaurantB.getRating());
        assertNull(testRestaurantB.getReview());
    }

    @Test
    void testAddName() {
        testRestaurant.addName("Pancake Land");
        assertEquals("Pancake Land", testRestaurant.getName());
    }

    @Test
    void testAddRating() {
        testRestaurant.addRating(1);
        assertEquals(1, testRestaurant.getRating());

        testRestaurant.addRating(10);
        assertEquals(10, testRestaurant.getRating());

        testRestaurant.addRating(5);
        assertEquals(5, testRestaurant.getRating());
    }

    @Test
    void testAddReview() {
        testRestaurant.addReview("I liked the syrup on the pancakes.");
        assertEquals("I liked the syrup on the pancakes.",
                testRestaurant.getReview());
    }
}
