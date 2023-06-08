/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components.strategies;

import com.mycompany.gestaoepi.components.interfaces.LabelColorStrategy;
import javafx.scene.control.Label;

/**
 *
 * @author Marcos
 */
public class LabelGreenStrategy implements LabelColorStrategy{

    @Override
    public String applyColor() {
        return "-fx-background-color: #98FB98";
    }
    
}
