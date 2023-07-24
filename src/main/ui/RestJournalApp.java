package ui;

import model.Restaurant;
import model.Journal;


import java.util.Scanner;

// Citation: CPSC 210 UBC (2022), TellerApp, [source code], https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// Citation: Rafael del Nero (2019), Sorting with Comparable and Comparator in Java, [source code]
// https://www.infoworld.com/article/3323403/java-challengers-5-sorting-with-comparable-and-comparator-in-java.html


// Journal application
public class RestJournalApp {
    private Scanner input;
    private Journal journal;

    // EFFECTS: runs the journal application
    public RestJournalApp() {
        runJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runJournal() {
        boolean keepGoing = true;
        String command;

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
        } else if (command.equals("c")) {
            editRestaurant();
        } else if (command.equals("t")) {
            printSortedRestaurants();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes journal
    private void init() {
        journal = new Journal();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new restaurant entry");
        System.out.println("\tv -> view restaurant entry");
        System.out.println("\tc -> change restaurant rating and review");
        System.out.println("\tt -> top rated restaurants");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new restaurant entry
    private void enterRestaurant() {
        Restaurant restaurant = new Restaurant(0);
        String name = "";

        while ((name.length() == 0) | (journal.getRestaurantNames().contains(name)))  {
            System.out.println("Enter new restaurant name");
            name = input.next();
        }
        restaurant.addName(name);

        int rating = 0;

        restaurant.addRating(checkValidRating(rating));

        String comment = "";

        while (comment.length() == 0) {
            System.out.println("Enter a short comment/review");
            comment = input.next();
        }
        restaurant.addReview(comment);

        journal.addRestaurant(restaurant);
        printName(restaurant);
        printRating(restaurant);
        System.out.println(restaurant.getReview());
    }

    // EFFECTS: ensures the user's input is an integer within the range of [1, 10]
    //          returns the rating given by the user
    private int checkValidRating(int rating) {
        while (((rating <= 0) | (rating >= 11))) {
            System.out.println("Enter restaurant rating: 1-10");
            input.nextLine();
            while (!input.hasNextInt()) {
                System.out.println("Please enter a number from 1-10");
                input.nextLine();
            }
            rating = input.nextInt();
        }
        return rating;
    }


    // EFFECTS: displays restaurant info to user
    private void viewRestaurant() {
        Restaurant selected = selectRestaurant();
        if (selected != null) {
            System.out.print("\nRestaurant name: " + selected.getName());
            System.out.print("\nRestaurant rating: " + selected.getRating() + " out of 10!");
            System.out.print("\nComments: " + selected.getReview() + "\n");
        } else {
            System.out.println("No restaurants have been added to the journal!");
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts and prints list of restaurants by rating in descending order
    private void printSortedRestaurants() {
        if (journal.getRestaurants().size() == 0) {
            System.out.println("No restaurants have been added to the journal!");
        } else {
            journal.sortByRanking();
            for (Restaurant r : journal.getRestaurants()) {
                System.out.print(r.getName() + " - Rating: " + r.getRating() + "\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new rating and comment given by user, replacing previous rating and comment for restaurant
    private void editRestaurant() {
        Restaurant selected = selectRestaurant();
        if (selected != null) {
            int rating = 0;
            while (((rating <= 0) | (rating >= 11))) {
                System.out.print("Enter new restaurant rating: 1-10\n");
                rating = input.nextInt();

            }
            selected.addRating(rating);
            System.out.print("New rating added!\n");

            String comment = "";

            while (comment.length() == 0) {
                System.out.print("Enter new restaurant review\n");
                comment = input.next();
            }
            selected.addReview(comment);
            System.out.print("New comment added!\n");
        } else {
            System.out.println("No restaurants have been added to the journal!");
        }
    }

    // EFFECTS: prompts user to select a restaurant and returns it
    private Restaurant selectRestaurant() {

        String finalSelection = "";

        if (journal.getRestaurants().size() == 0) {
            return null;
        } else {
            while (!(journal.getRestaurantNames().contains(finalSelection))) {
                System.out.print(journal.getRestaurantNames() + "\n");
                System.out.println("Please enter a restaurant name from the list");
                finalSelection = input.next();
            }
        }
        return lookup(finalSelection);
    }


    // EFFECTS: returns restaurant with given restaurant name
    private Restaurant lookup(String finalSelection) {
        Restaurant restSelected = null;

        for (Restaurant r : journal.getRestaurants()) {
            if (r.getName().equals(finalSelection)) {
                restSelected = r;
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




