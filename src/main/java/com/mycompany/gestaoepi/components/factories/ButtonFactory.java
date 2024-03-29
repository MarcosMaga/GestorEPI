/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components.factories;

import com.mycompany.gestaoepi.components.BackButton;
import com.mycompany.gestaoepi.components.ButtonConcret;
import com.mycompany.gestaoepi.components.ExitButton;

/**
 *
 * @author Marcos
 */
public class ButtonFactory {
    public ButtonConcret createButton(String type){
        if(type.equalsIgnoreCase("backbutton"))
            return new BackButton();
        else if(type.equalsIgnoreCase("exitbutton"))
            return new ExitButton();
        else
            throw new IllegalArgumentException("Tipo de botão desconhecido!");
    }
}
