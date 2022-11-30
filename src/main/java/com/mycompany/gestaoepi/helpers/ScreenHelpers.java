/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.helpers;

import com.mycompany.gestaoepi.App;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Marcos
 */
public class ScreenHelpers {
    public void backDash(){
        App.changeMainScreen("DashScreen");
    }
    
    public ObservableList<Integer> setNumberValues(int min, int max){
        List<Integer> values = new ArrayList();
        
        for(int i=min; i <= max; i++)
            values.add(i);
        
        ObservableList<Integer> obsNumber = FXCollections.observableArrayList(values);
        return obsNumber;
    }
    
    public String createCode(char epi, char func){
        String text = "";
        Random rdn = new Random();
        
        epi += rdn.nextInt(10);
        func += rdn.nextInt(10);
        
        Character a = epi;
        Character b = func;
        
        text = a.toString() + b.toString() + Integer.toString(rdn.nextInt(8999) + 1000);
        return text.toUpperCase();
    }
    
    public String formatData(String date){
        String[] value = date.split("-");
        return value[2] + "/" + value[1] + "/" + value[0];
    }
}
