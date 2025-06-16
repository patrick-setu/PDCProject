/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autacademicadvisor;

import java.sql.*;
/**
 *
 * @author Grady
 */
public class ServiceAuthentication {
    public static boolean authenticate(String username, String password)
    {
        String sql = "SELECT password FROM users WHERE username = ?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next())
            {
                String dbPassword = rs.getString("password");
                return password.equals(dbPassword);
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static String getUserRole(String username)
    {
        String sql = "SELECT role FROM users WHERE username = ?";
        
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return rs.getString("role");
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
