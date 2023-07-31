package ui;

import model.Restaurant;
import model.Journal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    public static final int WIDTH = 1150;
    public static final int HEIGHT = 750;


    private Restaurant currentRestaurant;

    public GUI() {
        super("My Journal");
        initializeFields();
        initializeGraphics();

        JButton addRestaurantButton = new JButton("Add new restaurant entry");
        addRestaurantButton.setActionCommand("add restaurant");
        add(addRestaurantButton, BorderLayout.SOUTH);

        JButton viewRestaurantsButton = new JButton("View all restaurant entries");
        viewRestaurantsButton.setActionCommand("view restaurants");
        add(viewRestaurantsButton, BorderLayout.BEFORE_LINE_BEGINS);
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeFields() {
        currentRestaurant = null;
    }

}
