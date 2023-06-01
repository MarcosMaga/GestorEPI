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
public class ExitButton extends ButtonConcret{
    public ExitButton() {
        super("Sair");
        this.setStyle("-fx-background-color: #D9534F; -fx-text-fill: white; -fx-font-size: 12px");
        this.setLayoutX(726.0);
        this.setLayoutY(11.0);
    }
    
    @Override
    public void action(ActionEvent e) {
        App.changeScreen("Login");
    }
}
