/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autacademicadvisor;

import java.util.HashMap;
import java.util.Map;

public class VirtualAcademicAdvisor extends BaseAdvisor {

    private Map<String, BaseCourseRecommender> recommenders;

    public VirtualAcademicAdvisor() {
        initializeRecommenders();
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

    @Override
    public void start() {
        System.out.println("\nWelcome to the Virtual Academic Advisor for AUT Undergraduates!\n");
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" ->
                    courseRecommendations();
                case "2" ->
                    checkPrerequisites();
                case "3" ->
                    creditSummary();
                case "4" ->
                    studyTips();
                case "5" -> {
                    System.out.println("\nThank you for using the Virtual Academic Advisor. Goodbye!");
                    System.exit(0);
                }
                default ->
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
    }

    @Override
    protected void displayMainMenu() {
        System.out.println("[Main Menu]");
        System.out.println("1. Bachelor's Degree Recommendations");
        System.out.println("2. Check Prerequisites");
        System.out.println("3. Credit Summary");
        System.out.println("4. General Study Tips");
        System.out.println("5. Exit");
        System.out.print("\nEnter your choice: ");
    }

    @Override
    protected void courseRecommendations() {
        System.out.println("\n=== AUT Bachelor's Degree Recommendations ===");

        // Display degrees
        System.out.println("\nAvailable Bachelor's Degrees:");
        String[] degrees = {"Computer and Information Sciences", "Engineering (Honours)", "Business",
            "Health Science", "Design", "Communication Studies"};

        for (int i = 0; i < degrees.length; i++) {
            System.out.println((i + 1) + ". " + degrees[i]);
        }

        try {
            System.out.print("\nSelect a degree (1-" + degrees.length + "): ");
            int degreeIdx = Integer.parseInt(scanner.nextLine()) - 1;

            if (degreeIdx < 0 || degreeIdx >= degrees.length) {
                System.out.println("Invalid selection. Please try again.");
                return;
            }

            String degree = degrees[degreeIdx];
            BaseCourseRecommender recommender = recommenders.get(degree);

            if (recommender != null) {
                // Show available majors
                String[] majors = recommender.getAvailableMajors();
                System.out.println("\nAvailable Majors in " + degree + ":");
                for (int i = 0; i < majors.length; i++) {
                    System.out.println((i + 1) + ". " + majors[i]);
                }

                System.out.print("\nSelect your major (1-" + majors.length + "): ");
                int majorIdx = Integer.parseInt(scanner.nextLine()) - 1;

                if (majorIdx < 0 || majorIdx >= majors.length) {
                    System.out.println("Invalid selection. Please try again.");
                    return;
                }

                String major = majors[majorIdx];

                System.out.print("Enter your year (1, 2, 3, 4): ");
                String year = scanner.nextLine();

                if (!year.matches("[1-4]")) {
                    System.out.println("Invalid year. Please enter 1, 2, 3, or 4.");
                    return;
                }

                System.out.printf("\nRecommended Courses for %s (%s) - Year %s:\n", major, degree, year);
                
                System.out.println();
            } else {
                System.out.println("No recommended available for this degree.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    @Override
    protected void checkPrerequisites() {
        System.out.println("\nEnter course name or code to check its prerequisites: ");
        String course = scanner.nextLine().trim().toLowerCase();

        String prerequisites = CheckPrerequisite.checkPrerequisites(course);
        System.out.println("\nPrerequisites for " + course + ":\n" + prerequisites + "\n");
    }

    @Override
    protected void creditSummary() {
        System.out.println("\nBachelor's Degree Requirements:");
        System.out.println("- Total Credits: 360");
        System.out.println("- Core Courses: 240 credits");
        System.out.println("- Electives: 90 credits");
        System.out.println("- General Education: 30 credits\n");
    }

    @Override
    protected void studyTips() {
        System.out.println("\n=== General Study Tips ===");
        System.out.println("1. Attend all lectures and tutorials");
        System.out.println("2. Create a study schedule for each semester");
        System.out.println("3. Utilize AUT's learning support services");
        System.out.println("4. Form study groups with classmates");
        System.out.println("5. Start assignments early and review marking criteria\n");
    }
}
