/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Marcos
 */
public class TextData extends Text{
    public TextData(String text, double size){
        this.setText(text);
        this.setWrappingWidth(size);
        this.setTextAlignment(TextAlignment.CENTER);
    }
}
