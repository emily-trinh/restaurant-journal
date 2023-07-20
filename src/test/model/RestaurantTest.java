package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    private Restaurant testRestaurant;

    @BeforeEach
    void runBefore() {
        testRestaurant = new Restaurant();
    }
    @Test
    void testRestaurantConstructor() {
        assertNull(testRestaurant.getName());
        assertNull(testRestaurant.getRating());
        assertNull(testRestaurant.getReview());
    }

    @Test
    void testAddName() {
        testRestaurant.addName("Pancake Land");
        assertEquals("Pancake Land", testRestaurant.getName());
    }



}
