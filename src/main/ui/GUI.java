package ui;

import model.Restaurant;
import model.Journal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/journal.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Journal journal;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 750;

    private JPanel mainPanel;
    private JButton addButton;
    private JButton viewAllButton;
    private JButton byRatingButton;
    private JButton saveButton;
    private JButton loadButton;

    private JPanel addPanel;
    private JButton confirmAdditionButton;
    private JButton toMainButton = new JButton("Back to front page");

    private JPanel viewAllPanel;
    private JList restaurantsJList;

    private JPanel viewRankingPanel;

    private Restaurant currentRestaurant;
    private JFrame frame;

    public GUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        journal = new Journal("My Restaurant Journal");
        mainPanel = new JPanel();
        addPanel = new JPanel();
        viewAllPanel = new JPanel();
        viewRankingPanel = new JPanel();


        frame = new JFrame("Journal");
        initializeFields();
        initializeFrame();
        createMainButtons();
        createAddPanelButtons();

        initializeMainPanel();
    }

    private void initializeFrame() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createMainButtons() {
        addButton = new JButton("Add restaurant");
        viewAllButton = new JButton("View all restaurants");
        byRatingButton = new JButton("View restaurants by top rating");
        loadButton = new JButton("Load journal file");
        saveButton = new JButton("Save journal file");
        addButton.setMargin(new Insets(10, 10, 10, 10));
        viewAllButton.setMargin(new Insets(10, 10, 10, 10));
        byRatingButton.setMargin(new Insets(10, 10, 10, 10));
        saveButton.setMargin(new Insets(10, 10, 10, 10));
        loadButton.setMargin(new Insets(10, 10, 10, 10));
    }

    private void initializeMainPanel() {
        initializeGeneralPanel(mainPanel);
        mainPanel.setVisible(true);
        mainPanel.add(addButton);
        mainPanel.add(viewAllButton);
        mainPanel.add(byRatingButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        addActions();
    }

    private void initializeAddPanel() {
        mainPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(false);
        initializeGeneralPanel(addPanel);

        JTextField restaurantName = new JTextField(50);
        restaurantName.setMargin(new Insets(5, 10, 5, 10));

        JLabel nameLabel = new JLabel("Enter restaurant name: ");
        addPanel.add(nameLabel, BorderLayout.WEST);
        addPanel.add(restaurantName);

        Integer[] validRatings = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        JComboBox rating = new JComboBox(validRatings);
        rating.setSelectedIndex(9);
        JLabel ratingLabel = new JLabel("Enter restaurant rating: ");
        addPanel.add(ratingLabel);
        addPanel.add(rating);

        createReviewArea();

        createAddPanelButtons();
        addPanel.add(confirmAdditionButton);
        addPanel.add(toMainButton);
    }

    private void createReviewArea() {
        JTextArea reviewArea = new JTextArea();
        reviewArea.setColumns(20);
        reviewArea.setRows(5);
        reviewArea.setLineWrap(true);
        JLabel reviewLabel = new JLabel("Enter a comment/review :");
        reviewArea.setMargin(new Insets(5, 10, 5, 10));
        addPanel.add(reviewLabel);
        addPanel.add(reviewArea);
    }

    private void createAddPanelButtons() {
        confirmAdditionButton = new JButton("Add restaurant to journal");
        confirmAdditionButton.setMargin(new Insets(10, 10, 10, 10));
        toMainButton.setMargin(new Insets(10, 10, 10, 10));
    }

    private void initializeViewAllPanel() {
        mainPanel.setVisible(false);
        addPanel.setVisible(false);
        viewAllPanel.setVisible(true);
        viewRankingPanel.setVisible(false);
        initializeGeneralPanel(viewAllPanel);

        List<Restaurant> restaurants = journal.getRestaurants();
        restaurantsJList = new JList<>(restaurants.toArray(new Restaurant[restaurants.size()]));
        viewAllPanel.add(restaurantsJList);
    }

    private void initializeViewRankingPanel() {
        mainPanel.setVisible(false);
        addPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(true);
        initializeGeneralPanel(viewRankingPanel);
    }

    private void initializeGeneralPanel(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.pink);
    }

    private void initializeFields() {
        currentRestaurant = null;
    }

    private void addActions() {
        addButton.addActionListener(this);
        addButton.setActionCommand("add restaurant");

        viewAllButton.addActionListener(this);
        viewAllButton.setActionCommand("view all restaurants");

        byRatingButton.addActionListener(this);
        byRatingButton.setActionCommand("view by rating");

        saveButton.addActionListener(this);
        saveButton.setActionCommand("save");

        loadButton.addActionListener(this);
        loadButton.setActionCommand("load");

        confirmAdditionButton.addActionListener(this);
        confirmAdditionButton.setActionCommand("confirm");

        toMainButton.addActionListener(this);
        toMainButton.setActionCommand("to main");
    }

    private void goFrontPage() {
        mainPanel.setVisible(true);
        addPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(false);
    }

    private void addRestaurantToJournal() {
        journal.addRestaurant(new Restaurant(10));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add restaurant")) {
            initializeAddPanel();
        }
        if (e.getActionCommand().equals("view all restaurants")) {
            initializeViewAllPanel();
        }
        if (e.getActionCommand().equals("view by rating")) {
            initializeViewRankingPanel();
        }
        if (e.getActionCommand().equals("save")) {
            saveJournal();
        }
        if (e.getActionCommand().equals("load")) {
            loadJournal();
        }
        if (e.getActionCommand().equals("confirm")) {
            addRestaurantToJournal();
        }
        if (e.getActionCommand().equals("to main")) {
            goFrontPage();
        }
    }

    public void saveJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(journal);
            jsonWriter.close();
            System.out.println("Saved " + journal.getJournalName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads journal from file
    private void loadJournal() {
        try {
            journal = jsonReader.read();
            System.out.println("Loaded " + journal.getJournalName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
