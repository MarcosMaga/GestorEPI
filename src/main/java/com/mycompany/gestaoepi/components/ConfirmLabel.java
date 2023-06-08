/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import com.mycompany.gestaoepi.components.interfaces.ConfirmLabelStrategy;
import com.mycompany.gestaoepi.components.interfaces.LabelColorStrategy;
import com.mycompany.gestaoepi.components.strategies.LabelGreenStrategy;
import com.mycompany.gestaoepi.components.strategies.LabelRedStrategy;
import com.mycompany.gestaoepi.components.strategies.TextConfirmStrategy;
import com.mycompany.gestaoepi.components.strategies.TextNotConfirmStrategy;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author Marcos
 */
public class ConfirmLabel extends Label{
    public ConfirmLabel(boolean value){
        this.setPrefHeight(34);
        this.setPrefWidth(38);
        this.setAlignment(Pos.CENTER);
        
        ConfirmLabelStrategy confirm;
        
        if(value)
            confirm = new TextConfirmStrategy();
        else
            confirm = new TextNotConfirmStrategy();
        
        
        this.setText(confirm.applyText());
        this.setStyle(confirm.applyColor());
    }
}
