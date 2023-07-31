package ui;

import java.io.FileNotFoundException;

// runs a new restaurant journal application
public class Main {
    public static void main(String[] args) {
        /* try {
            new RestJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } */
        new GUI();
    }
}
