/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autacademicadvisor;

import java.util.Scanner;

public abstract class BaseAdvisor {
    protected Scanner scanner = new Scanner(System.in);
    
    // Initialize methods
    public abstract void start();
    protected abstract void displayMainMenu();
    protected abstract void courseRecommendations();
    protected abstract void checkPrerequisites();
    protected abstract void creditSummary();
    protected abstract void studyTips();
}