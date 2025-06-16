/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */


import javax.swing.*;

public abstract class BaseCourseRecommender {
    public abstract String[] getAvailableMajors();
    
    protected void displayCourses(String[] courses, JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (String course : courses) {
            sb.append("- ").append(course).append("\n");
        }
        textArea.append(sb.toString());
    }
    
    public abstract void recommendCourses(String major, String year, JTextArea textArea);
}
