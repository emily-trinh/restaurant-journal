package ui;

import model.Restaurant;
import model.Journal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents the GUI for this program
public class GUI extends JFrame implements ActionListener {

    private final JFrame frame;
    private final JPanel cardPanel;
    private static final String JSON_STORE = "./data/journal.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private Journal journal;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 550;

    private final JPanel mainPanel;
    private JButton addButton;
    private JButton viewAllButton;
    private JButton byRatingButton;
    private JButton saveButton;
    private JButton loadButton;

    private final JPanel addPanel;
    private JButton confirmAdditionButton;
    private JComboBox rating;
    private JTextArea reviewArea;
    private JTextField restaurantName;
    private JButton goToMainButton;

    private final JPanel viewAllPanel;
    private List<Restaurant> restaurants;
    private DefaultListModel dmViewAll;

    private final JPanel viewRankingPanel;

    private DefaultListModel dm;

    // EFFECTS: Creates and shows GUI
    public GUI() throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        journal = new Journal("My Restaurant Journal");
        mainPanel = new JPanel();
        addPanel = new JPanel();
        viewAllPanel = new JPanel();
        viewRankingPanel = new JPanel();
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("My Restaurant Journal");
        initializeFrame();
        initializePanels();
        addActions();

        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes frame in which panels are stored
    private void initializeFrame() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    // MODIFIES: frame, cardPanel, this
    // EFFECTS: initializes all panels and adds them to card panel
    public void initializePanels() throws IOException {
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

    // EFFECTS: initializes and customizes all buttons for main panel
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

    // MODIFIES: this
    // EFFECTS: initializes main panel, adding all buttons and labels
    private void initializeMainPanel() throws IOException {
        initializeGeneralPanel(mainPanel);

        JLabel mainMenuLabel = new JLabel("My Restaurant Journal");
        mainMenuLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        mainPanel.add(mainMenuLabel);

        createMainButtons();
        mainPanel.add(addButton);
        mainPanel.add(viewAllButton);
        mainPanel.add(byRatingButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);

        BufferedImage mainMenuImage = ImageIO.read(new File("resources root/main menu image.jpg"));
        JLabel mainImageLabel = new JLabel(new ImageIcon(mainMenuImage));
        mainPanel.add(mainImageLabel);

        mainPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes panel where user can add restaurants to journal
    private void initializeAddPanel() {
        initializeGeneralPanel(addPanel);

        restaurantName = new JTextField(50);
        restaurantName.setMargin(new Insets(5, 10, 5, 10));

        JLabel nameLabel = new JLabel("Enter restaurant name: ");
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
        addPanel.add(goToMainButton);
        addPanel.setVisible(true);
    }

    // MODIFIES: addPanel
    // EFFECTS: constructs and adds review area (text area) for addPanel panel
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

    // MODIFIES: addPanel
    // EFFECTS: initializes and customizes buttons for addPanel panel
    private void createAddPanelButtons() {
        confirmAdditionButton = new JButton("Add restaurant");
        confirmAdditionButton.setMargin(new Insets(10, 10, 10, 10));
        confirmAdditionButton.addActionListener(e -> addRestaurantToJournal());

        goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.setActionCommand("to main");
        goToMainButton.addActionListener(e -> ((CardLayout) cardPanel.getLayout()).show(cardPanel, "main"));
    }

    // MODIFIES: restaurantsJList, dmViewAll, this
    // EFFECTS: initializes panel where user can view all restaurants added to journal in order of when they
    //          were added
    private void initializeViewAllPanel() {
        initializeGeneralPanel(viewAllPanel);

        restaurants = journal.getRestaurants();
        Restaurant[] restaurantsConvertedList = restaurants.toArray(new Restaurant[restaurants.size()]);
        dmViewAll = new DefaultListModel<>();
        JList restaurantsJList = new JList<>(dmViewAll);
        restaurantsJList.setModel(dmViewAll);
        dmViewAll.addAll(List.of(restaurantsConvertedList));
        viewAllPanel.add(restaurantsJList);

        JButton goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.setActionCommand("to main");
        goToMainButton.addActionListener(e -> ((CardLayout) cardPanel.getLayout()).show(cardPanel, "main"));
        viewAllPanel.add(goToMainButton);
        viewAllPanel.setVisible(true);
    }

    // MODIFIES: dm, sortedRestaurantsJList, this
    // EFFECTS: initializes panel where user can view all restaurants added
    //          to journal in order from the highest ranking to lowest
    private void initializeViewRankingPanel() {
        initializeGeneralPanel(viewRankingPanel);

        List<Restaurant> sortedRestaurants = new ArrayList<>(journal.getRestaurants());
        journal.sortByRanking(sortedRestaurants);
        Restaurant[] finalSortedRestaurants = sortedRestaurants.toArray(new Restaurant[sortedRestaurants.size()]);
        dm = new DefaultListModel<>();
        JList<Restaurant> sortedRestaurantsJList = new JList<>(dm);
        sortedRestaurantsJList.setModel(dm);
        dm.addAll(List.of(finalSortedRestaurants));

        viewRankingPanel.add(sortedRestaurantsJList);
        JButton goToMainButton = new JButton("Back to front page");
        goToMainButton.setMargin(new Insets(10, 10, 10, 10));
        goToMainButton.setActionCommand("to main");
        goToMainButton.addActionListener(e -> ((CardLayout) cardPanel.getLayout()).show(cardPanel, "main"));
        viewRankingPanel.add(goToMainButton);
        viewRankingPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: helper method to customize a general panel
    private void initializeGeneralPanel(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.pink);
    }

    // MODIFIES: addButton, viewAllButton, byRatingButton, saveButton, loadButton
    // EFFECTS: adds action listeners and set action commands to main panel buttons
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

    // MODIFIES: this, dmViewAll, dm
    // EFFECTS: gets user input and creates a new restaurant that is added to relevant lists in journal
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

    // MODIFIES: restaurantName, rating, reviewArea
    // EFFECTS: resets the input fields in addPanel
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

    // MODIFIES: this
    // EFFECTS: saves journal to file
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
