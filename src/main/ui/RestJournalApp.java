package ui;

import model.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Citation: CPSC 210 UBC (2022), TellerApp, [source code], https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// Citation: Rafael del Nero (2019), Sorting with Comparable and Comparator in Java, [source code]
// https://www.infoworld.com/article/3323403/java-challengers-5-sorting-with-comparable-and-comparator-in-java.html


// Journal application
public class RestJournalApp {
    private Scanner input;
    private List<Restaurant> restaurants;
    private List<String> restaurantNames;

    // EFFECTS: runs the journal application
    public RestJournalApp() {
        runJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runJournal() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nClosing the journal, goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            enterRestaurant();
        } else if (command.equals("v")) {
            viewRestaurant();
        } else if (command.equals("t")) {
            sortRestaurants();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes journal
    private void init() {
        restaurants = new ArrayList<>();
        restaurantNames = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new restaurant entry");
        System.out.println("\tv -> view restaurant entry");
        System.out.println("\tt -> top rated restaurants");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new restaurant entry
    private void enterRestaurant() {
        Restaurant restaurant = new Restaurant(1);
        System.out.print("Enter restaurant name\n");
        String name = input.next();

        if (name.length() > 0) {
            restaurant.addName(name);
        } else {
            System.out.println("Cannot add name with no length...\n");
        }
        System.out.print("Enter restaurant rating: 1-10\n");
        int rating = input.nextInt();

        if ((rating > 0) && (rating < 11)) {
            restaurant.addRating(rating);
        } else {
            System.out.println("Rating not on scale of 1-10...\n");
        }
        System.out.print("Enter a short comment/review \n");
        String review = input.next();
        restaurant.addReview(review);

        restaurants.add(restaurant);
        restaurantNames.add(restaurant.getName());
        printName(restaurant);
        printRating(restaurant);
    }

    // EFFECTS: displays restaurant info to user
    private void viewRestaurant() {
        Restaurant selected = selectRestaurant();
        System.out.print("\nRestaurant name: " + selected.getName());
        System.out.print("\nRestaurant rating: " + selected.getRating() + " out of 10!");
        System.out.print("\nComments: " + selected.getReview() + "\n");
    }

    // modifies: this
    // EFFECTS: sorts and prints list of restaurants by rating in descending order
    private void sortRestaurants() {
        Collections.sort(restaurants);
        for (Restaurant r : restaurants) {
            System.out.print(r.getName() + "\n");
        }
    }

    // EFFECTS: prompts user to select a restaurant and returns it
    private Restaurant selectRestaurant() {
        System.out.print(restaurantNames + "\n");

        String finalSelection = "";

        if (restaurants.size() == 0) {
            System.out.print("No restaurants had been added to the journal!\n");
        } else {
            while (!(restaurantNames.contains(finalSelection))) {
                System.out.println("Please enter a restaurant name from the list\n");
                finalSelection = input.next();
            }
        }
        return lookup(finalSelection);
    }

    // returns restaurant with given restaurant name
    private Restaurant lookup(String finalSelection) {
        Restaurant restSelected = null;
        for (Restaurant r : restaurants) {
            if (r.getName().equals(finalSelection)) {
                restSelected = r;
                break;
            }
        }
        return restSelected;
    }

    // EFFECTS: prints name of new restaurant entry to the screen
    private void printName(Restaurant selected) {
        System.out.printf("'%s' %n", "Restaurant name: " + selected.getName());
    }

    // EFFECTS: prints rating of new restaurant entry to the screen
    private void printRating(Restaurant selected) {
        System.out.printf("'%s' %n", selected.getRating() + " out of 10!");
    }
}




