package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant testRestaurant;


    @BeforeEach
    void runBefore() {
        testRestaurant = new Restaurant(5);
    }

    @Test
    void testRestaurantConstructor() {
        assertNull(testRestaurant.getName());
        assertEquals(5, testRestaurant.getRating());
        assertNull(testRestaurant.getReview());
    }

    @Test
    void testConstructorInvalidRatings() {
        Restaurant testRestaurantA = new Restaurant(15);
        Restaurant testRestaurantB = new Restaurant(-9);

        assertNull(testRestaurantA.getName());
        assertEquals(1, testRestaurantA.getRating());
        assertNull(testRestaurantA.getReview());


        assertNull(testRestaurantB.getName());
        assertEquals(1, testRestaurantB.getRating());
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

    @Test
    void testCompareTo() {
        Restaurant testRestaurantC = new Restaurant(10);
        Restaurant testRestaurantD = new Restaurant(10);

        assertEquals(-1, testRestaurant.compareTo(testRestaurantC));
        assertEquals(1, testRestaurantC.compareTo(testRestaurant));
        assertEquals(0, testRestaurantC.compareTo(testRestaurantD));
    }

    @Test
    void testToString() {
        Restaurant testRestaurant = new Restaurant(10);

        assertEquals(null, testRestaurant.toString());

        testRestaurant.addName("Pancake Land");
        assertEquals("Pancake Land", testRestaurant.toString());

        testRestaurant.addReview("Yummy in my tummy");
        assertEquals("Pancake Land", testRestaurant.toString());

    }
}
