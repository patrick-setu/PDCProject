/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autacademicadvisor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Grady
 */
public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/aut_recommendation";
    private static final String USER = "student";
    private static final String PASSWORD = "pass123";
    
    static {
        try {
            // Explicitly load the driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "Failed to load Derby JDBC driver: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
