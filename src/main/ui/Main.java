package ui;

import java.io.IOException;

// runs a new restaurant journal GUI
public class Main {
    public static void main(String[] args) {
        try {
            new GUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
