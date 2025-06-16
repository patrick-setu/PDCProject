package com.mycompany.autacademicadvisor;

//Import libraries
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class VirtualAcademicAdvisorGUI extends JFrame {
    
    // Initialize private variables
    private Map<String, BaseCourseRecommender> recommenders;
    private JPanel cards;
    private CardLayout cardLayout;
    private JLabel statusLabel;
    private String loggedInUser = null;
    private JTextArea viewRecommendationTextArea;

    // Purple color scheme
    private static final Color PURPLE_BG = new Color(108, 59, 122);       // AUT purple multipurpose
    private static final Color HOVER_GRAY = new Color(220, 220, 220);     // Light gray for hover
    private static final Color BLACK_TEXT = Color.BLACK;                  // Black text
    private static final Color WHITE_TEXT = Color.WHITE;                  // White text
    private static final Color CARD_BG = new Color(245, 245, 245);        // Light gray for card panels

    // Create constructor
    public VirtualAcademicAdvisorGUI() {
        super("Virtual Academic Advisor");
        setUIFont(new Font("Segoe UI", Font.PLAIN, 14));
        initializeRecommenders();
        setupUI();
        establishDatabaseConnection();
    }

    // Establish a connection to existing DB or create new
    private void establishDatabaseConnection() {
        try {
            String URL = "jdbc:derby:aut_recommendDB;create=true";
            Connection conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Connected to the embedded database successfully.");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Database authentication methods
    private boolean authenticateWithDatabase(String username, String password) throws SQLException {

        // Create sql query
        String sql = "SELECT password FROM users WHERE username = ?";

        // Try connect and run query; otherwise error
        try (Connection conn = DriverManager.getConnection(
                "jdbc:derby:aut_recommendDB;create=true"
        ); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Substitute placeholder
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // Check password with database password
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return password.equals(dbPassword);
            }
            return false;
        }
    }

    // Initialize database
    private void initializeDatabase() {
        // Create sql query
        String createTableSQL = """
        CREATE TABLE users (
            username VARCHAR(50) PRIMARY KEY,
            password VARCHAR(100),
            recommendation VARCHAR(500)
        )
    """;

        // Try connect and create table; otherwise error
        try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommendDB;create=true")) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createTableSQL);
            } catch (SQLException e) {
                // Ignore error if table already exists
                if (e.getSQLState().equals("X0Y32")) {
                } else {
                    throw e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Database initialization failed: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Set up font of components
    private void setUIFont(Font f) {
        UIManager.put("Label.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("Button.font", f);
    }

    // Initialize recommenders method
    private void initializeRecommenders() {
        recommenders = new HashMap<>();
        recommenders.put("Computer and Information Sciences", new ComputerScienceRecommender());
        recommenders.put("Engineering (Honours)", new EngineeringRecommender());
        recommenders.put("Business", new BusinessCourseRecommender());
        recommenders.put("Health Science", new HealthScienceRecommender());
        recommenders.put("Design", new DesignRecommender());
        recommenders.put("Communication Studies", new CommunicationStudiesRecommender());
    }

    // Load logo method
    private ImageIcon loadLogo() {
        try {
            URL imgURL = getClass().getResource("/autlogo.png");
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image scaledImage = originalIcon.getImage().getScaledInstance(115, 80, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Set up UI method
    private void setupUI() {
        // Customize frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setMinimumSize(new Dimension(900, 650));
        setLocationRelativeTo(null);
        getContentPane().setBackground(PURPLE_BG);

        JLabel logoLabel = new JLabel(loadLogo());

        // Header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(PURPLE_BG);
        topPanel.add(logoLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Virtual Academic Advisor", JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(WHITE_TEXT);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Footer
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(PURPLE_BG);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(WHITE_TEXT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        bottomPanel.add(statusLabel, BorderLayout.WEST);

        JLabel creditsLabel = new JLabel("Grady and Patrick");
        creditsLabel.setForeground(WHITE_TEXT);
        creditsLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        creditsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        bottomPanel.add(creditsLabel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Use card layout for program panels
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(CARD_BG);
        cards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cards.add(createSignUpPanel(), "SignUp");
        cards.add(createLoginPanel(), "Login");
        cards.add(createMainMenuPanel(), "MainMenu");
        cards.add(createViewRecommendationPanel(), "ViewRecommendation");
        cards.add(createCourseRecommendationPanel(), "CourseRecommendations");
        cards.add(createPrerequisitePanel(), "Prerequisites");
        cards.add(createCreditSummaryPanel(), "Credits");
        cards.add(createStudyTipsPanel(), "StudyTips");

        add(cards);
        // Go to first page
        cardLayout.show(cards, "SignUp");
    }

    // Hold all menu buttons
    private java.util.List<JButton> menuButtons = new java.util.ArrayList<>();

    // Enable all buttons method
    private void setButtonsEnabled(boolean enabled) {
        for (JButton btn : menuButtons) {
            btn.setEnabled(enabled);
            btn.setCursor(Cursor.getPredefinedCursor(enabled ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        }
    }

    // Button clicked method
    private void handleButtonClick(JButton button, String message, String cardName) {
        statusLabel.setText(message);

        // Disable all buttons
        setButtonsEnabled(false);

        // Simulate 0.25s transition
        Timer timer = new Timer(250, e -> {
            cardLayout.show(cards, cardName);
            statusLabel.setText(" ");
            setButtonsEnabled(true);
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    // Signup panel
    private JPanel createSignUpPanel() {
        JPanel panel = createStyledPanel("Sign Up");
        panel.setBackground(CARD_BG);
        // Create border for signup panel
        panel.setBorder(BorderFactory.createLineBorder(PURPLE_BG, 2));

        // Create panel to hold components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(CARD_BG);

        centerPanel.add(Box.createVerticalGlue());

        // Set variable parameters for the components
        int fieldHeight = 43;
        Dimension fieldSize = new Dimension(250, fieldHeight);

        // Username
        JTextField usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setPreferredSize(fieldSize);
        usernameField.setMaximumSize(fieldSize);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(usernameField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Confirm password
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        confirmPasswordField.setPreferredSize(fieldSize);
        confirmPasswordField.setMaximumSize(fieldSize);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(confirmPasswordField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create sign up button and add to panel
        JButton signUpButton = createPurpleButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(signUpButton);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Button if user already has account and add to panel
        JButton goLoginButton = createPurpleButton("Already have an account? Login");
        goLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        goLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(goLoginButton);

        centerPanel.add(Box.createVerticalGlue());

        // Create spacing using panel and add component panel
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(CARD_BG);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        paddedPanel.add(centerPanel, BorderLayout.CENTER);

        // Sign up action listener
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Empty fields logic
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mismatching passwords logic
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(panel, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create user if does not exist; otherwise error
            try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommendDB;create=true")) {
                String checkSql = "SELECT username FROM users WHERE username = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, username);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(panel, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Creaete sql query
                String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";

                // Upload to aut_recommendDB
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, password);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(panel, "Account created! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(cards, "Login");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Existing login button action listener
        goLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

        // Add panel with spacing holding components to the whole panel
        panel.add(paddedPanel, BorderLayout.CENTER);

        return panel;
    }
    
    // Login panel
    private JPanel createLoginPanel() {
        JPanel panel = createStyledPanel("Login");
        panel.setBackground(CARD_BG);
        // Add border to login panel
        panel.setBorder(BorderFactory.createLineBorder(PURPLE_BG, 2));

        // Create center panel to hold components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(CARD_BG);

        centerPanel.add(Box.createVerticalGlue());

        // Set variable parameters for components
        int fieldHeight = 43;
        Dimension fieldSize = new Dimension(250, fieldHeight);

        // Username
        JTextField usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setPreferredSize(fieldSize);
        usernameField.setMaximumSize(fieldSize);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(usernameField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create login button and add to panel
        JButton loginButton = createPurpleButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(loginButton);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Create signup button and add to panel
        JButton signUpButton = createPurpleButton("Create Account");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(signUpButton);

        // Signup button action listener
        signUpButton.addActionListener(e -> cardLayout.show(cards, "SignUp"));

        centerPanel.add(Box.createVerticalGlue());

        // Create spacing using panel and add component panel
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(CARD_BG);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        paddedPanel.add(centerPanel, BorderLayout.CENTER);

        // Login button action listener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            // Change to menu panel if valid; error if not
            try {
                if (authenticateWithDatabase(username, password)) {
                    loggedInUser = username;
                    cardLayout.show(cards, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(panel,
                            "Invalid username or password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Database error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add spacer panel to whole panel
        panel.add(paddedPanel, BorderLayout.CENTER);

        return panel;
    }


    // Menu panel
    private JPanel createMainMenuPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 15, 15));
        buttonPanel.setBackground(CARD_BG);

        //Create menu buttons
        JButton courseRecButton = createPurpleButton("Bachelor's Degree Recommendations");
        JButton viewRecButton = createPurpleButton("View Your Recommendation");
        JButton prereqButton = createPurpleButton("Check Prerequisites");
        JButton creditButton = createPurpleButton("Credit Summary");
        JButton tipsButton = createPurpleButton("General Study Tips");
        JButton exitButton = createPurpleButton("Exit");

        // Collect menu buttons for transition disabling
        menuButtons.add(courseRecButton);
        menuButtons.add(viewRecButton);
        menuButtons.add(prereqButton);
        menuButtons.add(creditButton);
        menuButtons.add(tipsButton);

        // Add action listeners to all menu buttons
        courseRecButton.addActionListener(e
                -> handleButtonClick(courseRecButton, "Loading Bachelor's Degree Recommendations...", "CourseRecommendations"));
        
        viewRecButton.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommendDB;create=true")) {
                String sql = "SELECT recommendation FROM users WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, loggedInUser);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            String recommendation = rs.getString("recommendation");
                            if (recommendation != null && !recommendation.trim().isEmpty()) {
                                viewRecommendationTextArea.setText(recommendation);
                                handleButtonClick(viewRecButton, "Loading Your Recommendation...", "ViewRecommendation");
                            } else {
                                JOptionPane.showMessageDialog(buttonPanel,
                                        "No saved recommendation found for your account.",
                                        "No Recommendation",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(buttonPanel,
                                    "User record not found.",
                                    "User Not Found",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(buttonPanel,
                        "Database error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        prereqButton.addActionListener(e
                -> handleButtonClick(prereqButton, "Loading Prerequisite Checker...", "Prerequisites"));

        creditButton.addActionListener(e
                -> handleButtonClick(creditButton, "Loading Credit Summary...", "Credits"));

        tipsButton.addActionListener(e
                -> handleButtonClick(tipsButton, "Loading Study Tips...", "StudyTips"));
        
        exitButton.addActionListener(e -> {
            statusLabel.setText("Exiting application...");
            Timer timer = new Timer(1000, evt -> System.exit(0));
            timer.setRepeats(false);
            timer.start();
        });

        // Create actual menu panel
        JPanel panel = createStyledPanel("");

        buttonPanel.add(courseRecButton);
        buttonPanel.add(viewRecButton);
        buttonPanel.add(prereqButton);
        buttonPanel.add(creditButton);
        buttonPanel.add(tipsButton);
        buttonPanel.add(exitButton);

        JPanel paddedButtonPanel = new JPanel(new BorderLayout());
        paddedButtonPanel.setBackground(CARD_BG);
        paddedButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        paddedButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        panel.add(paddedButtonPanel, BorderLayout.CENTER);
        return panel;
    }

    // Create purple button and control mouse interactions
    private JButton createPurpleButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(WHITE_TEXT);
        button.setBackground(PURPLE_BG);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PURPLE_BG, 2),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(HOVER_GRAY);
                button.setForeground(PURPLE_BG);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(PURPLE_BG);
                button.setForeground(WHITE_TEXT);
            }
        });
        return button;
    }

    // Generalize panel creation method
    private JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(CARD_BG);

        if (title != null) {
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(CARD_BG);

            JLabel titleLabel = new JLabel(title, JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(PURPLE_BG);

            JSeparator separator = new JSeparator();
            separator.setForeground(PURPLE_BG);

            header.add(titleLabel, BorderLayout.CENTER);
            // Do not add separator for menu
            if (!title.equals("") && !title.equals("Login") && !title.equals("Sign Up")) {
                header.add(separator, BorderLayout.SOUTH);
            }
            panel.add(header, BorderLayout.NORTH);
        }

        return panel;
    }

    // Course recommendation panel
    private JPanel createCourseRecommendationPanel() {
        JPanel panel = createStyledPanel("Bachelor's Degree Recommendations");

        // Grid bag layout panel to hold components
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Degree label and combobox
        JLabel degreeLabel = new JLabel("Select Degree:");
        degreeLabel.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(degreeLabel, gbc);

        String[] degrees = recommenders.keySet().toArray(new String[0]);
        JComboBox<String> degreeCombo = new JComboBox<>(degrees);
        degreeCombo.setBackground(Color.WHITE);
        degreeCombo.setForeground(Color.BLACK);
        gbc.gridx = 1;
        formPanel.add(degreeCombo, gbc);

        // Major label and combobox
        JLabel majorLabel = new JLabel("Select Major:");
        majorLabel.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(majorLabel, gbc);

        JComboBox<String> majorCombo = new JComboBox<>();
        majorCombo.setBackground(Color.WHITE);
        majorCombo.setForeground(BLACK_TEXT);
        gbc.gridx = 1;
        formPanel.add(majorCombo, gbc);

        // Year label and combobox
        JLabel yearLabel = new JLabel("Select Year:");
        yearLabel.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(yearLabel, gbc);

        JComboBox<String> yearCombo = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        yearCombo.setBackground(Color.WHITE);
        yearCombo.setForeground(BLACK_TEXT);
        gbc.gridx = 1;
        formPanel.add(yearCombo, gbc);

        // Text area display for recommendations
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        resultsArea.setBackground(Color.WHITE);
        resultsArea.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(new JScrollPane(resultsArea), gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(CARD_BG);

        JButton recommendButton = createPurpleButton("Get Recommendations");
        JButton backButton = createPurpleButton("Back to Main Menu");
        
        // Back button action listener
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        
        // Add buttons to button panel
        buttonPanel.add(recommendButton);
        buttonPanel.add(backButton);
        
        // Recommend button action listener
        recommendButton.addActionListener(e -> {
            String degree = (String) degreeCombo.getSelectedItem();
            String major = (String) majorCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();
            if (major == null || major.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a major", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BaseCourseRecommender recommender = recommenders.get(degree);
            if (recommender != null) {
                resultsArea.setText("");
                recommender.recommendCourses(major, year, resultsArea);
                // Upload logged in user's recommendation field in aut_recommendDB
                try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommendDB;create=true")) {
                    String updateSQL = "UPDATE users SET recommendation = ? WHERE username = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                        pstmt.setString(1, resultsArea.getText()); // save the text from resultsArea
                        System.out.println(resultsArea.getText());
                        pstmt.setString(2, loggedInUser);
                        int updatedRows = pstmt.executeUpdate();
                        if (updatedRows > 0) {
                            JOptionPane.showMessageDialog(this, "Recommendation saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to save recommendation.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Degree combobox action listener
        degreeCombo.addActionListener(e -> {
            String selectedDegree = (String) degreeCombo.getSelectedItem();
            BaseCourseRecommender recommender = recommenders.get(selectedDegree);
            if (recommender != null) {
                majorCombo.removeAllItems();
                for (String major : recommender.getAvailableMajors()) {
                    majorCombo.addItem(major);
                }
            }
        });
        degreeCombo.setSelectedIndex(0);
        degreeCombo.getActionListeners()[0].actionPerformed(null);

        // Add components panel and button panel
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // View recommendation panel
    private JPanel createViewRecommendationPanel() {
        JPanel panel = createStyledPanel("View your Recommendation");
        
        // Create recommendation diplay
        viewRecommendationTextArea = new JTextArea("");
        viewRecommendationTextArea.setEditable(false);
        viewRecommendationTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        viewRecommendationTextArea.setBackground(CARD_BG);
        viewRecommendationTextArea.setForeground(BLACK_TEXT);

        // Add scrollpane
        JScrollPane scrollPane = new JScrollPane(viewRecommendationTextArea);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        JButton backButton = createPurpleButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(backButton);

        // Add to the whole panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPrerequisitePanel() {
        JPanel panel = createStyledPanel("Check Course Prerequisites");

        // Create grid bag layout panel to hold components
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel courseLabel = new JLabel("Enter Course Name or Code:");
        courseLabel.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(courseLabel, gbc);

        JTextField courseField = new JTextField(20);
        courseField.setBackground(Color.WHITE);
        courseField.setForeground(BLACK_TEXT);
        gbc.gridx = 1;
        formPanel.add(courseField, gbc);

        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        resultsArea.setBackground(Color.WHITE);
        resultsArea.setForeground(BLACK_TEXT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(new JScrollPane(resultsArea), gbc);

        // Create panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        
        JButton checkButton = createPurpleButton("Check Prerequisites");
        JButton backButton = createPurpleButton("Back to Main Menu");

        // Check button action listener
        checkButton.addActionListener(e -> {
            String course = courseField.getText().trim().toLowerCase();
            if (course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a course name or code", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String prerequisites = CheckPrerequisite.checkPrerequisites(course);
            resultsArea.setText("Prerequisites for " + course + ":\n" + prerequisites);
        });

        // Back button action listener
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        // Add buttons to button panel
        buttonPanel.add(checkButton);
        buttonPanel.add(backButton);

        // Add both panels to whole panel;
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Credit summary panel
    private JPanel createCreditSummaryPanel() {
        JPanel panel = createStyledPanel("Credit Summary");

        // Customize input text to display
        JTextArea textArea = new JTextArea(
                "Bachelor's Degree Requirements:\n"
                + "- Total Credits: 360\n"
                + "- Core Courses: 240 credits\n"
                + "- Electives: 90 credits\n"
                + "- General Education: 30 credits\n"
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(CARD_BG);
        textArea.setForeground(BLACK_TEXT);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        
        // Add back button and action listener
        JButton backButton = createPurpleButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        
        // Add button to panel
        buttonPanel.add(backButton);

        // Add credit summary panel and button panel
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Study tips panel
    private JPanel createStudyTipsPanel() {
        JPanel panel = createStyledPanel("General Study Tips");

        // Customize input text to display
        JTextArea textArea = new JTextArea(
                "1. Attend all lectures and tutorials\n"
                + "2. Create a study schedule for each semester\n"
                + "3. Utilize AUT's learning support services\n"
                + "4. Form study groups with classmates\n"
                + "5. Start assignments early and review marking criteria\n"
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(CARD_BG);
        textArea.setForeground(BLACK_TEXT);

        // Create panel for back button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        
        // Create back button and action listenr
        JButton backButton = createPurpleButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        
        // Add back button to panel
        buttonPanel.add(backButton);

        // Add display text area and button panel to whole panel
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VirtualAcademicAdvisorGUI advisor = new VirtualAcademicAdvisorGUI();
            advisor.initializeDatabase();
            advisor.setVisible(true);
        });
    }
}
