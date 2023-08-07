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
import java.util.Collections;
import java.util.List;


public class GUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/journal.json";
    private final JPanel cardPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Journal journal;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 750;

    private final JPanel mainPanel;
    private JButton addButton;
    private JButton viewAllButton;
    private JButton byRatingButton;
    private JButton saveButton;
    private JButton loadButton;

    private JPanel addPanel;
    private JButton confirmAdditionButton;
    private JLabel nameLabel;
    private JComboBox rating;
    private JTextArea reviewArea;

    private JPanel viewAllPanel;
    private JList restaurantsJList;

    private JPanel viewRankingPanel;
    private JList<Restaurant> sortedRestaurantsJList;

    private JFrame frame;
    private JTextField restaurantName;
    private Restaurant[] finalSortedRestaurants;
    private DefaultListModel dm;
    private List<Restaurant> sortedRestaurants;
    private List<Restaurant> restaurants;
    private DefaultListModel dmViewAll;


    public GUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        journal = new Journal("My Restaurant Journal");
        mainPanel = new JPanel();
        addPanel = new JPanel();
        viewAllPanel = new JPanel();
        viewRankingPanel = new JPanel();
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Journal");
        initializeFrame();
        initializePanels();
        addActions();
        goFrontPage();

        frame.pack();
        frame.setVisible(true);
    }

    public void initializePanels() {
        initializeMainPanel();
        initializeAddPanel();
        initializeViewAllPanel();
        initializeViewRankingPanel();

        cardPanel.add(mainPanel, "main");
        cardPanel.add(addPanel, "add");
        cardPanel.add(viewAllPanel, "all");
        cardPanel.add(viewRankingPanel, "ranking");
        frame.add(cardPanel, BorderLayout.CENTER);
    }

    private void initializeFrame() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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
    }

    private void initializeMainPanel() {
        initializeGeneralPanel(mainPanel);
        createMainButtons();
        mainPanel.add(addButton);
        mainPanel.add(viewAllButton);
        mainPanel.add(byRatingButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.setVisible(true);
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
        createAddPanelButtons();

        addPanel.add(confirmAdditionButton);
        addPanel.setVisible(true);
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

        JButton goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.addActionListener(e -> goFrontPage());
        addPanel.add(goToMainButton);
    }

    private void initializeViewAllPanel() {
        initializeGeneralPanel(viewAllPanel);

        restaurants = journal.getRestaurants();
        Restaurant[] restaurantsConvertedList = restaurants.toArray(new Restaurant[restaurants.size()]);
        dmViewAll = new DefaultListModel<>();
        restaurantsJList = new JList<>(dmViewAll);
        restaurantsJList.setModel(dmViewAll);
        dmViewAll.addAll(List.of(restaurantsConvertedList));
        viewAllPanel.add(restaurantsJList);

        JButton goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.addActionListener(e -> goFrontPage());
        viewAllPanel.add(goToMainButton);
        viewAllPanel.setVisible(true);
    }

    private void initializeViewRankingPanel() {
        initializeGeneralPanel(viewRankingPanel);

        sortedRestaurants = new ArrayList<>(journal.getRestaurants());
        journal.sortByRanking(sortedRestaurants);
        finalSortedRestaurants = sortedRestaurants.toArray(new Restaurant[sortedRestaurants.size()]);
        dm = new DefaultListModel<>();
        sortedRestaurantsJList = new JList<>(dm);
        sortedRestaurantsJList.setModel(dm);
        dm.addAll(List.of(finalSortedRestaurants));

        viewRankingPanel.add(sortedRestaurantsJList);
        JButton goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.addActionListener(e -> goFrontPage());
        viewRankingPanel.add(goToMainButton);
        viewRankingPanel.setVisible(true);
    }

    private void initializeGeneralPanel(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.pink);
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
    }

    private void addRestaurantToJournal() {
        Restaurant newRestaurant = new Restaurant(rating.getSelectedIndex() + 1);
        newRestaurant.addName(restaurantName.getText());
        newRestaurant.addReview(reviewArea.getText());
        journal.addRestaurant(newRestaurant);

        dmViewAll.removeAllElements();
        dmViewAll.addAll(journal.getRestaurants());

        List<Restaurant> helperList = new ArrayList<>(journal.getRestaurants());
        journal.sortByRanking(helperList);
        dm.removeAllElements();
        dm.addAll(helperList);
    }

    private void goFrontPage() {
        mainPanel.setVisible(true);
        addPanel.setVisible(false);
        viewAllPanel.setVisible(false);
        viewRankingPanel.setVisible(false);
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

    private void switchToAddPanel() {
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "add");
        restaurantName.setText("");
        rating.setSelectedIndex(9);
        reviewArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add restaurant")) {
            switchToAddPanel();
        }
        if (e.getActionCommand().equals("view all restaurants")) {
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "all");
        }
        if (e.getActionCommand().equals("view by rating")) {
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "ranking");
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
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "main");
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
            restaurants.addAll(journal.getRestaurants());
            dmViewAll.addAll(journal.getRestaurants());
            List<Restaurant> tempRestaurantList = new ArrayList<>(journal.getRestaurants());
            journal.sortByRanking(tempRestaurantList);
            dm.addAll(tempRestaurantList);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
