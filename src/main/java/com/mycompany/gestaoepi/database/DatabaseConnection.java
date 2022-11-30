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
public abstract class DatabaseConnection {
    
    protected Connection con;
    protected String server;
    protected String db;
    protected String user;
    protected String password;
    protected String driver;
    protected int port;
    
    public abstract Connection getConnection();
    
    public abstract String getURL();
}
