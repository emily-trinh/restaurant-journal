package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

// runs a new restaurant journal application
public class Main {
    public static void main(String[] args) {
        /* try {
            new RestJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } */
        try {
            new GUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
