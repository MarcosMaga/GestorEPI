/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.database;

import java.sql.Connection;
/**
 *
 * @author Marcos
 */
public class DatabaseReturn {
    public static Connection getConnection(){
        return new DatabaseConnectionMySQL().getConnection();
    }
    
}
