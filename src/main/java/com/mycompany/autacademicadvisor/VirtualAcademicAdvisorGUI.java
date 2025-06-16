package com.mycompany.autacademicadvisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VirtualAcademicAdvisorGUI extends JFrame {
    private Map<String, BaseCourseRecommender> recommenders;
    private JPanel cards;
    private CardLayout cardLayout;
    
    public VirtualAcademicAdvisorGUI() {
        super("Virtual Academic Advisor");
        initializeRecommenders();
        setupUI();
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
    
    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        
        // Create panels for each function
        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel courseRecPanel = createCourseRecommendationPanel();
        JPanel prereqPanel = createPrerequisitePanel();
        JPanel creditPanel = createCreditSummaryPanel();
        JPanel tipsPanel = createStudyTipsPanel();
        
        cards.add(mainMenuPanel, "MainMenu");
        cards.add(courseRecPanel, "CourseRecommendations");
        cards.add(prereqPanel, "Prerequisites");
        cards.add(creditPanel, "Credits");
        cards.add(tipsPanel, "StudyTips");
        
        add(cards);
        
        cardLayout.show(cards, "MainMenu");
    }
    
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Virtual Academic Advisor", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        
        JButton courseRecButton = new JButton("Bachelor's Degree Recommendations");
        JButton prereqButton = new JButton("Check Prerequisites");
        JButton creditButton = new JButton("Credit Summary");
        JButton tipsButton = new JButton("General Study Tips");
        JButton exitButton = new JButton("Exit");
        
        courseRecButton.addActionListener(e -> cardLayout.show(cards, "CourseRecommendations"));
        prereqButton.addActionListener(e -> cardLayout.show(cards, "Prerequisites"));
        creditButton.addActionListener(e -> cardLayout.show(cards, "Credits"));
        tipsButton.addActionListener(e -> cardLayout.show(cards, "StudyTips"));
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(courseRecButton);
        buttonPanel.add(prereqButton);
        buttonPanel.add(creditButton);
        buttonPanel.add(tipsButton);
        buttonPanel.add(exitButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCourseRecommendationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Bachelor's Degree Recommendations", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Degree selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Select Degree:"), gbc);
        
        String[] degrees = {"Computer and Information Sciences", "Engineering (Honours)", "Business",
            "Health Science", "Design", "Communication Studies"};
        JComboBox<String> degreeCombo = new JComboBox<>(degrees);
        gbc.gridx = 1;
        formPanel.add(degreeCombo, gbc);
        
        // Major selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Select Major:"), gbc);
        
        JComboBox<String> majorCombo = new JComboBox<>();
        gbc.gridx = 1;
        formPanel.add(majorCombo, gbc);
        
        // Year selection
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Select Year:"), gbc);
        
        String[] years = {"1", "2", "3", "4"};
        JComboBox<String> yearCombo = new JComboBox<>(years);
        gbc.gridx = 1;
        formPanel.add(yearCombo, gbc);
        
        // Results area
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        formPanel.add(scrollPane, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton recommendButton = new JButton("Get Recommendations");
        JButton backButton = new JButton("Back to Main Menu");
        
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
        
        // Update majors when degree changes
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
        
        // Initialize majors for first degree
        if (degrees.length > 0) {
            degreeCombo.setSelectedIndex(0);
        }
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPrerequisitePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Check Course Prerequisites", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Course input
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Enter Course Name or Code:"), gbc);
        
        JTextField courseField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(courseField, gbc);
        
        // Results area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        formPanel.add(scrollPane, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton checkButton = new JButton("Check Prerequisites");
        JButton backButton = new JButton("Back to Main Menu");
        
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
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createCreditSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Credit Summary", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JTextArea textArea = new JTextArea(
            "Bachelor's Degree Requirements:\n" +
            "- Total Credits: 360\n" +
            "- Core Courses: 240 credits\n" +
            "- Electives: 90 credits\n" +
            "- General Education: 30 credits\n"
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(backButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStudyTipsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("General Study Tips", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JTextArea textArea = new JTextArea(
            "1. Attend all lectures and tutorials\n" +
            "2. Create a study schedule for each semester\n" +
            "3. Utilize AUT's learning support services\n" +
            "4. Form study groups with classmates\n" +
            "5. Start assignments early and review marking criteria\n"
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));
        buttonPanel.add(backButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VirtualAcademicAdvisorGUI advisor = new VirtualAcademicAdvisorGUI();
            advisor.setVisible(true);
        });
    }
}
