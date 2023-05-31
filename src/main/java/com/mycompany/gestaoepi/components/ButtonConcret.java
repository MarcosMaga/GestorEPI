/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import com.mycompany.gestaoepi.components.interfaces.ButtonInterface;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author Marcos
 */
public class ButtonConcret extends Button implements ButtonInterface{
    
    public ButtonConcret(String text){
        this.setOnAction(event -> {
            action(event);
        });
        this.setTextButton(text);
    }

    @Override
    public void action(ActionEvent e) {
        System.out.println("Button Action");
    }

    @Override
    public void setTextButton(String value) {
        this.setText(value);
    }
}
