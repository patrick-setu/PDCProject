package com.mycompany.autacademicadvisor;

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

    private Map<String, BaseCourseRecommender> recommenders;
    private JPanel cards;
    private CardLayout cardLayout;
    private JLabel statusLabel;

    // Purple color scheme
    private static final Color PURPLE_BG = new Color(108, 59, 122);       // AUT purple multipurpose
    private static final Color HOVER_GRAY = new Color(220, 220, 220);     // Light gray for hover
    private static final Color BLACK_TEXT = Color.BLACK;                  // Black text
    private static final Color WHITE_TEXT = Color.WHITE;                  // White text
    private static final Color CARD_BG = new Color(245, 245, 245);        // Light gray for card panels

    public VirtualAcademicAdvisorGUI() {
        super("Virtual Academic Advisor");
        setUIFont(new Font("Segoe UI", Font.PLAIN, 14));
        initializeRecommenders();
        setupUI();
        establishDatabaseConnection();
    }

    private void establishDatabaseConnection() {
        try {
            String URL = "jdbc:derby:aut_recommend;create=true";
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

    private void setUIFont(Font f) {
        UIManager.put("Label.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("Button.font", f);
    }

    private void initializeRecommenders() {
        recommenders = new HashMap<>();
        recommenders.put("Computer and Information Sciences", new ComputerScienceRecommender());
        recommenders.put("Engineering (Honours)", new EngineeringRecommender());
        recommenders.put("Business", new BusinessCourseRecommender());
        recommenders.put("Health Science", new HealthScienceRecommender());
        recommenders.put("Design", new DesignRecommender());
        recommenders.put("Communication Studies", new CommunicationStudiesRecommender());
    }

    private ImageIcon loadLogo() {
        try {
            URL imgURL = getClass().getResource("/autlogo.png"); // Adjust if needed
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

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setMinimumSize(new Dimension(900, 650));
        setLocationRelativeTo(null);
        getContentPane().setBackground(PURPLE_BG);

        JLabel logoLabel = new JLabel(loadLogo());
//        logoLabel.setHorizontalAlignment(SwingConstants.CENTER); so that logo is in upper right corner
//        logoLabel.setVerticalAlignment(SwingConstants.CENTER);
//        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(108, 59, 122));
        topPanel.add(logoLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Virtual Academic Advisor", JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(WHITE_TEXT);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(WHITE_TEXT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(statusLabel, BorderLayout.SOUTH);  // Show at bottom of window

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(CARD_BG);
        cards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cards.add(createSignUpPanel(), "SignUp");
        cards.add(createLoginPanel(), "Login");
        cards.add(createMainMenuPanel(), "MainMenu");
        cards.add(createCourseRecommendationPanel(), "CourseRecommendations");
        cards.add(createPrerequisitePanel(), "Prerequisites");
        cards.add(createCreditSummaryPanel(), "Credits");
        cards.add(createStudyTipsPanel(), "StudyTips");

        add(cards);
        cardLayout.show(cards, "SignUp");
    }

    private java.util.List<JButton> menuButtons = new java.util.ArrayList<>();

    private void setButtonsEnabled(boolean enabled) {
        for (JButton btn : menuButtons) {
            btn.setEnabled(enabled);
            btn.setCursor(Cursor.getPredefinedCursor(enabled ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        }
    }

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

    private JPanel createMainMenuPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBackground(CARD_BG);

        JButton courseRecButton = createPurpleButton("Bachelor's Degree Recommendations");
        JButton prereqButton = createPurpleButton("Check Prerequisites");
        JButton creditButton = createPurpleButton("Credit Summary");
        JButton tipsButton = createPurpleButton("General Study Tips");
        JButton exitButton = createPurpleButton("Exit");

        // Collect menu buttons for transition disabling
        menuButtons.add(courseRecButton);
        menuButtons.add(prereqButton);
        menuButtons.add(creditButton);
        menuButtons.add(tipsButton);

        courseRecButton.addActionListener(e
                -> handleButtonClick(courseRecButton, "Loading Bachelor's Degree Recommendations...", "CourseRecommendations"));

        prereqButton.addActionListener(e
                -> handleButtonClick(prereqButton, "Loading Prerequisite Checker...", "Prerequisites"));

        creditButton.addActionListener(e
                -> handleButtonClick(creditButton, "Loading Credit Summary...", "Credits"));

        tipsButton.addActionListener(e
                -> handleButtonClick(tipsButton, "Loading Study Tips...", "StudyTips"));

        // Exit
        exitButton.addActionListener(e -> {
            statusLabel.setText("Exiting application...");
            Timer timer = new Timer(1000, evt -> System.exit(0));
            timer.setRepeats(false);
            timer.start();
        });

        JPanel panel = createStyledPanel("");

        buttonPanel.add(courseRecButton);
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
            if (title != "") {
                header.add(separator, BorderLayout.SOUTH);
            }
            panel.add(header, BorderLayout.NORTH);
        }

        return panel;
    }

    private JPanel createCourseRecommendationPanel() {
        JPanel panel = createStyledPanel("Bachelor's Degree Recommendations");

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(CARD_BG);

        JButton recommendButton = createPurpleButton("Get Recommendations");
        JButton backButton = createPurpleButton("Back to Main Menu");

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
            }
        });

        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(recommendButton);
        buttonPanel.add(backButton);

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
        degreeCombo.getActionListeners()[0].actionPerformed(null); // trigger initial major load

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createPrerequisitePanel() {
        JPanel panel = createStyledPanel("Check Course Prerequisites");

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);

        JButton checkButton = createPurpleButton("Check Prerequisites");
        JButton backButton = createPurpleButton("Back to Main Menu");

        checkButton.addActionListener(e -> {
            String course = courseField.getText().trim().toLowerCase();
            if (course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a course name or code", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String prerequisites = CheckPrerequisite.checkPrerequisites(course);
            resultsArea.setText("Prerequisites for " + course + ":\n" + prerequisites);
        });

        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        buttonPanel.add(checkButton);
        buttonPanel.add(backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCreditSummaryPanel() {
        JPanel panel = createStyledPanel("Credit Summary");

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        JButton backButton = createPurpleButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(backButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStudyTipsPanel() {
        JPanel panel = createStyledPanel("General Study Tips");

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(CARD_BG);
        JButton backButton = createPurpleButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(backButton);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLoginPanel() {
        // Main panel with border
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(CARD_BG);
        mainPanel.setBorder(BorderFactory.createLineBorder(PURPLE_BG, 2));

        // Center container to hold all components vertically centered
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(CARD_BG);

        // Add vertical glue to push content to center
        centerPanel.add(Box.createVerticalGlue());

        // Field dimensions
        int fieldHeight = 43;
        Dimension fieldSize = new Dimension(250, fieldHeight);

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setPreferredSize(fieldSize);
        usernameField.setMaximumSize(fieldSize);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(usernameField);

        // Space between fields (15px)
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);

        // Space before button (20px)
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Login Button
        JButton loginButton = createPurpleButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(loginButton);
        
        JButton signUpNavButton = createPurpleButton("Create Account");
        signUpNavButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(signUpNavButton);

        signUpNavButton.addActionListener(e -> cardLayout.show(cards, "SignUp"));


        // Add vertical glue to complete centering
        centerPanel.add(Box.createVerticalGlue());

        // Add some padding around the entire form
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(CARD_BG);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        paddedPanel.add(centerPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            try {
                // Updated connection details to match YOUR database
                if (authenticateWithDatabase(username, password)) {
                    cardLayout.show(cards, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Invalid username or password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Database error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(paddedPanel, BorderLayout.CENTER);
        return mainPanel;
    }
    
    private JPanel createSignUpPanel() {
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(CARD_BG);
    mainPanel.setBorder(BorderFactory.createLineBorder(PURPLE_BG, 2));

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBackground(CARD_BG);

    centerPanel.add(Box.createVerticalGlue());

    int fieldHeight = 43;
    Dimension fieldSize = new Dimension(250, fieldHeight);

    JTextField usernameField = new JTextField();
    usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
    usernameField.setPreferredSize(fieldSize);
    usernameField.setMaximumSize(fieldSize);
    usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(usernameField);

    centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    JPasswordField passwordField = new JPasswordField();
    passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
    passwordField.setPreferredSize(fieldSize);
    passwordField.setMaximumSize(fieldSize);
    passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(passwordField);

    centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    JPasswordField confirmPasswordField = new JPasswordField();
    confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
    confirmPasswordField.setPreferredSize(fieldSize);
    confirmPasswordField.setMaximumSize(fieldSize);
    confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(confirmPasswordField);

    centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    JButton signUpButton = createPurpleButton("Sign Up");
    signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(signUpButton);

    centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    JButton goToLoginButton = createPurpleButton("Already have an account? Login");
    goToLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));
    goToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(goToLoginButton);

    centerPanel.add(Box.createVerticalGlue());

    JPanel paddedPanel = new JPanel(new BorderLayout());
    paddedPanel.setBackground(CARD_BG);
    paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
    paddedPanel.add(centerPanel, BorderLayout.CENTER);

    signUpButton.addActionListener(e -> {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(mainPanel, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommend;create=true")) {
            String checkSql = "SELECT username FROM users WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(mainPanel, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(mainPanel, "Account created! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "Login");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    goToLoginButton.addActionListener(e -> cardLayout.show(cards, "Login"));

    mainPanel.add(paddedPanel, BorderLayout.CENTER);
    return mainPanel;
}

// Database authentication methods
    private boolean authenticateWithDatabase(String username, String password) throws SQLException {

        String sql = "SELECT password FROM users WHERE username = ?";

        // CHANGED to your database name and credentials
        try (Connection conn = DriverManager.getConnection(
                "jdbc:derby:aut_recommend;create=true"
        ); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return password.equals(dbPassword);
            }
            return false;
        }
    }

    private void initializeDatabase() {
        String createTableSQL = """
        CREATE TABLE users (
            username VARCHAR(50) PRIMARY KEY,
            password VARCHAR(256)
        )
    """;

        String checkTableSQL = "SELECT COUNT(*) FROM users";

        try (Connection conn = DriverManager.getConnection("jdbc:derby:aut_recommend;create=true")) {
            // Try creating the table
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createTableSQL);
                System.out.println("Created 'users' table.");
            } catch (SQLException e) {
                if (e.getSQLState().equals("X0Y32")) {
                    // Table already exists
                    System.out.println("'users' table already exists.");
                } else {
                    throw e;
                }
            }

            // Check if there are any users; insert default admin if not
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(checkTableSQL)) {

                if (rs.next() && rs.getInt(1) == 0) {
                    String insertUserSQL = """
                    INSERT INTO users (username, password)
                    VALUES ('admin', 'admin123')
                """;
                    stmt.executeUpdate(insertUserSQL);
                    System.out.println("Inserted default admin user.");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VirtualAcademicAdvisorGUI advisor = new VirtualAcademicAdvisorGUI();
            advisor.initializeDatabase();
            advisor.setVisible(true);
        });
    }
}

