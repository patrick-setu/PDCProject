package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class ComputerScienceRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Computer Science",
            "Data Science",
            "Digital Service",
            "Network and Cybersecurity",
            "Software Development"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Computer Science" -> recommendComputerScienceCourses(year, textArea);
            case "Data Science" -> recommendDataScienceCourses(year, textArea);
            case "Digital Service" -> recommendDigitalServiceCourses(year, textArea);
            case "Network and Cybersecurity" -> recommendNetworkingCourses(year, textArea);
            case "Software Development" -> recommendSoftwareDevCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendComputerScienceCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Programming Concepts and Technique (COMP500)",
                    "Computing Technology in Society (COMP501)",
                    "Mathematics for Computing (MATH503)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "IT Project Management (COMP507)",
                    "Database System Design (COMP508)",
                    "Object Oriented Programming (COMP503)",
                    "1 minor course"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Software Development Practice (COMP602)",
                    "Data Structures and Algorithm (COMP610)",
                    "Program Design and Construction (COMP603)",
                    "Operating System (COMP604) or Algorithm Design and Analysis (COMP611)",
                    "4 minor courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Theory of Computation (COMP711)",
                    "Programming Languages (COMP712)",
                    "Distributed and Mobile Systems (COMP713) or Applied Human Computer Interaction (COMP719)",
                    "2 minor courses",
                    "Final R&D Project"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Computer Science\n");
        }
    }

    private void recommendDataScienceCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Programming Concepts and Technique (COMP500)",
                    "Computing Technology in Society (COMP501)",
                    "Mathematics for Computing (MATH503)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "IT Project Management (COMP507)",
                    "Database System Design (COMP508)",
                    "Data Analysis (COMP517)",
                    "1 minor course"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Foundations of Data Science (COMP615)",
                    "Statistics for Data Science (COMP616)",
                    "Forecasting (STAT603)",
                    "Data Structures and Algorithm (COMP610) or Combinatorics and Graph Theory (COMP613)",
                    "4 minor courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Artificial Intelligence (COMP717)",
                    "Data Mining and Knowledge Engineering (COMP723)",
                    "Text and Vision (COMP700) or Nature Inspired Computing (COMP701)",
                    "2 minor courses",
                    "Final R&D Project"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Data Science\n");
        }
    }

    private void recommendDigitalServiceCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Programming Concepts and Technique (COMP500)",
                    "Computing Technology in Society (COMP501)",
                    "Mathematics for Computing (MATH503)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "IT Project Management (COMP507)",
                    "Database System Design (COMP508)",
                    "Digital Service in IT (INFS502)",
                    "1 minor course"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Needs Analysis, Acquisition and Training (INFS603)",
                    "Service Modelling (INFS604)",
                    "Microservices (INFS605)",
                    "Program Design and Construction (COMP603) or Information Security Technologies (COMP607)",
                    "4 minor courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Service and Innovation Design (INFS704)",
                    "Blockchain and Cryptocurrencies (COMP726)",
                    "Internet of Things and Applications (COMP728)",
                    "Information Security Management (COMP718)",
                    "1 minor course",
                    "Final R&D Project"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Digital Service\n");
        }
    }

    private void recommendNetworkingCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Programming Concepts and Technique (COMP500)",
                    "Computing Technology in Society (COMP501)",
                    "Mathematics for Computing (MATH503)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "IT Project Management (COMP507)",
                    "Database System Design (COMP508)",
                    "Networks and Internet (COMP504)",
                    "1 minor course"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Operating Systems (COMP604)",
                    "Information Security Technologies (COMP607)",
                    "Network and System Administration (COMP609)",
                    "Computer Network Applications (ENEL611)",
                    "4 minor courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Network Security (COMP715)",
                    "Enterprise Networks (COMP729) or Advanced Network Technologies (COMP714)",
                    "Highly Secure Systems (COMP716) or Information Security Management (COMP718)",
                    "2 minor courses",
                    "Final R&D Project"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Network and Cybersecurity\n");
        }
    }

    private void recommendSoftwareDevCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Programming Concepts and Technique (COMP500)",
                    "Computing Technology in Society (COMP501)",
                    "Mathematics for Computing (MATH503)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "IT Project Management (COMP507)",
                    "Database System Design (COMP508)",
                    "Object Oriented Programming (COMP503)",
                    "1 minor course"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Software Development Practice (COMP602)",
                    "Program Design and Construction (COMP603)",
                    "Data Structures and Algorithm (COMP610)",
                    "Operating Systems (COMP604) or Algorithm Design and Analysis (COMP611)",
                    "4 minor courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Applied Human Computer Interaction (COMP719)",
                    "Contemporary Issues in Software Engineering (ENSE701)",
                    "Distributed Mobile Systems (COMP713) or Web Development (COMP721)",
                    "2 minor courses",
                    "Final R&D Project"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Software Development\n");
        }
    }
}