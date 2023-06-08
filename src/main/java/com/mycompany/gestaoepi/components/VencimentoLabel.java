/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import com.mycompany.gestaoepi.components.interfaces.LabelColorStrategy;
import com.mycompany.gestaoepi.components.strategies.LabelGreenStrategy;
import com.mycompany.gestaoepi.components.strategies.LabelRedStrategy;
import com.mycompany.gestaoepi.components.strategies.LabelYellowStrategy;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author Marcos
 */
public class VencimentoLabel extends Label{
    public VencimentoLabel(int value){
        this.setPrefHeight(36);
        this.setPrefWidth(70);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-border-color: black");
        this.setText(Integer.toString(value));
        
        LabelColorStrategy strategy;
        
        if(value <= 10)
            strategy = new LabelRedStrategy();            
        else if(value > 10 && value <= 30)
            strategy = new LabelYellowStrategy();
        else
            strategy = new LabelGreenStrategy();
        
        this.setStyle(strategy.applyColor());
    }
}
