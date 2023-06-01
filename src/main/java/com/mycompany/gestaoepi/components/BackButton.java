/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import com.mycompany.gestaoepi.App;
import javafx.event.ActionEvent;

/**
 *
 * @author Marcos
 */
public class BackButton extends ButtonConcret{
    public BackButton() {
        super("➜ Voltar");
        this.setStyle("-fx-background-color: #5CB85C; -fx-text-fill: white; -fx-font-size: 14px");
        this.setPrefHeight(28.0);
        this.setPrefWidth(79.0);
        this.setLayoutX(470.0);
        this.setLayoutY(65.0);
    }
    
    @Override
    public void action(ActionEvent e) {
        App.changeMainScreen("DashScreen");
    }
}
