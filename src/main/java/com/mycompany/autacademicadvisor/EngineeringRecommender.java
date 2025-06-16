package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class EngineeringRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Electrical and Electronic Engineering",
            "Mechanical Engineering"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Electrical and Electronic Engineering" -> recommendElectricalEngineeringCourses(year, textArea);
            case "Mechanical Engineering" -> recommendMechanicalEngineeringCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendElectricalEngineeringCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Sustainable Engineering Design (ENGE500)",
                    "Engineering Mathematics I (ENGE501)",
                    "Engineering Mechanics (ENGE503)",
                    "Electrical Engineering Fundamentals (ENGE504)",
                    "Programming Concepts and Technique (COMP500)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Engineering Materials I (ENME502)",
                    "Engineering Mathematics II (ENGE601)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Electrical Power Engineering (ENEL604)",
                    "Engineering Mathematics III (ENGE702)",
                    "Introduction to Microcontrollers (ENEL608)",
                    "Signals, Circuits and Systems (ENEL619)",
                    "Engineering Management (ENGE600)",
                    "Renewable Energy: Generation, Storage and Utilisation (ENGE503)",
                    "Electronics Project (ENEL602)",
                    "Analogue and Digital Systems (ENEL606)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Instrumentation and Control Systems (ENEL702)",
                    "Embedded System Design (ENEL712)",
                    "Power System Analysis and Design (ENEL713)",
                    "Engineering Numerical Techniques and Statistical Analysis (ENGE800)",
                    "Communication Engineering (ENEL700)",
                    "Engineering Design Innovation Project (ENEL709)",
                    "Machines and Drives (ENEL711)",
                    "Data Engineering and AI (ENGE707)"
                };
                displayCourses(courses, textArea);
            }
            case "4" -> {
                String[] courses = {
                    "Final Year Research Project (Part A) (ENGE891)",
                    "Innovation and Research Management (ENGE803)",
                    "2 Level 8 Optional Courses",
                    "Final Year Research Project (Part B) (ENGE892)",
                    "1 Elective Course",
                    "Another 2 Level 8 Optional Courses"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Electrical and Electronic Engineering\n");
        }
    }

    private void recommendMechanicalEngineeringCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Introduction to Sustainable Engineering Design (ENGE500)",
                    "Engineering Mathematics I (ENGE501)",
                    "Engineering Mechanics (ENGE503)",
                    "Electrical Engineering Fundamentals (ENGE504)",
                    "Programming Concepts and Technique (COMP500)",
                    "Mahitahi/Collaborative Practices (DIGD507)",
                    "Engineering Materials I (ENME502)",
                    "Engineering Mathematics II (ENGE601)"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Manufacturing Technology (ENME607)",
                    "Mechanisms and Dynamics of Machinery (ENME608)",
                    "Solid Mechanics I (ENME609)",
                    "Engineering Mathematics III (ENGE702)",
                    "Thermodynamics (ENME601)",
                    "Engineering Design Methodology (ENME602)",
                    "Fluid Mechanics (ENME603)",
                    "Engineering Management I (ENGE600)"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "System Dynamics and Vibrations (ENME701)",
                    "Solid Mechanics II (ENME704)",
                    "Advanced Manufacturing Technology (ENME708)",
                    "Engineering Numerical Techniques and Statistical Analysis (ENGE800)",
                    "Engineering Materials II (ENME700)",
                    "Mechanical Design (ENME702)",
                    "Mechatronics Design and Control (ENME703)",
                    "Heat Transfer (ENME707)"
                };
                displayCourses(courses, textArea);
            }
            case "4" -> {
                String[] courses = {
                    "Final Year Research Project (Part A) (ENGE891)",
                    "Innovation and Research Management (ENGE803)",
                    "2 Level 8 Optional Courses",
                    "Final Year Research Project (Part B) (ENGE892)",
                    "1 Elective Course",
                    "Another 2 Level 8 Optional Courses"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Mechanical Engineering\n");
        }
    }
}