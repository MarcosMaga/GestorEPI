/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components.strategies;

import com.mycompany.gestaoepi.components.interfaces.ConfirmLabelStrategy;

/**
 *
 * @author Marcos
 */
public class TextNotConfirmStrategy implements ConfirmLabelStrategy{

    @Override
    public String applyText() {
        return "❌";
    }

    @Override
    public String applyColor() {
        return "-fx-text-fill: #FF6347";
    }
    
}
