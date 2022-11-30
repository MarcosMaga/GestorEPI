/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class DatabaseConnectionMySQL extends DatabaseConnection{
    public DatabaseConnectionMySQL(){
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.port = 3306;
        this.server = "localhost";
        this.db = "gestao_epi";
        this.user = "root";
        this.password = "";
    }
    
    @Override
    public Connection getConnection() { 
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(getURL(), user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }

    @Override
    public String getURL() {
        return "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.db
                + "?useTimezone=true&serverTimezone=UTC";
        
    }
}
