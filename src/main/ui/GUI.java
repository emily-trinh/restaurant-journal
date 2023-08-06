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
    private JLabel nameLabel;
    private JComboBox rating;
    private JTextArea reviewArea;

    private JPanel viewAllPanel;
    private JList restaurantsJList;

    private JPanel viewRankingPanel;
    private JList<Restaurant> sortedRestaurantsJList;

    private Restaurant currentRestaurant;
    private JFrame frame;
    private JTextField restaurantName;


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
        initializeAddPanel();
        initializeViewAllPanel();
        initializeViewRankingPanel();
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
        addButton = new JButton("Add restaurant to journal");
        viewAllButton = new JButton("View all restaurants");
        byRatingButton = new JButton("View restaurants by top rating");
        loadButton = new JButton("Load journal file");
        saveButton = new JButton("Save journal file");
        addButton.setMargin(new Insets(10, 10, 10, 10));
        viewAllButton.setMargin(new Insets(10, 10, 10, 10));
        byRatingButton.setMargin(new Insets(10, 10, 10, 10));
        saveButton.setMargin(new Insets(10, 10, 10, 10));
        loadButton.setMargin(new Insets(10, 10, 10, 10));
        toMainButton.setMargin(new Insets(10, 10, 10, 10));

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
        initializeGeneralPanel(addPanel);

        restaurantName = new JTextField(50);
        restaurantName.setMargin(new Insets(5, 10, 5, 10));

        nameLabel = new JLabel("Enter restaurant name: ");
        addPanel.add(nameLabel, BorderLayout.WEST);
        addPanel.add(restaurantName);

        Integer[] validRatings = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        rating = new JComboBox(validRatings);
        rating.setSelectedIndex(9);
        JLabel ratingLabel = new JLabel("Enter restaurant rating: ");
        addPanel.add(ratingLabel);
        addPanel.add(rating);

        createReviewArea();

        addPanel.add(confirmAdditionButton);
        addPanel.add(toMainButton);
    }

    private void createReviewArea() {
        reviewArea = new JTextArea();
        reviewArea.setColumns(20);
        reviewArea.setRows(5);
        reviewArea.setLineWrap(true);
        JLabel reviewLabel = new JLabel("Enter a comment/review :");
        reviewArea.setMargin(new Insets(5, 10, 5, 10));
        addPanel.add(reviewLabel);
        addPanel.add(reviewArea);
    }

    private void createAddPanelButtons() {
        confirmAdditionButton = new JButton("Add restaurant");
        confirmAdditionButton.setMargin(new Insets(10, 10, 10, 10));
        confirmAdditionButton.addActionListener(e -> addRestaurantToJournal());
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

        viewAllPanel.add(toMainButton);
    }

    private void initializeViewRankingPanel() {
        initializeGeneralPanel(viewRankingPanel);

        List<Restaurant> sortedRestaurants = journal.getRestaurants();
        journal.sortByRanking(sortedRestaurants);
        sortedRestaurantsJList = new JList<>(sortedRestaurants.toArray(new Restaurant[sortedRestaurants.size()]));
        viewRankingPanel.add(sortedRestaurantsJList);

        viewRankingPanel.add(toMainButton);
    }

    private void initializeGeneralPanel(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.pink);
        setVisible(false);
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

        toMainButton.addActionListener(this);
        toMainButton.setActionCommand("to main");
    }

    private void addRestaurantToJournal() {
        Restaurant newRestaurant = new Restaurant(rating.getSelectedIndex() + 1);
        newRestaurant.addName(restaurantName.getText());
        newRestaurant.addName(reviewArea.getText());
        journal.addRestaurant(newRestaurant);
    }

    private void goFrontPage() {
        mainPanel.setVisible(true);
        addPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(false);
    }

    private void switchToAddPanel() {
        mainPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(false);
        addPanel.setVisible(true);
    }

    private void switchToViewAllPanel() {
        mainPanel.setVisible(false);
        viewAllPanel.setVisible(true);
        viewRankingPanel.setVisible(false);
        addPanel.setVisible(false);
    }

    private void switchToViewByRatingPanel() {
        mainPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(true);
        addPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add restaurant")) {
            switchToAddPanel();
        }
        if (e.getActionCommand().equals("view all restaurants")) {
            switchToViewAllPanel();
        }
        if (e.getActionCommand().equals("view by rating")) {
            switchToViewByRatingPanel();
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
